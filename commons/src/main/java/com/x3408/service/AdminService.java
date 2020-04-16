package com.x3408.service;

import com.x3408.entity.Admin;

public interface AdminService {
    Integer registerAdmin(Admin admin);

    Integer getAdminCount();
}
