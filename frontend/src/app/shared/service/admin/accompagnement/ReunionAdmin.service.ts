import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';

import {environment} from 'src/environments/environment';
import {PaginatedList} from 'src/app/zynerator/dto/PaginatedList.model';
import * as moment from 'moment/moment';

import {ReunionDto} from 'src/app/shared/model/accompagnement/Reunion.model';
import {ReunionCriteria} from 'src/app/shared/criteria/accompagnement/ReunionCriteria.model';
import {TemplateEmailCollaboratorDto} from "../../../model/accompagnement/TemplateEmailCollaborator.model";
import {TemplateEmailManagerDto} from "../../../model/accompagnement/TemplateEmailManager.model";


@Injectable({
  providedIn: 'root'
})
export class ReunionAdminService {
    protected _API = '';
    protected _items: Array<ReunionDto>;
    protected _item: ReunionDto;
    protected _selections: Array<ReunionDto>;
    protected _createDialog: boolean;
    protected _editDialog: boolean;
    protected _viewDialog: boolean;
    protected _criteria: ReunionCriteria;
    protected _validate = false;


    private _createActionIsValid = true;
    private _editActionIsValid = true;
    private _listActionIsValid = true;
    private _deleteActionIsValid = true;
    private _viewActionIsValid = true;
    private _duplicateActionIsValid = true;


    private _createAction = 'create';
    private _listAction = 'list';
    private _editAction = 'edit';
    private _deleteAction = 'delete';
    private _viewAction = 'view';
    private _duplicateAction = 'duplicate';
    private _entityName: string;

    protected API_PERMISSION: string ;


    constructor(private http: HttpClient) {
        this.API_PERMISSION = environment.apiUrl + 'modelPermissionUser/';
    }


    public findAll() {
        return this.http.get<Array<ReunionDto>>(this.API);
    }

    public findAllOptimized() {
        return this.http.get<Array<ReunionDto>>(this.API + 'optimized');
    }

    public findPaginatedByCriteria(criteria: ReunionCriteria): Observable<PaginatedList<ReunionDto>> {
        return this.http.post<PaginatedList<ReunionDto>>(this.API + 'find-paginated-by-criteria', criteria);
    }

    public save() :Observable<ReunionDto>{
        return this.http.post<ReunionDto>(this.API, this.item);
    }

    // public sendEmail(email: { to: string; subject: string; message: string }): Observable<any> {
    //     return this.http.post(this.API + 'send-email/', email);
    // }
    //
    // public saveAndSendEmail(emailCollaborateur: { collaborator: string; subject: string; body: string }, emailManager: { manager: string; subject: string; body: string }): Observable<ReunionDto> {
    //     return new Observable<ReunionDto>(observer => {
    //         this.http.post<ReunionDto>(this.API, this.item).subscribe({
    //             next: (createdReunion) => {
    //                 // 1. Envoyer l’email au collaborateur
    //                 this.sendEmail(emailCollaborateur).subscribe({
    //                     next: () => {
    //                         console.log('Email envoyé au collaborateur.');
    //
    //                         // 2. Ensuite, envoyer l’email au manager
    //                         this.sendEmail(emailManager).subscribe({
    //                             next: () => {
    //                                 console.log('Email envoyé au manager.');
    //                                 observer.next(createdReunion);
    //                                 observer.complete();
    //                             },
    //                             error: (err) => {
    //                                 console.error('Erreur lors de l’envoi de l’email au manager', err);
    //                                 observer.error(err);
    //                             }
    //                         });
    //
    //                     },
    //                     error: (err) => {
    //                         console.error('Erreur lors de l’envoi de l’email au collaborateur', err);
    //                         observer.error(err);
    //                     }
    //                 });
    //             },
    //             error: (err) => {
    //                 observer.error(err);
    //             }
    //         });
    //     });
    // }

// Service corrigé
    public sendEmail(email: { to: string; subject: string; message: string }): Observable<any> {
        return this.http.post(this.API + 'send-email/', email);
    }

