package com.qorder.qorderws.repository;

import com.qorder.qorderws.model.product.Product;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Grigorios
 */
public interface IProductRepository extends CrudRepository<Product, Long> {

}
