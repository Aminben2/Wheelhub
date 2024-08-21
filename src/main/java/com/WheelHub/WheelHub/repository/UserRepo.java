package com.WheelHub.WheelHub.repository;

import com.WheelHub.WheelHub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
