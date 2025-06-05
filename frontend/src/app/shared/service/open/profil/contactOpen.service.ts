import {Injectable} from "@angular/core";
import {environment} from "../../../../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})

export class ContactOpenService{

    constructor(private http: HttpClient) {
    }

    private _item: any={
        'from': '',
        'to': 'ibtissamyousfi111@gmail.com',
        'message': ''
    }
    // public sendEmail() {
    //     const emailPayload = {
    //         from: this.item.from,
    //         to: this.item.to,
    //         message: this.item.message
    //     };
    //     this.http.post(this.API+"send-email/"+emailPayload)
    //
    // }


    get item(): any {
        return this._item;
    }

    set item(value: any) {
        this._item = value;
    }

    get API() {
        return environment.apiUrlMs1datams1 + 'open/contact/';
    }
}
