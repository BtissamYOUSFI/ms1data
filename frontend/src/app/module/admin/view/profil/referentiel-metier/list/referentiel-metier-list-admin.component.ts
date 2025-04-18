import {Component, OnInit} from '@angular/core';
import {ReferentielMetierAdminService} from 'src/app/shared/service/admin/profil/ReferentielMetierAdmin.service';
import {ReferentielMetierDto} from 'src/app/shared/model/profil/ReferentielMetier.model';
import {ReferentielMetierCriteria} from 'src/app/shared/criteria/profil/ReferentielMetierCriteria.model';


import {ConfirmationService, MessageService,MenuItem} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';
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

import {AuthService} from 'src/app/zynerator/security/shared/service/Auth.service';
import {ExportService} from 'src/app/zynerator/util/Export.service';


import {MetierDto} from 'src/app/shared/model/profil/Metier.model';
import {MetierAdminService} from 'src/app/shared/service/admin/profil/MetierAdmin.service';
import {NiveauLangueDto} from 'src/app/shared/model/profil/NiveauLangue.model';
import {NiveauLangueAdminService} from 'src/app/shared/service/admin/profil/NiveauLangueAdmin.service';
import {LangueDto} from 'src/app/shared/model/profil/Langue.model';
import {LangueAdminService} from 'src/app/shared/service/admin/profil/LangueAdmin.service';


@Component({
  selector: 'app-referentiel-metier-list-admin',
  templateUrl: './referentiel-metier-list-admin.component.html'
})
export class ReferentielMetierListAdminComponent implements OnInit {

    protected fileName = 'ReferentielMetier';

    protected findByCriteriaShow = false;
    protected cols: any[] = [];
    protected excelPdfButons: MenuItem[];
    protected exportData: any[] = [];
    protected criteriaData: any[] = [];
    protected _totalRecords = 0;
    private _pdfName: string;


    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    protected authService: AuthService;
    protected exportService: ExportService;
    protected excelFile: File | undefined;
    protected enableSecurity = false;


     yesOrNoScelleRouge: any[] = [];
    metiers: Array<MetierDto>;
    langues: Array<LangueDto>;
    niveauLangues: Array<NiveauLangueDto>;


    constructor( private service: ReferentielMetierAdminService  , private metierService: MetierAdminService, private niveauLangueService: NiveauLangueAdminService, private langueService: LangueAdminService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.authService = ServiceLocator.injector.get(AuthService);
        this.exportService = ServiceLocator.injector.get(ExportService);
    }

    ngOnInit(): void {
        this.findPaginatedByCriteria();
        this.initExport();
        this.initCol();
        this.loadMetier();
        this.loadLangue();
        this.loadNiveauLangue();
        this.yesOrNoScelleRouge =  [{label: 'ScelleRouge', value: null},{label: 'Oui', value: 1},{label: 'Non', value: 0}];

    }




    public onExcelFileSelected(event: any): void {
        const input = event.target as HTMLInputElement;
        if (input.files && input.files.length > 0) {
            this.excelFile = input.files[0];
        }
    }

