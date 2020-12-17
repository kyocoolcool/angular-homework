export class Product {
  public id: number;
  public name: string;
  public description: string;
  public available: boolean;

  constructor(id: number, name: string, description: string, available: boolean) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.available = available;
  }
}
