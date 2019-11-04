package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionToken {
    private MenuExtended menuExtended;
    private String usuario;

    public MenuExtended getMenuExtended() {
        return menuExtended;
    }

    public void setMenuExtended(MenuExtended menuExtended) {
        this.menuExtended = menuExtended;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
