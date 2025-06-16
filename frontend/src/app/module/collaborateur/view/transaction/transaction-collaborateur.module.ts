import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {ToastModule} from 'primeng/toast';
import {ToolbarModule} from 'primeng/toolbar';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {InputSwitchModule} from 'primeng/inputswitch';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {EditorModule} from "primeng/editor";

import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DialogModule } from 'primeng/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {CalendarModule} from 'primeng/calendar';
import {PanelModule} from 'primeng/panel';
import {InputNumberModule} from 'primeng/inputnumber';
import {BadgeModule} from 'primeng/badge';
import { MultiSelectModule } from 'primeng/multiselect';
import {TranslateModule} from '@ngx-translate/core';
import {FileUploadModule} from 'primeng/fileupload';
import {FullCalendarModule} from '@fullcalendar/angular';
import {CardModule} from "primeng/card";
import {TagModule} from "primeng/tag";

import { StatusPaiementCreateCollaborateurComponent } from './status-paiement/create/status-paiement-create-collaborateur.component';
import { StatusPaiementEditCollaborateurComponent } from './status-paiement/edit/status-paiement-edit-collaborateur.component';
import { StatusPaiementViewCollaborateurComponent } from './status-paiement/view/status-paiement-view-collaborateur.component';
import { StatusPaiementListCollaborateurComponent } from './status-paiement/list/status-paiement-list-collaborateur.component';
import { PaiementCreateCollaborateurComponent } from './paiement/create/paiement-create-collaborateur.component';
import { PaiementEditCollaborateurComponent } from './paiement/edit/paiement-edit-collaborateur.component';
import { PaiementViewCollaborateurComponent } from './paiement/view/paiement-view-collaborateur.component';
import { PaiementListCollaborateurComponent } from './paiement/list/paiement-list-collaborateur.component';
import { MoyenPaiementCreateCollaborateurComponent } from './moyen-paiement/create/moyen-paiement-create-collaborateur.component';
import { MoyenPaiementEditCollaborateurComponent } from './moyen-paiement/edit/moyen-paiement-edit-collaborateur.component';
import { MoyenPaiementViewCollaborateurComponent } from './moyen-paiement/view/moyen-paiement-view-collaborateur.component';
import { MoyenPaiementListCollaborateurComponent } from './moyen-paiement/list/moyen-paiement-list-collaborateur.component';

import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {TabViewModule} from 'primeng/tabview';
import { SplitButtonModule } from 'primeng/splitbutton';
import { MessageModule } from 'primeng/message';
import {MessagesModule} from 'primeng/messages';
import {PaginatorModule} from 'primeng/paginator';
import {TransactionAdminModule} from "../../../admin/view/transaction/transaction-admin.module";



@NgModule({
  declarations: [

    StatusPaiementCreateCollaborateurComponent,
    StatusPaiementListCollaborateurComponent,
    StatusPaiementViewCollaborateurComponent,
    StatusPaiementEditCollaborateurComponent,
    PaiementCreateCollaborateurComponent,
    PaiementListCollaborateurComponent,
    PaiementViewCollaborateurComponent,
    PaiementEditCollaborateurComponent,
    MoyenPaiementCreateCollaborateurComponent,
    MoyenPaiementListCollaborateurComponent,
    MoyenPaiementViewCollaborateurComponent,
    MoyenPaiementEditCollaborateurComponent,
  ],
    imports: [
        CommonModule,
        ToastModule,
        ToolbarModule,
        TableModule,
        ConfirmDialogModule,
        DialogModule,
        PasswordModule,
        InputTextModule,
        ButtonModule,
        FormsModule,
        ReactiveFormsModule,
        RouterModule,
        SplitButtonModule,
        BrowserAnimationsModule,
        DropdownModule,
        TabViewModule,
        InputSwitchModule,
        InputTextareaModule,
        CalendarModule,
        PanelModule,
        MessageModule,
        MessagesModule,
        InputNumberModule,
        BadgeModule,
        MultiSelectModule,
        PaginatorModule,
        TranslateModule,
        FileUploadModule,
        FullCalendarModule,
        CardModule,
        EditorModule,
        TagModule,
        TransactionAdminModule,


    ],
  exports: [
  StatusPaiementCreateCollaborateurComponent,
  StatusPaiementListCollaborateurComponent,
  StatusPaiementViewCollaborateurComponent,
  StatusPaiementEditCollaborateurComponent,
  PaiementCreateCollaborateurComponent,
  PaiementListCollaborateurComponent,
  PaiementViewCollaborateurComponent,
  PaiementEditCollaborateurComponent,
  MoyenPaiementCreateCollaborateurComponent,
  MoyenPaiementListCollaborateurComponent,
  MoyenPaiementViewCollaborateurComponent,
  MoyenPaiementEditCollaborateurComponent,
  ],
})
export class TransactionCollaborateurModule { }
