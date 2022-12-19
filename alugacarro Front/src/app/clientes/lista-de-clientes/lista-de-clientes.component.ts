import { Component, OnInit } from '@angular/core';
import { ClienteService } from 'src/app/cliente.service';
import { Cliente } from '../Clientes';
import { Router } from '@angular/router';
import { Endereco } from './../Endereco';

@Component({
  selector: 'app-lista-de-clientes',
  templateUrl: './lista-de-clientes.component.html',
  styleUrls: ['./lista-de-clientes.component.css']
})
export class ListaDeClientesComponent implements OnInit {

  clientes: Cliente[];
  clienteSelecionado: Cliente;
  mensagemSucesso: boolean = false;
  mensagemErro: boolean = false;

  constructor(
    private service: ClienteService,
    private route: Router
    ) {
   }

  ngOnInit(): void {
    this.service
    .getClientes()
    .subscribe( resposta => this.clientes = resposta );
  }

  modalDelecao( cliente: Cliente ){
    this.clienteSelecionado = cliente;
  }

  modalEdicao( cliente: Cliente ){
    this.clienteSelecionado = cliente;
  }

  deletarCliente(){
    this.service
    .deletarCliente(this.clienteSelecionado)
    .subscribe(
      resposta => {
        this.mensagemSucesso = true;
        this.mensagemErro = false;
        this.ngOnInit()
      }, errorRespose => {
        this.mensagemErro = true;
        this.mensagemSucesso = false;
      })
  }

  editarCliente(){
    this.service
    .atualizarCliente(this.clienteSelecionado)
    .subscribe(
      resposta => {
        this.ngOnInit()
      }, error => {
        alert('erro')
      }
    )
  }



}
