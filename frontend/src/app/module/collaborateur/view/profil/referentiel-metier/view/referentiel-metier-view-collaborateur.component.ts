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


import {ReferentielMetierCollaborateurService} from 'src/app/shared/service/collaborateur/profil/ReferentielMetierCollaborateur.service';
import {ReferentielMetierDto} from 'src/app/shared/model/profil/ReferentielMetier.model';
import {ReferentielMetierCriteria} from 'src/app/shared/criteria/profil/ReferentielMetierCriteria.model';

import {MetierDto} from 'src/app/shared/model/profil/Metier.model';
import {MetierCollaborateurService} from 'src/app/shared/service/collaborateur/profil/MetierCollaborateur.service';
import {NiveauLangueDto} from 'src/app/shared/model/profil/NiveauLangue.model';
import {NiveauLangueCollaborateurService} from 'src/app/shared/service/collaborateur/profil/NiveauLangueCollaborateur.service';
import {LangueDto} from 'src/app/shared/model/profil/Langue.model';
import {LangueCollaborateurService} from 'src/app/shared/service/collaborateur/profil/LangueCollaborateur.service';
@Component({
  selector: 'app-referentiel-metier-view-collaborateur',
  templateUrl: './referentiel-metier-view-collaborateur.component.html'
})
export class ReferentielMetierViewCollaborateurComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: ReferentielMetierCollaborateurService, private metierService: MetierCollaborateurService, private niveauLangueService: NiveauLangueCollaborateurService, private langueService: LangueCollaborateurService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
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

    public hideViewDialog() {
        this.viewDialog = false;
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

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): ReferentielMetierCriteria {
        return this.service.criteria;
    }

    set criteria(value: ReferentielMetierCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
