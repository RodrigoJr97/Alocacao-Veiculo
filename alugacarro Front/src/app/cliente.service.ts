import { Injectable } from '@angular/core';
import { environment } from './../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cliente } from './clientes/Clientes';
import { Endereco } from './clientes/Endereco';


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
    return this.http.post<Cliente>(this.baseUrl, cliente);
  }

  deletarCliente(cliente: Cliente): Observable<any>{
    return this.http.delete<any>(`${this.baseUrl}/${cliente.id}`);
  }

  atualizarCliente(cliente: Cliente): Observable<any>{
    return this.http.put<any>(`${this.baseUrl}/${cliente.id}`, cliente)
  }
}
