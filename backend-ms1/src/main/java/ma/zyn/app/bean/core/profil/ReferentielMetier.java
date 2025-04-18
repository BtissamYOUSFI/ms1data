package ma.zyn.app.bean.core.profil;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "referentiel_metier")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="referentiel_metier_seq",sequenceName="referentiel_metier_seq",allocationSize=1, initialValue = 1)
public class ReferentielMetier  extends BaseEntity     {




    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String code;

    private String description;

    private Integer nombreHeuresExperienceMin = 0;

    @Column(columnDefinition = "boolean default false")
    private Boolean scelleRouge = false;

    private Metier metier ;
    private Langue langue ;
    private NiveauLangue niveauLangue ;


    public ReferentielMetier(){
        super();
    }

    public ReferentielMetier(Long id){
        this.id = id;
    }

    public ReferentielMetier(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public ReferentielMetier(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="referentiel_metier_seq")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metier")
    public Metier getMetier(){
        return this.metier;
    }
    public void setMetier(Metier metier){
        this.metier = metier;
    }
    public Integer getNombreHeuresExperienceMin(){
        return this.nombreHeuresExperienceMin;
    }
    public void setNombreHeuresExperienceMin(Integer nombreHeuresExperienceMin){
        this.nombreHeuresExperienceMin = nombreHeuresExperienceMin;
    }
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
    public Boolean  getScelleRouge(){
        return this.scelleRouge;
    }
    public void setScelleRouge(Boolean scelleRouge){
        this.scelleRouge = scelleRouge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReferentielMetier referentielMetier = (ReferentielMetier) o;
        return id != null && id.equals(referentielMetier.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

