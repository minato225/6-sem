import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { ReactiveFormsModule } from "@angular/forms";

import { ServicesComponent } from "./services.component";
import { ServiceListComponent } from "./components/service-list/service-list.component";
import { ServiceDetailsComponent } from "./components/service-details/service-details.component";
import { AddServiceFormComponent } from "./components/add-service-form/add-service-form.component";

import { DataService } from "./services/data.service";
import { ServicesRoutingModule } from "./services-routing.module";


@NgModule({
  declarations: [ServicesComponent, ServiceListComponent, ServiceDetailsComponent, AddServiceFormComponent],
  imports: [
    CommonModule,
    ServicesRoutingModule,
    ReactiveFormsModule,
  ],
  providers: [DataService]
})
export class ServicesModule {}
