import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {ReunionAdminService} from 'src/app/shared/service/admin/accompagnement/ReunionAdmin.service';
import {ReunionDto} from 'src/app/shared/model/accompagnement/Reunion.model';
import {ReunionCriteria} from 'src/app/shared/criteria/accompagnement/ReunionCriteria.model';
import {EtatReunionDto} from 'src/app/shared/model/accompagnement/EtatReunion.model';
import {EtatReunionAdminService} from 'src/app/shared/service/admin/accompagnement/EtatReunionAdmin.service';
import {CollaborateurDto} from 'src/app/shared/model/utilisateurs/Collaborateur.model';
import {CollaborateurAdminService} from 'src/app/shared/service/admin/utilisateurs/CollaborateurAdmin.service';
@Component({
  selector: 'app-reunion-create-admin',
  templateUrl: './reunion-create-admin.component.html'
})
export class ReunionCreateAdminComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;



   private _validReunionLibelle = true;
   private _validReunionCode = true;
    private _validEtatReunionLibelle = true;
    private _validEtatReunionCode = true;

	constructor(private service: ReunionAdminService , private etatReunionService: EtatReunionAdminService, private collaborateurService: CollaborateurAdminService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.collaborateurService.findAll().subscribe((data) => this.collaborateurs = data);
        // this.etatReunionService.findAll().subscribe((data) => this.etatReunions = data);
        this.etatReunionService.findAll().subscribe((data) => {
            this.etatReunions = data;
            this.item.etatReunion = this.etatReunions.find(e => e.libelle === 'APlanifier');
        });
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
                this.item = new ReunionDto();
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
        this.validReunionLibelle = value;
        this.validReunionCode = value;
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateReunionLibelle();
        this.validateReunionCode();
    }

    public validateReunionLibelle(){
        if (this.stringUtilService.isEmpty(this.item.libelle)) {
        this.errorMessages.push('Libelle non valide');
        this.validReunionLibelle = false;
        } else {
            this.validReunionLibelle = true;
        }
    }
    public validateReunionCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
        this.errorMessages.push('Code non valide');
        this.validReunionCode = false;
        } else {
            this.validReunionCode = true;
        }
    }


    public async openCreateEtatReunion(etatReunion: string) {
    const isPermistted = await this.roleService.isPermitted('EtatReunion', 'add');
    if(isPermistted) {
         this.etatReunion = new EtatReunionDto();
         this.createEtatReunionDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
    }

    get etatReunion(): EtatReunionDto {
        return this.etatReunionService.item;
    }
    set etatReunion(value: EtatReunionDto) {
        this.etatReunionService.item = value;
    }
    get etatReunions(): Array<EtatReunionDto> {
        return this.etatReunionService.items;
    }
    set etatReunions(value: Array<EtatReunionDto>) {
        this.etatReunionService.items = value;
    }
    get createEtatReunionDialog(): boolean {
        return this.etatReunionService.createDialog;
    }
    set createEtatReunionDialog(value: boolean) {
        this.etatReunionService.createDialog= value;
    }
    get collaborateur(): CollaborateurDto {
        return this.collaborateurService.item;
    }
    set collaborateur(value: CollaborateurDto) {
        this.collaborateurService.item = value;
    }
    get collaborateurs(): Array<CollaborateurDto> {
        return this.collaborateurService.items;
    }
    set collaborateurs(value: Array<CollaborateurDto>) {
        this.collaborateurService.items = value;
    }
    get createCollaborateurDialog(): boolean {
        return this.collaborateurService.createDialog;
    }
    set createCollaborateurDialog(value: boolean) {
        this.collaborateurService.createDialog= value;
    }



    get validReunionLibelle(): boolean {
        return this._validReunionLibelle;
    }

    set validReunionLibelle(value: boolean) {
         this._validReunionLibelle = value;
    }
    get validReunionCode(): boolean {
        return this._validReunionCode;
    }

    set validReunionCode(value: boolean) {
         this._validReunionCode = value;
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


    get items(): Array<ReunionDto> {
        return this.service.items;
    }

    set items(value: Array<ReunionDto>) {
        this.service.items = value;
    }

    get item(): ReunionDto {
        return this.service.item;
    }

    set item(value: ReunionDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): ReunionCriteria {
        return this.service.criteria;
    }

    set criteria(value: ReunionCriteria) {
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
