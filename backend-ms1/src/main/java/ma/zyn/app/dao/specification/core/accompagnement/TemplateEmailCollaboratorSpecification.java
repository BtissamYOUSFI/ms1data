package ma.zyn.app.dao.specification.core.accompagnement;

import ma.zyn.app.bean.core.accompagnement.TemplateEmailCollaborator;
import ma.zyn.app.dao.criteria.core.accompagnement.TemplateEmailCollaboratorCriteria;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class TemplateEmailCollaboratorSpecification extends  AbstractSpecification<TemplateEmailCollaboratorCriteria, TemplateEmailCollaborator>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("collaborator", criteria.getCollaborator(),criteria.getCollaboratorLike());
        addPredicate("subject", criteria.getSubject(),criteria.getSubjectLike());
    }

    public TemplateEmailCollaboratorSpecification(TemplateEmailCollaboratorCriteria criteria) {
        super(criteria);
    }

    public TemplateEmailCollaboratorSpecification(TemplateEmailCollaboratorCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
