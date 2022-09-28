package com.keys.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//this annotation is necessary to hibernate understand that is is an entity
@Entity

//defines the table name
@Table( name = "\"user\"" )

//this will create the getter's setter's, constructor's and builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    //validation of password(you can change this message and values of length later)
    @NotNull(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve conter pelo menos seis(6) caracteres")
    private String password;

    //validation of name(you can change this message and values of length later)
    @NotNull(message = "Nome é obrigatório")
    @Size(min = 6, message = "Nome deve conter pelo menos seis(6) caracteres")
    private String name;

    //validation of email(you can change this message and values of length later)
    @NotNull(message = "Email é obrigatório")
    @Size(min = 6, message = "Email deve conter pelo menos seis(6) caracteres")
    private String email;
    private Integer filial;

    //this annotation (@column) is necessary only if the name has camelCase
    @Column(name="requires_validation")
    //requiresValidation: to control when a user register if requiresValidation is true, an admin user must accept ou decline
    private Boolean requiresValidation;

    //to deactivate a user
    private Boolean active;
}
