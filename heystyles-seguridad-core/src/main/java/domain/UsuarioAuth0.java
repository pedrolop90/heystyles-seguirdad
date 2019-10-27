package domain;


import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsuarioAuth0 {

    private String userId;

    @NotNull
    @Size(min = 1, max = 15)
    private String userName;

    @NotNull
    @Size(min = 8, max = 30)
    private String password;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String nombre;

    @NotNull
    private String apellido;

    @NotNull
    private String roleIdSecurity;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleIdSecurity() {
        return roleIdSecurity;
    }

    public void setRoleIdSecurity(String roleIdSecurity) {
        this.roleIdSecurity = roleIdSecurity;
    }
}
