package domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.heystyles.common.types.DomainBean;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAuth0 extends DomainBean<Long> {

    private Long id;

    @NotNull
    @Size(min = 1, max = 15)
    private String usuario;

    @NotNull
    @Size(min = 8, max = 30)
    private String password;

    @NotNull
    @Email
    private String email;

    @NotNull
    private Long roleIdSecurity;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

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

    public Long getRoleIdSecurity() {
        return roleIdSecurity;
    }

    public void setRoleIdSecurity(Long roleIdSecurity) {
        this.roleIdSecurity = roleIdSecurity;
    }
}
