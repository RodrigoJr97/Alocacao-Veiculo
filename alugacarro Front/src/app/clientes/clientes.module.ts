import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientesRoutingModule } from './clientes-routing.module';
import { ListaDeClientesComponent } from './lista-de-clientes/lista-de-clientes.component';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [ListaDeClientesComponent],
  imports: [
    CommonModule,
    ClientesRoutingModule,
    RouterModule,
  ], exports: [
    ListaDeClientesComponent
  ]
})
export class ClientesModule { }
