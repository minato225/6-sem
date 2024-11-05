import { Component, Input, OnChanges } from "@angular/core";
import { Bill, Service } from "@core/models";
import { DataService } from "@pages/services/services/data.service";

@Component({
  selector: 'service-details, [service-details]',
  templateUrl: './service-details.component.html',
  styleUrls: ['./service-details.component.scss']
})
export class ServiceDetailsComponent implements OnChanges{
  @Input() service: Service = {} as Service;
  bills: Bill[] = [];
  
  constructor(private dataService: DataService) {}

  ngOnChanges() {
    this.bills = this.getServiceBills(this.service.id);
  }

  getServiceBills(id: number): Bill[] {
    return this.dataService.bills.filter(bill => bill.service.id === id);
  }
}
