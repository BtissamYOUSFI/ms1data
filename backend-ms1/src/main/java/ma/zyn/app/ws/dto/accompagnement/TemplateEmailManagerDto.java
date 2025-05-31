package ma.zyn.app.ws.dto.accompagnement;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.dto.AuditBaseDto;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemplateEmailManagerDto  extends AuditBaseDto {

    private String manager  ;
    private String subject  ;
    private String body  ;




    public TemplateEmailManagerDto(){
        super();
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


    public String getBody(){
        return this.body;
    }
    public void setBody(String body){
        this.body = body;
    }








}
