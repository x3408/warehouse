package com.x3408.warehouse.mapper;


import com.x3408.warehouse.entity.Cargo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoMapper {
    @Select("select * from cargo order by date")
    List<Cargo> getAllCargo();

    @Select("select sum(amount) from cargo")
    Integer getCargoCount();

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into cargo(cargoName,description,amount,stat,date) " +
            "values(#{cargoName},#{description},#{amount},#{stat},#{date})")
    Integer addCargo(Cargo cargo);

    @Select("select * from cargo where id=#{id}")
    Cargo getCargoById(Integer id);

    @Update("update cargo set cargoName=#{cargoName},description=#{description},amount=#{amount},stat=#{stat} where id=#{id}")
    Integer editCargoById(Cargo cargo);

    @Update("update cargonum set inboundNum=0,outboundNum=0 where id=1")
    void refreshCargoNum();

    @Select("select inboundNum from cargonum where id=1")
    Integer getInboundNum();

    @Select("select outboundNum from cargonum where id=1")
    Integer getOutboundNum();

    @Update("update cargonum set outboundNum=outboundNum+1 where id=1")
    void addOutboundNum();

    @Update("update cargonum set inboundNum=inboundNum+1 where id=1")
    void addInboundNum();
}
