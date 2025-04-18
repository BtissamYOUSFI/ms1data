package  ma.zyn.app.dao.specification.core.profil;

import ma.zyn.app.dao.criteria.core.profil.LangueCriteria;
import ma.zyn.app.bean.core.profil.Langue;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class LangueSpecification extends  AbstractSpecification<LangueCriteria, Langue>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public LangueSpecification(LangueCriteria criteria) {
        super(criteria);
    }

    public LangueSpecification(LangueCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
