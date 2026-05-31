package com.FoodApp.FoodiesApi.repository;

import com.FoodApp.FoodiesApi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email); // Login check karne ke liye kaam aayega
}
