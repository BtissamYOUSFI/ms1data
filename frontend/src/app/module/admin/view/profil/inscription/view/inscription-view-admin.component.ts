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


import {InscriptionAdminService} from 'src/app/shared/service/admin/profil/InscriptionAdmin.service';
import {InscriptionDto} from 'src/app/shared/model/profil/Inscription.model';
import {InscriptionCriteria} from 'src/app/shared/criteria/profil/InscriptionCriteria.model';

import {MetierDto} from 'src/app/shared/model/profil/Metier.model';
import {MetierAdminService} from 'src/app/shared/service/admin/profil/MetierAdmin.service';
import {NiveauLangueDto} from 'src/app/shared/model/profil/NiveauLangue.model';
import {NiveauLangueAdminService} from 'src/app/shared/service/admin/profil/NiveauLangueAdmin.service';
import {EtatInscriptionDto} from 'src/app/shared/model/profil/EtatInscription.model';
import {EtatInscriptionAdminService} from 'src/app/shared/service/admin/profil/EtatInscriptionAdmin.service';
import {LangueDto} from 'src/app/shared/model/profil/Langue.model';
import {LangueAdminService} from 'src/app/shared/service/admin/profil/LangueAdmin.service';
import {CollaborateurDto} from 'src/app/shared/model/utilisateurs/Collaborateur.model';
import {CollaborateurAdminService} from 'src/app/shared/service/admin/utilisateurs/CollaborateurAdmin.service';
@Component({
  selector: 'app-inscription-view-admin',
  templateUrl: './inscription-view-admin.component.html'
})
export class InscriptionViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: InscriptionAdminService, private metierService: MetierAdminService, private niveauLangueService: NiveauLangueAdminService, private etatInscriptionService: EtatInscriptionAdminService, private langueService: LangueAdminService, private collaborateurService: CollaborateurAdminService){
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
    get etatInscription(): EtatInscriptionDto {
        return this.etatInscriptionService.item;
    }
    set etatInscription(value: EtatInscriptionDto) {
        this.etatInscriptionService.item = value;
    }
    get etatInscriptions(): Array<EtatInscriptionDto> {
        return this.etatInscriptionService.items;
    }
    set etatInscriptions(value: Array<EtatInscriptionDto>) {
        this.etatInscriptionService.items = value;
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

    get items(): Array<InscriptionDto> {
        return this.service.items;
    }

    set items(value: Array<InscriptionDto>) {
        this.service.items = value;
    }

    get item(): InscriptionDto {
        return this.service.item;
    }

    set item(value: InscriptionDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): InscriptionCriteria {
        return this.service.criteria;
    }

    set criteria(value: InscriptionCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
