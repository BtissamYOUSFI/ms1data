
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class TemplateEmailManagerCriteria extends BaseCriteria {

    public id: number;
    public manager: string;
    public managerLike: string;
    public subject: string;
    public subjectLike: string;
    public body: string;
    public bodyLike: string;

}
