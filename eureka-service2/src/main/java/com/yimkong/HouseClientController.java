package com.yimkong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * author yg
 * description 测试调用
 * date 2020/2/25
 */
@RestController
public class HouseClientController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/call/data")
    public HouseInfo getData(@RequestParam("name") String name) {
        return restTemplate.getForObject("http://eureka-client2/house/data?name=" + name, HouseInfo.class);
    }

    @GetMapping("/call/data/{name}")
    public String getData2(@PathVariable("name") String name) {
        return restTemplate.getForObject("http://localhost:8082/house/data/{name}", String.class, name);
    }

    @GetMapping("/call/dataEntity")
    public HouseInfo getData3(@RequestParam("name") String name) {
        ResponseEntity<HouseInfo> forEntity = restTemplate.getForEntity("http://localhost:8082/house/data?name=" + name, HouseInfo.class);
        if (forEntity.getStatusCodeValue() == 200) {
            return forEntity.getBody();
        }
        return null;

    }

    @GetMapping("/call/save")
    public Long add() {
        HouseInfo houseInfo = new HouseInfo(2L, "佛山", "大良", "清晖园");
        Long id = restTemplate.postForObject("http://localhost:8082/house/save", houseInfo, Long.class);
        return id;
    }

}
