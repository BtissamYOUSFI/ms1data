import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {TemplateEmailManagerAdminService} from 'src/app/shared/service/admin/accompagnement/TemplateEmailManagerAdmin.service';
import {TemplateEmailManagerDto} from 'src/app/shared/model/accompagnement/TemplateEmailManager.model';
import {TemplateEmailManagerCriteria} from 'src/app/shared/criteria/accompagnement/TemplateEmailManagerCriteria.model';
@Component({
  selector: 'app-template-email-manager-create-admin',
  standalone: false,
  templateUrl: './template-email-manager-create-admin.component.html'
})
export class TemplateEmailManagerCreateAdminComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;



   private _validTemplateEmailManagerManager = true;
   private _validTemplateEmailManagerSubject = true;

	constructor(private service: TemplateEmailManagerAdminService , @Inject(PLATFORM_ID) private platformId? ) {
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
                this.item = new TemplateEmailManagerDto();
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
        this.validTemplateEmailManagerManager = value;
        this.validTemplateEmailManagerSubject = value;
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateTemplateEmailManagerManager();
        this.validateTemplateEmailManagerSubject();
    }

    public validateTemplateEmailManagerManager(){
        if (this.stringUtilService.isEmpty(this.item.manager)) {
        this.errorMessages.push('Manager non valide');
        this.validTemplateEmailManagerManager = false;
        } else {
            this.validTemplateEmailManagerManager = true;
        }
    }
    public validateTemplateEmailManagerSubject(){
        if (this.stringUtilService.isEmpty(this.item.subject)) {
        this.errorMessages.push('Subject non valide');
        this.validTemplateEmailManagerSubject = false;
        } else {
            this.validTemplateEmailManagerSubject = true;
        }
    }






    get validTemplateEmailManagerManager(): boolean {
        return this._validTemplateEmailManagerManager;
    }

    set validTemplateEmailManagerManager(value: boolean) {
         this._validTemplateEmailManagerManager = value;
    }
    get validTemplateEmailManagerSubject(): boolean {
        return this._validTemplateEmailManagerSubject;
    }

    set validTemplateEmailManagerSubject(value: boolean) {
         this._validTemplateEmailManagerSubject = value;
    }



    get items(): Array<TemplateEmailManagerDto> {
        return this.service.items;
    }

    set items(value: Array<TemplateEmailManagerDto>) {
        this.service.items = value;
    }

    get item(): TemplateEmailManagerDto {
        return this.service.item;
    }

    set item(value: TemplateEmailManagerDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): TemplateEmailManagerCriteria {
        return this.service.criteria;
    }

    set criteria(value: TemplateEmailManagerCriteria) {
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
