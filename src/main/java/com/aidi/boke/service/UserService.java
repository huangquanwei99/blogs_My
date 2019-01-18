package com.aidi.boke.service;


import com.aidi.boke.domain.User;


public interface UserService {

   User selectUserByName(String user);

   Integer saveUser(User user);

   User loginUser(User user);
}
