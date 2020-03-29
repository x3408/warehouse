package com.x3408.warehouse.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.x3408.warehouse.entity.Cargo;
import com.x3408.warehouse.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CargoController {
    @Autowired
    CargoService cargoService;

    @GetMapping("/listCargo")
    public String listCargo(HttpSession session) {
        List<Cargo> cargos = cargoService.listCargo();
        session.setAttribute("cargos", cargos);
        return "listCargo";
    }

    @ResponseBody
    @PostMapping("/addCargo")
    public String addCargo(Cargo cargo) {
        Integer successCount = cargoService.addCargo(cargo);
        Map<String, String> map = new HashMap<>();
        if (successCount == 1)
            map.put("data", "1");
        else
            map.put("data", "0");
        //设置提示
        return JSONUtils.toJSONString(map);
    }

    @GetMapping("/getCargoToEdit/{id}")
    public String toEdit(@PathVariable("id") Integer id, HttpServletRequest request) {
        Cargo cargo = cargoService.getCargo(id);
        request.setAttribute("cargo", cargo);
        return "editCargo";
    }

    @ResponseBody
    @PutMapping("/editCargo")
    public String editCargo(Cargo cargo) {
        Integer successCount = cargoService.editCargo(cargo);
        Map<String, String> map = new HashMap<>();
        if (successCount == 1)
            map.put("data", "1");
        else
            map.put("data", "0");
        //设置提示
        return JSONUtils.toJSONString(map);
    }

    @ResponseBody
    @PutMapping("/outBound")
    public String outBound(@RequestParam("id") Integer id) {
        Integer successCount = cargoService.outBound(id);
        Map<String, String> map = new HashMap<>();
        if (successCount == 1)
            map.put("data", "1");
        else
            map.put("data", "0");
        //设置提示
        return JSONUtils.toJSONString(map);
    }

}
