import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';

interface ContactInfo {
    icon: string;
    title: string;
    content: string;
    link?: string;
}

interface OfficeLocation {
    city: string;
    address: string;
    phone: string;
    email: string;
    hours: string;
}

@Component({
    selector: 'app-contact',
    templateUrl: './contact.component.html',
    styleUrls: ['./contact.component.scss'],
    providers: [MessageService]
})
export class ContactComponent implements OnInit {

    contactForm: FormGroup;
    loading = false;

    contactInfo: ContactInfo[] = [
        {
            icon: 'pi pi-phone',
            title: 'Téléphone',
            content: '1-800-909090',
            link: 'tel:1-800-266-7383'
        },
        {
            icon: 'pi pi-envelope',
            title: 'Email',
            content: 'info@competencescanada.ca',
            link: 'mailto:info@competencescanada.ca'
        },
        {
            icon: 'pi pi-map-marker',
            title: 'Adresse principale',
            content: '123 Rue Principale, Toronto, ON M5V 3A8'
        },
        {
            icon: 'pi pi-clock',
            title: 'Heures d\'ouverture',
            content: 'Lun-Ven: 9h00-17h00\nSam: 10h00-14h00'
        }
    ];

    offices: OfficeLocation[] = [
        {
            city: 'Toronto',
            address: '123 Rue Principale, Toronto, ON M5V 3A8',
            phone: '(416) 555-0123',
            email: 'toronto@competencescanada.ca',
            hours: 'Lun-Ven: 9h00-17h00'
        },
        {
            city: 'Montréal',
            address: '456 Boulevard Saint-Laurent, Montréal, QC H2X 2T1',
            phone: '(514) 555-0456',
            email: 'montreal@competencescanada.ca',
            hours: 'Lun-Ven: 9h00-17h00'
        },
        {
            city: 'Vancouver',
            address: '789 Granville Street, Vancouver, BC V6Z 1K3',
            phone: '(604) 555-0789',
            email: 'vancouver@competencescanada.ca',
            hours: 'Lun-Ven: 9h00-17h00'
        }
    ];

    subjectOptions = [
        { label: 'Évaluation des compétences', value: 'evaluation' },
        { label: 'Accompagnement personnalisé', value: 'accompagnement' },
        { label: 'Intégration professionnelle', value: 'integration' },
        { label: 'Support technique', value: 'support' },
        { label: 'Partenariat', value: 'partenariat' },
        { label: 'Autre', value: 'autre' }
    ];

    constructor(
        private fb: FormBuilder,
        private messageService: MessageService
    ) { }

    ngOnInit(): void {
        this.initializeForm();
    }

    initializeForm() {
        this.contactForm = this.fb.group({
            firstName: ['', [Validators.required, Validators.minLength(2)]],
            lastName: ['', [Validators.required, Validators.minLength(2)]],
            email: ['', [Validators.required, Validators.email]],
            phone: ['',Validators.required],
            message: ['', [Validators.required, Validators.minLength(10)]],
        });
    }

    onSubmit() {
        if (this.contactForm.valid) {
            this.loading = true;

            // Simulation d'envoi du message
            setTimeout(() => {
                this.loading = false;
                this.messageService.add({
                    severity: 'success',
                    summary: 'Message envoyé',
                    detail: 'Votre message a été envoyé avec succès. Nous vous répondrons dans les plus brefs délais.',
                    life: 5000
                });
                this.contactForm.reset();
                this.contactForm.patchValue({
                    preferredContact: 'email',
                    urgency: 'normal'
                });
            }, 2000);
        } else {
            this.markFormGroupTouched();
            this.messageService.add({
                severity: 'error',
                summary: 'Erreur',
                detail: 'Veuillez corriger les erreurs dans le formulaire.',
                life: 3000
            });
        }
    }

    private markFormGroupTouched() {
        Object.keys(this.contactForm.controls).forEach(key => {
            const control = this.contactForm.get(key);
            control?.markAsTouched();
        });
    }

    isFieldInvalid(fieldName: string): boolean {
        const field = this.contactForm.get(fieldName);
        return !!(field && field.invalid && field.touched);
    }

    getFieldError(fieldName: string): string {
        const field = this.contactForm.get(fieldName);
        if (field?.errors) {
            if (field.errors['required']) {
                return 'Ce champ est requis';
            }
            if (field.errors['email']) {
                return 'Format d\'email invalide';
            }
            if (field.errors['minlength']) {
                const requiredLength = field.errors['minlength'].requiredLength;
                return `Minimum ${requiredLength} caractères requis`;
            }
            if (field.errors['pattern']) {
                return 'Format de téléphone invalide';
            }
        }
        return '';
    }

    onContactInfoClick(info: ContactInfo) {
        if (info.link) {
            window.open(info.link, '_blank');
        }
    }
}
