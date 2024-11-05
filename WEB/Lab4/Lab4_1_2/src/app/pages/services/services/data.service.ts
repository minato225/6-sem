import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable } from "rxjs";
import { Bill, Service } from "@core/models";
import { BillsMock, ServicesMock } from "@core/consts";


@Injectable()
export class DataService {
  private serviceData = new BehaviorSubject<Service[]>([]);
  private billData = new BehaviorSubject<Bill[]>([]);

  get bills$(): Observable<Bill[]> {
    return this.billData.asObservable();
  }

  get bills(): Bill[] {
    return this.billData.value;
  }

  get services$(): Observable<Service[]> {
    return this.serviceData.asObservable();
  }

  get services(): Service[] {
    return this.serviceData.value;
  }

  constructor() {
    this.setBill(BillsMock)
    this.setServices(ServicesMock)
  }

  setBill(bills: Bill[]) {
    this.billData.next([...bills, ...this.billData.value]);
  }

  setServices(services: Service[]) {
    this.serviceData.next([...services, ...this.serviceData.value]);
  }
}
