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
import { LoginManagerComponent } from './login-manager/login-manager.component';
import { RegisterManagerComponent } from './register-manager/register-manager.component';
import { ChangePasswordManagerComponent } from './change-password-manager/change-password-manager.component';
import { ActivationManagerComponent } from './activation-manager/activation-manager.component';
import { ForgetPasswordManagerComponent } from './forget-password-manager/forget-password-manager.component';


import { ProfilManagerModule } from './view/profil/profil-manager.module';
import { ProfilManagerRoutingModule } from './view/profil/profil-manager-routing.module';
import { AccompagnementManagerModule } from './view/accompagnement/accompagnement-manager.module';
import { AccompagnementManagerRoutingModule } from './view/accompagnement/accompagnement-manager-routing.module';
import { TransactionManagerModule } from './view/transaction/transaction-manager.module';
import { TransactionManagerRoutingModule } from './view/transaction/transaction-manager-routing.module';
import { UtilisateursManagerModule } from './view/utilisateurs/utilisateurs-manager.module';
import { UtilisateursManagerRoutingModule } from './view/utilisateurs/utilisateurs-manager-routing.module';

import {SecurityModule} from 'src/app/module/security/security.module';
import {SecurityRoutingModule} from 'src/app/module/security/security-routing.module';


@NgModule({
  declarations: [
   LoginManagerComponent,
   RegisterManagerComponent,
   ChangePasswordManagerComponent,
   ActivationManagerComponent,
   ForgetPasswordManagerComponent
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
      BrowserAnimationsModule,
    CalendarModule,
    PanelModule,
    MessageModule,
    MessagesModule,
    InputNumberModule,
    BadgeModule,
      InputTextareaModule,
    MultiSelectModule,
  ProfilManagerModule,
  ProfilManagerRoutingModule,
  AccompagnementManagerModule,
  AccompagnementManagerRoutingModule,
  TransactionManagerModule,
  TransactionManagerRoutingModule,
  UtilisateursManagerModule,
  UtilisateursManagerRoutingModule,
   SecurityModule,
   SecurityRoutingModule
  ],
  exports: [
    LoginManagerComponent,
    RegisterManagerComponent,
    ChangePasswordManagerComponent,
    ActivationManagerComponent,
    ForgetPasswordManagerComponent,

    ProfilManagerModule,
    AccompagnementManagerModule,
    TransactionManagerModule,
    UtilisateursManagerModule,
    SecurityModule
  ],

})
export class ManagerModule { }
