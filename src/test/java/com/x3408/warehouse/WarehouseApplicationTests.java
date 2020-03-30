package com.x3408.warehouse;

import com.alibaba.druid.support.json.JSONUtils;
import com.x3408.warehouse.controller.CargoController;
import com.x3408.warehouse.entity.Admin;
import com.x3408.warehouse.entity.Cargo;
import com.x3408.warehouse.entity.Weather;
import com.x3408.warehouse.mapper.AdminMapper;
import com.x3408.warehouse.service.AdminService;
import com.x3408.warehouse.service.CargoService;
import com.x3408.warehouse.service.impl.AdminServiceImpl;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class WarehouseApplicationTests {

    @Autowired
    CargoService cargoService;
    @Autowired
    AdminService service;
    @Autowired
    AdminMapper mapper;
    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Test
    void SqlTest() {
        System.out.println(service.getAdminCount());
    }
    @Test
    void cargoTest() {
        cargoService.outBound(1);
    }
    @Test
    void weatherTest() {
        RestTemplate restTemplate = new RestTemplate();
        Weather we = restTemplate.getForObject("https://tianqiapi.com/api?version=v1&appid=23287568&appsecret=HSYCC4Zj",
                Weather.class);
        System.out.println(we);
    }

    @Test
    void strTest() {
        String str = "28日（今天）";
        String s = str.replaceAll("\\（.*?\\）", "");
        System.out.println(str);
        System.out.println(s);
    }

}
