package ma.zyn.app.bean.core.accompagnement;






import ma.zyn.app.bean.core.utilisateurs.Collaborateur;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "reunion")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="reunion_seq",sequenceName="reunion_seq",allocationSize=1, initialValue = 1)
public class Reunion  extends BaseEntity     {




    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String style;

    private String description;

    private Collaborateur collaborateur ;
    private EtatReunion etatReunion ;


    public Reunion(){
        super();
    }

    public Reunion(Long id){
        this.id = id;
    }

    public Reunion(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Reunion(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="reunion_seq")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collaborateur")
    public Collaborateur getCollaborateur(){
        return this.collaborateur;
    }
    public void setCollaborateur(Collaborateur collaborateur){
        this.collaborateur = collaborateur;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etat_reunion")
    public EtatReunion getEtatReunion(){
        return this.etatReunion;
    }
    public void setEtatReunion(EtatReunion etatReunion){
        this.etatReunion = etatReunion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reunion reunion = (Reunion) o;
        return id != null && id.equals(reunion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

