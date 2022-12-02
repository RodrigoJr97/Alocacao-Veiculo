import { Component, OnInit } from '@angular/core';
import { Carro } from '../carros/Carros';
import { CarrosService } from './../carros.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  carro: Carro;

  carros: Carro[] = [];

  constructor(private service: CarrosService) {

   }

  ngOnInit(): void {
    this.service.getCarros()
    .subscribe( reposta => {
      this.carros = reposta;
    })
  }

}
