import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {TemplateEmailCollaboratorAdminService} from 'src/app/shared/service/admin/accompagnement/TemplateEmailCollaboratorAdmin.service';
import {TemplateEmailCollaboratorDto} from 'src/app/shared/model/accompagnement/TemplateEmailCollaborator.model';
import {TemplateEmailCollaboratorCriteria} from 'src/app/shared/criteria/accompagnement/TemplateEmailCollaboratorCriteria.model';
@Component({
  selector: 'app-template-email-collaborator-create-admin',
  standalone: false,
  templateUrl: './template-email-collaborator-create-admin.component.html'
})
export class TemplateEmailCollaboratorCreateAdminComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;



   private _validTemplateEmailCollaboratorCollaborator = true;
   private _validTemplateEmailCollaboratorSubject = true;

	constructor(private service: TemplateEmailCollaboratorAdminService , @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
    }



    public save(): void {
        this.submitted = true;
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.saveWithShowOption(false);
        } else {
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs sur le formulaire'});
        }
    }

    public saveWithShowOption(showList: boolean) {
        this.service.save().subscribe(item => {
            if (item != null) {
                this.items.push({...item});
                this.createDialog = false;
                this.submitted = false;
                this.item = new TemplateEmailCollaboratorDto();
            } else {
                this.messageService.add({severity: 'error', summary: 'Erreurs', detail: 'Element existant'});
            }

        }, error => {
            console.log(error);
        });
    }


    public hideCreateDialog() {
        this.createDialog = false;
        this.setValidation(true);
    }





    public  setValidation(value: boolean){
        this.validTemplateEmailCollaboratorCollaborator = value;
        this.validTemplateEmailCollaboratorSubject = value;
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateTemplateEmailCollaboratorCollaborator();
        this.validateTemplateEmailCollaboratorSubject();
    }

    public validateTemplateEmailCollaboratorCollaborator(){
        if (this.stringUtilService.isEmpty(this.item.collaborator)) {
        this.errorMessages.push('Collaborator non valide');
        this.validTemplateEmailCollaboratorCollaborator = false;
        } else {
            this.validTemplateEmailCollaboratorCollaborator = true;
        }
    }
    public validateTemplateEmailCollaboratorSubject(){
        if (this.stringUtilService.isEmpty(this.item.subject)) {
        this.errorMessages.push('Subject non valide');
        this.validTemplateEmailCollaboratorSubject = false;
        } else {
            this.validTemplateEmailCollaboratorSubject = true;
        }
    }






    get validTemplateEmailCollaboratorCollaborator(): boolean {
        return this._validTemplateEmailCollaboratorCollaborator;
    }

    set validTemplateEmailCollaboratorCollaborator(value: boolean) {
         this._validTemplateEmailCollaboratorCollaborator = value;
    }
    get validTemplateEmailCollaboratorSubject(): boolean {
        return this._validTemplateEmailCollaboratorSubject;
    }

    set validTemplateEmailCollaboratorSubject(value: boolean) {
         this._validTemplateEmailCollaboratorSubject = value;
    }



    get items(): Array<TemplateEmailCollaboratorDto> {
        return this.service.items;
    }

    set items(value: Array<TemplateEmailCollaboratorDto>) {
        this.service.items = value;
    }

    get item(): TemplateEmailCollaboratorDto {
        return this.service.item;
    }

    set item(value: TemplateEmailCollaboratorDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): TemplateEmailCollaboratorCriteria {
        return this.service.criteria;
    }

    set criteria(value: TemplateEmailCollaboratorCriteria) {
        this.service.criteria = value;
    }

    get dateFormat() {
        return environment.dateFormatCreate;
    }

    get dateFormatColumn() {
        return environment.dateFormatCreate;
    }

    get submitted(): boolean {
        return this._submitted;
    }

    set submitted(value: boolean) {
        this._submitted = value;
    }

    get errorMessages(): string[] {
        if (this._errorMessages == null) {
            this._errorMessages = new Array<string>();
        }
        return this._errorMessages;
    }

    set errorMessages(value: string[]) {
        this._errorMessages = value;
    }

    get validate(): boolean {
        return this.service.validate;
    }

    set validate(value: boolean) {
        this.service.validate = value;
    }


    get activeTab(): number {
        return this._activeTab;
    }

    set activeTab(value: number) {
        this._activeTab = value;
    }

}
