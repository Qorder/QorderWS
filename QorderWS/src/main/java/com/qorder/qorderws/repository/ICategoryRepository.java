package com.qorder.qorderws.repository;

import com.qorder.qorderws.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Grigorios
 */
public interface ICategoryRepository extends JpaRepository<Category, Long> {

}
