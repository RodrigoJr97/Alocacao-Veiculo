import { Component, OnInit } from '@angular/core';
import { Carro } from '../Carros';
import { CarrosService } from './../../carros.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-lista-de-carros',
  templateUrl: './lista-de-carros.component.html',
  styleUrls: ['./lista-de-carros.component.css']
})
export class ListaDeCarrosComponent implements OnInit {

  carros: Carro[] = [];
  carroSelecionado: Carro;
  mensagemSucesso: boolean = false;
  mensagemErro: boolean = false;

  constructor(
    private service: CarrosService,
    private route: Router){
  }

  ngOnInit(): void {
    this.service
    .getCarros()
    .subscribe( reposta=> this.carros = reposta );
  }

  novoCarro(){
    this.route.navigate(['/carros/cadastro']);
  }

  modalDelecao(carro: Carro){
    this.carroSelecionado = carro;
  }

  deletarCarro(){
    this.service
    .deletar(this.carroSelecionado)
    .subscribe(
      resposta => {
        this.mensagemSucesso = true;
        this.mensagemErro = false;
        this.ngOnInit();
      },
      erro => {
        this.mensagemErro = true;
        this.mensagemSucesso = false;
    }
    )
  }

}
