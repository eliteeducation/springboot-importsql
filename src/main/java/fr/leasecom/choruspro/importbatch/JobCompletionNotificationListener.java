package fr.leasecom.choruspro.importbatch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

long avant;
long apres;
    private ChorusStructureRepository chorusStructureRepository;

    @Autowired
    public JobCompletionNotificationListener(ChorusStructureRepository chorusStructureRepository) {
        this.chorusStructureRepository = chorusStructureRepository;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(BatchStatus.STARTING==jobExecution.getStatus()) {
            avant = new Date().getTime();
        }
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
            log.info("Found <" + chorusStructureRepository.count() + "> in the database.");
            apres = new Date().getTime();

            Duration d = Duration.ofMillis(apres-avant ) ;
            long minutes = d.toMinutes() ;
            double seconds = d.toMillis() /1000d;
            long hour = d.toHours();
            log.info("Temps total du JOB:" + hour+ "h"+minutes+" min+ "+seconds+" sec");

        }
    }
}