package com.studi.joticketing.Repository;

import com.studi.joticketing.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}