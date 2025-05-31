import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {HomePublicComponent} from 'src/app/public/home/home-public.component';
// import {ContactUsComponent} from 'src/app/public/contact-us/contact-us.component';
import {
    InscriptionCreateCollaborateurComponent
} from "../module/collaborateur/view/profil/inscription/create/inscription-create-collaborateur.component";
import {InscriptionComponent} from "./inscription/inscription.component";
import {VoiceRegistrationComponent} from "./voice-registration/voice-registration.component";

@NgModule({
    imports: [
        RouterModule.forChild(
            [
                {
                    path: '',
                    children: [
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
