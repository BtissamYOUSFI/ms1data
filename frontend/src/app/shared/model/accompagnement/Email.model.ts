
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class EmailDto extends BaseDto{

    public to: string;

    public subject: string;


    public message: string;



    constructor() {
        super();

        this.to = '';
        this.subject = '';
        this.message = '';

    }

}
