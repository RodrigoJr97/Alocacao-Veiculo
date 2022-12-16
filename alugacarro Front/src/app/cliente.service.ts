import { Injectable } from '@angular/core';
import { environment } from './../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cliente } from './clientes/Clientes';
import { Endereco } from './clientes/Endereco';
import { Cep } from './cadastro/cep';

@Injectable({
  providedIn: 'root'
})
  export class ClienteService {

  baseUrl = environment.baseUrl + '/aluga/cliente'
  cepUrl: string = 'viacep.com.br/ws/01001000/json/'

  constructor(private http: HttpClient) {
  }

  getClientes(): Observable<Cliente[]>{
    return this.http.get<Cliente[]>(this.baseUrl)
  }

  getClienteById(id: any): Observable<Cliente> {
    return this.http.get<Cliente>(`${this.baseUrl}/${id}`)
  }

  salvarCliente(cliente: Cliente): Observable<Cliente>{
    return this.http.post<Cliente>(`${this.baseUrl}`, cliente)
  }

  getCep(cep: string): Observable<any>{
    return this.http.get<any>(`https://viacep.com.br/ws/${cep}/json/`)
  }

}
