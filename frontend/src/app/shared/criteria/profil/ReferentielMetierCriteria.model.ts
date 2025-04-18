import {MetierCriteria} from './MetierCriteria.model';
import {NiveauLangueCriteria} from './NiveauLangueCriteria.model';
import {LangueCriteria} from './LangueCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class ReferentielMetierCriteria extends BaseCriteria {

    public id: number;
    public libelle: string;
    public libelleLike: string;
    public code: string;
    public codeLike: string;
    public description: string;
    public descriptionLike: string;
     public nombreHeuresExperienceMin: number;
     public nombreHeuresExperienceMinMin: number;
     public nombreHeuresExperienceMinMax: number;
    public scelleRouge: null | boolean;
  public langue: LangueCriteria ;
  public langues: Array<LangueCriteria> ;

}
