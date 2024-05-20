package com.ssafy.trip.domain.user.mapper;

import com.ssafy.trip.domain.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    List<User> findAll();

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long userId);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    void save(User user);

    void update(User user);

    void updateIsLocked(Long userId);

    void delete(Long userId);
}
