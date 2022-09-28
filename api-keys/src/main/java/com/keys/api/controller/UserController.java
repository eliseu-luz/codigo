package com.keys.api.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keys.api.entity.User;
import com.keys.api.service.UserService;

@RestController
@RequestMapping( "/user" )
public class UserController {

    //this inject a UserService implementation in this context
    @Resource
    private UserService userService;


    //endpoint to create a new user
    @PostMapping
    public ResponseEntity< ? > save ( @Valid @RequestBody User user ) {
        this.userService.save( user );
        return ResponseEntity.status( HttpStatus.CREATED ).build();
    }

    //endpoint to update a existing user
    @PutMapping( "/{id}" )
    public ResponseEntity< ? > update ( @RequestBody User user, @PathVariable Long id ) {
        this.userService.updateUser( user, id );
        return ResponseEntity.status( HttpStatus.NO_CONTENT ).build();
    }

    //endpoint to validate a new user
    @PutMapping( "/validate/{id}" )
    public ResponseEntity< ? > updatePropertiesActiveAndRequiresValidation (
        @PathVariable Long id, @RequestParam Boolean active, @RequestParam Boolean requiresValidation ) {
        this.userService.updatePropertiesActiveAndRequiresValidation( active, requiresValidation, id );
        return ResponseEntity.status( HttpStatus.NO_CONTENT ).build();
    }

    //endpoint to list the users
    @GetMapping
    public ResponseEntity< ? > list () {
        return ResponseEntity.ok( this.userService.list() );
    }

    //endpoint to search a user by id
    @GetMapping( "/{id}" )
    public ResponseEntity< ? > findById ( @PathVariable Long id ) {
        return ResponseEntity.ok( this.userService.findById( id ) );
    }

    //endpoint to delete a user by id
    @DeleteMapping( "/{id}" )
    public ResponseEntity< ? > deleteById ( @PathVariable Long id ) {
        this.userService.delete( id );
        return ResponseEntity.status( HttpStatus.NO_CONTENT ).build();
    }
}
