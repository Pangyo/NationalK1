package com.pky.spring.service;

import com.pky.spring.domain.User;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 3. 19..
 */

public interface IUserService {
    public void save(User user);
    public User findByName(String name);
}
