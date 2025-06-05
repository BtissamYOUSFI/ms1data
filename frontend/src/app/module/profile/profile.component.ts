import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../../zynerator/security/shared/service/Auth.service";
import {UserDto} from "../../../zynerator/security/shared/model/User.model";
import {MessageService} from 'primeng/api';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.scss'],
    providers: [MessageService]
})
export class ProfileComponent implements OnInit {
    private _user: UserDto;
    private _originalUser: UserDto;
    isEditing: boolean = false;

    constructor(
        private authService: AuthService,
        private messageService: MessageService
    ) {}

    ngOnInit() {
        this.user = {...this.authService.authenticatedUser};
        this._originalUser = {...this.authService.authenticatedUser};
    }

    get user(): UserDto {
        return this._user;
    }

    set user(value: UserDto) {
        this._user = value;
    }

    startEditing() {
        this.isEditing = true;
    }

    cancelEditing() {
        this.user = {...this._originalUser};
        this.isEditing = false;
        this.messageService.add({
            severity: 'info',
            summary: 'Cancelled',
            detail: 'Changes discarded'
        });
    }

    updateProfile() {
        // Here you would typically call your service to update the user
        // For now, we'll just update the original user and show a success message
        this._originalUser = {...this.user};
        this.isEditing = false;

        // Call your API service here
        // this.authService.updateProfile(this.user).subscribe(...)

        this.messageService.add({
            severity: 'success',
            summary: 'Success',
            detail: 'Profile updated successfully'
        });
    }
}
