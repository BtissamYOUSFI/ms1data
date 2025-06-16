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

import { NiveauLangueCreateManagerComponent } from './niveau-langue/create/niveau-langue-create-manager.component';
import { NiveauLangueEditManagerComponent } from './niveau-langue/edit/niveau-langue-edit-manager.component';
import { NiveauLangueViewManagerComponent } from './niveau-langue/view/niveau-langue-view-manager.component';
import { NiveauLangueListManagerComponent } from './niveau-langue/list/niveau-langue-list-manager.component';
import { LangueCreateManagerComponent } from './langue/create/langue-create-manager.component';
import { LangueEditManagerComponent } from './langue/edit/langue-edit-manager.component';
import { LangueViewManagerComponent } from './langue/view/langue-view-manager.component';
import { LangueListManagerComponent } from './langue/list/langue-list-manager.component';
import { InscriptionCreateManagerComponent } from './inscription/create/inscription-create-manager.component';
import { InscriptionEditManagerComponent } from './inscription/edit/inscription-edit-manager.component';
import { InscriptionViewManagerComponent } from './inscription/view/inscription-view-manager.component';
import { InscriptionListManagerComponent } from './inscription/list/inscription-list-manager.component';
import { ReferentielMetierCreateManagerComponent } from './referentiel-metier/create/referentiel-metier-create-manager.component';
import { ReferentielMetierEditManagerComponent } from './referentiel-metier/edit/referentiel-metier-edit-manager.component';
import { ReferentielMetierViewManagerComponent } from './referentiel-metier/view/referentiel-metier-view-manager.component';
import { ReferentielMetierListManagerComponent } from './referentiel-metier/list/referentiel-metier-list-manager.component';
import { MetierCreateManagerComponent } from './metier/create/metier-create-manager.component';
import { MetierEditManagerComponent } from './metier/edit/metier-edit-manager.component';
import { MetierViewManagerComponent } from './metier/view/metier-view-manager.component';
import { MetierListManagerComponent } from './metier/list/metier-list-manager.component';

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
import {ProfilAdminModule} from "../../../admin/view/profil/profil-admin.module";

import {
    ProfileReunionCreateManagerComponent
} from "./inscription/reunion/create/profile-reunion-create-manager.component";


@NgModule({
  declarations: [
    ProfileReunionCreateManagerComponent,
    NiveauLangueCreateManagerComponent,
    NiveauLangueListManagerComponent,
    NiveauLangueViewManagerComponent,
    NiveauLangueEditManagerComponent,
    LangueCreateManagerComponent,
    LangueListManagerComponent,
    LangueViewManagerComponent,
    LangueEditManagerComponent,
    InscriptionCreateManagerComponent,
    InscriptionListManagerComponent,
    InscriptionViewManagerComponent,
    InscriptionEditManagerComponent,
    ReferentielMetierCreateManagerComponent,
    ReferentielMetierListManagerComponent,
    ReferentielMetierViewManagerComponent,
    ReferentielMetierEditManagerComponent,
    MetierCreateManagerComponent,
    MetierListManagerComponent,
    MetierViewManagerComponent,
    MetierEditManagerComponent,
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
        AccompagnementAdminModule,
        ProfilAdminModule

    ],
  exports: [
  NiveauLangueCreateManagerComponent,
  NiveauLangueListManagerComponent,
  NiveauLangueViewManagerComponent,
  NiveauLangueEditManagerComponent,
  LangueCreateManagerComponent,
  LangueListManagerComponent,
  LangueViewManagerComponent,
  LangueEditManagerComponent,
  InscriptionCreateManagerComponent,
  InscriptionListManagerComponent,
  InscriptionViewManagerComponent,
  InscriptionEditManagerComponent,
  ReferentielMetierCreateManagerComponent,
  ReferentielMetierListManagerComponent,
  ReferentielMetierViewManagerComponent,
  ReferentielMetierEditManagerComponent,
  MetierCreateManagerComponent,
  MetierListManagerComponent,
  MetierViewManagerComponent,
  MetierEditManagerComponent,
  ],
})
export class ProfilManagerModule { }
