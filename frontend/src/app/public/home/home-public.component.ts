import { Component, OnInit } from '@angular/core';
import { LayoutService } from "../../layout/service/app.layout.service";
import { AppComponent } from "../../app.component";
import gsap from "gsap";

@Component({
    selector: 'app-home',
    templateUrl: './home-public.component.html',
    styleUrls: ['./home-public.component.scss']
})
export class HomePublicComponent implements OnInit {
    testimonials: any[] = [
        {
            text: 'Since joining this platform, my client base has grown by 40%. The verification process gives customers confidence in my services.',
            name: 'Jean Tremblay',
            location: 'Montreal, QC',
            profession: 'Master Plumber',
            // image: 'assets/images/testimonial1.jpg'
        },
        {
            text: 'The registration process was simple and the support team was very helpful. I\'ve received consistent work through the platform.',
            name: 'Michael Johnson',
            location: 'Toronto, ON',
            profession: 'Electrician',
            // image: 'assets/images/testimonial2.jpg'
        },
        {
            text: 'As a new plumber in the industry, this platform helped me establish my reputation quickly with verified credentials.',
            name: 'Sarah Williams',
            location: 'Vancouver, BC',
            profession: 'HVAC Technician',
            // image: 'assets/images/testimonial3.jpg'
        }
    ];

    tradeCategories: any[] = [
        { name: 'Plumbing', icon: 'pi pi-wrench', jobs: '230+' },
        { name: 'Electrical', icon: 'pi pi-bolt', jobs: '185+' },
        { name: 'Carpentry', icon: 'pi pi-calculator', jobs: '156+' },
        { name: 'HVAC', icon: 'pi pi-cog', jobs: '142+' },
        { name: 'Masonry', icon: 'pi pi-building', jobs: '98+' },
        { name: 'Painting', icon: 'pi pi-palette', jobs: '112+' }
    ];

    constructor(public layoutService: LayoutService, public app: AppComponent) {}

    ngOnInit(): void {
        // Initialize animations
        this.initAnimations();

        // Fade in page content
        gsap.fromTo(".page-content",
            { opacity: 0, y: 30 },
            { opacity: 1, y: 0, duration: 0.8, ease: "power2.out" }
        );
    }

    initAnimations(): void {
        // Counter animation for statistics
        const counterElements = document.querySelectorAll('.counter-value');
        counterElements.forEach(element => {
            const target = parseInt(element.textContent || '0', 10);
            gsap.fromTo(element,
                { textContent: 0 },
                {
                    textContent: target,
                    duration: 2,
                    ease: "power2.out",
                    roundProps: "textContent",
                    onUpdate: function() {
                        element.textContent = Math.round(this.targets()[0].textContent).toString();
                    }
                }
            );
        });

        // Animate feature cards on scroll
        const featureCards = document.querySelectorAll('.feature-card');
        featureCards.forEach((card, index) => {
            gsap.fromTo(card,
                { opacity: 0, y: 30 },
                {
                    opacity: 1,
                    y: 0,
                    duration: 0.5,
                    delay: 0.2 * index,
                    scrollTrigger: {
                        trigger: card,
                        start: "top 80%"
                    }
                }
            );
        });
    }

    scrollToRegister(): void {
        const registerSection = document.getElementById('register-section');
        if (registerSection) {
            registerSection.scrollIntoView({ behavior: 'smooth' });
        }
    }
}


// import {Component, OnInit} from '@angular/core';
// import {LayoutService} from "../../layout/service/app.layout.service";
// import {AppComponent} from "../../app.component";
// import gsap from "gsap";
//
//
// @Component({
//     selector: 'app-home',
//     templateUrl: './home-public.component.html',
//     styleUrls: ['./home-public.component.scss']
// })
// export class HomePublicComponent implements OnInit {
//
//
//     testimonials: any[] = [
//         {
//             text: 'Since joining this platform, my client base has grown by 40%. The verification process gives customers confidence in my services.',
//             name: 'Jean Tremblay',
//             location: 'Montreal, QC',
//             // image: 'assets/images/plumber1.jpg'
//         },
//         {
//             text: 'The registration process was simple and the support team was very helpful. I\'ve received consistent work through the platform.',
//             name: 'Michael Johnson',
//             location: 'Toronto, ON',
//             // image: 'assets/images/plumber2.jpg'
//         },
//         {
//             text: 'As a new plumber in the industry, this platform helped me establish my reputation quickly with verified credentials.',
//             name: 'Sarah Williams',
//             location: 'Vancouver, BC',
//             // image: 'assets/images/plumber3.jpg'
//         }
//     ];
//     onSearchInput(event: any) {
//         const searchTerm = event.target.value;
//         // Handle search input here
//         console.log('Search term:', searchTerm);
//     }
//
//
//     constructor(public layoutService: LayoutService, public app: AppComponent ) {
//
//     }
//
//
//     loading = true;
//     bool: boolean = true;
//
//     ngOnInit(): void {
//         gsap.to(".spring", { rotation: 360, duration: 8, repeat: -1,ease: "linear" });
//         gsap.fromTo(".spring", {x: 500 , y: 10}, {x: 0 , y:0, duration: 5, ease: "circ"});
//         gsap.to(".dotnet", { rotation: 360, duration: 8, repeat: -1,ease: "linear" });
//         gsap.fromTo(".dotnet", {x: -500 , y: 10}, {x: 0 , y:0, duration: 5, ease: "circ"});
//         gsap.to(".react", { rotation: 360, duration: 8, repeat: -1,ease: "linear" });
//         gsap.fromTo(".react", {x: 500 , y: 10}, {x: 0 , y:0, duration: 5, ease: "circ"});
//         gsap.to(".angular", { rotation: 360, duration: 8, repeat: -1,ease: "linear" });
//         gsap.fromTo(".angular", {x: -500 , y: 10}, {x: 0 , y:0, duration: 5, ease: "circ"});
//         gsap.to(".title", { translate: 1, y: 50, duration: 3,ease: "linear" });
//
//
//         const tl = gsap.timeline()
//         if(this.bool){
//             tl.fromTo(".body1", {opacity: 0}, {opacity: 1, duration: 3})
//             this.bool = false
//         }
//
//
//     }
//
// }
