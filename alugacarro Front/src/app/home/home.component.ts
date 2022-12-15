import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Carro } from '../carros/Carros';
import { CarrosService } from './../carros.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  carro: Carro;

  categoria: string;

  carros: Carro[] = [];

  constructor(private service: CarrosService,
    private router: Router) {
  }

  ngOnInit(): void {
    this.service.getCarrosDisponiveis()
    .subscribe( reposta => {
      this.carros = reposta;
    })
  }

  carrosPorCategoria(tipo: string){
    this.service.getCarrosPorTipo(tipo)
    .subscribe( reposta => {
      this.carros = reposta;
      this.router.navigate[(`/carros/catalogo/${tipo}`)]
    })
  }

  carrosDisponiveis(){
    this.service.getCarrosDisponiveis()
    .subscribe( resposta => {
      this.carros = resposta;
    })
  }

}
