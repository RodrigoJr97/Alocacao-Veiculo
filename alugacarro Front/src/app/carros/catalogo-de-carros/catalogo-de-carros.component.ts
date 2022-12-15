import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CarrosService } from 'src/app/carros.service';
import { Carro } from '../Carros';

@Component({
  selector: 'app-catalogo-de-carros',
  templateUrl: './catalogo-de-carros.component.html',
  styleUrls: ['./catalogo-de-carros.component.css']
})
export class CatalogoDeCarrosComponent implements OnInit {

  carro: Carro;
  carros: Carro[] = [];
  urlTipoDeCarro: string

  constructor(private service: CarrosService,
    private router: Router) {
  }

  ngOnInit(): void {
    this.service.getCarrosPorTipo('hatch')
    .subscribe( reposta => {
      this.carros = reposta;
    })
  }




}
