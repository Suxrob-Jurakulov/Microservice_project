package com.company.service.feign;

import com.company.dto.UserDTO;
import com.company.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("DEMO1")
public interface Demo1Service {

    @PostMapping("/demo1/user/create")
    UserDTO create(@RequestBody UserDTO dto);



    @PostMapping("/demo1/user/find_by_username/{username}")
    UserEntity findByUsername(@PathVariable("username") String username);


}
