package com.x3408.service;

import com.x3408.entity.Cargo;

import java.util.List;

public interface CargoService {

    List<Cargo> listCargo();

    Integer getCargoCount();

    Integer addCargo(Cargo cargo);

    Cargo getCargo(Integer id);

    Integer editCargo(Cargo cargo);

    Integer outBound(Integer id);

    Integer getInboundNum();

    Integer getOutboundNum();
}
