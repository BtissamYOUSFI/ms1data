import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { LayoutService } from './service/app.layout.service';
import {RoleService} from "../zynerator/security/shared/service/Role.service";
import {AppComponent} from "../app.component";
import {AuthService} from "../zynerator/security/shared/service/Auth.service";
import {Router} from "@angular/router";
import {AppLayoutComponent} from "./app.layout.component";
import Swal from 'sweetalert2';

@Component({
  selector: 'app-menu',
  templateUrl: './app.menu.component.html'
})
export class AppMenuComponent implements OnInit {
  model: any[];
  modelanonymous: any[];
    modelAdmin: any[];
  modelCollaborateur: any[];
constructor(public layoutService: LayoutService, public app: AppComponent, public appMain: AppLayoutComponent, private roleService: RoleService, private authService: AuthService, private router: Router) { }
  ngOnInit() {
    this.modelAdmin =
      [

				{
                    // label: 'Pages',
                    icon: 'pi pi-fw pi-briefcase',
                    items: [
					  {
						label: 'Collaborators',
						icon: 'pi pi-user',
						items: [
								  {
									label: 'Liste collaborateur',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/utilisateurs/collaborateur/list']
								  },
						]
					  },
					  {
						label: 'Paiements',
						icon: 'pi pi-credit-card',
						items: [
								  {
									label: 'Liste status paiement',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/transaction/status-paiement/list']
								  },
								  {
									label: 'Liste paiement',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/transaction/paiement/list']
								  },
								  {
									label: 'Liste moyen paiement',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/transaction/moyen-paiement/list']
								  },
						]
					  },
					  {
						label: 'Inscriptions',
						icon: 'pi pi-user-plus',
						items: [
								  {
									label: 'Liste inscription',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/profil/inscription/list']
								  },
						]
					  },
					  {
						label: 'User Profile',
						icon: 'pi pi-cog',
						items: [
								  {
									label: 'Liste etat inscription',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/profil/etat-inscription/list']
								  },
								  {
									label: 'Liste langue',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/profil/langue/list']
								  },
								  {
									label: 'Liste referentiel metier',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/profil/referentiel-metier/list']
								  },
								  {
									label: 'Liste niveau langue',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/profil/niveau-langue/list']
								  },
								  {
									label: 'Liste metier',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/profil/metier/list']
								  },
						]
					  },
					  {
						label: 'Meetings',
						icon: 'pi pi-calendar',
						items: [
								  {
									label: 'Liste etat reunion',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/accompagnement/etat-reunion/list']
								  },
								  {
									label: 'Liste reunion',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/accompagnement/reunion/list']
								  },
						]
					  },
                        {
                            label: 'Template Email',
                            icon: 'pi pi-envelope',
                            items: [
                                {
                                    label: 'Liste Template Email Manager',
                                    icon: 'pi pi-fw pi-plus-circle',
                                    routerLink: ['/app/admin/accompagnement/template-email-manager/list']
                                },
                                {
                                    label: 'Liste Template Email Collaborator',
                                    icon: 'pi pi-fw pi-plus-circle',
                                    routerLink: ['/app/admin/accompagnement/template-email-collaborator/list']
                                },
                            ]
                        },

				   {
					  label: 'Security',
					  icon: 'pi pi-lock',
					  items: [
						  {
							  label: 'List User',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/user/list']
						  },
						  {
							  label: 'List Model',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/model-permission/list']
						  },
						  {
							  label: 'List Action Permission',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/action-permission/list']
						  },
					  ]
				  },
                        // {
                        //     label: 'Log out',
                        //     icon: 'pi pi-fw pi-sign-in',
                        //     styleClass: 'logout-menu-item',
                        //     command: () => {
                        //         this.authService.logout();
                        //     }
                        // }
			]
	    }
    ];
    this.modelCollaborateur =
      [

				{
                    // label: 'Pages',
                    icon: 'pi pi-fw pi-briefcase',
                    items: [
					  // {
						// label: 'Gestion des Collaborateurs',
						// icon: 'pi pi-wallet',
						// items: [
						// 		  {
						// 			label: 'Liste collaborateur',
						// 			icon: 'pi pi-fw pi-plus-circle',
						// 			routerLink: ['/app/collaborateur/utilisateurs/collaborateur/list']
						// 		  },
						// ]
					  // },
					  {
						label: 'Paiements',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste status paiement',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/collaborateur/transaction/status-paiement/list']
								  },
								  {
									label: 'Liste paiement',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/collaborateur/transaction/paiement/list']
								  },
								  {
									label: 'Liste moyen paiement',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/collaborateur/transaction/moyen-paiement/list']
								  },
						]
					  },
					  {
						label: 'Gestion des Inscriptions',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste inscription',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/collaborateur/profil/inscription/list']
								  },
						]
					  },
					  {
						label: 'Profil Utilisateur',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste etat inscription',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/collaborateur/profil/etat-inscription/list']
								  },
								  {
									label: 'Liste langue',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/collaborateur/profil/langue/list']
								  },
								  {
									label: 'Liste referentiel metier',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/collaborateur/profil/referentiel-metier/list']
								  },
								  {
									label: 'Liste niveau langue',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/collaborateur/profil/niveau-langue/list']
								  },
								  {
									label: 'Liste metier',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/collaborateur/profil/metier/list']
								  },
						]
					  },
					  {
						label: 'Gestion des Réunions',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste etat reunion',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/collaborateur/accompagnement/etat-reunion/list']
								  },
								  {
									label: 'Liste reunion',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/collaborateur/accompagnement/reunion/list']
								  },
						]
					  },

				   {
					  label: 'Security Management',
					  icon: 'pi pi-wallet',
					  items: [
						  {
							  label: 'List User',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/user/list']
						  },
						  {
							  label: 'List Model',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/model-permission/list']
						  },
						  {
							  label: 'List Action Permission',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/action-permission/list']
						  },
					  ]
				  }
			]
	    }
    ];

        if (this.authService.authenticated) {
            if (this.authService.authenticatedUser.roleUsers) {
              this.authService.authenticatedUser.roleUsers.forEach(role => {
                  const roleName: string = this.getRole(role.role.authority);
                  this.roleService._role.next(roleName.toUpperCase());
                  eval('this.model = this.model' + this.getRole(role.role.authority));
              })
            }
        }
  }

    getRole(text){
        const [role, ...rest] = text.split('_');
        return this.upperCaseFirstLetter(rest.join(''));
    }

    upperCaseFirstLetter(word: string) {
      if (!word) { return word; }
      return word[0].toUpperCase() + word.substr(1).toLowerCase();
    }

    onMenuClick(event) {
        this.appMain.onMenuClick(event);
    }

    public logout(): void {
        Swal.fire({
            title: 'Are you sure?',
            text: "You are about to logout.",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, logout'
        }).then((result) => {
            if (result.isConfirmed) {
                this.authService.logout();
            }
        });
    }

}
