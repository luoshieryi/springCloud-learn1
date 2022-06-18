package loli.gothic.order.service;

import loli.gothic.feign.client.UserClient;
import loli.gothic.feign.pojo.User;
import loli.gothic.order.mapper.OrderMapper;
import loli.gothic.order.pojo.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OrderService {

    private final OrderMapper orderMapper;

    private final UserClient userClient;

//    private final RestTemplate restTemplate;

//    private static final String USER_URL = "http://user-service/user/";

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        User user = userClient.queryById(order.getUserId());
        order.setUser(user);
        // 4.返回
        return order;
    }

//    public Order queryOrderById(Long orderId) {
//        // 1.查询订单
//        Order order = orderMapper.findById(orderId);
//        String userUrl = USER_URL + order.getUserId();
//        System.out.println(USER_URL);
//        User user = restTemplate.getForObject(userUrl, User.class);
//        order.setUser(user);
//        // 4.返回
//        return order;
//    }
}
