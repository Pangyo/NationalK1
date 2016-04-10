package org.inspection.nationalk1.service;

import org.inspection.nationalk1.domain.User;
import org.inspection.nationalk1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 3. 19..
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired private UserRepository userRepository;

    public void save(User user){
        userRepository.save(user);
    }

    public User findByName(String name){
        return userRepository.findByName(name);
    }
}
