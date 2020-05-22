package com.deal.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.deal.model.ERole;
import com.deal.model.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

	Optional<Role> findByName(ERole name);
}
