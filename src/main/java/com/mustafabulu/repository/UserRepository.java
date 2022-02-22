package com.mustafabulu.repository;

import com.mustafabulu.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findUserEntitiesByIdentificationNumber(Long identificationNumber);
}
