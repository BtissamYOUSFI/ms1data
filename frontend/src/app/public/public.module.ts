import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ToastModule} from 'primeng/toast';
import {ToolbarModule} from 'primeng/toolbar';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {InputSwitchModule} from 'primeng/inputswitch';
import {InputTextareaModule} from 'primeng/inputtextarea';

import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {DialogModule} from 'primeng/dialog';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {CalendarModule} from 'primeng/calendar';
import {PanelModule} from 'primeng/panel';
import {InputNumberModule} from 'primeng/inputnumber';
import {BadgeModule} from 'primeng/badge';
import {MultiSelectModule} from 'primeng/multiselect';

import {PasswordModule} from 'primeng/password';
import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {TabViewModule} from 'primeng/tabview';
import {SplitButtonModule} from 'primeng/splitbutton';
import {MessageModule} from 'primeng/message';
import {MessagesModule} from 'primeng/messages';
// import {PreloadpageComponent} from './preloadpage/preloadpage.component';
// import {MainComponent} from './main/main.component';
// import {StepsComponent} from './main/steps/steps.component';
// import {FrameworksComponent} from './main/frameworks/frameworks.component';
// import {FooterComponent} from './footer/footer.component';
import {HomePublicComponent} from './home/home-public.component';
// import {ContactUsComponent} from './contact-us/contact-us.component';
// import {TopComponent} from './top/top.component';
import {CarouselModule} from "primeng/carousel";
import { InscriptionComponent } from './inscription/inscription.component';
import { NavbarComponent } from './navbar/navbar.component';
import {MenubarModule} from "primeng/menubar";
import {RippleModule} from "primeng/ripple";
import {CardModule} from "primeng/card";
import {DividerModule} from "primeng/divider";
import {TranslateModule} from "@ngx-translate/core";
import { VoiceRegistrationComponent } from './voice-registration/voice-registration.component';
import {SelectButtonModule} from "primeng/selectbutton";
import {TagModule} from "primeng/tag";
import { ContactComponent } from './contact/contact.component';
import {RadioButtonModule} from "primeng/radiobutton";
import { LoginComponent } from './login/login.component';
import { RegisterManagerComponent } from './register-manager/register-manager.component';


@NgModule({
    declarations: [
        // PreloadpageComponent,
        // MainComponent,
        // StepsComponent,
        // FrameworksComponent,
        // FooterComponent,
        HomePublicComponent,
        // ContactUsComponent,
        // TopComponent,
        InscriptionComponent,
        NavbarComponent,
        VoiceRegistrationComponent,
        ContactComponent,
        LoginComponent,
        RegisterManagerComponent
    ],
    imports: [
        CarouselModule,
        RippleModule,
        CardModule,
        DividerModule,
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
        CarouselModule,
        MenubarModule,
        TranslateModule,
        SelectButtonModule,
        TagModule,
        RadioButtonModule,
    ],
    exports: [
        // PreloadpageComponent,
        // MainComponent,
        NavbarComponent

    ],

})
export class PublicModule {
}
