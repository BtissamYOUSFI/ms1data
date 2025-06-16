import {MoyenPaiementDto} from './MoyenPaiement.model';
import {StatusPaiementDto} from './StatusPaiement.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';
import {CollaborateurDto} from "../utilisateurs/Collaborateur.model";


export class PaiementDto extends BaseDto{

    public libelle: string;

    public code: string;

    public description: string;

    public montant: null | number;

   public datePaiement: Date;

    public moyenPaiement: MoyenPaiementDto ;
    public statusPaiement: StatusPaiementDto ;
    public collaborateur: CollaborateurDto;


    constructor() {
        super();

        this.libelle = '';
        this.code = '';
        this.description = '';
        this.montant = null;
        this.datePaiement = null;
        this.statusPaiement = new StatusPaiementDto() ;
        this.collaborateur= new CollaborateurDto()
        }

}
