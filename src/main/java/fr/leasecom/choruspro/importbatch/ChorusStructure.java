package fr.leasecom.choruspro.importbatch;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by eblonvia
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "CHORUS_STRUCTURE")
public class ChorusStructure {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id;
    @Column(name = "type_identifiant")
    private int typeIdentifiant;
    private String identifiant;
    @Column(name = "Raison_Sociale")
    private String raisonSociale;
    @Column(name = "Emetteur_Edi")
    private boolean emetteurEdi;
    @Column(name = "Recepteur_Edi")
    private boolean recepteurEdi;
    @Column(name = "Gestion_Statut_Mise_EnPaiement")
    private boolean gestionStatutMiseEnPaiement;
    @Column(name = "Gestion_Engagement")
    private boolean gestionEngagement;
    @Column(name = "Gestion_Service")
    private boolean gestionService;
    @Column(name = "Gestion_Service_Engagement")
    private boolean gestionServiceEngagement;
    @Column(name = "Est_MOA")
    private boolean estMOA;
    @Column(name = "Est_MOA_Uniquement")
    private boolean estMOAUniquement;
    @Column(name = "Structure_Active")
    private boolean structureActive;
    private String adresse;
    @Column(name = "Complement_Adresse_1")
    private String complementAdresse1;
    @Column(name = "Complement_Adresse_2")
    private String complementAdresse2;
    @Column(name = "CODE_POSTAL")
    private String codePostal;
    private String ville;
    @Column(name = "NUM_TELEPHONE")
    private String numTelephone;
    private String courriel;
    @Column(name = "CODE_PAYS")
    private String codePays;
    @Column(name = "LIBELLE_PAYS")
    private String libellePays;


}