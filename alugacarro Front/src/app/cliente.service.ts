import { Injectable } from '@angular/core';
import { environment } from './../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cliente } from './clientes/Clientes';

@Injectable({
  providedIn: 'root'
})
  export class ClienteService {

  baseUrl = environment.baseUrl + '/aluga/cliente'

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
  
}
