import {MetierCriteria} from './MetierCriteria.model';
import {NiveauLangueCriteria} from './NiveauLangueCriteria.model';
import {EtatInscriptionCriteria} from './EtatInscriptionCriteria.model';
import {LangueCriteria} from './LangueCriteria.model';
import {CollaborateurCriteria} from '../utilisateurs/CollaborateurCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class InscriptionCriteria extends BaseCriteria {

    public id: number;
    public phone: string;
    public style: string;
    public styleLike: string;
    public description: string;
    public descriptionLike: string;
    public nom: string;
    public nomLike: string;
    public prenom: string;
    public prenomLike: string;
    public email: string;
    public emailLike: string;
    public password: string;
    public passwordLike: string;
     public nombreHeureExperience: number;
     public nombreHeureExperienceMin: number;
     public nombreHeureExperienceMax: number;
  public langue: LangueCriteria ;
  public langues: Array<LangueCriteria> ;
  public etatInscription: EtatInscriptionCriteria ;
  public etatInscriptions: Array<EtatInscriptionCriteria> ;

}
