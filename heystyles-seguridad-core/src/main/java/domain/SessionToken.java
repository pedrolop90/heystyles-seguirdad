package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionToken {
    private MenuExtended menuExtended;
    private String email;

    public MenuExtended getMenuExtended() {
        return menuExtended;
    }

    public void setMenuExtended(MenuExtended menuExtended) {
        this.menuExtended = menuExtended;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
