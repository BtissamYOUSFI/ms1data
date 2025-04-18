package  ma.zyn.app.dao.specification.core.transaction;

import ma.zyn.app.dao.criteria.core.transaction.PaiementCriteria;
import ma.zyn.app.bean.core.transaction.Paiement;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class PaiementSpecification extends  AbstractSpecification<PaiementCriteria, Paiement>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicateBigDecimal("montant", criteria.getMontant(), criteria.getMontantMin(), criteria.getMontantMax());
        addPredicate("datePaiement", criteria.getDatePaiement(), criteria.getDatePaiementFrom(), criteria.getDatePaiementTo());
        addPredicateFk("moyenPaiement","id", criteria.getMoyenPaiement()==null?null:criteria.getMoyenPaiement().getId());
        addPredicateFk("moyenPaiement","id", criteria.getMoyenPaiements());
        addPredicateFk("moyenPaiement","code", criteria.getMoyenPaiement()==null?null:criteria.getMoyenPaiement().getCode());
        addPredicateFk("statusPaiement","id", criteria.getStatusPaiement()==null?null:criteria.getStatusPaiement().getId());
        addPredicateFk("statusPaiement","id", criteria.getStatusPaiements());
        addPredicateFk("statusPaiement","code", criteria.getStatusPaiement()==null?null:criteria.getStatusPaiement().getCode());
    }

    public PaiementSpecification(PaiementCriteria criteria) {
        super(criteria);
    }

    public PaiementSpecification(PaiementCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
