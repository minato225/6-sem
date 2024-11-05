import { Bill } from "@core/models";
import { ServicesMock } from "@consts/mock/services.mock";
import { SubscribersMock } from "@consts/mock/subscribers.mock";

export const BillsMock = [
  new Bill(0, 100, ServicesMock[0], SubscribersMock[0], true),
  new Bill(1, 200, ServicesMock[1], SubscribersMock[1], true),
  new Bill(2, 300, ServicesMock[4], SubscribersMock[2], false),
  new Bill(3, 100, ServicesMock[0], SubscribersMock[3], true),
  new Bill(4, 300, ServicesMock[5], SubscribersMock[0], true),
  new Bill(5, 100, ServicesMock[3], SubscribersMock[3], true),
  new Bill(6, 450, ServicesMock[6], SubscribersMock[4], true),
  new Bill(7, 700, ServicesMock[7], SubscribersMock[5], true),
  new Bill(8, 100, ServicesMock[2], SubscribersMock[7], false),
  new Bill(9, 200, ServicesMock[2], SubscribersMock[6], true),
  new Bill(10, 1200, ServicesMock[8], SubscribersMock[8], true),
  new Bill(11, 800, ServicesMock[8], SubscribersMock[7], false),
  new Bill(12, 100, ServicesMock[0], SubscribersMock[8], true),
]
