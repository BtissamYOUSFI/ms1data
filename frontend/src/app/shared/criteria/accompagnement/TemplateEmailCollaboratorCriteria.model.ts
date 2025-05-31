
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class TemplateEmailCollaboratorCriteria extends BaseCriteria {

    public id: number;
    public collaborator: string;
    public collaboratorLike: string;
    public subject: string;
    public subjectLike: string;
    public body: string;
    public bodyLike: string;

}
