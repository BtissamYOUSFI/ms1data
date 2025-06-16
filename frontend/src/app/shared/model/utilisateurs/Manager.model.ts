
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class ManagerDto extends BaseDto{

    public description: string;

   public accountNonLocked: null | boolean;

   public passwordChanged: null | boolean;

    public username: string;

   public accountNonExpired: null | boolean;

   public credentialsNonExpired: null | boolean;

   public enabled: null | boolean;

    public email: string;

    public password: string;



    constructor() {
        super();

        this.description = '';
        this.accountNonLocked = null;
        this.passwordChanged = null;
        this.username = '';
        this.accountNonExpired = null;
        this.credentialsNonExpired = null;
        this.enabled = null;
        this.email = '';
        this.password = '';

        }

}
