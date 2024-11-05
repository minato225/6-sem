import { Component, Input } from "@angular/core";
import { Service } from "@core/models";


@Component({
  selector: 'service-list, [service-list]',
  templateUrl: './service-list.component.html',
  styleUrls: ['./service-list.component.scss']
})
export class ServiceListComponent {
  @Input() items: Service[] = [];
  activeItem = -1;

  onItemClick(index: number) {
    this.activeItem = this.activeItem == index ? -1: index;
  }
}
