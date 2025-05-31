package ma.zyn.app.ws.dto.accompagnement;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.dto.AuditBaseDto;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemplateEmailCollaboratorDto  extends AuditBaseDto {

    private String collaborator  ;
    private String subject  ;
    private String body  ;




    public TemplateEmailCollaboratorDto(){
        super();
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


    public String getBody(){
        return this.body;
    }
    public void setBody(String body){
        this.body = body;
    }








}
