package com.keys.api.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.keys.api.entity.User;
import com.keys.api.exceptions.ObjectNotFoundException;
import com.keys.api.exceptions.UserAlreadyException;
import com.keys.api.repository.UserRepository;

//service of entity user, this class can be injected to apply the business logic

@Service
public class UserService {

    //this inject the user repository in this context
    @Resource
    private UserRepository userRepository;

    //creates a new user
    public void save ( User user ) {
        //first we need verify if there is a users with same email
        Optional< User > userPrevious = this.findByEmail( user.getEmail() );
        if ( userPrevious.isPresent() ) {
            throw new UserAlreadyException( "Usuário já cadastrado com este email" );
        }

        //by default a user must be created as inactive and requires Validation by a admin user
        user.setActive( false );
        user.setRequiresValidation( true );

        //TODO: later we will encrypt the user password with BcryptPasswordEncoder of spring security
        this.userRepository.save( user );
    }

    //update a existing user
    public void updateUser ( User user, Long id ) {
        //first we need verify if user exist
        User userPrevious = this.findById( id );

        //this will copy the new properties from user to previous user, ignoring the id and password
        BeanUtils.copyProperties( user, userPrevious, "password", "id" );
        this.userRepository.save( userPrevious );
    }

    //update properties active and requiresValidation
    public void updatePropertiesActiveAndRequiresValidation(Boolean active, Boolean requiresValidation, Long id){
        //first we need verify if user exist
        User user = this.findById( id );
        user.setActive( active );
        user.setRequiresValidation( requiresValidation );
        this.userRepository.save( user );
    }


    //list all users
    public List< User > list () {
        return this.userRepository.findAll();
    }

    //search an user by id
    public User findById ( Long id ) {
        Optional< User > user = this.userRepository.findById( id );
        if ( user.isEmpty() ) {
            throw new ObjectNotFoundException( "Usuário não encontrado!" );
        }
        return user.get();
    }

    //search an user by email
    public Optional< User > findByEmail ( String email ) {
        return this.userRepository.findByEmail( email );
    }

    //delete an user by id
    public void delete ( Long id ) {
        //first we need verify if user exist
        //the method findById will verify if user exist and if false throws an exception
        User user = this.findById( id );
        this.userRepository.delete( user );
    }
}
