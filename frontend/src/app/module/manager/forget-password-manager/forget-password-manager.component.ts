import { Component } from '@angular/core';
import { MessageService } from 'primeng/api';
import { AuthService } from 'src/app/zynerator/security/shared/service/Auth.service';
import {LayoutService} from "../../../layout/service/app.layout.service";
import { Router } from '@angular/router';
import { ForgetPassword } from 'src/app/zynerator/security/shared/model/ForgetPassword.model';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
    selector: 'app-forget-password-manager',
    standalone: false,
    templateUrl: './forget-password-manager.component.html',
    styleUrls: ['./forget-password-manager.component.css'],
})
export class ForgetPasswordManagerComponent {
    forgetPasswordDto = new ForgetPassword();
    active: boolean = false;

    constructor(
        private authService: AuthService,
        public layoutService: LayoutService,
        private router: Router,
        private messageService: MessageService,
    ) {
    }

    ngOnInit(): void {
    }

    submit() {
        this.authService.sendForgetPasswordActivation(this.forgetPasswordDto.email).subscribe({
            next: () => {
                this.messageService.add({
                    severity: 'success',
                    summary: 'Activation Code Sent',
                    detail: 'Check your email for the activation code.'
                });
                this.router.navigate(['manager/changePassword'])
            },
            error: (error: HttpErrorResponse) => {
                let errorMessage = 'Error resending activation code';
                if (
                    error.status === 400 &&
                    error.error.message.includes('Max forget password requests send code reached')
                ) {
                    if (error.error.retryAfterSeconds) {
                        const retryAfterSeconds = error.error.retryAfterSeconds;
                        const hours = Math.floor(retryAfterSeconds / 3600);
                        const minutes = Math.floor((retryAfterSeconds % 3600) / 60);
                        const seconds = retryAfterSeconds % 60;
                        errorMessage = `Max forget password requests reached. Please try again in ${hours} hours, ${minutes} minutes, and ${seconds} seconds.`;
                    } else {
                        errorMessage = 'Max forget password requests reached. Please try again later.';
                    }
                } else if (error.error && error.error.message) {
                    errorMessage = error.error.message;
                }
                this.messageService.add({
                    severity: 'error',
                    summary: 'Error',
                    detail: errorMessage
                });
            }
        });
    }

    clearMessages() {
        this.messageService.clear();
    }
}
