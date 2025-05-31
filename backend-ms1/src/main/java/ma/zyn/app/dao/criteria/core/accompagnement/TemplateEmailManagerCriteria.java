package ma.zyn.app.dao.criteria.core.accompagnement;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

public class TemplateEmailManagerCriteria extends  BaseCriteria  {

    private String manager;
    private String managerLike;
    private String subject;
    private String subjectLike;
    private String body;
    private String bodyLike;



    public String getManager(){
        return this.manager;
    }
    public void setManager(String manager){
        this.manager = manager;
    }
    public String getManagerLike(){
        return this.managerLike;
    }
    public void setManagerLike(String managerLike){
        this.managerLike = managerLike;
    }

    public String getSubject(){
        return this.subject;
    }
    public void setSubject(String subject){
        this.subject = subject;
    }
    public String getSubjectLike(){
        return this.subjectLike;
    }
    public void setSubjectLike(String subjectLike){
        this.subjectLike = subjectLike;
    }

    public String getBody(){
        return this.body;
    }
    public void setBody(String body){
        this.body = body;
    }
    public String getBodyLike(){
        return this.bodyLike;
    }
    public void setBodyLike(String bodyLike){
        this.bodyLike = bodyLike;
    }


}
