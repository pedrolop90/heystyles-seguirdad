package domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.heystyles.common.types.DomainBean;
import com.heystyles.common.util.Constants;

import java.time.LocalDateTime;

public class LoginHistory extends DomainBean<String> {

    private String id;

    private String username;

    private String ip;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.FORMAT_DATE_TIME)
    private LocalDateTime date;

    public LoginHistory(String username, LocalDateTime date, String ip) {
        this.username = username;
        this.date = date;
        this.ip = ip;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
