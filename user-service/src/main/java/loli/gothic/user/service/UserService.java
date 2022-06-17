package loli.gothic.user.service;

import loli.gothic.user.mapper.UserMapper;
import loli.gothic.user.pojo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserService {

    private final UserMapper userMapper;

    public User queryById(Long id) {
        return userMapper.findById(id);
    }
}