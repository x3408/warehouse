package com.x3408.service.impl;


import com.x3408.entity.Admin;
import com.x3408.service.AdminService;
import com.x3408.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.dubbo.config.annotation.Service;

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
