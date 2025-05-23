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

import { EtatInscriptionCreateAdminComponent } from './etat-inscription/create/etat-inscription-create-admin.component';
import { EtatInscriptionEditAdminComponent } from './etat-inscription/edit/etat-inscription-edit-admin.component';
import { EtatInscriptionViewAdminComponent } from './etat-inscription/view/etat-inscription-view-admin.component';
import { EtatInscriptionListAdminComponent } from './etat-inscription/list/etat-inscription-list-admin.component';
import { LangueCreateAdminComponent } from './langue/create/langue-create-admin.component';
import { LangueEditAdminComponent } from './langue/edit/langue-edit-admin.component';
import { LangueViewAdminComponent } from './langue/view/langue-view-admin.component';
import { LangueListAdminComponent } from './langue/list/langue-list-admin.component';
import { ReferentielMetierCreateAdminComponent } from './referentiel-metier/create/referentiel-metier-create-admin.component';
import { ReferentielMetierEditAdminComponent } from './referentiel-metier/edit/referentiel-metier-edit-admin.component';
import { ReferentielMetierViewAdminComponent } from './referentiel-metier/view/referentiel-metier-view-admin.component';
import { ReferentielMetierListAdminComponent } from './referentiel-metier/list/referentiel-metier-list-admin.component';
import { InscriptionCreateAdminComponent } from './inscription/create/inscription-create-admin.component';
import { InscriptionEditAdminComponent } from './inscription/edit/inscription-edit-admin.component';
import { InscriptionViewAdminComponent } from './inscription/view/inscription-view-admin.component';
import { InscriptionListAdminComponent } from './inscription/list/inscription-list-admin.component';
import { NiveauLangueCreateAdminComponent } from './niveau-langue/create/niveau-langue-create-admin.component';
import { NiveauLangueEditAdminComponent } from './niveau-langue/edit/niveau-langue-edit-admin.component';
import { NiveauLangueViewAdminComponent } from './niveau-langue/view/niveau-langue-view-admin.component';
import { NiveauLangueListAdminComponent } from './niveau-langue/list/niveau-langue-list-admin.component';
import { MetierCreateAdminComponent } from './metier/create/metier-create-admin.component';
import { MetierEditAdminComponent } from './metier/edit/metier-edit-admin.component';
import { MetierViewAdminComponent } from './metier/view/metier-view-admin.component';
import { MetierListAdminComponent } from './metier/list/metier-list-admin.component';

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
import {AccompagnementAdminModule} from "../accompagnement/accompagnement-admin.module";
import {
    ProfileReunionCreateAdminComponent,
} from "./inscription/reunion/create/profile-reunion-create-admin.component";

@NgModule({
  declarations: [
    ProfileReunionCreateAdminComponent,
    EtatInscriptionCreateAdminComponent,
    EtatInscriptionListAdminComponent,
    EtatInscriptionViewAdminComponent,
    EtatInscriptionEditAdminComponent,
    LangueCreateAdminComponent,
    LangueListAdminComponent,
    LangueViewAdminComponent,
    LangueEditAdminComponent,
    ReferentielMetierCreateAdminComponent,
    ReferentielMetierListAdminComponent,
    ReferentielMetierViewAdminComponent,
    ReferentielMetierEditAdminComponent,
    InscriptionCreateAdminComponent,
    InscriptionListAdminComponent,
    InscriptionViewAdminComponent,
    InscriptionEditAdminComponent,
    NiveauLangueCreateAdminComponent,
    NiveauLangueListAdminComponent,
    NiveauLangueViewAdminComponent,
    NiveauLangueEditAdminComponent,
    MetierCreateAdminComponent,
    MetierListAdminComponent,
    MetierViewAdminComponent,
    MetierEditAdminComponent,
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
        AccompagnementAdminModule,


    ],
  exports: [
  EtatInscriptionCreateAdminComponent,
  EtatInscriptionListAdminComponent,
  EtatInscriptionViewAdminComponent,
  EtatInscriptionEditAdminComponent,
  LangueCreateAdminComponent,
  LangueListAdminComponent,
  LangueViewAdminComponent,
  LangueEditAdminComponent,
  ReferentielMetierCreateAdminComponent,
  ReferentielMetierListAdminComponent,
  ReferentielMetierViewAdminComponent,
  ReferentielMetierEditAdminComponent,
  InscriptionCreateAdminComponent,
  InscriptionListAdminComponent,
  InscriptionViewAdminComponent,
  InscriptionEditAdminComponent,
  NiveauLangueCreateAdminComponent,
  NiveauLangueListAdminComponent,
  NiveauLangueViewAdminComponent,
  NiveauLangueEditAdminComponent,
  MetierCreateAdminComponent,
  MetierListAdminComponent,
  MetierViewAdminComponent,
  MetierEditAdminComponent,
  ],
})
export class ProfilAdminModule { }
