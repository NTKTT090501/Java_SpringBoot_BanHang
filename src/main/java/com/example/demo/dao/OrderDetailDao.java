package com.example.demo.dao;


import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.example.demo.entity.OrderDetail;

public interface OrderDetailDao extends JpaRepositoryImplementation<OrderDetail, Long> {

}
