package com.itmuch.dao;

import com.itmuch.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by leo on 2017/7/28.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
