package com.heavydelay.model.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.heavydelay.validation.ValidPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserUpdateDto {

    public interface RegisterUserView {}
    public interface LoginUserView {}
    public interface PasswordUpdateView {}
    public interface EmailUpdateView {}
    public interface OtherValuesUpdateView {}

    // Basic DATA
    @JsonView({OtherValuesUpdateView.class, RegisterUserView.class})
    @NotBlank(groups =  {OtherValuesUpdateView.class, RegisterUserView.class}, message = "'name' cannot be empty")
    @Size(min = 2, max = 50, message = "The 'name' must be between 2 and 50 characters long")
    private String name;

    @JsonView(OtherValuesUpdateView.class)
    @NotBlank(groups =  OtherValuesUpdateView.class, message = "The 'role name' cannot be empty")
    private String roleName;

    @JsonView({OtherValuesUpdateView.class, RegisterUserView.class})
    @NotBlank(groups =  {OtherValuesUpdateView.class, RegisterUserView.class}, message = "The 'lastname' cannot be empty")
    @Size(min = 2, max = 50, message = "The 'lastname' must be between 2 and 50 characters long")
    private String lastname;

    @JsonView({OtherValuesUpdateView.class, RegisterUserView.class})
    @NotBlank(groups =  {OtherValuesUpdateView.class, RegisterUserView.class}, message = "The 'username' cannot be empty")
    @Size(min = 2, max = 50, message = "The 'username' must be between 2 and 50 characters long")
    private String username;

    @JsonView(OtherValuesUpdateView.class)
    @NotNull(groups =  OtherValuesUpdateView.class, message = "The 'description' is required")
    @Size(max = 255, message = "The 'description' cannot exceed 255 characters")
    private String description;

    @JsonIgnore
    @NotNull(message = "The 'Last Conecction' is required")
    private String lastConnection;

    // Password DATA

    @JsonView(PasswordUpdateView.class)
    @NotBlank(groups =  PasswordUpdateView.class, message = "The 'Old Password' cannot be empty")
    private String oldPasword;

    @JsonView(PasswordUpdateView.class)
    @NotBlank(groups =  PasswordUpdateView.class, message = "The 'New Password' cannot be empty")
    @ValidPassword(groups =  PasswordUpdateView.class)
    private String newPassword;

    @JsonView({RegisterUserView.class, LoginUserView.class})
    @NotBlank(groups =  {PasswordUpdateView.class, LoginUserView.class}, message = "The 'Password' cannot be empty")
    @ValidPassword(groups =  RegisterUserView.class)
    private String password;

    // Email DATA

    @JsonView(EmailUpdateView.class)
    @NotBlank(groups =  EmailUpdateView.class, message = "Old email cannot be empty")
    private String oldEmail;

    @JsonView(EmailUpdateView.class)
    @NotBlank(groups =  EmailUpdateView.class, message = "New email cannot be empty")
    @Email(groups =  EmailUpdateView.class, message = "It must be a valid email")
    private String newEmail;

    @JsonView({RegisterUserView.class, LoginUserView.class})
    @NotBlank(groups =  {RegisterUserView.class, LoginUserView.class}, message = "The 'email' cannot be empty")
    @Email(groups =  {RegisterUserView.class, LoginUserView.class}, message = "It must be a valid email")
    private String email;



}
