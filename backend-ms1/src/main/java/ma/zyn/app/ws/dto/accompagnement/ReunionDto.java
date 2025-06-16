package  ma.zyn.app.ws.dto.accompagnement;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zyn.app.ws.dto.utilisateurs.CollaborateurDto;
import ma.zyn.app.ws.dto.utilisateurs.ManagerDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReunionDto  extends AuditBaseDto {

    private String libelle  ;
    private String code  ;
    private String style  ;
    private String description  ;

    private CollaborateurDto collaborateur ;
    private EtatReunionDto etatReunion ;
    private ManagerDto manager ;



    public ReunionDto(){
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


    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
    }


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    public CollaborateurDto getCollaborateur(){
        return this.collaborateur;
    }

    public void setCollaborateur(CollaborateurDto collaborateur){
        this.collaborateur = collaborateur;
    }
    public EtatReunionDto getEtatReunion(){
        return this.etatReunion;
    }

    public void setEtatReunion(EtatReunionDto etatReunion){
        this.etatReunion = etatReunion;
    }
    public ManagerDto getManager(){
        return this.manager;
    }

    public void setManager(ManagerDto manager){
        this.manager = manager;
    }






}

//package  ma.zyn.app.ws.dto.accompagnement;
//
//import ma.zyn.app.zynerator.dto.AuditBaseDto;
//import com.fasterxml.jackson.annotation.JsonInclude;
//
//
//
//import ma.zyn.app.ws.dto.utilisateurs.CollaborateurDto;
//
//
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class ReunionDto  extends AuditBaseDto {
//
//    private String libelle  ;
//    private String code  ;
//    private String style  ;
//    private String description  ;
//
//    private CollaborateurDto collaborateur ;
//    private EtatReunionDto etatReunion ;
//
//
//
//    public ReunionDto(){
//        super();
//    }
//
//
//
//
//    public String getLibelle(){
//        return this.libelle;
//    }
//    public void setLibelle(String libelle){
//        this.libelle = libelle;
//    }
//
//
//    public String getCode(){
//        return this.code;
//    }
//    public void setCode(String code){
//        this.code = code;
//    }
//
//
//    public String getStyle(){
//        return this.style;
//    }
//    public void setStyle(String style){
//        this.style = style;
//    }
//
//
//    public String getDescription(){
//        return this.description;
//    }
//    public void setDescription(String description){
//        this.description = description;
//    }
//
//
//    public CollaborateurDto getCollaborateur(){
//        return this.collaborateur;
//    }
//
//    public void setCollaborateur(CollaborateurDto collaborateur){
//        this.collaborateur = collaborateur;
//    }
//    public EtatReunionDto getEtatReunion(){
//        return this.etatReunion;
//    }
//
//    public void setEtatReunion(EtatReunionDto etatReunion){
//        this.etatReunion = etatReunion;
//    }
//
//
//
//
//
//
//}
