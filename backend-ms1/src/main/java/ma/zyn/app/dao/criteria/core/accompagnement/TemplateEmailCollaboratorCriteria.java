package ma.zyn.app.dao.criteria.core.accompagnement;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

public class TemplateEmailCollaboratorCriteria extends  BaseCriteria  {

    private String collaborator;
    private String collaboratorLike;
    private String subject;
    private String subjectLike;
    private String body;
    private String bodyLike;



    public String getCollaborator(){
        return this.collaborator;
    }
    public void setCollaborator(String collaborator){
        this.collaborator = collaborator;
    }
    public String getCollaboratorLike(){
        return this.collaboratorLike;
    }
    public void setCollaboratorLike(String collaboratorLike){
        this.collaboratorLike = collaboratorLike;
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
