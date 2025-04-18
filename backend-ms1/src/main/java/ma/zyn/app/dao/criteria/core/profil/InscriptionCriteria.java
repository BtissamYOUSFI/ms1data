package  ma.zyn.app.dao.criteria.core.profil;


import ma.zyn.app.dao.criteria.core.utilisateurs.CollaborateurCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class InscriptionCriteria extends  BaseCriteria  {

    private String libelle;
    private String libelleLike;
    private String code;
    private String codeLike;
    private String style;
    private String styleLike;
    private String description;
    private String descriptionLike;
    private String nom;
    private String nomLike;
    private String prenom;
    private String prenomLike;
    private String email;
    private String emailLike;
    private String password;
    private String passwordLike;
    private String nombreHeureExperience;
    private String nombreHeureExperienceMin;
    private String nombreHeureExperienceMax;

    private LangueCriteria langue ;
    private List<LangueCriteria> langues ;
    private NiveauLangueCriteria niveauLangue ;
    private List<NiveauLangueCriteria> niveauLangues ;
    private MetierCriteria metier ;
    private List<MetierCriteria> metiers ;
    private EtatInscriptionCriteria etatInscription ;
    private List<EtatInscriptionCriteria> etatInscriptions ;
    private CollaborateurCriteria collaborateur ;
    private List<CollaborateurCriteria> collaborateurs ;


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

    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getNomLike(){
        return this.nomLike;
    }
    public void setNomLike(String nomLike){
        this.nomLike = nomLike;
    }

    public String getPrenom(){
        return this.prenom;
    }
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    public String getPrenomLike(){
        return this.prenomLike;
    }
    public void setPrenomLike(String prenomLike){
        this.prenomLike = prenomLike;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmailLike(){
        return this.emailLike;
    }
    public void setEmailLike(String emailLike){
        this.emailLike = emailLike;
    }

    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPasswordLike(){
        return this.passwordLike;
    }
    public void setPasswordLike(String passwordLike){
        this.passwordLike = passwordLike;
    }

    public String getNombreHeureExperience(){
        return this.nombreHeureExperience;
    }
    public void setNombreHeureExperience(String nombreHeureExperience){
        this.nombreHeureExperience = nombreHeureExperience;
    }   
    public String getNombreHeureExperienceMin(){
        return this.nombreHeureExperienceMin;
    }
    public void setNombreHeureExperienceMin(String nombreHeureExperienceMin){
        this.nombreHeureExperienceMin = nombreHeureExperienceMin;
    }
    public String getNombreHeureExperienceMax(){
        return this.nombreHeureExperienceMax;
    }
    public void setNombreHeureExperienceMax(String nombreHeureExperienceMax){
        this.nombreHeureExperienceMax = nombreHeureExperienceMax;
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
    public EtatInscriptionCriteria getEtatInscription(){
        return this.etatInscription;
    }

    public void setEtatInscription(EtatInscriptionCriteria etatInscription){
        this.etatInscription = etatInscription;
    }
    public List<EtatInscriptionCriteria> getEtatInscriptions(){
        return this.etatInscriptions;
    }

    public void setEtatInscriptions(List<EtatInscriptionCriteria> etatInscriptions){
        this.etatInscriptions = etatInscriptions;
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
}
