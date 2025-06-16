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


import {PaiementManagerManagerService} from 'src/app/shared/service/manager/transaction/PaiementManagerManager.service';
import {PaiementManagerDto} from 'src/app/shared/model/transaction/PaiementManager.model';
import {PaiementManagerCriteria} from 'src/app/shared/criteria/transaction/PaiementManagerCriteria.model';

import {MoyenPaiementDto} from 'src/app/shared/model/transaction/MoyenPaiement.model';
import {MoyenPaiementManagerService} from 'src/app/shared/service/manager/transaction/MoyenPaiementManager.service';
import {StatusPaiementDto} from 'src/app/shared/model/transaction/StatusPaiement.model';
import {StatusPaiementManagerService} from 'src/app/shared/service/manager/transaction/StatusPaiementManager.service';
import {ManagerDto} from 'src/app/shared/model/utilisateurs/Manager.model';
import {ManagerManagerService} from 'src/app/shared/service/manager/utilisateurs/ManagerManager.service';
@Component({
  selector: 'app-paiement-manager-view-manager',
  standalone: false,
  templateUrl: './paiement-manager-view-manager.component.html'
})
export class PaiementManagerViewManagerComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: PaiementManagerManagerService, private moyenPaiementService: MoyenPaiementManagerService, private statusPaiementService: StatusPaiementManagerService, private managerService: ManagerManagerService){
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

    public hideViewDialog() {
        this.viewDialog = false;
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

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): PaiementManagerCriteria {
        return this.service.criteria;
    }

    set criteria(value: PaiementManagerCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
