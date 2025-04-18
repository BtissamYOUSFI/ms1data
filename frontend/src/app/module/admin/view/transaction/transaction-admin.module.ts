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

import { StatusPaiementCreateAdminComponent } from './status-paiement/create/status-paiement-create-admin.component';
import { StatusPaiementEditAdminComponent } from './status-paiement/edit/status-paiement-edit-admin.component';
import { StatusPaiementViewAdminComponent } from './status-paiement/view/status-paiement-view-admin.component';
import { StatusPaiementListAdminComponent } from './status-paiement/list/status-paiement-list-admin.component';
import { PaiementCreateAdminComponent } from './paiement/create/paiement-create-admin.component';
import { PaiementEditAdminComponent } from './paiement/edit/paiement-edit-admin.component';
import { PaiementViewAdminComponent } from './paiement/view/paiement-view-admin.component';
import { PaiementListAdminComponent } from './paiement/list/paiement-list-admin.component';
import { MoyenPaiementCreateAdminComponent } from './moyen-paiement/create/moyen-paiement-create-admin.component';
import { MoyenPaiementEditAdminComponent } from './moyen-paiement/edit/moyen-paiement-edit-admin.component';
import { MoyenPaiementViewAdminComponent } from './moyen-paiement/view/moyen-paiement-view-admin.component';
import { MoyenPaiementListAdminComponent } from './moyen-paiement/list/moyen-paiement-list-admin.component';

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



@NgModule({
  declarations: [

    StatusPaiementCreateAdminComponent,
    StatusPaiementListAdminComponent,
    StatusPaiementViewAdminComponent,
    StatusPaiementEditAdminComponent,
    PaiementCreateAdminComponent,
    PaiementListAdminComponent,
    PaiementViewAdminComponent,
    PaiementEditAdminComponent,
    MoyenPaiementCreateAdminComponent,
    MoyenPaiementListAdminComponent,
    MoyenPaiementViewAdminComponent,
    MoyenPaiementEditAdminComponent,
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


  ],
  exports: [
  StatusPaiementCreateAdminComponent,
  StatusPaiementListAdminComponent,
  StatusPaiementViewAdminComponent,
  StatusPaiementEditAdminComponent,
  PaiementCreateAdminComponent,
  PaiementListAdminComponent,
  PaiementViewAdminComponent,
  PaiementEditAdminComponent,
  MoyenPaiementCreateAdminComponent,
  MoyenPaiementListAdminComponent,
  MoyenPaiementViewAdminComponent,
  MoyenPaiementEditAdminComponent,
  ],
})
export class TransactionAdminModule { }
