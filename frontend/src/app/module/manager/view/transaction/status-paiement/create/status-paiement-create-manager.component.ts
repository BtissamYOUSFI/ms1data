import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {StatusPaiementManagerService} from 'src/app/shared/service/manager/transaction/StatusPaiementManager.service';
import {StatusPaiementDto} from 'src/app/shared/model/transaction/StatusPaiement.model';
import {StatusPaiementCriteria} from 'src/app/shared/criteria/transaction/StatusPaiementCriteria.model';
@Component({
  selector: 'app-status-paiement-create-manager',
  standalone: false,
  templateUrl: './status-paiement-create-manager.component.html'
})
export class StatusPaiementCreateManagerComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;



   private _validStatusPaiementLibelle = true;
   private _validStatusPaiementCode = true;

	constructor(private service: StatusPaiementManagerService , @Inject(PLATFORM_ID) private platformId? ) {
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
                this.item = new StatusPaiementDto();
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
        this.validStatusPaiementLibelle = value;
        this.validStatusPaiementCode = value;
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateStatusPaiementLibelle();
        this.validateStatusPaiementCode();
    }

    public validateStatusPaiementLibelle(){
        if (this.stringUtilService.isEmpty(this.item.libelle)) {
        this.errorMessages.push('Libelle non valide');
        this.validStatusPaiementLibelle = false;
        } else {
            this.validStatusPaiementLibelle = true;
        }
    }
    public validateStatusPaiementCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
        this.errorMessages.push('Code non valide');
        this.validStatusPaiementCode = false;
        } else {
            this.validStatusPaiementCode = true;
        }
    }






    get validStatusPaiementLibelle(): boolean {
        return this._validStatusPaiementLibelle;
    }

    set validStatusPaiementLibelle(value: boolean) {
         this._validStatusPaiementLibelle = value;
    }
    get validStatusPaiementCode(): boolean {
        return this._validStatusPaiementCode;
    }

    set validStatusPaiementCode(value: boolean) {
         this._validStatusPaiementCode = value;
    }



    get items(): Array<StatusPaiementDto> {
        return this.service.items;
    }

    set items(value: Array<StatusPaiementDto>) {
        this.service.items = value;
    }

    get item(): StatusPaiementDto {
        return this.service.item;
    }

    set item(value: StatusPaiementDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): StatusPaiementCriteria {
        return this.service.criteria;
    }

    set criteria(value: StatusPaiementCriteria) {
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
