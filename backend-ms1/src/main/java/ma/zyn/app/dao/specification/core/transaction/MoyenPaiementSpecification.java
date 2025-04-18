package  ma.zyn.app.dao.specification.core.transaction;

import ma.zyn.app.dao.criteria.core.transaction.MoyenPaiementCriteria;
import ma.zyn.app.bean.core.transaction.MoyenPaiement;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class MoyenPaiementSpecification extends  AbstractSpecification<MoyenPaiementCriteria, MoyenPaiement>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public MoyenPaiementSpecification(MoyenPaiementCriteria criteria) {
        super(criteria);
    }

    public MoyenPaiementSpecification(MoyenPaiementCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
