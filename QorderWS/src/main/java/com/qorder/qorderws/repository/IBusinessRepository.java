package com.qorder.qorderws.repository;

import com.qorder.qorderws.model.business.Business;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Grigorios
 */
public interface IBusinessRepository extends JpaRepository<Business, Long> {

}