    public saveAndSendEmail(emailCollaborateur: TemplateEmailCollaboratorDto, emailManager: TemplateEmailManagerDto): Observable<ReunionDto> {
        return new Observable<ReunionDto>(observer => {
            // 1. D'abord sauvegarder la réunion
            this.http.post<ReunionDto>(this.API, this.item).subscribe({
                next: (createdReunion) => {
                    console.log('Réunion créée:', createdReunion);

                    // 2. Préparer les emails avec les bonnes propriétés
                    const emailToCollaborator = {
                        to: this.item.collaborateur?.email || emailCollaborateur.collaborator,
                        subject: emailCollaborateur.subject,
                        message: emailCollaborateur.body // Utiliser 'message' au lieu de 'body'
                    };

                    const emailToManager = {
                        to: "ibtissamyousfi111@gmail.com", // ou emailManager.manager
                        subject: emailManager.subject,
                        message: emailManager.body // Utiliser 'message' au lieu de 'body'
                    };

                    console.log('Email collaborateur:', emailToCollaborator);
                    console.log('Email manager:', emailToManager);

                    // 3. Envoyer l'email au collaborateur
                    this.sendEmail(emailToCollaborator).subscribe({
                        next: (response1) => {
                            console.log('Email envoyé au collaborateur:', response1);

                            // 4. Envoyer l'email au manager
                            this.sendEmail(emailToManager).subscribe({
                                next: (response2) => {
                                    console.log('Email envoyé au manager:', response2);
                                    observer.next(createdReunion);
                                    observer.complete();
                                },
                                error: (err) => {
                                    console.error('Erreur lors de lenvoi de lemail au manager:', err);
                                    // Même si l'email manager échoue, on peut considérer que la réunion est créée
                                    observer.next(createdReunion);
                                    observer.complete();
                                }
                            });
                        },
                        error: (err) => {
                            console.error('Erreur lors de lenvoi de lemail au collaborateur:', err);
                            // Même si l'email collaborateur échoue, on peut considérer que la réunion est créée
                            observer.next(createdReunion);
                            observer.complete();
                        }
                    });
                },
                error: (err) => {
                    console.error('Erreur lors de la création de la réunion:', err);
                    observer.error(err);
                }
            });
        });
    }

    // public save(): Observable<ReunionDto> {
    //     return new Observable<ReunionDto>(observer=>{
    //         this.http.post<ReunionDto>(this.API, this.item).subscribe({
    //             next: (createdReunion) => {
    //                 const emailPayload = {
    //                     to: createdReunion.collaborateur.email,
    //                     subject: 'Nouvelle réunion programmée',
    //                     message: `
    //                           Bonjour ${createdReunion.collaborateur.username},
    //
    //                           Une réunion a été programmée.
    //
    //                           📅 Description : ${createdReunion.description}
    //                           🔗 Lien : ${createdReunion.style}
    //
    //                           Merci.
    //                         `
    //                 };
    //                 this.http.post(this.API+"send-email/", emailPayload).subscribe({
    //                     next: () => {
    //                         console.log('Email envoyé au collaborateur.');
    //                         observer.next(createdReunion);
    //                         observer.complete();
    //                     },
    //                     error: (err) => {
    //                         console.error('Erreur lors de l’envoi de l’email', err);
    //                         observer.error(err);
    //                     }
    //                 });
    //             },
    //             error: (err) => {
    //                 observer.error(err);
    //             }
    //         });
    //     })
    // }

    public delete(dto: ReunionDto) {
        return this.http.delete<number>(this.API + 'id/' + dto.id);
    }


    public edit(): Observable<ReunionDto> {
        return this.http.put<ReunionDto>(this.API, this.item);
    }


    public findByCriteria(criteria: ReunionCriteria): Observable<Array<ReunionDto>> {
        return this.http.post<Array<ReunionDto>>(this.API + 'find-by-criteria', criteria);
    }

    public findByIdWithAssociatedList(item: ReunionDto): Observable<ReunionDto> {
        return this.http.get<ReunionDto>(this.API + 'id/' + item.id);
    }

    public deleteMultiple() {
        return this.http.post<void>(this.API + 'multiple', this.selections);
    }


    public exportPdf(element: ReunionDto): Observable<ArrayBuffer> {
        return this.http.post(this.API + 'exportPdf/', element, {responseType: 'arraybuffer'});
    }

