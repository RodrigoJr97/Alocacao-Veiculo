import { Component, OnInit } from '@angular/core';
import { ClienteService } from '../cliente.service';
import { Cliente } from '../clientes/Clientes';
import { Router } from '@angular/router';
import { CepService } from './../cep.service';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {

  cliente: Cliente = {
    nome: '',
    cpf: '',
    numeroTelefone: '',
    email: '',
    disponivel: true,
    endereco: {
      cep: '',
      logradouro: '',
      numeroLogradouro: '',
      bairro: '',
      localidade: '',
      uf: '',
      complemento: ''
    }
  }

  endereco: any = {
    cep: '',
    logradouro: '',
    numeroLogradouro: '',
    bairro: '',
    localidade: '',
    uf: '',
    complemento: '',
    ibge: '',
    gia: '',
    ddd: '',
    siafi: ''
  }

  falhaDeCadastro: boolean = false;
  sucesso: boolean = false;
  cep: string = '';


  constructor(
    private clienteService : ClienteService,
    private cepService: CepService,
    private router: Router
  ) {
  }

  ngOnInit(): void {}

  consultaCep(){
    this.cepService.getCep(this.cep)
    .subscribe( resposta => {
      this.endereco = resposta;
      this.cliente.endereco = this.endereco;
    });
  }

  cadastrar(){
    this.clienteService
    .salvarCliente(this.cliente)
    .subscribe( resposta => {
      this.sucesso = true;
      this.falhaDeCadastro = false
    }, err => {
      alert('algo deu errado!')
      console.log(err);
      this.sucesso = false;
      this.falhaDeCadastro = true;
    })
  }

  voltar(){
    this.router.navigate(['/login']);
  }

}
