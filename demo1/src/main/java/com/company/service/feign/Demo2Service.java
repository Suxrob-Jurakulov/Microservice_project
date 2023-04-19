package com.company.service.feign;


import com.company.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("demo2")
public interface Demo2Service {

    @PostMapping("/test/message/{id}")
    public UserDTO message(@PathVariable Integer id);
}
