import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Product} from '../model/product.model';

const baseURL = '/api';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  constructor(private httpClient: HttpClient) {
  }

  readAll(): Observable<any> {
    return this.httpClient.get(`${baseURL}/products`);
  }

  read(id: number): Observable<any> {
    return this.httpClient.get(`${baseURL}/products/${id}`);
  }

  create(data: Product): Observable<any> {
    return this.httpClient.post(`${baseURL}/products`, data);
  }

  update(id: number, data: Product): Observable<any> {
    return this.httpClient.put(`${baseURL}/product/${id}`, data);
  }

  delete(id: number): Observable<any> {
    return this.httpClient.delete(`${baseURL}/product/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.httpClient.delete(`${baseURL}/products`);
  }

  searchByName(name: string): Observable<any> {
    return this.httpClient.get(`${baseURL}/product?name=${name}`);
  }
}
