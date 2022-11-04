import { Injectable } from '@angular/core';
import { Course } from '../components/admin/admin.component';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  constructor() { }

  cart:any[]=[];

  addToCart(course:any){
    let c=localStorage.getItem("cart")
    if(c!=null){
      this.cart = JSON.parse(c);
    }
    this.cart.unshift(course)
    localStorage.setItem('cart', JSON.stringify(this.cart));
  }

  clearCart(){
    this.cart = [];
    localStorage.setItem('cart', JSON.stringify(this.cart));
    return this.cart
  }

  getCart(){
    return JSON.parse(localStorage.getItem('cart')||'[]') || [];
  }
}
