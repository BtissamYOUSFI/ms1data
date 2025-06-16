import {EtatReunionDto} from './EtatReunion.model';
import {CollaborateurDto} from '../utilisateurs/Collaborateur.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';
import {ManagerDto} from "../utilisateurs/Manager.model";


export class ReunionDto extends BaseDto{

    public libelle: string;

    public code: string;

    public style: string;

    public description: string;

    public collaborateur: CollaborateurDto ;
    public etatReunion: EtatReunionDto ;
    public manager: ManagerDto ;

    constructor() {
        super();

        this.libelle = '';
        this.code = '';
        this.style = '';
        this.description = '';
        this.etatReunion = new EtatReunionDto() ;
        this.collaborateur = new CollaborateurDto() ;
        this.manager = new ManagerDto() ;
        }

}
