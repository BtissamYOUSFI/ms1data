package ma.zyn.app.bean.core.utilisateurs;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import ma.zyn.app.zynerator.security.bean.User;

@Entity
@Table(name = "collaborateur")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="collaborateur_seq",sequenceName="collaborateur_seq",allocationSize=1, initialValue = 1)
public class Collaborateur  extends User    {


    public Collaborateur(String username) {
        super(username);
    }


    private String description;











    public Collaborateur(){
        super();
    }

    public Collaborateur(Long id){
        this.id = id;
    }

    public Collaborateur(Long id,String email){
        this.id = id;
        this.email = email ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="collaborateur_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
      @Column(columnDefinition="TEXT")
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collaborateur collaborateur = (Collaborateur) o;
        return id != null && id.equals(collaborateur.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

