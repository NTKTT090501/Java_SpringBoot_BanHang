package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RoleDao;
import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao dao;

    @Override
    public List<Role> findAll() {
        // TODO Auto-generated method stub
        return dao.findAll();
    }

}
