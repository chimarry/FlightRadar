import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { NavmenuComponent } from './navmenu/navmenu.component';



@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent,
    NavmenuComponent
  ],
  imports: [
    CommonModule
  ]
})
export class GlobalModule { }
