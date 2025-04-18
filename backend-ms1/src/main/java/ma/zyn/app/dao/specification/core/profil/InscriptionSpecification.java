package  ma.zyn.app.dao.specification.core.profil;

import ma.zyn.app.dao.criteria.core.profil.InscriptionCriteria;
import ma.zyn.app.bean.core.profil.Inscription;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class InscriptionSpecification extends  AbstractSpecification<InscriptionCriteria, Inscription>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
        addPredicate("nom", criteria.getNom(),criteria.getNomLike());
        addPredicate("prenom", criteria.getPrenom(),criteria.getPrenomLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
        addPredicateInt("nombreHeureExperience", criteria.getNombreHeureExperience(), criteria.getNombreHeureExperienceMin(), criteria.getNombreHeureExperienceMax());
        addPredicateFk("langue","id", criteria.getLangue()==null?null:criteria.getLangue().getId());
        addPredicateFk("langue","id", criteria.getLangues());
        addPredicateFk("langue","code", criteria.getLangue()==null?null:criteria.getLangue().getCode());
        addPredicateFk("niveauLangue","id", criteria.getNiveauLangue()==null?null:criteria.getNiveauLangue().getId());
        addPredicateFk("niveauLangue","id", criteria.getNiveauLangues());
        addPredicateFk("niveauLangue","code", criteria.getNiveauLangue()==null?null:criteria.getNiveauLangue().getCode());
        addPredicateFk("metier","id", criteria.getMetier()==null?null:criteria.getMetier().getId());
        addPredicateFk("metier","id", criteria.getMetiers());
        addPredicateFk("metier","code", criteria.getMetier()==null?null:criteria.getMetier().getCode());
        addPredicateFk("etatInscription","id", criteria.getEtatInscription()==null?null:criteria.getEtatInscription().getId());
        addPredicateFk("etatInscription","id", criteria.getEtatInscriptions());
        addPredicateFk("etatInscription","code", criteria.getEtatInscription()==null?null:criteria.getEtatInscription().getCode());
        addPredicateFk("collaborateur","id", criteria.getCollaborateur()==null?null:criteria.getCollaborateur().getId());
        addPredicateFk("collaborateur","id", criteria.getCollaborateurs());
        addPredicateFk("collaborateur","email", criteria.getCollaborateur()==null?null:criteria.getCollaborateur().getEmail());
    }

    public InscriptionSpecification(InscriptionCriteria criteria) {
        super(criteria);
    }

    public InscriptionSpecification(InscriptionCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
