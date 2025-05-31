package ma.zyn.app.bean.core.accompagnement;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import ma.zyn.app.zynerator.bean.BaseEntity;

import java.util.Objects;

@Entity
@Table(name = "template_email_collaborator")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="template_email_collaborator_seq",sequenceName="template_email_collaborator_seq",allocationSize=1, initialValue = 1)
public class TemplateEmailCollaborator  extends BaseEntity     {




    @Column(length = 500)
    private String collaborator;

    @Column(length = 500)
    private String subject;

    private String body;



    public TemplateEmailCollaborator(){
        super();
    }

    public TemplateEmailCollaborator(Long id){
        this.id = id;
    }

    public TemplateEmailCollaborator(Long id,String collaborator){
        this.id = id;
        this.collaborator = collaborator ;
    }
    public TemplateEmailCollaborator(String collaborator){
        this.collaborator = collaborator ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="template_email_collaborator_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getCollaborator(){
        return this.collaborator;
    }
    public void setCollaborator(String collaborator){
        this.collaborator = collaborator;
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
        TemplateEmailCollaborator templateEmailCollaborator = (TemplateEmailCollaborator) o;
        return id != null && id.equals(templateEmailCollaborator.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

