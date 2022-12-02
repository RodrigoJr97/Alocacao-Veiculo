import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import { LayoutComponent } from '../layout/layout.component';
import { CadastroDeCarrosComponent } from './cadastro-de-carros/cadastro-de-carros.component';
import { DetalhesDoCarroComponent } from './detalhes-do-carro/detalhes-do-carro.component';
import { ListaDeCarrosComponent } from './lista-de-carros/lista-de-carros.component';


const routes: Routes = [
  { path: 'carros', component: LayoutComponent, children: [
    { path: 'cadastro', component: CadastroDeCarrosComponent },
    { path: 'cadastro/:id', component: CadastroDeCarrosComponent },
    { path: 'listagem', component: ListaDeCarrosComponent },
    { path: 'detalhes/:id', component: DetalhesDoCarroComponent },
    { path: '', redirectTo: '/home', pathMatch: 'full' }
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CarrosRoutingModule { }
