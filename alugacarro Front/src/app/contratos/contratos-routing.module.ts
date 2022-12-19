import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from '../layout/layout.component';
import { ContratoDeAluguelComponent } from './contrato-de-aluguel/contrato-de-aluguel.component';


const routes: Routes = [
  { path: 'contratos', component: LayoutComponent, children: [
    { path: 'listagem', component: ContratoDeAluguelComponent }
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContratosRoutingModule { }
