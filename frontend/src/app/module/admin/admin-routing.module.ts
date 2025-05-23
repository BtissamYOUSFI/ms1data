import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AuthGuard} from 'src/app/zynerator/security/guards/auth.guard';

import { ActivationAdminComponent } from './activation-admin/activation-admin.component';
import { LoginAdminComponent } from './login-admin/login-admin.component';
import { RegisterAdminComponent } from './register-admin/register-admin.component';
import { ForgetPasswordAdminComponent } from './forget-password-admin/forget-password-admin.component';
import { ChangePasswordAdminComponent } from './change-password-admin/change-password-admin.component';
import {ProfileComponent} from "./profile/profile.component";

@NgModule({
    imports: [
        RouterModule.forChild(
            [
                {
                    path: '',
                    children: [
                        {
                            path: 'login',
                            children: [
                                {
                                    path: '',
                                    component: LoginAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'register',
                            children: [
                                {
                                    path: '',
                                    component: RegisterAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },{
                            path: 'forget-password',
                            children: [
                                {
                                    path: '',
                                    component: ForgetPasswordAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },{
                            path: 'change-password',
                            children: [
                                {
                                    path: '',
                                    component: ChangePasswordAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'profile',
                            children: [
                                {
                                    path: '',
                                    component: ProfileComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },
                        {
                            path: 'activation',
                            children: [
                                {
                                    path: '',
                                    component: ActivationAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'profil',
                            loadChildren: () => import('./view/profil/profil-admin-routing.module').then(x => x.ProfilAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'accompagnement',
                            loadChildren: () => import('./view/accompagnement/accompagnement-admin-routing.module').then(x => x.AccompagnementAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'transaction',
                            loadChildren: () => import('./view/transaction/transaction-admin-routing.module').then(x => x.TransactionAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'utilisateurs',
                            loadChildren: () => import('./view/utilisateurs/utilisateurs-admin-routing.module').then(x => x.UtilisateursAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'security',
                            loadChildren: () => import('../security/security-routing.module').then(x => x.SecurityRoutingModule),
                            canActivate: [AuthGuard],
                        }
                    ]
                },
            ]
        ),
    ],
    exports: [RouterModule],
})
export class AdminRoutingModule { }
