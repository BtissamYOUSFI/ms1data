package  ma.zyn.app.dao.specification.core.transaction;

import ma.zyn.app.dao.criteria.core.transaction.StatusPaiementCriteria;
import ma.zyn.app.bean.core.transaction.StatusPaiement;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class StatusPaiementSpecification extends  AbstractSpecification<StatusPaiementCriteria, StatusPaiement>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public StatusPaiementSpecification(StatusPaiementCriteria criteria) {
        super(criteria);
    }

    public StatusPaiementSpecification(StatusPaiementCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
