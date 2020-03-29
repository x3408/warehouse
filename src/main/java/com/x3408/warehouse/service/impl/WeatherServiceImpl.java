package com.x3408.warehouse.service.impl;

import com.x3408.warehouse.entity.Weather;
import com.x3408.warehouse.entity.WeatherDetail;
import com.x3408.warehouse.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Override
    public Weather getWeatherData() {
        RestTemplate restTemplate = new RestTemplate();
        Weather weather = restTemplate.getForObject("https://tianqiapi.com/api?appid=23287568&appsecret=HSYCC4Zj",
                Weather.class);

        List<WeatherDetail> data = weather.getData();
        for (WeatherDetail detail : data) {
            detail.setDay(detail.getDay().replaceAll("\\（.*?\\）", ""));
            switch (detail.getWea_img()) {
                case "qing":
                case "shachen":
                    detail.setWea_img("clear-day");
                    break;
                case "yin":
                    detail.setWea_img("partly-cloudy-day");
                    break;
                case "yun":
                case "wu":
                    detail.setWea_img("cloudy");
                    break;
                case "bingbao":
                case "xue":
                    detail.setWea_img("snow");
                    break;
                case "lei":
                case "yu":
                    detail.setWea_img("rain");
                    break;
            }
        }

        return weather;
    }
}
