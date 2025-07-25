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

import { CollaborateurCreateAdminComponent } from './collaborateur/create/collaborateur-create-admin.component';
import { CollaborateurEditAdminComponent } from './collaborateur/edit/collaborateur-edit-admin.component';
import { CollaborateurViewAdminComponent } from './collaborateur/view/collaborateur-view-admin.component';
import { CollaborateurListAdminComponent } from './collaborateur/list/collaborateur-list-admin.component';

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
import {ManagerCreateAdminComponent} from "./manager/create/manager-create-admin.component";
import {ManagerEditAdminComponent} from "./manager/edit/manager-edit-admin.component";
import {ManagerListAdminComponent} from "./manager/list/manager-list-admin.component";
import {ManagerViewAdminComponent} from "./manager/view/manager-view-admin.component";



@NgModule({
  declarations: [

    CollaborateurCreateAdminComponent,
    CollaborateurListAdminComponent,
    CollaborateurViewAdminComponent,
    CollaborateurEditAdminComponent,
      ManagerCreateAdminComponent,
      ManagerEditAdminComponent,
      ManagerListAdminComponent,
      ManagerViewAdminComponent
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
  CollaborateurCreateAdminComponent,
  CollaborateurListAdminComponent,
  CollaborateurViewAdminComponent,
  CollaborateurEditAdminComponent,
      ManagerCreateAdminComponent,
      ManagerEditAdminComponent,
      ManagerListAdminComponent,
      ManagerViewAdminComponent
  ],
})
export class UtilisateursAdminModule { }
