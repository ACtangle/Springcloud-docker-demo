package com.melon.springcloud.feign;

import com.melon.springcloud.pojo.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName UserFeignClient
 * @Description
 * @Author melon
 * @Date 2019-07-26 00:04
 * @Version
 */

@FeignClient(name = "mircroservice-provider-user",fallback = UserFeignClient.FeignClientFallback.class)
public interface UserFeignClient {

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);

    /**
     * 回退类FeignClientFallback需实现FeignClient接口
     * FeignClientFallback也可以是public class，没有区别
     */

    @Component
    class FeignClientFallback implements UserFeignClient {
        @Override
        public User findById(Long id) {
            User user = new User();
            user.setId(-1L);
            user.setName("默认用户");
            return user;
        }
    }
}
