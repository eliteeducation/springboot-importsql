package fr.leasecom.choruspro.importbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private ChorusStructureRepository chorusStructureRepository;



    @Bean
    public FlatFileItemReader<ChorusStructure> reader() {
        FlatFileItemReader<ChorusStructure> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("import/ChorusStructures.csv"));
        reader.setLineMapper(createStudentLineMapper());
        reader.setLinesToSkip(1);
        reader.setEncoding("UTF-8");


        return reader;
    }

    private LineMapper<ChorusStructure> createStudentLineMapper() {
        DefaultLineMapper<ChorusStructure> studentLineMapper = new DefaultLineMapper<>();

        LineTokenizer studentLineTokenizer = createStudentLineTokenizer();
        studentLineMapper.setLineTokenizer(studentLineTokenizer);

        FieldSetMapper<ChorusStructure> studentInformationMapper = createChorusStructureMapper();
        studentLineMapper.setFieldSetMapper(studentInformationMapper);

        return studentLineMapper;
    }

    private LineTokenizer createStudentLineTokenizer() {
        DelimitedLineTokenizer studentLineTokenizer = new DelimitedLineTokenizer();
        studentLineTokenizer.setDelimiter(";");
        studentLineTokenizer.setNames(new String[]{"TypeIdentifiant",	"Identifiant",
                "RaisonSociale",
                "EmetteurEdi",
                "RecepteurEdi",
                "GestionStatutMiseEnPaiement",
                "GestionEngagement",
                "GestionService",
                "GestionServiceEngagement",
                "EstMOA",
                "EstMOAUniquement",
                "StructureActive",
                "Adresse",
                "ComplementAdresse1",
                "ComplementAdresse2",
                "CodePostal",
                "Ville",
                "NumTelephone",
                "Courriel",
                "CodePays",
                "LibellePays"
        });
        return studentLineTokenizer;
    }

    private FieldSetMapper<ChorusStructure> createChorusStructureMapper() {

        BeanWrapperFieldSetMapper<ChorusStructure> studentInformationMapper = new BeanWrapperFieldSetMapper<ChorusStructure>(){
            {
                setTargetType(ChorusStructure.class);
            }
        };

        studentInformationMapper.setTargetType(ChorusStructure.class);
        return studentInformationMapper;
    }

    @Bean
    public RepositoryItemWriter<ChorusStructure> writer() {

        RepositoryItemWriter<ChorusStructure> writer = new RepositoryItemWriter<>();
        writer.setRepository(
               chorusStructureRepository);

        writer.setMethodName("save"); //nom de la méthode du repository qui sera appelée

        return writer;
    }

    @Bean
    public Job importChorusProStructureJob( ) {
        SimpleDateFormat sfd  = new SimpleDateFormat("yyyy-MM-dd");

        return jobBuilderFactory.get("importChorusProStructureJob - " + sfd.format(new Date())).incrementer(new RunIdIncrementer())
               .flow(lectureEcriture()).end().build();
    }

    @Bean
    public Step lectureEcriture() {
        return stepBuilderFactory.get("lectureEcriture").<ChorusStructure, ChorusStructure>chunk(10).reader(reader())
               .writer(writer()).build();
    }

}