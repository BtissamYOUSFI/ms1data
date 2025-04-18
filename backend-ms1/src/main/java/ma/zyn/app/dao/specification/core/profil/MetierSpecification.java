package  ma.zyn.app.dao.specification.core.profil;

import ma.zyn.app.dao.criteria.core.profil.MetierCriteria;
import ma.zyn.app.bean.core.profil.Metier;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class MetierSpecification extends  AbstractSpecification<MetierCriteria, Metier>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public MetierSpecification(MetierCriteria criteria) {
        super(criteria);
    }

    public MetierSpecification(MetierCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
