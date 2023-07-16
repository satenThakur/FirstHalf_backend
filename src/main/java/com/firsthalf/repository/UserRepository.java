package com.firsthalf.repository;

import com.firsthalf.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Long> {



@Query(value = "{'email':?0}")
User findByUserEmail(String email);

    @Query(value = "{'email':?0}")
    Boolean existsByEmail(String email);

    @Query(value = "{'phoneNumber':?0}")
    User existsByPhoneNumber(String phoneNumber);
}
