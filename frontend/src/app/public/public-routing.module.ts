import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {HomePublicComponent} from 'src/app/public/home/home-public.component';
// import {ContactUsComponent} from 'src/app/public/contact-us/contact-us.component';
import {
    InscriptionCreateCollaborateurComponent
} from "../module/collaborateur/view/profil/inscription/create/inscription-create-collaborateur.component";
import {InscriptionComponent} from "./inscription/inscription.component";
import {VoiceRegistrationComponent} from "./voice-registration/voice-registration.component";
import {ContactComponent} from "./contact/contact.component";
import {LoginComponent} from "./login/login.component";
import {RegisterManagerComponent} from "./register-manager/register-manager.component";

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
                                    component: LoginComponent,
                                }
                            ]
                        },
                        {
                            path: 'contact',
                            children: [
                                {
                                    path: '',
                                    component: ContactComponent,
                                }
                            ]
                        },
                        {
                            path: 'inscription',
                            children: [
                                {
                                    path: '',
                                    component: InscriptionComponent,
                                }
                            ]
                        },
                        {
                            path: 'inscription_manager',
                            children: [
                                {
                                    path: '',
                                    component: RegisterManagerComponent,
                                }
                            ]
                        },
                        {
                            path: 'voiceRegistration',
                            children: [
                                {
                                    path: '',
                                    component: VoiceRegistrationComponent,
                                }
                            ]
                        },
                        {
                            path: 'home',
                            children: [
                                {
                                    path: '',
                                    component: HomePublicComponent,
                                }
                            ]
                        },
                        // {
                        //     path: 'contact',
                        //     children: [
                        //         {
                        //             path: '',
                        //             component: ContactUsComponent,
                        //         }
                        //     ]
                        // }
                    ]
                },
            ]
        ),
    ],
    exports: [RouterModule],
})
export class PublicRoutingModule {
}
