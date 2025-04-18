import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';
import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';

import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {EtatReunionAdminService} from 'src/app/shared/service/admin/accompagnement/EtatReunionAdmin.service';
import {EtatReunionDto} from 'src/app/shared/model/accompagnement/EtatReunion.model';
import {EtatReunionCriteria} from 'src/app/shared/criteria/accompagnement/EtatReunionCriteria.model';



@Component({
  selector: 'app-etat-reunion-edit-admin',
  templateUrl: './etat-reunion-edit-admin.component.html'
})
export class EtatReunionEditAdminComponent implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();


    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;
    private _file: any;
    private _files: any;



    private _validEtatReunionLibelle = true;
    private _validEtatReunionCode = true;




    constructor(private service: EtatReunionAdminService , @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
    }

    public prepareEdit() {
    }



 public edit(): void {
        this.submitted = true;
        this.prepareEdit();
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.editWithShowOption(false);
        } else {
            this.messageService.add({
                severity: 'error',
                summary: 'Erreurs',
                detail: 'Merci de corrigé les erreurs sur le formulaire'
            });
        }
    }

    public editWithShowOption(showList: boolean) {
        this.service.edit().subscribe(religion=>{
            const myIndex = this.items.findIndex(e => e.id === this.item.id);
            this.items[myIndex] = religion;
            this.editDialog = false;
            this.submitted = false;
            this.item = new EtatReunionDto();
        } , error =>{
            console.log(error);
        });
    }

    public hideEditDialog() {
        this.editDialog = false;
        this.setValidation(true);
    }





    public setValidation(value: boolean){
        this.validEtatReunionLibelle = value;
        this.validEtatReunionCode = value;
    }


    public validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateEtatReunionLibelle();
        this.validateEtatReunionCode();
    }

    public validateEtatReunionLibelle(){
        if (this.stringUtilService.isEmpty(this.item.libelle)) {
            this.errorMessages.push('Libelle non valide');
            this.validEtatReunionLibelle = false;
        } else {
            this.validEtatReunionLibelle = true;
        }
    }

    public validateEtatReunionCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
            this.errorMessages.push('Code non valide');
            this.validEtatReunionCode = false;
        } else {
            this.validEtatReunionCode = true;
        }
    }







    get validEtatReunionLibelle(): boolean {
        return this._validEtatReunionLibelle;
    }
    set validEtatReunionLibelle(value: boolean) {
        this._validEtatReunionLibelle = value;
    }
    get validEtatReunionCode(): boolean {
        return this._validEtatReunionCode;
    }
    set validEtatReunionCode(value: boolean) {
        this._validEtatReunionCode = value;
    }


	get items(): Array<EtatReunionDto> {
        return this.service.items;
    }

    set items(value: Array<EtatReunionDto>) {
        this.service.items = value;
    }

    get item(): EtatReunionDto {
        return this.service.item;
    }

    set item(value: EtatReunionDto) {
        this.service.item = value;
    }

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): EtatReunionCriteria {
        return this.service.criteria;
    }

    set criteria(value: EtatReunionCriteria) {
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
