package ma.zyn.app.bean.core.profil;






import ma.zyn.app.bean.core.utilisateurs.Collaborateur;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "inscription")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="inscription_seq",sequenceName="inscription_seq",allocationSize=1, initialValue = 1)
public class Inscription  extends BaseEntity     {




    @Column(length = 500)
    private String style;

    private String description;

    @Column(length = 500)
    private String nom;

    @Column(length = 500)
    private String prenom;
    private String phone;

    @Column(length = 500)
    private String email;

    @Column(length = 500)
    private String password;

    private Integer nombreHeureExperience = 0;

    private Langue langue ;
    private NiveauLangue niveauLangue ;
    private Metier metier ;
    private EtatInscription etatInscription ;
    private Collaborateur collaborateur ;


    public Inscription(){
        super();
    }

    public Inscription(Long id){
        this.id = id;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="inscription_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }

    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
    }
      @Column(columnDefinition="TEXT")
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
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "langue")
    public Langue getLangue(){
        return this.langue;
    }
    public void setLangue(Langue langue){
        this.langue = langue;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "niveau_langue")
    public NiveauLangue getNiveauLangue(){
        return this.niveauLangue;
    }
    public void setNiveauLangue(NiveauLangue niveauLangue){
        this.niveauLangue = niveauLangue;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metier")
    public Metier getMetier(){
        return this.metier;
    }
    public void setMetier(Metier metier){
        this.metier = metier;
    }
    public Integer getNombreHeureExperience(){
        return this.nombreHeureExperience;
    }
    public void setNombreHeureExperience(Integer nombreHeureExperience){
        this.nombreHeureExperience = nombreHeureExperience;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etat_inscription")
    public EtatInscription getEtatInscription(){
        return this.etatInscription;
    }
    public void setEtatInscription(EtatInscription etatInscription){
        this.etatInscription = etatInscription;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collaborateur")
    public Collaborateur getCollaborateur(){
        return this.collaborateur;
    }
    public void setCollaborateur(Collaborateur collaborateur){
        this.collaborateur = collaborateur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inscription inscription = (Inscription) o;
        return id != null && id.equals(inscription.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

