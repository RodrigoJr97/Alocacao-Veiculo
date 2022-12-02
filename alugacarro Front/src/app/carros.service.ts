import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Carro } from './carros/Carros';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class CarrosService {

  baseUrl = environment.baseUrl + '/aluga/carro'

  constructor(private http: HttpClient) { }

  getCarroById(id: number): Observable<Carro>{
    return this.http.get<Carro>(`${this.baseUrl}/${id}`)
  }

  getCarros(): Observable<Carro[]> {
    return this.http.get<Carro[]>(this.baseUrl);
  }

  salvar(carro: Carro): Observable<Carro>{
    return this.http.post<Carro>(this.baseUrl, carro);
  }

  atualizar(carro: Carro): Observable<any>{
    return this.http.put<Carro>(`${this.baseUrl}/${carro.id}`, carro);
  }

  deletar(carro: Carro): Observable<any>{
    return this.http.delete<any>(`${this.baseUrl}/${carro.id}`);
  }

}
