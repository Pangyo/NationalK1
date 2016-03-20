package com.pky.spring.repository;

import com.pky.spring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 3. 19..
 */
public interface UserRepository extends JpaRepository<User,Long>{
    User findByName(String name);
}
