package com.ideaco.dia.backendproject.repository;

import com.ideaco.dia.backendproject.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    @Query(value = "SELECT * from dia_batch3.tab_sandy ts WHERE ts.email= :email AND ts.password = :password", nativeQuery = true)
    UserModel getUsers(@Param("email") String email, @Param("password") String password);
    Optional<UserModel> findById(Integer id);
    UserModel findByEmail(@Param("email") String email);

}