    public importExcel(): void {
        if (this.excelFile) {
            this.service.importExcel(this.excelFile).subscribe(
                response => {
                    this.items = response;
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Success',
                        detail: 'File uploaded successfully!',
                        life: 3000
                    });
                },
                error => {
                    this.messageService.add({
                        severity: 'error',
                        summary: 'Error',
                        detail: 'File uploaded with Error!',
                        life: 3000
                    });
                }
            );
        }
    }

    public findPaginatedByCriteria() {
        this.service.findPaginatedByCriteria(this.criteria).subscribe(paginatedItems => {
            this.items = paginatedItems.list;
            this.totalRecords = paginatedItems.dataSize;
            this.selections = new Array<ReferentielMetierDto>();
        }, error => console.log(error));
    }

    public onPage(event: any) {
        this.criteria.page = event.page;
        this.criteria.maxResults = event.rows;
        this.findPaginatedByCriteria();
    }

    public async edit(dto: ReferentielMetierDto) {
        this.service.findByIdWithAssociatedList(dto).subscribe(res => {
            this.item = res;
            console.log(res);
            this.editDialog = true;
        });

    }

    public async view(dto: ReferentielMetierDto) {
        this.service.findByIdWithAssociatedList(dto).subscribe(res => {
            this.item = res;
            this.viewDialog = true;
        });
    }

    public async openCreate() {
        this.item = new ReferentielMetierDto();
        this.createDialog = true;
    }

    public async deleteMultiple() {
        this.confirmationService.confirm({
            message: 'Voulez-vous supprimer ces éléments ?',
            header: 'Confirmation',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.service.deleteMultiple().subscribe(() => {
                    for (let selection of this.selections) {
                        let index = this.items.findIndex(element => element.id === selection.id);
                        this.items.splice(index,1);
                    }
                    this.selections = new Array<ReferentielMetierDto>();
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Succès',
                        detail: 'Les éléments sélectionnés ont été supprimés',
                        life: 3000
                    });

                }, error => console.log(error));
            }
        });
    }


    public isSelectionDisabled(): boolean {
        return this.selections == null || this.selections.length == 0;
    }


    public async delete(dto: ReferentielMetierDto) {

        this.confirmationService.confirm({
            message: 'Voulez-vous supprimer cet élément ?',
            header: 'Confirmation',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.service.delete(dto).subscribe(status => {
                    if (status > 0) {
                        const position = this.items.indexOf(dto);
                        position > -1 ? this.items.splice(position, 1) : false;
                        this.messageService.add({
                            severity: 'success',
                            summary: 'Succès',
                            detail: 'Element Supprimé',
                            life: 3000
                        });
                    }

                }, error => console.log(error));
            }
        });

    }

    public async duplicate(dto: ReferentielMetierDto) {
        this.service.findByIdWithAssociatedList(dto).subscribe(
            res => {
                this.initDuplicate(res);
                this.item = res;
                this.item.id = null;
                this.createDialog = true;
            });
    }

    // TODO : check if correct
    public initExport(): void {
        this.excelPdfButons = [
            {
                label: 'CSV', icon: 'pi pi-file', command: () => {
                    this.prepareColumnExport();
                    this.exportService.exporterCSV(this.criteriaData, this.exportData, this.fileName);
                }
            },
            {
                label: 'XLS', icon: 'pi pi-file-excel', command: () => {
                    this.prepareColumnExport();
                    this.exportService.exporterExcel(this.criteriaData, this.exportData, this.fileName);
                }
            },
            {
                label: 'PDF', icon: 'pi pi-file-pdf', command: () => {
                    this.prepareColumnExport();
                    this.exportService.exporterPdf(this.criteriaData, this.exportData, this.fileName);
                }
            }
        ];
    }

    public exportPdf(dto: ReferentielMetierDto): void {
        this.service.exportPdf(dto).subscribe((data: ArrayBuffer) => {
            const blob = new Blob([data], {type: 'application/pdf'});
            const url = window.URL.createObjectURL(blob);
            const link = document.createElement('a');
            link.href = url;
            link.download = this.pdfName;
            link.setAttribute('target', '_blank'); // open link in new tab
            link.click();
            window.URL.revokeObjectURL(url);
        }, (error) => {
            console.error(error); // handle any errors that occur
        });
    }

    public showSearch(): void {
        this.findByCriteriaShow = !this.findByCriteriaShow;
    }


    update() {
        this.service.edit().subscribe(data => {
            const myIndex = this.items.findIndex(e => e.id === this.item.id);
            this.items[myIndex] = data;
            this.editDialog = false;
            this.item = new ReferentielMetierDto();
        } , error => {
            console.log(error);
        });
    }

    public save() {
        this.service.save().subscribe(item => {
            if (item != null) {
                this.items.push({...item});
                this.createDialog = false;


                this.item = new ReferentielMetierDto();
            } else {
                this.messageService.add({severity: 'error', summary: 'Erreurs', detail: 'Element existant'});
            }
        }, error => {
            console.log(error);
        });
    }

