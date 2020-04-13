package com.x3408.warehouse;

import com.alibaba.druid.support.json.JSONUtils;
import com.x3408.warehouse.controller.CargoController;
import com.x3408.warehouse.entity.Admin;
import com.x3408.warehouse.entity.Cargo;
import com.x3408.warehouse.entity.Weather;
import com.x3408.warehouse.entity.WeatherDetail;
import com.x3408.warehouse.mapper.AdminMapper;
import com.x3408.warehouse.service.AdminService;
import com.x3408.warehouse.service.CargoService;
import com.x3408.warehouse.service.WeatherService;
import com.x3408.warehouse.service.impl.AdminServiceImpl;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

@SpringBootTest
class WarehouseApplicationTests {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    WeatherService weatherService;
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
    void weatherTest() throws InterruptedException {
        Weather weatherData = weatherService.getWeatherData();
        System.out.println(weatherData.getData().get(0).getTem());

        sleep(1000);
        Weather wea = weatherService.getWeatherData();
        System.out.println(wea.getData().get(0).getTem());
    }

    @Test
    void strTest() {
        String str = "28日（今天）";
        String s = str.replaceAll("\\（.*?\\）", "");
        System.out.println(str);
        System.out.println(s);
    }

    @Test
    void redisTest() {
        Weather weather = weatherService.getWeatherData();
        stringRedisTemplate.opsForHash().put("weather","city",weather.getCity());
        stringRedisTemplate.opsForHash().put("weather","update_time",weather.getUpdate_time());
        for (WeatherDetail detail :
                weather.getData()) {
            stringRedisTemplate.opsForList().leftPush("weather_day",detail.getDay());
            stringRedisTemplate.opsForList().leftPush("weather_tem",detail.getTem());
            stringRedisTemplate.opsForList().leftPush("weather_img",detail.getWea_img());
            stringRedisTemplate.opsForList().leftPush("weather_wea",detail.getWea());
            stringRedisTemplate.opsForList().leftPush("weather_week",detail.getWeek());
            stringRedisTemplate.opsForList().leftPush("weather_speed",detail.getWin_speed());
        }
    }

}
