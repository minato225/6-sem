import { Service } from "@core/models";

export const ServicesMock = [
  new Service(0, 'Calls within the network', 100),
  new Service(1, 'Calls to other networks', 200),
  new Service(2, 'Calls abroad', 300),
  new Service(3, 'Internet 1GB', 100),
  new Service(4, 'Internet 2GB', 200),
  new Service(5, 'Internet 3GB', 300),
  new Service(6, 'Internet 5GB', 450),
  new Service(7, 'Internet 10GB', 700),
  new Service(8, 'Internet unlimit', 1200),
]
