package com.x3408.service.impl;


import com.x3408.service.WeatherService;
import org.apache.dubbo.config.annotation.Service;
import com.x3408.entity.Weather;
import com.x3408.entity.WeatherDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@CacheConfig(cacheNames = "Weather")
public class WeatherServiceImpl implements WeatherService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    @Cacheable(value = "'weatherData'")
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
        System.out.println("查询天气----未从缓存中");

        return weather;
    }
}
