package fr.leasecom.choruspro.importbatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

@EnableBatchProcessing
@SpringBootApplication
@Slf4j
public class ImportbatchApplication {

	public static void main(String[] args) {
        long lStartTime = new Date().getTime();
        ConfigurableApplicationContext ctx =SpringApplication.run(ImportbatchApplication.class, args);

        int exitCode = SpringApplication.exit(ctx, new ExitCodeGenerator() {
            @Override
            public int getExitCode() {
                // no errors
                return 0;
            }
        });
        System.exit(exitCode);
        long lEndTime = new Date().getTime();
        long output = lEndTime - lStartTime;

        log.info("Elapsed time in milliseconds: " + output);
	}


}
