package com.x3408.warehouse.service;

import com.x3408.warehouse.entity.Admin;

public interface AdminService {
    Integer registerAdmin(Admin admin);

    Integer getAdminCount();
}
