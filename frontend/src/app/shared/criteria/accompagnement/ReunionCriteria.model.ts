import {EtatReunionCriteria} from './EtatReunionCriteria.model';
import {CollaborateurCriteria} from '../utilisateurs/CollaborateurCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class ReunionCriteria extends BaseCriteria {

    public id: number;
    public libelle: string;
    public libelleLike: string;
    public code: string;
    public codeLike: string;
    public style: string;
    public styleLike: string;
    public description: string;
    public descriptionLike: string;
  public etatReunion: EtatReunionCriteria ;
  public etatReunions: Array<EtatReunionCriteria> ;

}
