import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AuthGuard} from 'src/app/zynerator/security/guards/auth.guard';

import { ActivationCollaborateurComponent } from './activation-collaborateur/activation-collaborateur.component';
import { LoginCollaborateurComponent } from './login-collaborateur/login-collaborateur.component';
import { RegisterCollaborateurComponent } from './register-collaborateur/register-collaborateur.component';
import { ForgetPasswordCollaborateurComponent } from './forget-password-collaborateur/forget-password-collaborateur.component';
import { ChangePasswordCollaborateurComponent } from './change-password-collaborateur/change-password-collaborateur.component';

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
                                    component: LoginCollaborateurComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'register',
                            children: [
                                {
                                    path: '',
                                    component: RegisterCollaborateurComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },{
                            path: 'forget-password',
                            children: [
                                {
                                    path: '',
                                    component: ForgetPasswordCollaborateurComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },{
                            path: 'change-password',
                            children: [
                                {
                                    path: '',
                                    component: ChangePasswordCollaborateurComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'activation',
                            children: [
                                {
                                    path: '',
                                    component: ActivationCollaborateurComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'profil',
                            loadChildren: () => import('./view/profil/profil-collaborateur-routing.module').then(x => x.ProfilCollaborateurRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'accompagnement',
                            loadChildren: () => import('./view/accompagnement/accompagnement-collaborateur-routing.module').then(x => x.AccompagnementCollaborateurRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'transaction',
                            loadChildren: () => import('./view/transaction/transaction-collaborateur-routing.module').then(x => x.TransactionCollaborateurRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'utilisateurs',
                            loadChildren: () => import('./view/utilisateurs/utilisateurs-collaborateur-routing.module').then(x => x.UtilisateursCollaborateurRoutingModule),
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
export class CollaborateurRoutingModule { }
