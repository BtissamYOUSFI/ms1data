package  ma.zyn.app.dao.specification.core.profil;

import ma.zyn.app.dao.criteria.core.profil.EtatInscriptionCriteria;
import ma.zyn.app.bean.core.profil.EtatInscription;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class EtatInscriptionSpecification extends  AbstractSpecification<EtatInscriptionCriteria, EtatInscription>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public EtatInscriptionSpecification(EtatInscriptionCriteria criteria) {
        super(criteria);
    }

    public EtatInscriptionSpecification(EtatInscriptionCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
