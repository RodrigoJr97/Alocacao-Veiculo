import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';


import { ClientesRoutingModule } from './clientes-routing.module';
import { ListaDeClientesComponent } from './lista-de-clientes/lista-de-clientes.component';
import { RouterModule } from '@angular/router';
import { FormularioDeClienteComponent } from './formulario-de-cliente/formulario-de-cliente.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [ListaDeClientesComponent, FormularioDeClienteComponent],
  imports: [
    CommonModule,
    ClientesRoutingModule,
    RouterModule,
    FormsModule,
  ], exports: [
    ListaDeClientesComponent,
    FormularioDeClienteComponent
  ]
})
export class ClientesModule { }
