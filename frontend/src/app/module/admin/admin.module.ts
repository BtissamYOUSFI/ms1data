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

import { LoginAdminComponent } from './login-admin/login-admin.component';
import { RegisterAdminComponent } from './register-admin/register-admin.component';
import { ChangePasswordAdminComponent } from './change-password-admin/change-password-admin.component';
import { ActivationAdminComponent } from './activation-admin/activation-admin.component';
import { ForgetPasswordAdminComponent } from './forget-password-admin/forget-password-admin.component';


import { ProfilAdminModule } from './view/profil/profil-admin.module';
import { ProfilAdminRoutingModule } from './view/profil/profil-admin-routing.module';
import { AccompagnementAdminModule } from './view/accompagnement/accompagnement-admin.module';
import { AccompagnementAdminRoutingModule } from './view/accompagnement/accompagnement-admin-routing.module';
import { TransactionAdminModule } from './view/transaction/transaction-admin.module';
import { TransactionAdminRoutingModule } from './view/transaction/transaction-admin-routing.module';
import { UtilisateursAdminModule } from './view/utilisateurs/utilisateurs-admin.module';
import { UtilisateursAdminRoutingModule } from './view/utilisateurs/utilisateurs-admin-routing.module';

import {SecurityModule} from 'src/app/module/security/security.module';
import {SecurityRoutingModule} from 'src/app/module/security/security-routing.module';
import { ProfileComponent } from './profile/profile.component';
import {CardModule} from "primeng/card";
import {AvatarModule} from "primeng/avatar";
import {ChipModule} from "primeng/chip";


@NgModule({
  declarations: [
   LoginAdminComponent,
   RegisterAdminComponent,
   ChangePasswordAdminComponent,
   ActivationAdminComponent,
   ForgetPasswordAdminComponent,
   ProfileComponent
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
        ProfilAdminModule,
        ProfilAdminRoutingModule,
        AccompagnementAdminModule,
        AccompagnementAdminRoutingModule,
        TransactionAdminModule,
        TransactionAdminRoutingModule,
        UtilisateursAdminModule,
        UtilisateursAdminRoutingModule,
        SecurityModule,
        SecurityRoutingModule,
        CardModule,
        AvatarModule,
        ChipModule
    ],
  exports: [
    LoginAdminComponent,
    RegisterAdminComponent,
    ChangePasswordAdminComponent,
    ActivationAdminComponent,
    ForgetPasswordAdminComponent,

    ProfilAdminModule,
    AccompagnementAdminModule,
    TransactionAdminModule,
    UtilisateursAdminModule,
    SecurityModule
  ],

})
export class AdminModule { }
