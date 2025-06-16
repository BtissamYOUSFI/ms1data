import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {PaiementManagerAdminService} from 'src/app/shared/service/admin/transaction/PaiementManagerAdmin.service';
import {PaiementManagerDto} from 'src/app/shared/model/transaction/PaiementManager.model';
import {PaiementManagerCriteria} from 'src/app/shared/criteria/transaction/PaiementManagerCriteria.model';
import {MoyenPaiementDto} from 'src/app/shared/model/transaction/MoyenPaiement.model';
import {MoyenPaiementAdminService} from 'src/app/shared/service/admin/transaction/MoyenPaiementAdmin.service';
import {StatusPaiementDto} from 'src/app/shared/model/transaction/StatusPaiement.model';
import {StatusPaiementAdminService} from 'src/app/shared/service/admin/transaction/StatusPaiementAdmin.service';
import {ManagerDto} from 'src/app/shared/model/utilisateurs/Manager.model';
import {ManagerAdminService} from 'src/app/shared/service/admin/utilisateurs/ManagerAdmin.service';
@Component({
  selector: 'app-paiement-manager-create-admin',
  standalone: false,
  templateUrl: './paiement-manager-create-admin.component.html'
})
export class PaiementManagerCreateAdminComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;



   private _validPaiementManagerLibelle = true;
   private _validPaiementManagerCode = true;
    private _validMoyenPaiementLibelle = true;
    private _validMoyenPaiementCode = true;
    private _validStatusPaiementLibelle = true;
    private _validStatusPaiementCode = true;

	constructor(private service: PaiementManagerAdminService , private moyenPaiementService: MoyenPaiementAdminService, private statusPaiementService: StatusPaiementAdminService, private managerService: ManagerAdminService, @Inject(PLATFORM_ID) private platformId? ) {
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
        this.managerService.findAll().subscribe((data) => this.managers = data);
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
                this.item = new PaiementManagerDto();
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
        this.validPaiementManagerLibelle = value;
        this.validPaiementManagerCode = value;
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validatePaiementManagerLibelle();
        this.validatePaiementManagerCode();
    }

    public validatePaiementManagerLibelle(){
        if (this.stringUtilService.isEmpty(this.item.libelle)) {
        this.errorMessages.push('Libelle non valide');
        this.validPaiementManagerLibelle = false;
        } else {
            this.validPaiementManagerLibelle = true;
        }
    }
    public validatePaiementManagerCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
        this.errorMessages.push('Code non valide');
        this.validPaiementManagerCode = false;
        } else {
            this.validPaiementManagerCode = true;
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
    get manager(): ManagerDto {
        return this.managerService.item;
    }
    set manager(value: ManagerDto) {
        this.managerService.item = value;
    }
    get managers(): Array<ManagerDto> {
        return this.managerService.items;
    }
    set managers(value: Array<ManagerDto>) {
        this.managerService.items = value;
    }
    get createManagerDialog(): boolean {
        return this.managerService.createDialog;
    }
    set createManagerDialog(value: boolean) {
        this.managerService.createDialog= value;
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



    get validPaiementManagerLibelle(): boolean {
        return this._validPaiementManagerLibelle;
    }

    set validPaiementManagerLibelle(value: boolean) {
         this._validPaiementManagerLibelle = value;
    }
    get validPaiementManagerCode(): boolean {
        return this._validPaiementManagerCode;
    }

    set validPaiementManagerCode(value: boolean) {
         this._validPaiementManagerCode = value;
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


    get items(): Array<PaiementManagerDto> {
        return this.service.items;
    }

    set items(value: Array<PaiementManagerDto>) {
        this.service.items = value;
    }

    get item(): PaiementManagerDto {
        return this.service.item;
    }

    set item(value: PaiementManagerDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): PaiementManagerCriteria {
        return this.service.criteria;
    }

    set criteria(value: PaiementManagerCriteria) {
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
