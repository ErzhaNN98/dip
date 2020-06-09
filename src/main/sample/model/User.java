package main.sample.model;

import java.util.Objects;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String name, surname;
    private String phoneNumber;

    @Override
    public String toString() {
        return "{" +
                "\"firmId\": \"" + 1 + "\"," +
                "\"login\": \"" + username + "\"," +
                "\"password\": \"" + password + "\"," +
                "\"name\": \"" + name + "\"," +
                "\"surname\": \"" + surname + "\"," +
                "\"phone\": \"" + phoneNumber + "\"," +
                "\"position\": \"" + 1 + "\"" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, name, surname);
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public User(Integer id, String username, String password, String name, String surname, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }
}
