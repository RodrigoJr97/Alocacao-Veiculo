import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CarrosService } from 'src/app/carros.service';
import { Carro } from '../Carros';

@Component({
  selector: 'app-detalhes-do-carro',
  templateUrl: './detalhes-do-carro.component.html',
  styleUrls: ['./detalhes-do-carro.component.css']
})
export class DetalhesDoCarroComponent implements OnInit {

  id: number;
  carro: Carro;
  diarias: number;
  valorTotal: number;
  contrato: boolean = false;

  constructor(
    private service: CarrosService,
    private activatedRoute: ActivatedRoute,
    private route: Router
    ) {

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
          this.valorTotal = this.carro.valorDiaria * this.diarias;
      }
    })
  }


  onSubmit(){
    console.log(this.carro);
  }

  calcularValor(): number {
    this.valorTotal = this.carro.valorDiaria * this.diarias;
    return this.valorTotal;
  }

  confirmarContrato(){
    this.contrato = true;
  }
}
