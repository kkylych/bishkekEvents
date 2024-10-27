package com.bishevents.repository;
import com.bishevents.entity.User_;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User_, Long> {
}
