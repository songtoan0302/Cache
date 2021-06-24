package com.iphayao.cache.repository;

import com.iphayao.cache.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
