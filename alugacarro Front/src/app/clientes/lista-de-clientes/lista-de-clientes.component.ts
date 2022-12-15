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

  cliente: Cliente
  clientes: Cliente[];
  endereco: Endereco;
  constructor(
    private service: ClienteService,
    private route: Router
    ) {
      this.cliente = new Cliente()
   }

  ngOnInit(): void {
    this.service
    .getClientes()
    .subscribe( reposta => this.clientes = reposta
    );
  }

  novoCliente(){
  }
}
