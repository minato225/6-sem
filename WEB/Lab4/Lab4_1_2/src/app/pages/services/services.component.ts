import { Component, OnDestroy, OnInit } from "@angular/core";
import { DataService } from "./services/data.service";
import { Subject } from "rxjs";
import { Service } from "@core/models";
import { takeUntil } from "rxjs/operators";


@Component({
  selector: "race-page",
  templateUrl: "./services.component.html",
  styleUrls: ["./services.component.scss"],
})
export class ServicesComponent implements OnInit, OnDestroy {
  private destroy = new Subject();
  services: Service[] = [];

  constructor(private dataService: DataService) {}

  ngOnInit() {
    this.dataService.services$
      .pipe(takeUntil(this.destroy))
      .subscribe((services) => {
        this.services = services;
      });
  }

  ngOnDestroy() {
    this.destroy.next(true);
  }
}
