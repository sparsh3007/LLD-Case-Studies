package com.lldcasestudies.bookmyshow.repositories;

import com.lldcasestudies.bookmyshow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    Optional<User> findById(Long userId);
    @Override
    List<User> findAllById(Iterable<Long> userIds);
}
