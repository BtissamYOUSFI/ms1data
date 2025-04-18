import {MoyenPaiementCriteria} from './MoyenPaiementCriteria.model';
import {StatusPaiementCriteria} from './StatusPaiementCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class PaiementCriteria extends BaseCriteria {

    public id: number;
    public libelle: string;
    public libelleLike: string;
    public code: string;
    public codeLike: string;
    public description: string;
    public descriptionLike: string;
     public montant: number;
     public montantMin: number;
     public montantMax: number;
    public datePaiement: Date;
    public datePaiementFrom: Date;
    public datePaiementTo: Date;
  public statusPaiement: StatusPaiementCriteria ;
  public statusPaiements: Array<StatusPaiementCriteria> ;

}
