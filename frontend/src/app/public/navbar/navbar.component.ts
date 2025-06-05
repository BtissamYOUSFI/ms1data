import { Component, OnInit } from '@angular/core';
import { MenuItem, SelectItem } from 'primeng/api';
import {TranslateService} from "@ngx-translate/core";
import {Router} from "@angular/router";

@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
    items: MenuItem[];
    languageOptions: SelectItem[];
    selectedLanguage: string ;

    scrollToSection(sectionId: string) {
        const element = document.getElementById(sectionId);
        if (element) {
            element.scrollIntoView({ behavior: 'smooth' });
        }
    }

    constructor(private translateService: TranslateService) {
        this.items = [];
        this.languageOptions = [
            { label: '🇬🇧 English', value: 'en' },
            { label: '🇫🇷 Français', value: 'fr' },
            { label: '🇲🇦 العربية', value: 'ar' }
        ];
    }

    ngOnInit() {
        this.items = [
            {
                label: 'Home',
                icon: 'pi pi-home',
                routerLink: ['/']
            },
            {
                label: 'Services',
                icon: 'pi pi-briefcase'
            },
            {
                label: 'About Us',
                icon: 'pi pi-info-circle'
            },
            {
                label: 'Contact',
                icon: 'pi pi-envelope'
            }
        ];
    }

    useLanguage(language: string): void {
        this.translateService.use(language);
    }
}
