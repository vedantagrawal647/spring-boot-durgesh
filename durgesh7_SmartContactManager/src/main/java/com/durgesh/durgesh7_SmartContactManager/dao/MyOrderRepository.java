package com.durgesh.durgesh7_SmartContactManager.dao;

import com.durgesh.durgesh7_SmartContactManager.entities.MyOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  MyOrderRepository extends JpaRepository<MyOrder, Long> {

    public MyOrder findByOrderId(String orderId);



}
