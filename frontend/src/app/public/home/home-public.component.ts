import { Component } from '@angular/core';
interface ServiceFeature {
    text: string;
    completed: boolean;
}

interface Service {
    id: number;
    title: string;
    description: string;
    icon: string;
    iconBg: string;
    features: ServiceFeature[];
}
@Component({
    selector: 'app-home',
    templateUrl: './home-public.component.html',
    styleUrls: ['./home-public.component.scss']
})

export class HomePublicComponent{

    services: Service[] = [
        {
            id: 1,
            title: 'Évaluation des compétences',
            description: 'Analyse approfondie de vos qualifications et expériences professionnelles',
            icon: 'pi pi-star',
            iconBg: '#fef2f2',
            features: [
                { text: 'Évaluation personnalisée', completed: true },
                { text: 'Rapport détaillé', completed: true },
                { text: 'Recommandations ciblées', completed: true }
            ]
        },
        {
            id: 2,
            title: 'Accompagnement personnalisé',
            description: 'Suivi individuel avec des conseillers spécialisés dans votre domaine',
            icon: 'pi pi-users',
            iconBg: '#fef2f2',
            features: [
                { text: 'Conseiller dédié', completed: true },
                { text: 'Suivi régulier', completed: true },
                { text: 'Support multilingue', completed: true }
            ]
        },
        {
            id: 3,
            title: 'Intégration professionnelle',
            description: 'Connexion avec les employeurs et opportunités d\'emploi dans votre secteur',
            icon: 'pi pi-globe',
            iconBg: '#fef2f2',
            features: [
                { text: 'Réseau d\'employeurs', completed: true },
                { text: 'Préparation aux entretiens', completed: true },
                { text: 'Suivi post-placement', completed: true }
            ]
        }
    ];

    constructor() { }

    onServiceClick(service: Service) {
        console.log('Service sélectionné:', service.title);
        // Logique pour naviguer vers les détails du service
    }

    onFeatureClick(feature: ServiceFeature, service: Service) {
        console.log(`Feature "${feature.text}" du service "${service.title}" cliquée`);
        // Logique pour afficher plus de détails sur la fonctionnalité
    }
    processSteps = [
        {
            step: '01',
            title: 'Inscription',
            description: 'Créez votre profil et téléchargez vos documents'
        },
        {
            step: '02',
            title: 'Évaluation',
            description: 'Analyse de vos compétences par nos experts'
        },
        {
            step: '03',
            title: 'Plan d\'action',
            description: 'Stratégie personnalisée pour votre reconnaissance'
        },
        {
            step: '04',
            title: 'Intégration',
            description: 'Accompagnement vers l\'emploi dans votre domaine'
        }
    ];

    stats = [
        { value: '5000+', label: 'Professionnels aidés' },
        { value: '95%', label: 'Taux de réussite' },
        { value: '50+', label: 'Métiers couverts' }
    ];

    footerLinks = {
        services: [
            { label: 'Évaluation', link: '#' },
            { label: 'Accompagnement', link: '#' },
            { label: 'Formation', link: '#' },
            { label: 'Placement', link: '#' }
        ],
        resources: [
            { label: 'Guide d\'immigration', link: '#' },
            { label: 'Métiers réglementés', link: '#' },
            { label: 'FAQ', link: '#' },
            { label: 'Blog', link: '#' }
        ]
    };

    currentYear = new Date().getFullYear();

    scrollToSection(sectionId: string) {
        const element = document.getElementById(sectionId);
        if (element) {
            element.scrollIntoView({ behavior: 'smooth' });
        }
    }

    onCommencer() {
        console.log('Commencer l\'évaluation');
    }

    onPlanifierConsultation() {
        console.log('Planifier une consultation');
    }
}
