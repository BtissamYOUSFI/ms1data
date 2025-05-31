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

import { EtatReunionCreateAdminComponent } from './etat-reunion/create/etat-reunion-create-admin.component';
import { EtatReunionEditAdminComponent } from './etat-reunion/edit/etat-reunion-edit-admin.component';
import { EtatReunionViewAdminComponent } from './etat-reunion/view/etat-reunion-view-admin.component';
import { EtatReunionListAdminComponent } from './etat-reunion/list/etat-reunion-list-admin.component';
import { ReunionCreateAdminComponent } from './reunion/create/reunion-create-admin.component';
import { ReunionEditAdminComponent } from './reunion/edit/reunion-edit-admin.component';
import { ReunionViewAdminComponent } from './reunion/view/reunion-view-admin.component';
import { ReunionListAdminComponent } from './reunion/list/reunion-list-admin.component';

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
import {
    TemplateEmailCollaboratorListAdminComponent
} from "./template-email-collaborator/list/template-email-collaborator-list-admin.component";
import {
    TemplateEmailManagerListAdminComponent
} from "./template-email-manager/list/template-email-manager-list-admin.component";
import {
    TemplateEmailCollaboratorEditAdminComponent
} from "./template-email-collaborator/edit/template-email-collaborator-edit-admin.component";
import {
    TemplateEmailManagerEditAdminComponent
} from "./template-email-manager/edit/template-email-manager-edit-admin.component";
import {
    TemplateEmailCollaboratorCreateAdminComponent
} from "./template-email-collaborator/create/template-email-collaborator-create-admin.component";
import {
    TemplateEmailManagerCreateAdminComponent
} from "./template-email-manager/create/template-email-manager-create-admin.component";
import {
    TemplateEmailCollaboratorViewAdminComponent
} from "./template-email-collaborator/view/template-email-collaborator-view-admin.component";
import {
    TemplateEmailManagerViewAdminComponent
} from "./template-email-manager/view/template-email-manager-view-admin.component";



@NgModule({
  declarations: [

    EtatReunionCreateAdminComponent,
    EtatReunionListAdminComponent,
    EtatReunionViewAdminComponent,
    EtatReunionEditAdminComponent,
    ReunionCreateAdminComponent,
    ReunionListAdminComponent,
    ReunionViewAdminComponent,
    ReunionEditAdminComponent,
      TemplateEmailCollaboratorListAdminComponent,
      TemplateEmailManagerListAdminComponent,
      TemplateEmailCollaboratorEditAdminComponent,
      TemplateEmailManagerEditAdminComponent,
      TemplateEmailCollaboratorCreateAdminComponent,
      TemplateEmailManagerCreateAdminComponent,
      TemplateEmailCollaboratorViewAdminComponent,
      TemplateEmailManagerViewAdminComponent
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
  EtatReunionCreateAdminComponent,
  EtatReunionListAdminComponent,
  EtatReunionViewAdminComponent,
  EtatReunionEditAdminComponent,
  ReunionCreateAdminComponent,
  ReunionListAdminComponent,
  ReunionViewAdminComponent,
  ReunionEditAdminComponent,
      TemplateEmailCollaboratorListAdminComponent,
      TemplateEmailManagerListAdminComponent,
      TemplateEmailCollaboratorEditAdminComponent,
      TemplateEmailManagerEditAdminComponent,
      TemplateEmailCollaboratorCreateAdminComponent,
      TemplateEmailManagerCreateAdminComponent,
      TemplateEmailCollaboratorViewAdminComponent,
      TemplateEmailManagerViewAdminComponent
  ],
})
export class AccompagnementAdminModule { }
