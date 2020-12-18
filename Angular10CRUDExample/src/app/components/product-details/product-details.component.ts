import {Component, OnInit} from '@angular/core';
import {ProductService} from 'src/app/services/product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Product} from '../../model/product.model';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  public = null;
  message = '';
  // @ts-ignore
  currentProduct: Product;

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router,
  ) {
  }

  ngOnInit(): void {
    this.message = '';
    this.getProduct(this.route.snapshot.paramMap.get('id'));
  }

  getProduct(id: any): void {
    this.productService.read(id)
      .subscribe(
        product => {
          this.currentProduct = product[0];
          console.log(product);
        },
        error => {
          console.log(error);
        });
  }

  setAvailableStatus(status: boolean): void {
    this.currentProduct.available = status;
    this.productService.update(this.currentProduct.id, this.currentProduct)
      .subscribe(
        response => {
          // @ts-ignore
          this.currentProduct.available = status;
          console.log(response);
        },
        error => {
          console.log(error);
        });
  }

  updateProduct(): void {
    this.productService.update(this.currentProduct.id, this.currentProduct)
      .subscribe(
        response => {
          console.log(response);
          this.message = 'The product was updated!';
        },
        error => {
          console.log(error);
        });
  }

  deleteProduct(): void {
    this.productService.delete(this.currentProduct.id)
      .subscribe(
        response => {
          console.log(response);
          this.router.navigate(['/products']);
        },
        error => {
          console.log(error);
        });
  }
}
