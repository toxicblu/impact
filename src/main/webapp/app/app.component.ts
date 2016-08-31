import { Component } from '@angular/core';

import { NavbarComponent } from './navbar/navbar.component';
import { SideMenuWrapperComponent, SideMenuComponent } from './side-menu/index';

@Component({
  moduleId: module.id,
  selector: 'si-app',
  templateUrl: 'app.component.html',
  directives: [
    NavbarComponent,
    SideMenuWrapperComponent,
    SideMenuComponent,
  ],
})
export class AppComponent { }
