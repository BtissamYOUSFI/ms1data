
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class CollaborateurCriteria extends BaseCriteria {

    public description: string;
    public descriptionLike: string;
    public email: string;
    public emailLike: string;
    public enabled: null | boolean;
    public credentialsNonExpired: null | boolean;
    public accountNonExpired: null | boolean;
    public username: string;
    public usernameLike: string;
    public passwordChanged: null | boolean;
    public accountNonLocked: null | boolean;
    public password: string;
    public passwordLike: string;

}
