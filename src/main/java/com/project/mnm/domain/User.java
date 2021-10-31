package com.project.mnm.domain;

public class User {
    private long id;
    private String email;
    private String password;
    private String type;
    private boolean matching;
    private String enter;
    private String exit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isMatching() {
        return matching;
    }

    public void setMatching(boolean matching) {
        this.matching = matching;
    }

    public String getEnter() {
        return enter;
    }

    public void setEnter(String enter) {
        this.enter = enter;
    }

    public String getExit() {
        return exit;
    }

    public void setExit(String exit) {
        this.exit = exit;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", matching=" + matching +
                ", enter='" + enter + '\'' +
                ", exit='" + exit + '\'' +
                '}';
    }
}
