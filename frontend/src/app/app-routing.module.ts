import {RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';
import {AuthGuard} from 'src/app/zynerator/security/guards/auth.guard';
import {AppLayoutComponent} from 'src/app/layout/app.layout.component';

import {HomePublicComponent} from 'src/app/public/home/home-public.component';

import {LoginAdminComponent} from 'src/app/module/admin/login-admin/login-admin.component';
import {RegisterAdminComponent} from 'src/app/module/admin/register-admin/register-admin.component';
import {ChangePasswordAdminComponent} from 'src/app/module/admin/change-password-admin/change-password-admin.component';
import {LoginCollaborateurComponent} from 'src/app/module/collaborateur/login-collaborateur/login-collaborateur.component';
import {RegisterCollaborateurComponent} from 'src/app/module/collaborateur/register-collaborateur/register-collaborateur.component';
import {ChangePasswordCollaborateurComponent} from 'src/app/module/collaborateur/change-password-collaborateur/change-password-collaborateur.component';

@NgModule({
    imports: [
        RouterModule.forRoot(
            [
                {path: '', component: HomePublicComponent},
                {path: 'admin/login', component: LoginAdminComponent },
                {path: 'admin/register', component: RegisterAdminComponent },
                {path: 'admin/changePassword', component: ChangePasswordAdminComponent },
                {path: 'collaborateur/login', component: LoginCollaborateurComponent },
                {path: 'collaborateur/register', component: RegisterCollaborateurComponent },
                {path: 'collaborateur/changePassword', component: ChangePasswordCollaborateurComponent },
                {
                    path: 'app',
                    component: AppLayoutComponent,
                    children: [
                        {
                            path: 'admin',
                            loadChildren: () => import( './module/admin/admin-routing.module').then(x => x.AdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'collaborateur',
                            loadChildren: () => import( './module/collaborateur/collaborateur-routing.module').then(x => x.CollaborateurRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'manager',
                            loadChildren: () => import( './module/manager/manager-routing.module').then(x => x.ManagerRoutingModule),
                            canActivate: [AuthGuard],
                        },

                    ],
                    canActivate: [AuthGuard]
                },
            ],
                { scrollPositionRestoration: 'enabled' }
            ),
        ],
    exports: [RouterModule],
    })
export class AppRoutingModule { }
