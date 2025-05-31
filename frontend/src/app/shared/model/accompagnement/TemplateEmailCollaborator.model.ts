
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class TemplateEmailCollaboratorDto extends BaseDto{

    public collaborator: string;

    public subject: string;

    public body: string;



    constructor() {
        super();

        this.collaborator = '';
        this.subject = '';
        this.body = '';

        }

}
