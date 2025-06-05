package ma.zyn.app.bean.core.utilisateurs;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import ma.zyn.app.zynerator.security.bean.User;

import java.util.Objects;

@Entity
@Table(name = "manager")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="manager_seq",sequenceName="manager_seq",allocationSize=1, initialValue = 1)
public class Manager extends User    {

    public Manager(String username) {
        super(username);
    }

    private String description;











    public Manager(){
        super();
    }

    public Manager(Long id){
        this.id = id;
    }

    public Manager(Long id, String email){
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
        Manager collaborateur = (Manager) o;
        return id != null && id.equals(collaborateur.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

