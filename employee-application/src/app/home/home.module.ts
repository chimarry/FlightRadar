import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from '../app-routing.module';
import { AppMaterialModule } from '../app-material/app-material.module';
import { HomeComponent } from './home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';



@NgModule({
  declarations: [
    HomeComponent,
    FooterComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    AppMaterialModule,
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class HomeModule { }
