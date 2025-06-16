package ma.zyn.app.bean.core.transaction;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import ma.zyn.app.bean.core.utilisateurs.Manager;
import ma.zyn.app.zynerator.bean.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "paiement_manager")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="paiement_manager_seq",sequenceName="paiement_manager_seq",allocationSize=1, initialValue = 1)
public class PaiementManager extends BaseEntity     {




    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String code;

    private String description;

    private BigDecimal montant = BigDecimal.ZERO;

    private LocalDateTime datePaiement ;

    private MoyenPaiement moyenPaiement ;
    private StatusPaiement statusPaiement ;
    private Manager manager ;


    public PaiementManager(){
        super();
    }

    public PaiementManager(Long id){
        this.id = id;
    }

    public PaiementManager(Long id, String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public PaiementManager(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="paiement_manager_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
      @Column(columnDefinition="TEXT")
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public BigDecimal getMontant(){
        return this.montant;
    }
    public void setMontant(BigDecimal montant){
        this.montant = montant;
    }
    public LocalDateTime getDatePaiement(){
        return this.datePaiement;
    }
    public void setDatePaiement(LocalDateTime datePaiement){
        this.datePaiement = datePaiement;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moyen_paiement")
    public MoyenPaiement getMoyenPaiement(){
        return this.moyenPaiement;
    }
    public void setMoyenPaiement(MoyenPaiement moyenPaiement){
        this.moyenPaiement = moyenPaiement;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_paiement")
    public StatusPaiement getStatusPaiement(){
        return this.statusPaiement;
    }
    public void setStatusPaiement(StatusPaiement statusPaiement){
        this.statusPaiement = statusPaiement;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager")
    public Manager getManager(){
        return this.manager;
    }
    public void setManager(Manager manager){
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaiementManager paiementManager = (PaiementManager) o;
        return id != null && id.equals(paiementManager.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

