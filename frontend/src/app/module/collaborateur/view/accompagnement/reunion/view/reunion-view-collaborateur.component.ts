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


import {ReunionCollaborateurService} from 'src/app/shared/service/collaborateur/accompagnement/ReunionCollaborateur.service';
import {ReunionDto} from 'src/app/shared/model/accompagnement/Reunion.model';
import {ReunionCriteria} from 'src/app/shared/criteria/accompagnement/ReunionCriteria.model';

import {EtatReunionDto} from 'src/app/shared/model/accompagnement/EtatReunion.model';
import {EtatReunionCollaborateurService} from 'src/app/shared/service/collaborateur/accompagnement/EtatReunionCollaborateur.service';
import {CollaborateurDto} from 'src/app/shared/model/utilisateurs/Collaborateur.model';
import {CollaborateurCollaborateurService} from 'src/app/shared/service/collaborateur/utilisateurs/CollaborateurCollaborateur.service';
@Component({
  selector: 'app-reunion-view-collaborateur',
  templateUrl: './reunion-view-collaborateur.component.html'
})
export class ReunionViewCollaborateurComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: ReunionCollaborateurService, private etatReunionService: EtatReunionCollaborateurService, private collaborateurService: CollaborateurCollaborateurService){
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
