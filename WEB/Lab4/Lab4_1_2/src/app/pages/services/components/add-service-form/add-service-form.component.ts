import { Component } from "@angular/core";
import { DataService } from "@pages/services/services/data.service";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { Service } from "@core/models";


@Component({
  selector: "add-service-form, [add-service-form]",
  templateUrl: "./add-service-form.component.html",
  styleUrls: ["./add-service-form.component.scss"],
})
export class AddServiceFormComponent {
  form: FormGroup = new FormGroup({
    name: new FormControl("", [
      Validators.required,
      Validators.minLength(3),
    ]),
    price: new FormControl(0, [Validators.min(100)]),
  });

  constructor(private dataService: DataService) {}

  onSubmit() {
    const formData = this.form.value;
    const service = new Service(this.dataService.services.length, formData.name, formData.price);

    this.dataService.setServices([service])
  }
}
