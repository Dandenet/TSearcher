package com.example.tsearcher.service;

import com.example.tsearcher.models.Subject;
import com.example.tsearcher.models.User;

import java.util.Collection;
import java.util.Map;


public interface UserJavaService {

	Collection<User> users();

	User user(Long id);

	Map<Subject, Integer> scores(User user);

	User createUser(String name);

	void deleteUser(Long id);

}
