import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ContratoDeAluguelComponent } from './contrato-de-aluguel/contrato-de-aluguel.component';


const routes: Routes = [
  { path: 'alugar', component: ContratoDeAluguelComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContratosRoutingModule { }
