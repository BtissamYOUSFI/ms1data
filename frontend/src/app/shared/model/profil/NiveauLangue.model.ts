
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class NiveauLangueDto extends BaseDto{

    public libelle: string;

    public code: string;

    public style: string;

    public description: string;
    public valeur: number;



    constructor() {
        super();

        this.libelle = '';
        this.code = '';
        this.style = '';
        this.description = '';
        this.valeur=0;

        }

}
