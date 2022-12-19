import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class CepService {

  constructor(private http: HttpClient) { }

  getCep(cep: string): Observable<string>{
    return this.http.get<string>(`https://viacep.com.br/ws/${cep}/json/`)
  }
}
