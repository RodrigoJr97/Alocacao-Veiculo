import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from './../environments/environment';
import { Observable } from 'rxjs';
import { Contrato } from './contratos/Contrato';

@Injectable({
  providedIn: 'root'
})
export class ContratosService {

  constructor(private http: HttpClient) { }
  baseUrl = environment.baseUrl + '/aluga/contrato'


  getContratos(): Observable<Contrato[]>{
    return this.http.get<Contrato[]>(this.baseUrl)
  }
}
