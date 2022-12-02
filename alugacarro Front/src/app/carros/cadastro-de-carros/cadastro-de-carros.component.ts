import { Component, OnInit } from '@angular/core';
import { Carro } from '../Carros';
import { CarrosService } from './../../carros.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Observable } from 'rxjs/internal/Observable';

@Component({
  selector: 'app-cadastro-de-carros',
  templateUrl: './cadastro-de-carros.component.html',
  styleUrls: ['./cadastro-de-carros.component.css']
})
export class CadastroDeCarrosComponent implements OnInit {

  carro: Carro;
  success: boolean = false;
  falhaDeCadastro: boolean = false;
  id: any;

  constructor(
    private service: CarrosService,
    private route: Router,
    private activatedRoute: ActivatedRoute,
    ) {
      this.carro = new Carro();
  }

  voltarParaListagem(){
    this.route.navigate(['/carros/listagem'])
  }

  ngOnInit(): void {
    let params: Observable<Params> = this.activatedRoute.params
    params.subscribe( urlParams => {
      this.id = urlParams['id'];
      if (this.id) {
        this.service
          .getCarroById(this.id)
          .subscribe (
            resposta => this.carro = resposta
          )
      }
    })
  }

  onSubmit(){

    if (this.id) {

      this.service
      .atualizar(this.carro)
        .subscribe( resposta => {
          this.success = true;
            this.falhaDeCadastro = false;
        }, error => {
          this.falhaDeCadastro = true;
          this.success = false;
        })
    } else {

      this.service
      .salvar(this.carro)
        .subscribe( resposta => {
          console.log(resposta);
          this.success = true;
          this.falhaDeCadastro = false;
        }, errorRespose => {
          this.falhaDeCadastro = true;
          this.success = false;
        })
    }
  }


}
