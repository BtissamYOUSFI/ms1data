package  ma.zyn.app.dao.criteria.core.accompagnement;


import ma.zyn.app.dao.criteria.core.utilisateurs.CollaborateurCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class ReunionCriteria extends  BaseCriteria  {

    private String libelle;
    private String libelleLike;
    private String code;
    private String codeLike;
    private String style;
    private String styleLike;
    private String description;
    private String descriptionLike;

    private CollaborateurCriteria collaborateur ;
    private List<CollaborateurCriteria> collaborateurs ;
    private EtatReunionCriteria etatReunion ;
    private List<EtatReunionCriteria> etatReunions ;


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

    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
    }
    public String getStyleLike(){
        return this.styleLike;
    }
    public void setStyleLike(String styleLike){
        this.styleLike = styleLike;
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


    public CollaborateurCriteria getCollaborateur(){
        return this.collaborateur;
    }

    public void setCollaborateur(CollaborateurCriteria collaborateur){
        this.collaborateur = collaborateur;
    }
    public List<CollaborateurCriteria> getCollaborateurs(){
        return this.collaborateurs;
    }

    public void setCollaborateurs(List<CollaborateurCriteria> collaborateurs){
        this.collaborateurs = collaborateurs;
    }
    public EtatReunionCriteria getEtatReunion(){
        return this.etatReunion;
    }

    public void setEtatReunion(EtatReunionCriteria etatReunion){
        this.etatReunion = etatReunion;
    }
    public List<EtatReunionCriteria> getEtatReunions(){
        return this.etatReunions;
    }

    public void setEtatReunions(List<EtatReunionCriteria> etatReunions){
        this.etatReunions = etatReunions;
    }
}
