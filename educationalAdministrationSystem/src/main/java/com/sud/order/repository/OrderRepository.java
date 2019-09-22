package com.sud.order.repository;

import com.sud.order.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order,String> {

    Page<Order> findByLoginName(String loginName, Pageable pageable);

    Page<Order> findByLoginNameAndStatus(String loginName, Integer status, Pageable pageable);

    Page<Order> findByHandleLoginName(String loginName, Pageable pageable);

    Page<Order> findByStatus(Integer status, Pageable pageable);

    Page<Order> findAll(Pageable pageable);
}
