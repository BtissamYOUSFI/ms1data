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

import { EtatReunionCreateCollaborateurComponent } from './etat-reunion/create/etat-reunion-create-collaborateur.component';
import { EtatReunionEditCollaborateurComponent } from './etat-reunion/edit/etat-reunion-edit-collaborateur.component';
import { EtatReunionViewCollaborateurComponent } from './etat-reunion/view/etat-reunion-view-collaborateur.component';
import { EtatReunionListCollaborateurComponent } from './etat-reunion/list/etat-reunion-list-collaborateur.component';
import { ReunionCreateCollaborateurComponent } from './reunion/create/reunion-create-collaborateur.component';
import { ReunionEditCollaborateurComponent } from './reunion/edit/reunion-edit-collaborateur.component';
import { ReunionViewCollaborateurComponent } from './reunion/view/reunion-view-collaborateur.component';
import { ReunionListCollaborateurComponent } from './reunion/list/reunion-list-collaborateur.component';

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

    EtatReunionCreateCollaborateurComponent,
    EtatReunionListCollaborateurComponent,
    EtatReunionViewCollaborateurComponent,
    EtatReunionEditCollaborateurComponent,
    ReunionCreateCollaborateurComponent,
    ReunionListCollaborateurComponent,
    ReunionViewCollaborateurComponent,
    ReunionEditCollaborateurComponent,
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
  EtatReunionCreateCollaborateurComponent,
  EtatReunionListCollaborateurComponent,
  EtatReunionViewCollaborateurComponent,
  EtatReunionEditCollaborateurComponent,
  ReunionCreateCollaborateurComponent,
  ReunionListCollaborateurComponent,
  ReunionViewCollaborateurComponent,
  ReunionEditCollaborateurComponent,
  ],
})
export class AccompagnementCollaborateurModule { }
