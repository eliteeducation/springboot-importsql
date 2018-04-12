package fr.leasecom.choruspro.importbatch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
/**
 * Created by eblonvia .
 */
 
@Repository
public interface ChorusStructureRepository extends JpaRepository<ChorusStructure, Long> {

}