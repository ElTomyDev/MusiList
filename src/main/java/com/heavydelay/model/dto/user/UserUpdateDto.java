package com.heavydelay.model.dto.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.heavydelay.enums.UserStatus;
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
    public interface NameUpdateView {}
    public interface RoleUpdateView {}
    public interface LastnameUpdateView {}
    public interface UsernameUpdateView {}
    public interface DescriptionUpdateView {}
    public interface StatusUpdateView {}
    public interface AllValuesUpdateView {}

    // Basic DATA
    @JsonView({AllValuesUpdateView.class, RegisterUserView.class, NameUpdateView.class})
    @NotBlank(groups =  {AllValuesUpdateView.class, RegisterUserView.class, NameUpdateView.class}, message = "'name' cannot be empty")
    @Size(groups =  {AllValuesUpdateView.class, RegisterUserView.class, NameUpdateView.class}, min = 2, max = 50, message = "The 'name' must be between 2 and 50 characters long")
    private String name;

    @JsonView({AllValuesUpdateView.class, RoleUpdateView.class})
    @NotBlank(groups =  {AllValuesUpdateView.class, RoleUpdateView.class}, message = "The 'role name' cannot be empty")
    private String roleName;

    @JsonView({AllValuesUpdateView.class, RegisterUserView.class, LastnameUpdateView.class})
    @NotBlank(groups =  {AllValuesUpdateView.class, RegisterUserView.class, LastnameUpdateView.class}, message = "The 'lastname' cannot be empty")
    @Size(groups =  {AllValuesUpdateView.class, RegisterUserView.class, LastnameUpdateView.class}, min = 2, max = 50, message = "The 'lastname' must be between 2 and 50 characters long")
    private String lastname;

    @JsonView({AllValuesUpdateView.class, RegisterUserView.class, UsernameUpdateView.class})
    @NotBlank(groups =  {AllValuesUpdateView.class, RegisterUserView.class, UsernameUpdateView.class}, message = "The 'username' cannot be empty")
    @Size(groups =  {AllValuesUpdateView.class, RegisterUserView.class, UsernameUpdateView.class}, min = 2, max = 50, message = "The 'username' must be between 2 and 50 characters long")
    private String username;

    @JsonView({AllValuesUpdateView.class, StatusUpdateView.class})
    @NotBlank(groups =  {AllValuesUpdateView.class, StatusUpdateView.class}, message = "The 'username' cannot be empty")
    @Size(groups =  {AllValuesUpdateView.class, StatusUpdateView.class}, min = 2, max = 50, message = "The 'username' must be between 2 and 50 characters long")
    private UserStatus status;

    @JsonView({AllValuesUpdateView.class, DescriptionUpdateView.class})
    @NotNull(groups =  {AllValuesUpdateView.class, DescriptionUpdateView.class}, message = "The 'description' is required")
    @Size(groups =  {AllValuesUpdateView.class, DescriptionUpdateView.class}, max = 255, message = "The 'description' cannot exceed 255 characters")
    private String description;

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
    @NotBlank(groups =  RegisterUserView.class, message = "The 'email' cannot be empty")
    private String email;



}
