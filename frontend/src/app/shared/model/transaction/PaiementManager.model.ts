import {MoyenPaiementDto} from './MoyenPaiement.model';
import {StatusPaiementDto} from './StatusPaiement.model';
import {ManagerDto} from '../utilisateurs/Manager.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class PaiementManagerDto extends BaseDto{

    public libelle: string;

    public code: string;

    public description: string;

    public montant: null | number;

   public datePaiement: Date;

    public moyenPaiement: MoyenPaiementDto ;
    public statusPaiement: StatusPaiementDto ;
    public manager: ManagerDto ;


    constructor() {
        super();

        this.libelle = '';
        this.code = '';
        this.description = '';
        this.montant = null;
        this.datePaiement = null;

        }

}
