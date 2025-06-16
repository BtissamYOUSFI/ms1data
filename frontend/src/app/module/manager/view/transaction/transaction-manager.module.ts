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

import { PaiementCreateManagerComponent } from './paiement/create/paiement-create-manager.component';
import { PaiementEditManagerComponent } from './paiement/edit/paiement-edit-manager.component';
import { PaiementViewManagerComponent } from './paiement/view/paiement-view-manager.component';
import { PaiementListManagerComponent } from './paiement/list/paiement-list-manager.component';
import { PaiementManagerCreateManagerComponent } from './paiement-manager/create/paiement-manager-create-manager.component';
import { PaiementManagerEditManagerComponent } from './paiement-manager/edit/paiement-manager-edit-manager.component';
import { PaiementManagerViewManagerComponent } from './paiement-manager/view/paiement-manager-view-manager.component';
import { PaiementManagerListManagerComponent } from './paiement-manager/list/paiement-manager-list-manager.component';
import { StatusPaiementCreateManagerComponent } from './status-paiement/create/status-paiement-create-manager.component';
import { StatusPaiementEditManagerComponent } from './status-paiement/edit/status-paiement-edit-manager.component';
import { StatusPaiementViewManagerComponent } from './status-paiement/view/status-paiement-view-manager.component';
import { StatusPaiementListManagerComponent } from './status-paiement/list/status-paiement-list-manager.component';
import { MoyenPaiementCreateManagerComponent } from './moyen-paiement/create/moyen-paiement-create-manager.component';
import { MoyenPaiementEditManagerComponent } from './moyen-paiement/edit/moyen-paiement-edit-manager.component';
import { MoyenPaiementViewManagerComponent } from './moyen-paiement/view/moyen-paiement-view-manager.component';
import { MoyenPaiementListManagerComponent } from './moyen-paiement/list/moyen-paiement-list-manager.component';

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

    PaiementCreateManagerComponent,
    PaiementListManagerComponent,
    PaiementViewManagerComponent,
    PaiementEditManagerComponent,
    PaiementManagerCreateManagerComponent,
    PaiementManagerListManagerComponent,
    PaiementManagerViewManagerComponent,
    PaiementManagerEditManagerComponent,
    StatusPaiementCreateManagerComponent,
    StatusPaiementListManagerComponent,
    StatusPaiementViewManagerComponent,
    StatusPaiementEditManagerComponent,
    MoyenPaiementCreateManagerComponent,
    MoyenPaiementListManagerComponent,
    MoyenPaiementViewManagerComponent,
    MoyenPaiementEditManagerComponent,
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
        BrowserAnimationsModule,
        TransactionAdminModule


    ],
  exports: [
  PaiementCreateManagerComponent,
  PaiementListManagerComponent,
  PaiementViewManagerComponent,
  PaiementEditManagerComponent,
  PaiementManagerCreateManagerComponent,
  PaiementManagerListManagerComponent,
  PaiementManagerViewManagerComponent,
  PaiementManagerEditManagerComponent,
  StatusPaiementCreateManagerComponent,
  StatusPaiementListManagerComponent,
  StatusPaiementViewManagerComponent,
  StatusPaiementEditManagerComponent,
  MoyenPaiementCreateManagerComponent,
  MoyenPaiementListManagerComponent,
  MoyenPaiementViewManagerComponent,
  MoyenPaiementEditManagerComponent,
  ],
})
export class TransactionManagerModule { }
