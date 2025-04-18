package  ma.zyn.app.dao.specification.core.profil;

import ma.zyn.app.dao.criteria.core.profil.ReferentielMetierCriteria;
import ma.zyn.app.bean.core.profil.ReferentielMetier;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ReferentielMetierSpecification extends  AbstractSpecification<ReferentielMetierCriteria, ReferentielMetier>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicateInt("nombreHeuresExperienceMin", criteria.getNombreHeuresExperienceMin(), criteria.getNombreHeuresExperienceMinMin(), criteria.getNombreHeuresExperienceMinMax());
        addPredicateBool("scelleRouge", criteria.getScelleRouge());
        addPredicateFk("metier","id", criteria.getMetier()==null?null:criteria.getMetier().getId());
        addPredicateFk("metier","id", criteria.getMetiers());
        addPredicateFk("metier","code", criteria.getMetier()==null?null:criteria.getMetier().getCode());
        addPredicateFk("langue","id", criteria.getLangue()==null?null:criteria.getLangue().getId());
        addPredicateFk("langue","id", criteria.getLangues());
        addPredicateFk("langue","code", criteria.getLangue()==null?null:criteria.getLangue().getCode());
        addPredicateFk("niveauLangue","id", criteria.getNiveauLangue()==null?null:criteria.getNiveauLangue().getId());
        addPredicateFk("niveauLangue","id", criteria.getNiveauLangues());
        addPredicateFk("niveauLangue","code", criteria.getNiveauLangue()==null?null:criteria.getNiveauLangue().getCode());
    }

    public ReferentielMetierSpecification(ReferentielMetierCriteria criteria) {
        super(criteria);
    }

    public ReferentielMetierSpecification(ReferentielMetierCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
