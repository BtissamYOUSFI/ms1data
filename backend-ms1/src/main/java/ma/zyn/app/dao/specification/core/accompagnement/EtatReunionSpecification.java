package  ma.zyn.app.dao.specification.core.accompagnement;

import ma.zyn.app.dao.criteria.core.accompagnement.EtatReunionCriteria;
import ma.zyn.app.bean.core.accompagnement.EtatReunion;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class EtatReunionSpecification extends  AbstractSpecification<EtatReunionCriteria, EtatReunion>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public EtatReunionSpecification(EtatReunionCriteria criteria) {
        super(criteria);
    }

    public EtatReunionSpecification(EtatReunionCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
