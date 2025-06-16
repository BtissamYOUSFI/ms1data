import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AuthGuard} from 'src/app/zynerator/security/guards/auth.guard';
import { ActivationManagerComponent } from './activation-manager/activation-manager.component';
import { LoginManagerComponent } from './login-manager/login-manager.component';
import { RegisterManagerComponent } from './register-manager/register-manager.component';
import { ForgetPasswordManagerComponent } from './forget-password-manager/forget-password-manager.component';
import { ChangePasswordManagerComponent } from './change-password-manager/change-password-manager.component';

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
                                    component: LoginManagerComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'register',
                            children: [
                                {
                                    path: '',
                                    component: RegisterManagerComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },{
                            path: 'forget-password',
                            children: [
                                {
                                    path: '',
                                    component: ForgetPasswordManagerComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },{
                            path: 'change-password',
                            children: [
                                {
                                    path: '',
                                    component: ChangePasswordManagerComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'activation',
                            children: [
                                {
                                    path: '',
                                    component: ActivationManagerComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'profil',
                            loadChildren: () => import('./view/profil/profil-manager-routing.module').then(x => x.ProfilManagerRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'accompagnement',
                            loadChildren: () => import('./view/accompagnement/accompagnement-manager-routing.module').then(x => x.AccompagnementManagerRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'transaction',
                            loadChildren: () => import('./view/transaction/transaction-manager-routing.module').then(x => x.TransactionManagerRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'utilisateurs',
                            loadChildren: () => import('./view/utilisateurs/utilisateurs-manager-routing.module').then(x => x.UtilisateursManagerRoutingModule),
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
export class ManagerRoutingModule { }
