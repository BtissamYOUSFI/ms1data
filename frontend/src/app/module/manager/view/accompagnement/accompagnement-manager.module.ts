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

import { ReunionCreateManagerComponent } from './reunion/create/reunion-create-manager.component';
import { ReunionEditManagerComponent } from './reunion/edit/reunion-edit-manager.component';
import { ReunionViewManagerComponent } from './reunion/view/reunion-view-manager.component';
import { ReunionListManagerComponent } from './reunion/list/reunion-list-manager.component';
import { EtatReunionCreateManagerComponent } from './etat-reunion/create/etat-reunion-create-manager.component';
import { EtatReunionEditManagerComponent } from './etat-reunion/edit/etat-reunion-edit-manager.component';
import { EtatReunionViewManagerComponent } from './etat-reunion/view/etat-reunion-view-manager.component';
import { EtatReunionListManagerComponent } from './etat-reunion/list/etat-reunion-list-manager.component';

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
import {AccompagnementAdminModule} from "../../../admin/view/accompagnement/accompagnement-admin.module";


@NgModule({
  declarations: [

    ReunionCreateManagerComponent,
    ReunionListManagerComponent,
    ReunionViewManagerComponent,
    ReunionEditManagerComponent,
    EtatReunionCreateManagerComponent,
    EtatReunionListManagerComponent,
    EtatReunionViewManagerComponent,
    EtatReunionEditManagerComponent,
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
        AccompagnementAdminModule


    ],
  exports: [
  ReunionCreateManagerComponent,
  ReunionListManagerComponent,
  ReunionViewManagerComponent,
  ReunionEditManagerComponent,
  EtatReunionCreateManagerComponent,
  EtatReunionListManagerComponent,
  EtatReunionViewManagerComponent,
  EtatReunionEditManagerComponent,
  ],
})
export class AccompagnementManagerModule { }
