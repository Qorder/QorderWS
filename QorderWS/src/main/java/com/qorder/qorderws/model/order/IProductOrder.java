package com.qorder.qorderws.model.order;
 
import com.qorder.qorderws.model.product.IProduct;
 
public interface IProductOrder {

   public IProduct getProduct();
   
   public void setProduct(IProduct product);
   
   public String getComments();
   
   public void setComments(String comments);
 }