import { Subscriber } from "@models/subscriber.model";
import { Service } from "@models/service.model";

export class Bill {
  id: number;
  amount: number;
  service: Service;
  subscriber: Subscriber;
  isPaid: boolean;

  constructor(id: number, amount: number, service: Service, subscriber: Subscriber, isPaid = false) {
    this.id = id;
    this.amount = amount;
    this.service = service;
    this.subscriber = subscriber;
    this.isPaid = isPaid;
  }
}
