package ma.zyn.app.bean.core.accompagnement;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import ma.zyn.app.zynerator.bean.BaseEntity;

import java.util.Objects;

@Entity
@Table(name = "template_email_manager")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="template_email_manager_seq",sequenceName="template_email_manager_seq",allocationSize=1, initialValue = 1)
public class TemplateEmailManager  extends BaseEntity     {




    @Column(length = 500)
    private String manager;

    @Column(length = 500)
    private String subject;

    private String body;



    public TemplateEmailManager(){
        super();
    }

    public TemplateEmailManager(Long id){
        this.id = id;
    }

    public TemplateEmailManager(Long id,String manager){
        this.id = id;
        this.manager = manager ;
    }
    public TemplateEmailManager(String manager){
        this.manager = manager ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="template_email_manager_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getManager(){
        return this.manager;
    }
    public void setManager(String manager){
        this.manager = manager;
    }
    public String getSubject(){
        return this.subject;
    }
    public void setSubject(String subject){
        this.subject = subject;
    }
      @Column(columnDefinition="TEXT")
    public String getBody(){
        return this.body;
    }
    public void setBody(String body){
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemplateEmailManager templateEmailManager = (TemplateEmailManager) o;
        return id != null && id.equals(templateEmailManager.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

