package com.example.doctorbe.repositories;

import com.example.doctorbe.models.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Repositories define the operations that can be done to data sources
//     Insert, remove, edit, delete, search, ect...
@Repository
public interface UserRepository extends CrudRepository<AppUser, Long> {
//    Optional - an easier way to write null safe code
    public Optional<AppUser> findAppUserByUsernameAndPassword(String username, String password);

    public Optional<AppUser> findAppUserByUsername(String username);

}
