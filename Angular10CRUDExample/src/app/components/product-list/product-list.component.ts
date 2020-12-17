import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';
import {Product} from '../../model/product.model';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: any;
  currentProduct: Product | undefined;
  currentIndex = -1;
  name = '';

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.readProducts();
  }

  readProducts(): void {
    this.productService.readAll()
      .subscribe(
        products => {
          this.products = products;
          console.log(products);
        },
        error => {
          console.log(error);
        });
  }

  refresh(): void {
    this.readProducts();
    this.currentProduct = undefined;
    this.currentIndex = -1;
  }

  setCurrentProduct(product: Product, index: number): void {
    this.currentProduct = product;
    this.currentIndex = index;
  }

  deleteAllProducts(): void {
    this.productService.deleteAll()
      .subscribe(
        response => {
          console.log(response);
          this.readProducts();
        },
        error => {
          console.log(error);
        });
  }

  searchByName(): void {
    this.productService.searchByName(this.name)
      .subscribe(
        products => {
          this.products = products;
          console.log(products);
        },
        error => {
          console.log(error);
        });
  }
}