    public hasActionPermission(username: string, actionReference: string): Observable<boolean> {
        // tslint:disable-next-line:max-line-length
        return this.http.get<boolean>(this.API_PERMISSION + 'user/' + username + '/model/' + this.entityName + '/action/' + actionReference);
    }

    public importExcel(file: File): Observable<Array<ReunionDto>> {
        const formData: FormData = new FormData();
        formData.append('file', file, file.name);
        return this.http.post<Array<ReunionDto>>(this.API + 'import-excel', formData);
    }



    public format(myDate: Date): Date {
        if (myDate != null) {
            const newdate = new Date(myDate);
            const formattedDate = moment(newdate).format(environment.dateFormatEdit);
            console.log(formattedDate);
            myDate = new Date(formattedDate);
        }
        return myDate;
    }

    get API() {
        return environment.apiUrlMs1datams1 + 'admin/reunion/';
    }

    public get items(): Array<ReunionDto> {
        if (this._items == null) {
            this._items = new Array<ReunionDto>();
        }
        return this._items;
    }

    public set items(value: Array<ReunionDto>) {
        this._items = value;
    }

    public get item(): ReunionDto {
        if (this._item == null) {
            this._item = new ReunionDto();
        }
        return this._item;
    }

    public set item(value: ReunionDto) {
        this._item = value;
    }

    public get selections(): Array<ReunionDto> {
        if (this._selections == null) {
            this._selections = new Array<ReunionDto>();
        }
        return this._selections;
    }


    public set selections(value: Array<ReunionDto>) {
        this._selections = value;
    }

    public get createDialog(): boolean {
        return this._createDialog;
    }

    public set createDialog(value: boolean) {
        this._createDialog = value;
    }

    public get editDialog(): boolean {
        return this._editDialog;
    }

    public set editDialog(value: boolean) {
        this._editDialog = value;
    }

    public get viewDialog(): boolean {
        return this._viewDialog;
    }

    public set viewDialog(value: boolean) {
        this._viewDialog = value;
    }

    public get criteria(): ReunionCriteria {
        if (this._criteria == null) {
            this._criteria = new ReunionCriteria();
        }
        return this._criteria;
    }

    public set criteria(value: ReunionCriteria) {
        this._criteria = value;
    }


    public setApi(API: string) {
        this._API = API;
    }

    set validate(value: boolean) {
        this._validate = value;
    }


    get createAction(): string {
        return this._createAction;
    }

    set createAction(value: string) {
        this._createAction = value;
    }

    get listAction(): string {
        return this._listAction;
    }

    set listAction(value: string) {
        this._listAction = value;
    }

    get editAction(): string {
        return this._editAction;
    }

    set editAction(value: string) {
        this._editAction = value;
    }

    get deleteAction(): string {
        return this._deleteAction;
    }

    set deleteAction(value: string) {
        this._deleteAction = value;
    }

    get createActionIsValid(): boolean {
        return this._createActionIsValid;
    }

    set createActionIsValid(value: boolean) {
        this._createActionIsValid = value;
    }


    get editActionIsValid(): boolean {
        return this._editActionIsValid;
    }

    set editActionIsValid(value: boolean) {
        this._editActionIsValid = value;
    }

    get listActionIsValid(): boolean {
        return this._listActionIsValid;
    }

    set listActionIsValid(value: boolean) {
        this._listActionIsValid = value;
    }

    get deleteActionIsValid(): boolean {
        return this._deleteActionIsValid;
    }

    set deleteActionIsValid(value: boolean) {
        this._deleteActionIsValid = value;
    }

    get viewAction(): string {
        return this._viewAction;
    }

    set viewAction(value: string) {
        this._viewAction = value;
    }

    get duplicateAction(): string {
        return this._duplicateAction;
    }

    set duplicateAction(value: string) {
        this._duplicateAction = value;
    }

    get viewActionIsValid(): boolean {
        return this._viewActionIsValid;
    }

    set viewActionIsValid(value: boolean) {
        this._viewActionIsValid = value;
    }

    get duplicateActionIsValid(): boolean {
        return this._duplicateActionIsValid;
    }

    set duplicateActionIsValid(value: boolean) {
        this._duplicateActionIsValid = value;
    }

    get entityName(): string {
        return this._entityName;
    }

    set entityName(value: string) {
        this._entityName = value;
    }

}
