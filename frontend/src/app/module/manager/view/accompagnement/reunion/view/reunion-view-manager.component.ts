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


import {ReunionManagerService} from 'src/app/shared/service/manager/accompagnement/ReunionManager.service';
import {ReunionDto} from 'src/app/shared/model/accompagnement/Reunion.model';
import {ReunionCriteria} from 'src/app/shared/criteria/accompagnement/ReunionCriteria.model';

import {EtatReunionDto} from 'src/app/shared/model/accompagnement/EtatReunion.model';
import {EtatReunionManagerService} from 'src/app/shared/service/manager/accompagnement/EtatReunionManager.service';
import {CollaborateurDto} from 'src/app/shared/model/utilisateurs/Collaborateur.model';
import {CollaborateurManagerService} from 'src/app/shared/service/manager/utilisateurs/CollaborateurManager.service';
import {ManagerDto} from 'src/app/shared/model/utilisateurs/Manager.model';
import {ManagerManagerService} from 'src/app/shared/service/manager/utilisateurs/ManagerManager.service';
@Component({
  selector: 'app-reunion-view-manager',
  standalone: false,
  templateUrl: './reunion-view-manager.component.html'
})
export class ReunionViewManagerComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: ReunionManagerService, private etatReunionService: EtatReunionManagerService, private collaborateurService: CollaborateurManagerService, private managerService: ManagerManagerService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
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

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): ReunionCriteria {
        return this.service.criteria;
    }

    set criteria(value: ReunionCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