// add


    public initCol() {
        this.cols = [
            {field: 'libelle', header: 'Libelle'},
            {field: 'code', header: 'Code'},
            {field: 'metier?.libelle', header: 'Metier'},
            {field: 'nombreHeuresExperienceMin', header: 'Nombre heures experience min'},
            {field: 'langue?.libelle', header: 'Langue'},
            {field: 'niveauLangue?.libelle', header: 'Niveau langue'},
            {field: 'scelleRouge', header: 'Scelle rouge'},
        ];
    }


    public async loadMetier(){
        this.metierService.findAllOptimized().subscribe(metiers => this.metiers = metiers, error => console.log(error))
    }
    public async loadLangue(){
        this.langueService.findAllOptimized().subscribe(langues => this.langues = langues, error => console.log(error))
    }
    public async loadNiveauLangue(){
        this.niveauLangueService.findAllOptimized().subscribe(niveauLangues => this.niveauLangues = niveauLangues, error => console.log(error))
    }


	public initDuplicate(res: ReferentielMetierDto) {
	}


    public prepareColumnExport(): void {
        this.service.findByCriteria(this.criteria).subscribe(
            (allItems) =>{
                this.exportData = allItems.map(e => {
					return {
						'Libelle': e.libelle ,
						'Code': e.code ,
						'Description': e.description ,
						'Metier': e.metier?.libelle ,
						'Nombre heures experience min': e.nombreHeuresExperienceMin ,
						'Langue': e.langue?.libelle ,
						'Niveau langue': e.niveauLangue?.libelle ,
						'Scelle rouge': e.scelleRouge? 'Vrai' : 'Faux' ,
					}
				});

            this.criteriaData = [{
                'Libelle': this.criteria.libelle ? this.criteria.libelle : environment.emptyForExport ,
                'Code': this.criteria.code ? this.criteria.code : environment.emptyForExport ,
                'Description': this.criteria.description ? this.criteria.description : environment.emptyForExport ,
            //'Metier': this.criteria.metier?.libelle ? this.criteria.metier?.libelle : environment.emptyForExport ,
                'Nombre heures experience min Min': this.criteria.nombreHeuresExperienceMinMin ? this.criteria.nombreHeuresExperienceMinMin : environment.emptyForExport ,
                'Nombre heures experience min Max': this.criteria.nombreHeuresExperienceMinMax ? this.criteria.nombreHeuresExperienceMinMax : environment.emptyForExport ,
            //'Langue': this.criteria.langue?.libelle ? this.criteria.langue?.libelle : environment.emptyForExport ,
            //'Niveau langue': this.criteria.niveauLangue?.libelle ? this.criteria.niveauLangue?.libelle : environment.emptyForExport ,
                'Scelle rouge': this.criteria.scelleRouge ? (this.criteria.scelleRouge ? environment.trueValue : environment.falseValue) : environment.emptyForExport ,
            }];
			}

        )
    }


    get items(): Array<ReferentielMetierDto> {
        return this.service.items;
    }

    set items(value: Array<ReferentielMetierDto>) {
        this.service.items = value;
    }

    get selections(): Array<ReferentielMetierDto> {
        return this.service.selections;
    }

    set selections(value: Array<ReferentielMetierDto>) {
        this.service.selections = value;
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

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
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

    get dateFormat() {
        return environment.dateFormatList;
    }


    get totalRecords(): number {
        return this._totalRecords;
    }

    set totalRecords(value: number) {
        this._totalRecords = value;
    }

    get pdfName(): string {
        return this._pdfName;
    }

    set pdfName(value: string) {
        this._pdfName = value;
    }

    get createActionIsValid(): boolean {
        return this.service.createActionIsValid;
    }

    set createActionIsValid(value: boolean) {
        this.service.createActionIsValid = value;
    }


    get editActionIsValid(): boolean {
        return this.service.editActionIsValid;
    }

    set editActionIsValid(value: boolean) {
        this.service.editActionIsValid = value;
    }

    get listActionIsValid(): boolean {
        return this.service.listActionIsValid;
    }

    set listActionIsValid(value: boolean) {
        this.service.listActionIsValid = value;
    }

    get deleteActionIsValid(): boolean {
        return this.service.deleteActionIsValid;
    }

    set deleteActionIsValid(value: boolean) {
        this.service.deleteActionIsValid = value;
    }


    get viewActionIsValid(): boolean {
        return this.service.viewActionIsValid;
    }

    set viewActionIsValid(value: boolean) {
        this.service.viewActionIsValid = value;
    }

    get duplicateActionIsValid(): boolean {
        return this.service.duplicateActionIsValid;
    }

    set duplicateActionIsValid(value: boolean) {
        this.service.duplicateActionIsValid = value;
    }

    get createAction(): string {
        return this.service.createAction;
    }

    set createAction(value: string) {
        this.service.createAction = value;
    }

    get listAction(): string {
        return this.service.listAction;
    }

    set listAction(value: string) {
        this.service.listAction = value;
    }

    get editAction(): string {
        return this.service.editAction;
    }

    set editAction(value: string) {
        this.service.editAction = value;
    }

    get deleteAction(): string {
        return this.service.deleteAction;
    }

    set deleteAction(value: string) {
        this.service.deleteAction = value;
    }

    get viewAction(): string {
        return this.service.viewAction;
    }

    set viewAction(value: string) {
        this.service.viewAction = value;
    }

    get duplicateAction(): string {
        return this.service.duplicateAction;
    }

    set duplicateAction(value: string) {
        this.service.duplicateAction = value;
    }

    get entityName(): string {
        return this.service.entityName;
    }

    set entityName(value: string) {
        this.service.entityName = value;
    }
}
