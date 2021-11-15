package com.example.CaseStudyBackend.repository;

import com.example.CaseStudyBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;
@Repository
public interface Userrepository extends JpaRepository<User, Long> {
    @Query(value = "Select * FROM user WHERE username = ?1",nativeQuery = true)
    User findByName(String username);

    @Query(value = "Select * FROM user WHERE username = ?1 and password = ?2",nativeQuery = true)
    User findByNameAndPassword(String username, String password);



}
