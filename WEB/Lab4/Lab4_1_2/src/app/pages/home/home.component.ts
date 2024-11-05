import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { NavigationRoute } from "@core/consts";

@Component({
  selector: "home-page",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"],
})
export class HomeComponent {
  constructor(private router: Router) {}

  routeToDetails() {
    this.router.navigate([NavigationRoute.SERVICES]);
  }
}
