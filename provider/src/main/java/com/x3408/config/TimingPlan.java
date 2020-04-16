package com.x3408.config;

import com.x3408.mapper.CargoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configurable
public class TimingPlan {
    @Autowired
    private CargoMapper cargoMapper;

    //定时计划每天0点清空入库数和出库数
    @Scheduled(cron = "0 0 0 1/1 * ?")
    public void doTimingPlan() {
        cargoMapper.refreshCargoNum();
    }
}
