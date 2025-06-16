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
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
    providers: [MessageService]
})
export class LoginComponent implements OnInit {
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
        this.router.navigate(['/inscription']);
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
