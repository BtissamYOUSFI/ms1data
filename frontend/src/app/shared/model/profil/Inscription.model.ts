import {MetierDto} from './Metier.model';
import {NiveauLangueDto} from './NiveauLangue.model';
import {EtatInscriptionDto} from './EtatInscription.model';
import {LangueDto} from './Langue.model';
import {CollaborateurDto} from '../utilisateurs/Collaborateur.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';
import {ManagerDto} from "../utilisateurs/Manager.model";


export class InscriptionDto extends BaseDto{


    public phone: string;
    public style: string;

    public description: string;

    public nom: string;

    public prenom: string;

    public email: string;

    public password: string;

    public nombreHeureExperience: null | number;

    public langue: LangueDto ;
    public niveauLangue: NiveauLangueDto ;
    public metier: MetierDto ;
    public etatInscription: EtatInscriptionDto ;
    public collaborateur: CollaborateurDto ;
    public manager: ManagerDto


    constructor() {
        super();

        this.phone = '';
        this.style = '';
        this.description = '';
        this.nom = '';
        this.prenom = '';
        this.email = '';
        this.password = '';
        this.nombreHeureExperience = null;
        this.langue = new LangueDto() ;
        this.etatInscription = new EtatInscriptionDto() ;
        this.manager = new ManagerDto();
        }

}
