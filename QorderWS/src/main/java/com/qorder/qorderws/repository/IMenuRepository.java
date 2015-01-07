package com.qorder.qorderws.repository;

import com.qorder.qorderws.model.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Grigorios
 */
public interface IMenuRepository extends JpaRepository<Menu, Long> {

}
