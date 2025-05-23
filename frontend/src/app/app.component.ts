import { Component, OnInit } from '@angular/core';
import { PrimeNGConfig } from 'primeng/api';
import {RoleService} from "./zynerator/security/shared/service/Role.service";
import {TranslateService} from "@ngx-translate/core";
import {Observable} from "rxjs";
import {NavigationEnd, Router} from "@angular/router";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
    showNavbar = true;
    layoutMode = 'static';
    lightMenu = true;
    topbarColor = 'layout-topbar-blue';
    inlineUser = false;
    isRTL = false;
    inputStyle = 'outlined';
    ripple = true;
    private role$: Observable<string>;

    // constructor(private primengConfig: PrimeNGConfig, public translateService: TranslateService) {
    //     translateService.addLangs(['en', 'fr']);
    //     translateService.setDefaultLang('fr');
    //     const browserLang = translateService.getBrowserLang();
    //     translateService.use(browserLang.match(/en|fr/) ? browserLang : 'fr');
    // }

    constructor(private primengConfig: PrimeNGConfig, private roleService: RoleService, private translateService: TranslateService, private router: Router) {
        translateService.addLangs(['ar','en', 'fr']);
        translateService.setDefaultLang('en');
        const browserLang = translateService.getBrowserLang();
        console.log('browser lang ==>  ' +browserLang);
        translateService.use('en');
        //translateService.use(browserLang.match(/ar|en|fr/) ? browserLang : 'eng');

        this.router.events.subscribe(event => {
            if (event instanceof NavigationEnd) {
                const currentUrl = event.urlAfterRedirects;
                this.showNavbar = !(
                    currentUrl.startsWith('/app/admin') ||
                    currentUrl.startsWith('/app/collaborateur')
                );
            }
        });
    }



    ngOnInit() {
        this.primengConfig.ripple = true;
        this.role$ = this.roleService.role$;
        this.role$.subscribe(role => {
            // if (role.toLowerCase() === 'admin') {
            //     this.topbarColor = 'layout-topbar-green';
            // } else {
            //     this.topbarColor = 'layout-topbar-blue';
            // }

        });
    }
}
