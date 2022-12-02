import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ContratosRoutingModule } from './contratos-routing.module';
import { ContratoDeAluguelComponent } from './contrato-de-aluguel/contrato-de-aluguel.component';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [ContratoDeAluguelComponent],
  imports: [
    CommonModule,
    ContratosRoutingModule,
    RouterModule
  ], exports: [
    ContratoDeAluguelComponent
  ]
})
export class ContratosModule { }
