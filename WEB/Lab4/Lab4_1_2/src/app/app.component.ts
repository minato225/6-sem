import { Component } from '@angular/core';
import { NavigationRoute } from "@core/consts";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  route = NavigationRoute;
}
