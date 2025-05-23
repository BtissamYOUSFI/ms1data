import {MetierDto} from './Metier.model';
import {NiveauLangueDto} from './NiveauLangue.model';
import {LangueDto} from './Langue.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class ReferentielMetierDto extends BaseDto{

    public libelle: string;

    public code: string;

    public description: string;

    public nombreHeuresExperienceMin: null | number;

   public scelleRouge: null | boolean;

    public metier: MetierDto ;
    public langue: LangueDto ;
    public niveauLangue: NiveauLangueDto ;


    constructor() {
        super();

        this.libelle = '';
        this.code = '';
        this.description = '';
        this.nombreHeuresExperienceMin = null;
        this.scelleRouge = null;
        this.langue = new LangueDto() ;
        this.metier= new MetierDto();
        this.niveauLangue= new NiveauLangueDto()

        }

}
