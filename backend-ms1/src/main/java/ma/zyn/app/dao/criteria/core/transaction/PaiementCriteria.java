package  ma.zyn.app.dao.criteria.core.transaction;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class PaiementCriteria extends  BaseCriteria  {

    private String libelle;
    private String libelleLike;
    private String code;
    private String codeLike;
    private String description;
    private String descriptionLike;
    private String montant;
    private String montantMin;
    private String montantMax;
    private LocalDateTime datePaiement;
    private LocalDateTime datePaiementFrom;
    private LocalDateTime datePaiementTo;

    private MoyenPaiementCriteria moyenPaiement ;
    private List<MoyenPaiementCriteria> moyenPaiements ;
    private StatusPaiementCriteria statusPaiement ;
    private List<StatusPaiementCriteria> statusPaiements ;


    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    public String getLibelleLike(){
        return this.libelleLike;
    }
    public void setLibelleLike(String libelleLike){
        this.libelleLike = libelleLike;
    }

    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCodeLike(){
        return this.codeLike;
    }
    public void setCodeLike(String codeLike){
        this.codeLike = codeLike;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }

    public String getMontant(){
        return this.montant;
    }
    public void setMontant(String montant){
        this.montant = montant;
    }   
    public String getMontantMin(){
        return this.montantMin;
    }
    public void setMontantMin(String montantMin){
        this.montantMin = montantMin;
    }
    public String getMontantMax(){
        return this.montantMax;
    }
    public void setMontantMax(String montantMax){
        this.montantMax = montantMax;
    }
      
    public LocalDateTime getDatePaiement(){
        return this.datePaiement;
    }
    public void setDatePaiement(LocalDateTime datePaiement){
        this.datePaiement = datePaiement;
    }
    public LocalDateTime getDatePaiementFrom(){
        return this.datePaiementFrom;
    }
    public void setDatePaiementFrom(LocalDateTime datePaiementFrom){
        this.datePaiementFrom = datePaiementFrom;
    }
    public LocalDateTime getDatePaiementTo(){
        return this.datePaiementTo;
    }
    public void setDatePaiementTo(LocalDateTime datePaiementTo){
        this.datePaiementTo = datePaiementTo;
    }

    public MoyenPaiementCriteria getMoyenPaiement(){
        return this.moyenPaiement;
    }

    public void setMoyenPaiement(MoyenPaiementCriteria moyenPaiement){
        this.moyenPaiement = moyenPaiement;
    }
    public List<MoyenPaiementCriteria> getMoyenPaiements(){
        return this.moyenPaiements;
    }

    public void setMoyenPaiements(List<MoyenPaiementCriteria> moyenPaiements){
        this.moyenPaiements = moyenPaiements;
    }
    public StatusPaiementCriteria getStatusPaiement(){
        return this.statusPaiement;
    }

    public void setStatusPaiement(StatusPaiementCriteria statusPaiement){
        this.statusPaiement = statusPaiement;
    }
    public List<StatusPaiementCriteria> getStatusPaiements(){
        return this.statusPaiements;
    }

    public void setStatusPaiements(List<StatusPaiementCriteria> statusPaiements){
        this.statusPaiements = statusPaiements;
    }
}
