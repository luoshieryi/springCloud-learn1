package loli.gothic.feign.client;

import loli.gothic.feign.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service", path = "user")
public interface UserClient {

    @GetMapping("{id}")
    User queryById(@PathVariable("id") Long id);

}
