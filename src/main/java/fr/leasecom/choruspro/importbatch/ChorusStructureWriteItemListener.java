package fr.leasecom.choruspro.importbatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.List;

/**
 * Created by eblonvia on 12/04/2018.
 */
@Slf4j
@Component
public class ChorusStructureWriteItemListener implements ItemWriteListener<ChorusStructure> {
    private long avant;
    private long apres;
    private long total;

    @Override
    public void beforeWrite(List<? extends ChorusStructure> list) {
        avant = new Date().getTime();
    }

    @Override
    public void afterWrite(List<? extends ChorusStructure> list) {
        total += list.size();
        log.info("Nombre de lignes ecrites : " + total);
        apres = new Date().getTime();
        Duration d = Duration.ofMillis(apres - avant);
        long minutes = d.toMinutes();
        double seconds = d.toMillis() / 1000d;
        long hour = d.toHours();
        log.info("Temps total :" + hour + "h" + minutes + " min+ " + seconds + " sec");
    }

    @Override
    public void onWriteError(Exception e, List<? extends ChorusStructure> list) {

    }


}

