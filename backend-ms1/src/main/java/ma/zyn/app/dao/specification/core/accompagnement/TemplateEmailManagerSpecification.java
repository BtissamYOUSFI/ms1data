package ma.zyn.app.dao.specification.core.accompagnement;

import ma.zyn.app.bean.core.accompagnement.TemplateEmailManager;
import ma.zyn.app.dao.criteria.core.accompagnement.TemplateEmailManagerCriteria;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class TemplateEmailManagerSpecification extends  AbstractSpecification<TemplateEmailManagerCriteria, TemplateEmailManager>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("manager", criteria.getManager(),criteria.getManagerLike());
        addPredicate("subject", criteria.getSubject(),criteria.getSubjectLike());
    }

    public TemplateEmailManagerSpecification(TemplateEmailManagerCriteria criteria) {
        super(criteria);
    }

    public TemplateEmailManagerSpecification(TemplateEmailManagerCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
