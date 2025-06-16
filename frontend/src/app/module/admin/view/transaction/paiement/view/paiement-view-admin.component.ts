import {Component, OnInit} from '@angular/core';


import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {AbstractService} from 'src/app/zynerator/service/AbstractService';
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';
import {ConfirmationService, MessageService,MenuItem} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';


import {PaiementAdminService} from 'src/app/shared/service/admin/transaction/PaiementAdmin.service';
import {PaiementDto} from 'src/app/shared/model/transaction/Paiement.model';
import {PaiementCriteria} from 'src/app/shared/criteria/transaction/PaiementCriteria.model';

import {MoyenPaiementDto} from 'src/app/shared/model/transaction/MoyenPaiement.model';
import {MoyenPaiementAdminService} from 'src/app/shared/service/admin/transaction/MoyenPaiementAdmin.service';
import {StatusPaiementDto} from 'src/app/shared/model/transaction/StatusPaiement.model';
import {StatusPaiementAdminService} from 'src/app/shared/service/admin/transaction/StatusPaiementAdmin.service';
import {
    CollaborateurAdminService
} from "../../../../../../shared/service/admin/utilisateurs/CollaborateurAdmin.service";
import {CollaborateurDto} from "../../../../../../shared/model/utilisateurs/Collaborateur.model";
@Component({
  selector: 'app-paiement-view-admin',
  templateUrl: './paiement-view-admin.component.html'
})
export class PaiementViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor( private collaborateurService: CollaborateurAdminService,private service: PaiementAdminService, private moyenPaiementService: MoyenPaiementAdminService, private statusPaiementService: StatusPaiementAdminService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
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

    public hideViewDialog() {
        this.viewDialog = false;
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

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): PaiementCriteria {
        return this.service.criteria;
    }

    set criteria(value: PaiementCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
