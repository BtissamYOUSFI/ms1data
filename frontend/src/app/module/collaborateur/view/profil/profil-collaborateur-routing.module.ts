
// const root = environment.rootAppUrl;



import {UserListComponent} from 'src/app/module/security/user/list/user-list.component';
import {ModelPermissionListComponent} from 'src/app/module/security/model-permission/list/model-permission-list.component';
import {ActionPermissionListComponent} from 'src/app/module/security/action-permission/list/action-permission-list.component';
import {ModelPermissionUserListComponent} from 'src/app/module/security/model-permission-utilisateur/list/model-permission-user-list.component';
import {RoleListComponent} from 'src/app/module/security/role/list/role-list.component';



import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AuthGuard} from 'src/app/zynerator/security/guards/auth.guard';



import { EtatInscriptionListCollaborateurComponent } from './etat-inscription/list/etat-inscription-list-collaborateur.component';
import { LangueListCollaborateurComponent } from './langue/list/langue-list-collaborateur.component';
import { ReferentielMetierListCollaborateurComponent } from './referentiel-metier/list/referentiel-metier-list-collaborateur.component';
import { InscriptionListCollaborateurComponent } from './inscription/list/inscription-list-collaborateur.component';
import { NiveauLangueListCollaborateurComponent } from './niveau-langue/list/niveau-langue-list-collaborateur.component';
import { MetierListCollaborateurComponent } from './metier/list/metier-list-collaborateur.component';
import {InscriptionCreateCollaborateurComponent} from "./inscription/create/inscription-create-collaborateur.component";
@NgModule({
    imports: [
        RouterModule.forChild(
            [
                {
                    path: '',
                    children: [
                        {

                            path: 'action-permission',
                            children: [
                                {
                                    path: 'list',
                                    component: ActionPermissionListComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'model-permission-user',
                            children: [
                                {
                                    path: 'list',
                                    component: ModelPermissionUserListComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },
                        {

                            path: 'role',
                            children: [
                                {
                                    path: 'list',
                                    component: RoleListComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },
                        {

                            path: 'user',
                            children: [
                                {
                                    path: 'list',
                                    component: UserListComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'model-permission',
                            children: [
                                {
                                    path: 'list',
                                    component: ModelPermissionListComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },


                        {

                            path: 'etat-inscription',
                            children: [
                                {
                                    path: 'list',
                                    component: EtatInscriptionListCollaborateurComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'langue',
                            children: [
                                {
                                    path: 'list',
                                    component: LangueListCollaborateurComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'referentiel-metier',
                            children: [
                                {
                                    path: 'list',
                                    component: ReferentielMetierListCollaborateurComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {
                            path: 'inscription',
                            children: [
                                {
                                    path: 'create',
                                    component: InscriptionCreateCollaborateurComponent ,
                                    canActivate: [AuthGuard]
                                },
                                {
                                    path: 'list',
                                    component: InscriptionListCollaborateurComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'niveau-langue',
                            children: [
                                {
                                    path: 'list',
                                    component: NiveauLangueListCollaborateurComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                        {

                            path: 'metier',
                            children: [
                                {
                                    path: 'list',
                                    component: MetierListCollaborateurComponent ,
                                    canActivate: [AuthGuard]
                                }
                            ]
                        },

                    ]
                },
            ]
        ),
    ],
    exports: [RouterModule],
})
export class ProfilCollaborateurRoutingModule { }
