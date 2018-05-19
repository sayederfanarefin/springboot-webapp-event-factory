package com.avnrsol.eventfactory.service.interfaces;

import org.springframework.data.domain.Page;

import com.avnrsol.eventfactory.Model.User;
import com.avnrsol.eventfactory.exception.UserAlreadyExistException;

public interface IUserService {

    User registerNewUserAccount(User account) throws UserAlreadyExistException;


    void saveRegisteredUser(User user);

    User findUserByEmail(String email);

    User getUserByID(long id);


    boolean checkIfValidOldPassword(User user, String password);

   
    public Page<User> findAllUser(int page) ;
  
}
