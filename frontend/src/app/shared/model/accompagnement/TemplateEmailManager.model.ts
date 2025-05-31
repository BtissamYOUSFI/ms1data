
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class TemplateEmailManagerDto extends BaseDto{

    public manager: string;

    public subject: string;

    public body: string;



    constructor() {
        super();

        this.manager = '';
        this.subject = '';
        this.body = '';

        }

}
