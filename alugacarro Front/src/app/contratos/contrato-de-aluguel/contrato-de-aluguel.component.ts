import { Component, OnInit } from '@angular/core';
import { ContratosService } from './../../contratos.service';
import { Contrato } from './../Contrato';

@Component({
  selector: 'app-contrato-de-aluguel',
  templateUrl: './contrato-de-aluguel.component.html',
  styleUrls: ['./contrato-de-aluguel.component.css']
})
export class ContratoDeAluguelComponent implements OnInit {

  contratos: Contrato[];

  constructor(private service: ContratosService) { }

  ngOnInit(): void {
    this.service
    .getContratos()
    .subscribe( resposta => this.contratos = resposta);
  }

}
