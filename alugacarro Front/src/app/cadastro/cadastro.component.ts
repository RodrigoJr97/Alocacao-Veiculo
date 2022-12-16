import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ClienteService } from '../cliente.service';
import { Cliente } from '../clientes/Clientes';
import { Endereco } from '../clientes/Endereco';
import { Cep } from './cep';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {
  cliente: Cliente;

  cepForm = {
    cep: '',
    uf: '',
    complemento: '',
    localidade: '',
    logradouro: '',
    bairro: ''
  };


  constructor(
    private clienteService : ClienteService
  ) {
    this.cliente = new Cliente();
  }

  ngOnInit(): void {
  }

  consultaCep(){
    this.clienteService.getCep(this.cepForm.cep).subscribe( reposta => this.cepForm = reposta);
  }

  onSubmit(){
    this.clienteService.salvarCliente(this.cliente)
    .subscribe( res => this.cliente = res, err => alert('deu erro ai mano'))
  }
}
