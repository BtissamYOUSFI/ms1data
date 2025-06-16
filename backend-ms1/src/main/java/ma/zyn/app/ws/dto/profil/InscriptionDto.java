package  ma.zyn.app.ws.dto.profil;

import ma.zyn.app.ws.dto.utilisateurs.ManagerDto;
import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zyn.app.ws.dto.utilisateurs.CollaborateurDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class InscriptionDto  extends AuditBaseDto {

    private String style  ;
    private String description  ;
    private String nom  ;
    private String prenom  ;
    private String phone;
    private String email  ;
    private String password  ;
    private Integer nombreHeureExperience  = 0 ;

    private LangueDto langue ;
    private NiveauLangueDto niveauLangue ;
    private MetierDto metier ;
    private EtatInscriptionDto etatInscription ;
    private CollaborateurDto collaborateur ;
    private ManagerDto manager;



    public InscriptionDto(){
        super();
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


    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }


    public String getPrenom(){
        return this.prenom;
    }
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }


    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }


    public Integer getNombreHeureExperience(){
        return this.nombreHeureExperience;
    }
    public void setNombreHeureExperience(Integer nombreHeureExperience){
        this.nombreHeureExperience = nombreHeureExperience;
    }


    public LangueDto getLangue(){
        return this.langue;
    }

    public void setLangue(LangueDto langue){
        this.langue = langue;
    }
    public NiveauLangueDto getNiveauLangue(){
        return this.niveauLangue;
    }

    public void setNiveauLangue(NiveauLangueDto niveauLangue){
        this.niveauLangue = niveauLangue;
    }
    public MetierDto getMetier(){
        return this.metier;
    }

    public void setMetier(MetierDto metier){
        this.metier = metier;
    }
    public EtatInscriptionDto getEtatInscription(){
        return this.etatInscription;
    }

    public void setEtatInscription(EtatInscriptionDto etatInscription){
        this.etatInscription = etatInscription;
    }
    public CollaborateurDto getCollaborateur(){
        return this.collaborateur;
    }

    public void setCollaborateur(CollaborateurDto collaborateur){
        this.collaborateur = collaborateur;
    }

    public ManagerDto getManager() {
        return manager;
    }

    public void setManager(ManagerDto manager) {
        this.manager = manager;
    }
}
