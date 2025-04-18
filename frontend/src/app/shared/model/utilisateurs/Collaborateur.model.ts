
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class CollaborateurDto extends BaseDto{

    public description: string;

    public email: string;

   public enabled: null | boolean;

   public credentialsNonExpired: null | boolean;

   public accountNonExpired: null | boolean;

    public username: string;

   public passwordChanged: null | boolean;

   public accountNonLocked: null | boolean;

    public password: string;



    constructor() {
        super();

        this.description = '';
        this.email = '';
        this.enabled = null;
        this.credentialsNonExpired = null;
        this.accountNonExpired = null;
        this.username = '';
        this.passwordChanged = null;
        this.accountNonLocked = null;
        this.password = '';

        }

}
