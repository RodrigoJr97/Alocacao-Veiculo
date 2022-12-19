import { Component, OnInit } from '@angular/core';
import { Params, ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ClienteService } from 'src/app/cliente.service';
import { Cliente } from '../Clientes';

@Component({
  selector: 'app-formulario-de-cliente',
  templateUrl: './formulario-de-cliente.component.html',
  styleUrls: ['./formulario-de-cliente.component.css']
})
export class FormularioDeClienteComponent implements OnInit {
  cliente : Cliente;
  id: any;
  success: boolean = false;
  falha: boolean = false;

  constructor(
    private service: ClienteService,
    private route: Router,
    private activatedRoute: ActivatedRoute,
  ) {
    this.cliente = new Cliente();
  }

  ngOnInit(): void {
    let params: Observable<Params> = this.activatedRoute.params
    params.subscribe( urlParams => {
      this.id = urlParams['id'];
      if (this.id) {
        this.service
          .getClienteById(this.id)
          .subscribe (
            resposta => this.cliente = resposta
          )
      }
    })
  }

  atualizar(){
    this.service
    .atualizarCliente(this.cliente)
    .subscribe( resposta => {
      console.log(resposta);
      this.success = true;
      this.falha = false;
    }, err => {
      this.falha = true;
      this.success = false;
    })
  }

  voltar(){
    this.route.navigate(['/clientes/listagem'])
  }

}
