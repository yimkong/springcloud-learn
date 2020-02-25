package com.yimkong;

import org.springframework.web.bind.annotation.*;

/**
 * author yg
 * description
 * date 2020/2/25
 */
@RestController
public class HouseController {
    @GetMapping("/house/data")
    public HouseInfo getData(@RequestParam("name") String name) {
        return new HouseInfo(1L, "广州", "天河", "珠江新城");
    }

    @GetMapping("/house/data/{name}")
    public String getData2(@PathVariable("name") String name) {
        return name;
    }

    @PostMapping("/house/save")
    public Long addData(@RequestBody HouseInfo houseInfo) {
        System.out.println(houseInfo.getName());
        return houseInfo.getId();
    }

}
