import { Component, type OnInit } from "@angular/core"
import {  FormBuilder,  FormGroup, Validators } from "@angular/forms"
import { MessageService } from "primeng/api"
import { Router } from '@angular/router';
import { LangueOpenService} from "../../shared/service/open/profil/LangueOpen.service";
import { LangueDto } from "../../shared/model/profil/Langue.model";
import { NiveauLangueOpenService} from "../../shared/service/open/profil/NiveauLangueOpen.service";
import { NiveauLangueDto } from "../../shared/model/profil/NiveauLangue.model";
import { MetierOpenService } from "../../shared/service/open/profil/MetierOpen.service";
import { MetierDto } from "../../shared/model/profil/Metier.model";
import { InscriptionOpenService } from "../../shared/service/open/profil/inscriptionOpen.service";
import {InscriptionDto} from "../../shared/model/profil/Inscription.model";
import { CollaborateurDto } from "../../shared/model/utilisateurs/Collaborateur.model";
import { CollaborateurOpenService } from "../../shared/service/open/utilisateurs/CollaborateurOpen.service";

import {error} from "protractor";

@Component({
    selector: "app-inscription",
    templateUrl: "./inscription.component.html",
    styleUrls: ["./inscription.component.scss"],
    providers: [MessageService],
})
export class InscriptionComponent implements OnInit {
    inscriptionForm!: FormGroup
    protected _items: Array<InscriptionDto>;
    protected _item: InscriptionDto;
    private _collaborateur = new CollaborateurDto();


    constructor(
        private fb: FormBuilder,
        private messageService: MessageService,
        private router: Router,
        private langueService: LangueOpenService,
        private niveauLangueService: NiveauLangueOpenService,
        private metierService: MetierOpenService,
        private inscriptionService: InscriptionOpenService,
        private collaborateurService: CollaborateurOpenService
    ) {}

    ngOnInit() {
        this.inscriptionForm = this.fb.group({
            phone: ["", [Validators.pattern(/^[0-9]{10,15}$/)]],
            style: [""],
            description: [""],
            nom: [""],
            prenom: [""],
            email: ["", [Validators.email]],
            password: ["", [Validators.minLength(8)]],
            langue: [null],
            niveauLangue: [null],
            metier: [null],
            nombreHeureExperience: [0],
            collaborateur: [null],
        });
        this.langueService.findAll().subscribe((data) => this.langues=data);
        this.niveauLangueService.findAll().subscribe((data) => this.niveauLangues = data);
        this.metierService.findAll().subscribe((data) => this.metiers = data);
    }

    onSubmit() {
        if (this.inscriptionForm.valid) {
            this.inscriptionService.item=this.inscriptionForm.value;
            this.collaborateur=this.inscriptionService.item;
            this.collaborateurService.item=this.collaborateur;
            this.collaborateurService.save().subscribe(item=>{
                if(item) this.inscriptionService.item.collaborateur=item;
                // console.log(this.inscriptionService.item.collaborateur)
                this.inscriptionService.save().subscribe(item =>{
                    if (item){
                        this.messageService.add({
                            severity: "success",
                            summary: "Inscription réussie",
                            detail: "On va vous contacter après",
                            life: 5000,
                        })
                        setTimeout(() => {
                            this.router.navigate(['/home']);
                        }, 5000);
                    }
                }, error => {
                    console.log(error);
                });
            })


        } else {
            Object.keys(this.inscriptionForm.controls).forEach((key) => {
                const control = this.inscriptionForm.get(key)
                control?.markAsTouched()
            })
            this.messageService.add({
                severity: "error",
                summary: "Erreur",
                detail: "Veuillez corriger les erreurs dans le formulaire",
                life: 5000,
            })
        }
    }

    get langue(): LangueDto {
        return this.langueService.item;
    }
    set langue(value: LangueDto) {
        this.langueService.item = value;
    }
    get langues(): Array<LangueDto> {
        return this.langueService.items;
    }
    set langues(value: Array<LangueDto>) {
        this.langueService.items = value;
    }

    get niveauLangue(): NiveauLangueDto {
        return this.niveauLangueService.item;
    }
    set niveauLangue(value: NiveauLangueDto) {
        this.niveauLangueService.item = value;
    }
    get niveauLangues(): Array<NiveauLangueDto> {
        return this.niveauLangueService.items;
    }
    set niveauLangues(value: Array<NiveauLangueDto>) {
        this.niveauLangueService.items = value;
    }

    get metier(): MetierDto {
        return this.metierService.item;
    }
    set metier(value: MetierDto) {
        this.metierService.item = value;
    }
    get metiers(): Array<MetierDto> {
        return this.metierService.items;
    }
    set metiers(value: Array<MetierDto>) {
        this.metierService.items = value;
    }

    public get items(): Array<InscriptionDto> {
        return this.inscriptionService.items;
    }

    public set items(value: Array<InscriptionDto>) {
        this.inscriptionService.items = value;
    }

    public get item(): InscriptionDto {
        return this.inscriptionService.item;
    }

    public set item(value: InscriptionDto) {
        this.inscriptionService.item = value;
    }


    get collaborateur(): CollaborateurDto {
        return this._collaborateur;
    }

    set collaborateur(inscription: InscriptionDto) {
       this._collaborateur.username= inscription.prenom+" "+inscription.nom;
       this._collaborateur.email=inscription.email;
       this._collaborateur.password=inscription.password;
       this._collaborateur.description=inscription.description;
       this._collaborateur.accountNonExpired=true;
       this._collaborateur.accountNonLocked=true;
       this._collaborateur.enabled=true;
       this._collaborateur.passwordChanged=false;
       this._collaborateur.credentialsNonExpired=true;
       this._collaborateur.phone=inscription.phone;
    }
}
