import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'

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
import { FormsModule } from '@angular/forms';
import { LayoutComponent } from './layout/layout.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    LayoutComponent
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
  ],
  providers: [
    CarrosService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
