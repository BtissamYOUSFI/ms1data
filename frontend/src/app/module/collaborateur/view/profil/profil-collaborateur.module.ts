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

import { EtatInscriptionCreateCollaborateurComponent } from './etat-inscription/create/etat-inscription-create-collaborateur.component';
import { EtatInscriptionEditCollaborateurComponent } from './etat-inscription/edit/etat-inscription-edit-collaborateur.component';
import { EtatInscriptionViewCollaborateurComponent } from './etat-inscription/view/etat-inscription-view-collaborateur.component';
import { EtatInscriptionListCollaborateurComponent } from './etat-inscription/list/etat-inscription-list-collaborateur.component';
import { LangueCreateCollaborateurComponent } from './langue/create/langue-create-collaborateur.component';
import { LangueEditCollaborateurComponent } from './langue/edit/langue-edit-collaborateur.component';
import { LangueViewCollaborateurComponent } from './langue/view/langue-view-collaborateur.component';
import { LangueListCollaborateurComponent } from './langue/list/langue-list-collaborateur.component';
import { ReferentielMetierCreateCollaborateurComponent } from './referentiel-metier/create/referentiel-metier-create-collaborateur.component';
import { ReferentielMetierEditCollaborateurComponent } from './referentiel-metier/edit/referentiel-metier-edit-collaborateur.component';
import { ReferentielMetierViewCollaborateurComponent } from './referentiel-metier/view/referentiel-metier-view-collaborateur.component';
import { ReferentielMetierListCollaborateurComponent } from './referentiel-metier/list/referentiel-metier-list-collaborateur.component';
import { InscriptionCreateCollaborateurComponent } from './inscription/create/inscription-create-collaborateur.component';
import { InscriptionEditCollaborateurComponent } from './inscription/edit/inscription-edit-collaborateur.component';
import { InscriptionViewCollaborateurComponent } from './inscription/view/inscription-view-collaborateur.component';
import { InscriptionListCollaborateurComponent } from './inscription/list/inscription-list-collaborateur.component';
import { NiveauLangueCreateCollaborateurComponent } from './niveau-langue/create/niveau-langue-create-collaborateur.component';
import { NiveauLangueEditCollaborateurComponent } from './niveau-langue/edit/niveau-langue-edit-collaborateur.component';
import { NiveauLangueViewCollaborateurComponent } from './niveau-langue/view/niveau-langue-view-collaborateur.component';
import { NiveauLangueListCollaborateurComponent } from './niveau-langue/list/niveau-langue-list-collaborateur.component';
import { MetierCreateCollaborateurComponent } from './metier/create/metier-create-collaborateur.component';
import { MetierEditCollaborateurComponent } from './metier/edit/metier-edit-collaborateur.component';
import { MetierViewCollaborateurComponent } from './metier/view/metier-view-collaborateur.component';
import { MetierListCollaborateurComponent } from './metier/list/metier-list-collaborateur.component';

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

    EtatInscriptionCreateCollaborateurComponent,
    EtatInscriptionListCollaborateurComponent,
    EtatInscriptionViewCollaborateurComponent,
    EtatInscriptionEditCollaborateurComponent,
    LangueCreateCollaborateurComponent,
    LangueListCollaborateurComponent,
    LangueViewCollaborateurComponent,
    LangueEditCollaborateurComponent,
    ReferentielMetierCreateCollaborateurComponent,
    ReferentielMetierListCollaborateurComponent,
    ReferentielMetierViewCollaborateurComponent,
    ReferentielMetierEditCollaborateurComponent,
    InscriptionCreateCollaborateurComponent,
    InscriptionListCollaborateurComponent,
    InscriptionViewCollaborateurComponent,
    InscriptionEditCollaborateurComponent,
    NiveauLangueCreateCollaborateurComponent,
    NiveauLangueListCollaborateurComponent,
    NiveauLangueViewCollaborateurComponent,
    NiveauLangueEditCollaborateurComponent,
    MetierCreateCollaborateurComponent,
    MetierListCollaborateurComponent,
    MetierViewCollaborateurComponent,
    MetierEditCollaborateurComponent,
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
  EtatInscriptionCreateCollaborateurComponent,
  EtatInscriptionListCollaborateurComponent,
  EtatInscriptionViewCollaborateurComponent,
  EtatInscriptionEditCollaborateurComponent,
  LangueCreateCollaborateurComponent,
  LangueListCollaborateurComponent,
  LangueViewCollaborateurComponent,
  LangueEditCollaborateurComponent,
  ReferentielMetierCreateCollaborateurComponent,
  ReferentielMetierListCollaborateurComponent,
  ReferentielMetierViewCollaborateurComponent,
  ReferentielMetierEditCollaborateurComponent,
  InscriptionCreateCollaborateurComponent,
  InscriptionListCollaborateurComponent,
  InscriptionViewCollaborateurComponent,
  InscriptionEditCollaborateurComponent,
  NiveauLangueCreateCollaborateurComponent,
  NiveauLangueListCollaborateurComponent,
  NiveauLangueViewCollaborateurComponent,
  NiveauLangueEditCollaborateurComponent,
  MetierCreateCollaborateurComponent,
  MetierListCollaborateurComponent,
  MetierViewCollaborateurComponent,
  MetierEditCollaborateurComponent,
  ],
})
export class ProfilCollaborateurModule { }
