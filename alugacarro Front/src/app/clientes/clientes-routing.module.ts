import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from '../layout/layout.component';
import { FormularioDeClienteComponent } from './formulario-de-cliente/formulario-de-cliente.component';
import { ListaDeClientesComponent } from './lista-de-clientes/lista-de-clientes.component';


const routes: Routes = [
  { path: 'clientes', component: LayoutComponent, children: [
    { path: 'listagem' , component: ListaDeClientesComponent },
    { path: 'formulario-edicao/:id' , component: FormularioDeClienteComponent },
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientesRoutingModule { }
