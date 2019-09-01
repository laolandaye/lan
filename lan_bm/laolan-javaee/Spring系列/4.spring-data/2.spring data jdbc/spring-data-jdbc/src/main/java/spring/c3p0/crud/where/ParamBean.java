package spring.c3p0.crud.where;

import java.util.List;

public class ParamBean {

    private String username;
    private List<String> password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getPassword() {
        return password;
    }

    public void setPassword(List<String> password) {
        this.password = password;
    }
}
