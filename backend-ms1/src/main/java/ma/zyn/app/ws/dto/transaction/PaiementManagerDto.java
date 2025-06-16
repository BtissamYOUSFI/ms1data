package ma.zyn.app.ws.dto.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.ws.dto.utilisateurs.ManagerDto;
import ma.zyn.app.zynerator.dto.AuditBaseDto;

import java.math.BigDecimal;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaiementManagerDto extends AuditBaseDto {

    private String libelle  ;
    private String code  ;
    private String description  ;
    private BigDecimal montant  ;
    private String datePaiement ;

    private MoyenPaiementDto moyenPaiement ;
    private StatusPaiementDto statusPaiement ;
    private ManagerDto manager ;



    public PaiementManagerDto(){
        super();
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


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDatePaiement(){
        return this.datePaiement;
    }
    public void setDatePaiement(String datePaiement){
        this.datePaiement = datePaiement;
    }


    public MoyenPaiementDto getMoyenPaiement(){
        return this.moyenPaiement;
    }

    public void setMoyenPaiement(MoyenPaiementDto moyenPaiement){
        this.moyenPaiement = moyenPaiement;
    }
    public StatusPaiementDto getStatusPaiement(){
        return this.statusPaiement;
    }

    public void setStatusPaiement(StatusPaiementDto statusPaiement){
        this.statusPaiement = statusPaiement;
    }
    public ManagerDto getManager(){
        return this.manager;
    }

    public void setManager(ManagerDto manager){
        this.manager = manager;
    }






}
