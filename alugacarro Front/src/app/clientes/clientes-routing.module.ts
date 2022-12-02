import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from '../layout/layout.component';
import { ListaDeClientesComponent } from './lista-de-clientes/lista-de-clientes.component';


const routes: Routes = [
  { path: 'clientes', component: LayoutComponent, children: [
    { path: 'listagem' , component: ListaDeClientesComponent }
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientesRoutingModule { }
