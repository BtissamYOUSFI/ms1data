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
import {ManagerDto} from "../../shared/model/utilisateurs/Manager.model";

@Component({
    selector: "app-inscription",
    templateUrl: "./register-manager.component.html",
    styleUrls: ["./register-manager.component.scss"],
    providers: [MessageService],
})
export class RegisterManagerComponent implements OnInit {
    inscriptionForm!: FormGroup
    protected _items: Array<InscriptionDto>;
    protected _item: InscriptionDto;
    private _manager = new ManagerDto();

    constructor(
        private fb: FormBuilder,
        private messageService: MessageService,
        private router: Router,
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
            manager: [null],
        });
    }

    // onSubmit() {
    //     if (this.inscriptionForm.valid) {
    //         this.inscriptionService.item=this.inscriptionForm.value;
    //         this.collaborateur=this.inscriptionService.item;
    //         this.collaborateurService.item=this.collaborateur;
    //         this.collaborateurService.save().subscribe(item=>{
    //             if(item) this.inscriptionService.item.collaborateur=item;
    //             // console.log(this.inscriptionService.item.collaborateur)
    //             this.inscriptionService.save().subscribe(item =>{
    //                 if (item){
    //                     this.messageService.add({
    //                         severity: "success",
    //                         summary: "Inscription réussie",
    //                         detail: "On va vous contacter après",
    //                         life: 2000,
    //                     })
    //                     setTimeout(() => {
    //                         this.router.navigate(['/home']);
    //                     }, 5000);
    //                 }
    //             }, error => {
    //                 console.log(error);
    //             });
    //         })
    //
    //
    //     } else {
    //         Object.keys(this.inscriptionForm.controls).forEach((key) => {
    //             const control = this.inscriptionForm.get(key)
    //             control?.markAsTouched()
    //         })
    //         this.messageService.add({
    //             severity: "error",
    //             summary: "Erreur",
    //             detail: "Veuillez corriger les erreurs dans le formulaire",
    //             life: 5000,
    //         })
    //     }
    // }

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


    get manager(): ManagerDto {
        return this._manager;
    }

    // set collaborateur(inscription: InscriptionDto) {
    //    this._collaborateur.username= inscription.prenom+" "+inscription.nom;
    //    this._collaborateur.email=inscription.email;
    //    this._collaborateur.password=inscription.password;
    //    this._collaborateur.description=inscription.description;
    //    this._collaborateur.accountNonExpired=true;
    //    this._collaborateur.accountNonLocked=true;
    //    this._collaborateur.enabled=true;
    //    this._collaborateur.passwordChanged=false;
    //    this._collaborateur.credentialsNonExpired=true;
    //    this._collaborateur.phone=inscription.phone;
    // }
}
