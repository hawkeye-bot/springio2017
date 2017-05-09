package com.acme.warehouse.domain.repository;

import com.acme.warehouse.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to store orders.
 *
 * @author ajorritsma
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
