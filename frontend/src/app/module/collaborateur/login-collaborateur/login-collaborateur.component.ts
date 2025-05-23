// import { Component, OnInit } from '@angular/core';
// import {Router} from '@angular/router';
// import {  MessageService} from 'primeng/api';
// import {AuthService} from 'src/app/zynerator/security/shared/service/Auth.service';
// import {LayoutService} from 'src/app/layout/service/app.layout.service';
// import {UserDto} from "src/app/zynerator/security/shared/model/User.model";
// import {environment} from "src/environments/environment";
// import { LoginDto } from 'src/app/zynerator/security/shared/model/Login.model';
//
//
//
// @Component({
//   selector: 'app-login-collaborateur',
//   templateUrl: './login-collaborateur.component.html',
//   styleUrls: ['./login-collaborateur.component.css']
// })
// export class LoginCollaborateurComponent implements OnInit {
//     readonly API = environment.loginUrl;
//     googleLoginOptions = {
//       scope: 'profile email',
//       plugin_name: 'sample_login'
//     };
//
//     private _authenticatedUser = new UserDto();
//     loginDto: LoginDto = new LoginDto();
//
//
//     constructor(private authService: AuthService, public layoutService: LayoutService, private router: Router, private messageService: MessageService) { }
//
//     ngOnInit(): void {
//     }
//
//     submit() {
//       if (this.loginDto.username && this.loginDto.password) {
//         this.authService.login(this.loginDto.username, this.loginDto.password);
//       } else {
//         this.messageService.add({
//           severity: 'error',
//           summary: 'Error',
//           detail: 'Fill in all fields'
//         });
//       }
//     }
//
//     public signInWithGoogle(): void {
//       /*
//        this.oauthService.signIn(GoogleLoginProvider.PROVIDER_ID,this.googleLoginOptions).then(
//           data => {
//             this.socialUser = data;
//           const role = new RoleDto();
//           role.authority = 'ROLE_COLLABORATOR' ;
//           const roleUser = new RoleUserDto();
//           roleUser.role = role;
//           this.user.accountNonExpired=true;
//           this.user.accountNonLocked=true;
//           this.user.credentialsNonExpired=true;
//           this.user.enabled=false;
//           this.user.passwordChanged=false;
//           this.user.firstName = data.firstName;
//           this.user.lastName = data.lastName;
//           this.user.phone = null;
//           this.user.username = data.email;
//           this.user.password = Date.now()+"-"+data.email;
//           this.user.email = data.email;
//           this.user.roleUsers = new Array<RoleUserDto>();
//           this.user.roleUsers.push(roleUser);
//             this.authService.loginUsingGoogle();
//           }
//         ).catch(
//           err => {
//             console.log(err);
//           }
//         );
//     */
//     }
//
//     register() {
//       this.router.navigate(['/collaborateur/register']);
//     }
//
//     forget() {
//         this.router.navigate(['/collaborateur/forget-password']);
//     }
//
//
//     get user(): UserDto {
//       return this.authService.user;
//     }
//
//     set user(value: UserDto) {
//       this.authService.user = value;
//     }
//   }

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { AuthService } from 'src/app/zynerator/security/shared/service/Auth.service';
import { LayoutService } from 'src/app/layout/service/app.layout.service';
import { UserDto } from "src/app/zynerator/security/shared/model/User.model";
import { environment } from "src/environments/environment";
import { LoginDto } from 'src/app/zynerator/security/shared/model/Login.model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
    selector: 'app-login-collaborateur',
    templateUrl: './login-collaborateur.component.html',
    styleUrls: ['./login-collaborateur.component.css'],
    providers: [MessageService]
})
export class LoginCollaborateurComponent implements OnInit {
    readonly API = environment.loginUrl;
    googleLoginOptions = {
        scope: 'profile email',
        plugin_name: 'sample_login'
    };

    private _authenticatedUser = new UserDto();
    loginDto: LoginDto = new LoginDto();
    loginForm: FormGroup;
    loading = false;

    constructor(
        private authService: AuthService,
        public layoutService: LayoutService,
        private router: Router,
        private messageService: MessageService,
        private formBuilder: FormBuilder
    ) { }

    ngOnInit(): void {
        this.loginForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', [Validators.required, Validators.minLength(1)]]
        });
    }

    onSubmit(): void {
        if (this.loginForm.invalid) {
            this.messageService.add({
                severity: 'error',
                summary: 'Erreur',
                detail: 'Veuillez remplir correctement tous les champs'
            });
            return;
        }

        this.loading = true;
        this.loginDto.username = this.loginForm.value.username;
        this.loginDto.password = this.loginForm.value.password;

        if (this.loginDto.username && this.loginDto.password) {
            this.authService.login(this.loginDto.username, this.loginDto.password);
            this.loading = false;
        } else {
            this.messageService.add({
                severity: 'error',
                summary: 'Error',
                detail: 'Fill in all fields'
            });
            this.loading = false;
        }
    }

    public signInWithGoogle(): void {
        // Your Google login logic here
    }

    register() {
        this.router.navigate(['/collaborateur/register']);
    }

    forget() {
        this.router.navigate(['/collaborateur/forget-password']);
    }

    get user(): UserDto {
        return this.authService.user;
    }

    set user(value: UserDto) {
        this.authService.user = value;
    }
}
