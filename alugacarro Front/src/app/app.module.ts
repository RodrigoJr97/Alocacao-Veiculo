import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { TemplateModule } from './template/template.module';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component'
import { CarrosModule } from './carros/carros.module';
import { RouterModule } from '@angular/router';
import { ContratosModule } from './contratos/contratos.module';
import { ClientesModule } from './clientes/clientes.module';
import { CarrosService } from './carros.service';
import { LayoutComponent } from './layout/layout.component';
import { CadastroComponent } from './cadastro/cadastro.component';
import { ClienteService } from './cliente.service';
import { CepService } from './cep.service';
import { ContratosService } from './contratos.service';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    LayoutComponent,
    CadastroComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    TemplateModule,
    CarrosModule,
    ContratosModule,
    ClientesModule,
    RouterModule,
    ClientesModule,
    ReactiveFormsModule
  ],
  providers: [
    CarrosService,
    ClienteService,
    CepService,
    ContratosService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
