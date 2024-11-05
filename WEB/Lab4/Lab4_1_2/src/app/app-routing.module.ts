import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { NavigationRoute } from "@core/consts";

const routes: Routes = [
  { path: NavigationRoute.HOME, loadChildren: () => import('@pages/home/home.module').then(m => m.HomeModule) },
  { path: NavigationRoute.SERVICES, loadChildren: () => import('@pages/services/services.module').then(m => m.ServicesModule) },
  { path: '**', loadChildren: () => import('./pages/not-found/not-found.module').then(m => m.NotFoundModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
