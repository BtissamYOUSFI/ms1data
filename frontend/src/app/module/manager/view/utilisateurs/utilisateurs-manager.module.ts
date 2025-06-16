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

import { CollaborateurCreateManagerComponent } from './collaborateur/create/collaborateur-create-manager.component';
import { CollaborateurEditManagerComponent } from './collaborateur/edit/collaborateur-edit-manager.component';
import { CollaborateurViewManagerComponent } from './collaborateur/view/collaborateur-view-manager.component';
import { CollaborateurListManagerComponent } from './collaborateur/list/collaborateur-list-manager.component';
import { ManagerCreateManagerComponent } from './manager/create/manager-create-manager.component';
import { ManagerEditManagerComponent } from './manager/edit/manager-edit-manager.component';
import { ManagerViewManagerComponent } from './manager/view/manager-view-manager.component';
import { ManagerListManagerComponent } from './manager/list/manager-list-manager.component';

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

    CollaborateurCreateManagerComponent,
    CollaborateurListManagerComponent,
    CollaborateurViewManagerComponent,
    CollaborateurEditManagerComponent,
    ManagerCreateManagerComponent,
    ManagerListManagerComponent,
    ManagerViewManagerComponent,
    ManagerEditManagerComponent,
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
      DialogModule


  ],
  exports: [
  CollaborateurCreateManagerComponent,
  CollaborateurListManagerComponent,
  CollaborateurViewManagerComponent,
  CollaborateurEditManagerComponent,
  ManagerCreateManagerComponent,
  ManagerListManagerComponent,
  ManagerViewManagerComponent,
  ManagerEditManagerComponent,
  ],
})
export class UtilisateursManagerModule { }
