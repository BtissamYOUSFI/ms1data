package ma.zyn.app.dao.specification.core.transaction;

import ma.zyn.app.bean.core.transaction.PaiementManager;
import ma.zyn.app.dao.criteria.core.transaction.PaiementManagerCriteria;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class PaiementManagerSpecification extends  AbstractSpecification<PaiementManagerCriteria, PaiementManager>  {

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
        addPredicateFk("manager","id", criteria.getManager()==null?null:criteria.getManager().getId());
        addPredicateFk("manager","id", criteria.getManagers());
        addPredicateFk("manager","email", criteria.getManager()==null?null:criteria.getManager().getEmail());
    }

    public PaiementManagerSpecification(PaiementManagerCriteria criteria) {
        super(criteria);
    }

    public PaiementManagerSpecification(PaiementManagerCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
