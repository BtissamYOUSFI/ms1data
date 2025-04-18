import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {ReferentielMetierAdminService} from 'src/app/shared/service/admin/profil/ReferentielMetierAdmin.service';
import {ReferentielMetierDto} from 'src/app/shared/model/profil/ReferentielMetier.model';
import {ReferentielMetierCriteria} from 'src/app/shared/criteria/profil/ReferentielMetierCriteria.model';
import {MetierDto} from 'src/app/shared/model/profil/Metier.model';
import {MetierAdminService} from 'src/app/shared/service/admin/profil/MetierAdmin.service';
import {NiveauLangueDto} from 'src/app/shared/model/profil/NiveauLangue.model';
import {NiveauLangueAdminService} from 'src/app/shared/service/admin/profil/NiveauLangueAdmin.service';
import {LangueDto} from 'src/app/shared/model/profil/Langue.model';
import {LangueAdminService} from 'src/app/shared/service/admin/profil/LangueAdmin.service';
@Component({
  selector: 'app-referentiel-metier-create-admin',
  templateUrl: './referentiel-metier-create-admin.component.html'
})
export class ReferentielMetierCreateAdminComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;



   private _validReferentielMetierLibelle = true;
   private _validReferentielMetierCode = true;
    private _validMetierLibelle = true;
    private _validMetierCode = true;
    private _validLangueLibelle = true;
    private _validLangueCode = true;
    private _validNiveauLangueLibelle = true;
    private _validNiveauLangueCode = true;

	constructor(private service: ReferentielMetierAdminService , private metierService: MetierAdminService, private niveauLangueService: NiveauLangueAdminService, private langueService: LangueAdminService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.metierService.findAll().subscribe((data) => this.metiers = data);
        this.langueService.findAll().subscribe((data) => this.langues = data);
        this.niveauLangueService.findAll().subscribe((data) => this.niveauLangues = data);
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
                this.item = new ReferentielMetierDto();
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
        this.validReferentielMetierLibelle = value;
        this.validReferentielMetierCode = value;
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateReferentielMetierLibelle();
        this.validateReferentielMetierCode();
    }

    public validateReferentielMetierLibelle(){
        if (this.stringUtilService.isEmpty(this.item.libelle)) {
        this.errorMessages.push('Libelle non valide');
        this.validReferentielMetierLibelle = false;
        } else {
            this.validReferentielMetierLibelle = true;
        }
    }
    public validateReferentielMetierCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
        this.errorMessages.push('Code non valide');
        this.validReferentielMetierCode = false;
        } else {
            this.validReferentielMetierCode = true;
        }
    }


    public async openCreateLangue(langue: string) {
    const isPermistted = await this.roleService.isPermitted('Langue', 'add');
    if(isPermistted) {
         this.langue = new LangueDto();
         this.createLangueDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
    }
    public async openCreateNiveauLangue(niveauLangue: string) {
    const isPermistted = await this.roleService.isPermitted('NiveauLangue', 'add');
    if(isPermistted) {
         this.niveauLangue = new NiveauLangueDto();
         this.createNiveauLangueDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
    }
    public async openCreateMetier(metier: string) {
    const isPermistted = await this.roleService.isPermitted('Metier', 'add');
    if(isPermistted) {
         this.metier = new MetierDto();
         this.createMetierDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
    }

    get langue(): LangueDto {
        return this.langueService.item;
    }
    set langue(value: LangueDto) {
        this.langueService.item = value;
    }
    get langues(): Array<LangueDto> {
        return this.langueService.items;
    }
    set langues(value: Array<LangueDto>) {
        this.langueService.items = value;
    }
    get createLangueDialog(): boolean {
        return this.langueService.createDialog;
    }
    set createLangueDialog(value: boolean) {
        this.langueService.createDialog= value;
    }
    get niveauLangue(): NiveauLangueDto {
        return this.niveauLangueService.item;
    }
    set niveauLangue(value: NiveauLangueDto) {
        this.niveauLangueService.item = value;
    }
    get niveauLangues(): Array<NiveauLangueDto> {
        return this.niveauLangueService.items;
    }
    set niveauLangues(value: Array<NiveauLangueDto>) {
        this.niveauLangueService.items = value;
    }
    get createNiveauLangueDialog(): boolean {
        return this.niveauLangueService.createDialog;
    }
    set createNiveauLangueDialog(value: boolean) {
        this.niveauLangueService.createDialog= value;
    }
    get metier(): MetierDto {
        return this.metierService.item;
    }
    set metier(value: MetierDto) {
        this.metierService.item = value;
    }
    get metiers(): Array<MetierDto> {
        return this.metierService.items;
    }
    set metiers(value: Array<MetierDto>) {
        this.metierService.items = value;
    }
    get createMetierDialog(): boolean {
        return this.metierService.createDialog;
    }
    set createMetierDialog(value: boolean) {
        this.metierService.createDialog= value;
    }



    get validReferentielMetierLibelle(): boolean {
        return this._validReferentielMetierLibelle;
    }

    set validReferentielMetierLibelle(value: boolean) {
         this._validReferentielMetierLibelle = value;
    }
    get validReferentielMetierCode(): boolean {
        return this._validReferentielMetierCode;
    }

    set validReferentielMetierCode(value: boolean) {
         this._validReferentielMetierCode = value;
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
    get validLangueLibelle(): boolean {
        return this._validLangueLibelle;
    }
    set validLangueLibelle(value: boolean) {
        this._validLangueLibelle = value;
    }
    get validLangueCode(): boolean {
        return this._validLangueCode;
    }
    set validLangueCode(value: boolean) {
        this._validLangueCode = value;
    }
    get validNiveauLangueLibelle(): boolean {
        return this._validNiveauLangueLibelle;
    }
    set validNiveauLangueLibelle(value: boolean) {
        this._validNiveauLangueLibelle = value;
    }
    get validNiveauLangueCode(): boolean {
        return this._validNiveauLangueCode;
    }
    set validNiveauLangueCode(value: boolean) {
        this._validNiveauLangueCode = value;
    }


    get items(): Array<ReferentielMetierDto> {
        return this.service.items;
    }

    set items(value: Array<ReferentielMetierDto>) {
        this.service.items = value;
    }

    get item(): ReferentielMetierDto {
        return this.service.item;
    }

    set item(value: ReferentielMetierDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): ReferentielMetierCriteria {
        return this.service.criteria;
    }

    set criteria(value: ReferentielMetierCriteria) {
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
