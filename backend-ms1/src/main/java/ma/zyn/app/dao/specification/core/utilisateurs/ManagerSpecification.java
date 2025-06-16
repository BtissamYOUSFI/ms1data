package ma.zyn.app.dao.specification.core.utilisateurs;

import ma.zyn.app.bean.core.utilisateurs.Manager;
import ma.zyn.app.dao.criteria.core.utilisateurs.ManagerCriteria;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ManagerSpecification extends  AbstractSpecification<ManagerCriteria, Manager>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
    }

    public ManagerSpecification(ManagerCriteria criteria) {
        super(criteria);
    }

    public ManagerSpecification(ManagerCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
