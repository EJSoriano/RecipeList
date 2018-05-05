package com.RecipeList.FirstSpring;

import org.springframework.data.repository.CrudRepository;

import java.util.*;

public interface UserRepository extends CrudRepository<User, Long>{

	List<User> findByLogin(String login);
}
