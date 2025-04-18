package  ma.zyn.app.dao.criteria.core.profil;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class ReferentielMetierCriteria extends  BaseCriteria  {

    private String libelle;
    private String libelleLike;
    private String code;
    private String codeLike;
    private String description;
    private String descriptionLike;
    private String nombreHeuresExperienceMin;
    private String nombreHeuresExperienceMinMin;
    private String nombreHeuresExperienceMinMax;
    private Boolean scelleRouge;

    private MetierCriteria metier ;
    private List<MetierCriteria> metiers ;
    private LangueCriteria langue ;
    private List<LangueCriteria> langues ;
    private NiveauLangueCriteria niveauLangue ;
    private List<NiveauLangueCriteria> niveauLangues ;


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

    public String getNombreHeuresExperienceMin(){
        return this.nombreHeuresExperienceMin;
    }
    public void setNombreHeuresExperienceMin(String nombreHeuresExperienceMin){
        this.nombreHeuresExperienceMin = nombreHeuresExperienceMin;
    }   
    public String getNombreHeuresExperienceMinMin(){
        return this.nombreHeuresExperienceMinMin;
    }
    public void setNombreHeuresExperienceMinMin(String nombreHeuresExperienceMinMin){
        this.nombreHeuresExperienceMinMin = nombreHeuresExperienceMinMin;
    }
    public String getNombreHeuresExperienceMinMax(){
        return this.nombreHeuresExperienceMinMax;
    }
    public void setNombreHeuresExperienceMinMax(String nombreHeuresExperienceMinMax){
        this.nombreHeuresExperienceMinMax = nombreHeuresExperienceMinMax;
    }
      
    public Boolean getScelleRouge(){
        return this.scelleRouge;
    }
    public void setScelleRouge(Boolean scelleRouge){
        this.scelleRouge = scelleRouge;
    }

    public MetierCriteria getMetier(){
        return this.metier;
    }

    public void setMetier(MetierCriteria metier){
        this.metier = metier;
    }
    public List<MetierCriteria> getMetiers(){
        return this.metiers;
    }

    public void setMetiers(List<MetierCriteria> metiers){
        this.metiers = metiers;
    }
    public LangueCriteria getLangue(){
        return this.langue;
    }

    public void setLangue(LangueCriteria langue){
        this.langue = langue;
    }
    public List<LangueCriteria> getLangues(){
        return this.langues;
    }

    public void setLangues(List<LangueCriteria> langues){
        this.langues = langues;
    }
    public NiveauLangueCriteria getNiveauLangue(){
        return this.niveauLangue;
    }

    public void setNiveauLangue(NiveauLangueCriteria niveauLangue){
        this.niveauLangue = niveauLangue;
    }
    public List<NiveauLangueCriteria> getNiveauLangues(){
        return this.niveauLangues;
    }

    public void setNiveauLangues(List<NiveauLangueCriteria> niveauLangues){
        this.niveauLangues = niveauLangues;
    }
}
