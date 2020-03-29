package com.x3408.warehouse.service.impl;

import com.x3408.warehouse.entity.Admin;
import com.x3408.warehouse.mapper.AdminMapper;
import com.x3408.warehouse.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public Integer registerAdmin(Admin admin) {
        admin.setStatus(1);     //更改在藉状态
        return adminMapper.insertAdmin(admin);

    }

    @Override
    public Integer getAdminCount() {
        return adminMapper.getAdminCount();
    }
}
