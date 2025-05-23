import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {ToastModule} from 'primeng/toast';
import {ToolbarModule} from 'primeng/toolbar';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {InputSwitchModule} from 'primeng/inputswitch';
import {InputTextareaModule} from 'primeng/inputtextarea';

import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DialogModule } from 'primeng/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {CalendarModule} from 'primeng/calendar';
import {PanelModule} from 'primeng/panel';
import {InputNumberModule} from 'primeng/inputnumber';
import {BadgeModule} from 'primeng/badge';
import { MultiSelectModule } from 'primeng/multiselect';

import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {TabViewModule} from 'primeng/tabview';
import { SplitButtonModule } from 'primeng/splitbutton';
import { MessageModule } from 'primeng/message';
import {MessagesModule} from 'primeng/messages';

import { LoginCollaborateurComponent } from './login-collaborateur/login-collaborateur.component';
import { RegisterCollaborateurComponent } from './register-collaborateur/register-collaborateur.component';
import { ChangePasswordCollaborateurComponent } from './change-password-collaborateur/change-password-collaborateur.component';
import { ActivationCollaborateurComponent } from './activation-collaborateur/activation-collaborateur.component';
import { ForgetPasswordCollaborateurComponent } from './forget-password-collaborateur/forget-password-collaborateur.component';


import { ProfilCollaborateurModule } from './view/profil/profil-collaborateur.module';
import { ProfilCollaborateurRoutingModule } from './view/profil/profil-collaborateur-routing.module';
import { AccompagnementCollaborateurModule } from './view/accompagnement/accompagnement-collaborateur.module';
import { AccompagnementCollaborateurRoutingModule } from './view/accompagnement/accompagnement-collaborateur-routing.module';
import { TransactionCollaborateurModule } from './view/transaction/transaction-collaborateur.module';
import { TransactionCollaborateurRoutingModule } from './view/transaction/transaction-collaborateur-routing.module';
import { UtilisateursCollaborateurModule } from './view/utilisateurs/utilisateurs-collaborateur.module';
import { UtilisateursCollaborateurRoutingModule } from './view/utilisateurs/utilisateurs-collaborateur-routing.module';

import {SecurityModule} from 'src/app/module/security/security.module';
import {SecurityRoutingModule} from 'src/app/module/security/security-routing.module';



@NgModule({
  declarations: [
   LoginCollaborateurComponent,
   RegisterCollaborateurComponent,
   ChangePasswordCollaborateurComponent,
   ActivationCollaborateurComponent,
   ForgetPasswordCollaborateurComponent,
      LoginCollaborateurComponent
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
  ProfilCollaborateurModule,
  ProfilCollaborateurRoutingModule,
  AccompagnementCollaborateurModule,
  AccompagnementCollaborateurRoutingModule,
  TransactionCollaborateurModule,
  TransactionCollaborateurRoutingModule,
  UtilisateursCollaborateurModule,
  UtilisateursCollaborateurRoutingModule,
   SecurityModule,
   SecurityRoutingModule
  ],
  exports: [
    LoginCollaborateurComponent,
    RegisterCollaborateurComponent,
    ChangePasswordCollaborateurComponent,
    ActivationCollaborateurComponent,
    ForgetPasswordCollaborateurComponent,

    ProfilCollaborateurModule,
    AccompagnementCollaborateurModule,
    TransactionCollaborateurModule,
    UtilisateursCollaborateurModule,
    SecurityModule,

  ],

})
export class CollaborateurModule { }
