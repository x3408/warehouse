package com.x3408.warehouse.service.impl;

import com.x3408.warehouse.mapper.CargoMapper;
import com.x3408.warehouse.entity.Cargo;
import com.x3408.warehouse.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class CargoServiceImpl implements CargoService {
    @Autowired
    CargoMapper cargoMapper;
    @Override
    public List<Cargo> listCargo() {

        return cargoMapper.getAllCargo();
    }

    @Override
    public Integer getCargoCount() {
        return cargoMapper.getCargoCount();
    }

    @Override
    public Integer addCargo(Cargo cargo) {
        //设置存储时间
        Timestamp timestamp = new Timestamp(new Date().getTime());
        cargo.setDate(timestamp);
        return cargoMapper.addCargo(cargo);
    }

    @Override
    public Cargo getCargo(Integer id) {
        return cargoMapper.getCargoById(id);
    }

    @Override
    public Integer editCargo(Cargo cargo) {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        cargo.setDate(timestamp);
        return cargoMapper.editCargoById(cargo);
    }

    @Override
    public Integer outBound(Integer id) {
        Cargo cargo = getCargo(id);
        cargo.setStat(3);
        return this.editCargo(cargo);
    }
}
