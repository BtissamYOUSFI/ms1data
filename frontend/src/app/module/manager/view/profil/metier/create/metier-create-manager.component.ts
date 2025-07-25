import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {MetierManagerService} from 'src/app/shared/service/manager/profil/MetierManager.service';
import {MetierDto} from 'src/app/shared/model/profil/Metier.model';
import {MetierCriteria} from 'src/app/shared/criteria/profil/MetierCriteria.model';
@Component({
  selector: 'app-metier-create-manager',
  standalone: false,
  templateUrl: './metier-create-manager.component.html'
})
export class MetierCreateManagerComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;



   private _validMetierLibelle = true;
   private _validMetierCode = true;

	constructor(private service: MetierManagerService , @Inject(PLATFORM_ID) private platformId? ) {
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
                this.item = new MetierDto();
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
        this.validMetierLibelle = value;
        this.validMetierCode = value;
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateMetierLibelle();
        this.validateMetierCode();
    }

    public validateMetierLibelle(){
        if (this.stringUtilService.isEmpty(this.item.libelle)) {
        this.errorMessages.push('Libelle non valide');
        this.validMetierLibelle = false;
        } else {
            this.validMetierLibelle = true;
        }
    }
    public validateMetierCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
        this.errorMessages.push('Code non valide');
        this.validMetierCode = false;
        } else {
            this.validMetierCode = true;
        }
    }






    get validMetierLibelle(): boolean {
        return this._validMetierLibelle;
    }

    set validMetierLibelle(value: boolean) {
         this._validMetierLibelle = value;
    }
    get validMetierCode(): boolean {
        return this._validMetierCode;
    }

    set validMetierCode(value: boolean) {
         this._validMetierCode = value;
    }



    get items(): Array<MetierDto> {
        return this.service.items;
    }

    set items(value: Array<MetierDto>) {
        this.service.items = value;
    }

    get item(): MetierDto {
        return this.service.item;
    }

    set item(value: MetierDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): MetierCriteria {
        return this.service.criteria;
    }

    set criteria(value: MetierCriteria) {
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
