package  ma.zyn.app.dao.specification.core.profil;

import ma.zyn.app.dao.criteria.core.profil.NiveauLangueCriteria;
import ma.zyn.app.bean.core.profil.NiveauLangue;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class NiveauLangueSpecification extends  AbstractSpecification<NiveauLangueCriteria, NiveauLangue>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public NiveauLangueSpecification(NiveauLangueCriteria criteria) {
        super(criteria);
    }

    public NiveauLangueSpecification(NiveauLangueCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
