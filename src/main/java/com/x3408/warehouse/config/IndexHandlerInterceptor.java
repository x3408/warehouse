package com.x3408.warehouse.config;

import com.x3408.warehouse.entity.Weather;
import com.x3408.warehouse.service.AdminService;
import com.x3408.warehouse.service.CargoService;
import com.x3408.warehouse.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class IndexHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    WeatherService weatherService;
    @Autowired
    CargoService cargoService;
    @Autowired
    AdminService adminService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //先从Redis中查询
        //获得天气数据
        Weather weatherData = weatherService.getWeatherData();
        //查询货物总数
        Integer cargoCount = cargoService.getCargoCount();
        //数据库查询管理员人数
        Integer adminCount = adminService.getAdminCount();
        Integer inboundNum = cargoService.getInboundNum();
        Integer outboundNum = cargoService.getOutboundNum();

        //放进request域中回显
        request.setAttribute("adminCount",adminCount);
        request.setAttribute("cargoCount",cargoCount);
        request.setAttribute("weathers",weatherData);
        request.setAttribute("inboundNum",inboundNum);
        request.setAttribute("outboundNum",outboundNum);

        return true;
    }
}
