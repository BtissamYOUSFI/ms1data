import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {PaiementCollaborateurService} from 'src/app/shared/service/collaborateur/transaction/PaiementCollaborateur.service';
import {PaiementDto} from 'src/app/shared/model/transaction/Paiement.model';
import {PaiementCriteria} from 'src/app/shared/criteria/transaction/PaiementCriteria.model';
import {MoyenPaiementDto} from 'src/app/shared/model/transaction/MoyenPaiement.model';
import {MoyenPaiementCollaborateurService} from 'src/app/shared/service/collaborateur/transaction/MoyenPaiementCollaborateur.service';
import {StatusPaiementDto} from 'src/app/shared/model/transaction/StatusPaiement.model';
import {StatusPaiementCollaborateurService} from 'src/app/shared/service/collaborateur/transaction/StatusPaiementCollaborateur.service';
@Component({
  selector: 'app-paiement-create-collaborateur',
  templateUrl: './paiement-create-collaborateur.component.html'
})
export class PaiementCreateCollaborateurComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;



   private _validPaiementLibelle = true;
   private _validPaiementCode = true;
    private _validMoyenPaiementLibelle = true;
    private _validMoyenPaiementCode = true;
    private _validStatusPaiementLibelle = true;
    private _validStatusPaiementCode = true;

	constructor(private service: PaiementCollaborateurService , private moyenPaiementService: MoyenPaiementCollaborateurService, private statusPaiementService: StatusPaiementCollaborateurService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.moyenPaiementService.findAll().subscribe((data) => this.moyenPaiements = data);
        this.statusPaiementService.findAll().subscribe((data) => this.statusPaiements = data);
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
                this.item = new PaiementDto();
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
        this.validPaiementLibelle = value;
        this.validPaiementCode = value;
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validatePaiementLibelle();
        this.validatePaiementCode();
    }

    public validatePaiementLibelle(){
        if (this.stringUtilService.isEmpty(this.item.libelle)) {
        this.errorMessages.push('Libelle non valide');
        this.validPaiementLibelle = false;
        } else {
            this.validPaiementLibelle = true;
        }
    }
    public validatePaiementCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
        this.errorMessages.push('Code non valide');
        this.validPaiementCode = false;
        } else {
            this.validPaiementCode = true;
        }
    }


    public async openCreateStatusPaiement(statusPaiement: string) {
    const isPermistted = await this.roleService.isPermitted('StatusPaiement', 'add');
    if(isPermistted) {
         this.statusPaiement = new StatusPaiementDto();
         this.createStatusPaiementDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
    }
    public async openCreateMoyenPaiement(moyenPaiement: string) {
    const isPermistted = await this.roleService.isPermitted('MoyenPaiement', 'add');
    if(isPermistted) {
         this.moyenPaiement = new MoyenPaiementDto();
         this.createMoyenPaiementDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
    }

    get statusPaiement(): StatusPaiementDto {
        return this.statusPaiementService.item;
    }
    set statusPaiement(value: StatusPaiementDto) {
        this.statusPaiementService.item = value;
    }
    get statusPaiements(): Array<StatusPaiementDto> {
        return this.statusPaiementService.items;
    }
    set statusPaiements(value: Array<StatusPaiementDto>) {
        this.statusPaiementService.items = value;
    }
    get createStatusPaiementDialog(): boolean {
        return this.statusPaiementService.createDialog;
    }
    set createStatusPaiementDialog(value: boolean) {
        this.statusPaiementService.createDialog= value;
    }
    get moyenPaiement(): MoyenPaiementDto {
        return this.moyenPaiementService.item;
    }
    set moyenPaiement(value: MoyenPaiementDto) {
        this.moyenPaiementService.item = value;
    }
    get moyenPaiements(): Array<MoyenPaiementDto> {
        return this.moyenPaiementService.items;
    }
    set moyenPaiements(value: Array<MoyenPaiementDto>) {
        this.moyenPaiementService.items = value;
    }
    get createMoyenPaiementDialog(): boolean {
        return this.moyenPaiementService.createDialog;
    }
    set createMoyenPaiementDialog(value: boolean) {
        this.moyenPaiementService.createDialog= value;
    }



    get validPaiementLibelle(): boolean {
        return this._validPaiementLibelle;
    }

    set validPaiementLibelle(value: boolean) {
         this._validPaiementLibelle = value;
    }
    get validPaiementCode(): boolean {
        return this._validPaiementCode;
    }

    set validPaiementCode(value: boolean) {
         this._validPaiementCode = value;
    }

    get validMoyenPaiementLibelle(): boolean {
        return this._validMoyenPaiementLibelle;
    }
    set validMoyenPaiementLibelle(value: boolean) {
        this._validMoyenPaiementLibelle = value;
    }
    get validMoyenPaiementCode(): boolean {
        return this._validMoyenPaiementCode;
    }
    set validMoyenPaiementCode(value: boolean) {
        this._validMoyenPaiementCode = value;
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


    get items(): Array<PaiementDto> {
        return this.service.items;
    }

    set items(value: Array<PaiementDto>) {
        this.service.items = value;
    }

    get item(): PaiementDto {
        return this.service.item;
    }

    set item(value: PaiementDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): PaiementCriteria {
        return this.service.criteria;
    }

    set criteria(value: PaiementCriteria) {
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
