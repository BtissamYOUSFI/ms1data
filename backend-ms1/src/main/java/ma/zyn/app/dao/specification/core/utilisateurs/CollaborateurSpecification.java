package  ma.zyn.app.dao.specification.core.utilisateurs;

import ma.zyn.app.dao.criteria.core.utilisateurs.CollaborateurCriteria;
import ma.zyn.app.bean.core.utilisateurs.Collaborateur;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class CollaborateurSpecification extends  AbstractSpecification<CollaborateurCriteria, Collaborateur>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
    }

    public CollaborateurSpecification(CollaborateurCriteria criteria) {
        super(criteria);
    }

    public CollaborateurSpecification(CollaborateurCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
