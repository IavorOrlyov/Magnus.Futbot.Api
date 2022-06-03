package com.magnus.futbot.database.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Profile {

    @Id @GeneratedValue private String id;
    private String email;

    private String password;
    private Date createdDate;

    public Profile() { }

    public Profile(String email, String password, Date createdDate) {
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(id, profile.id) && Objects.equals(email, profile.email) && Objects.equals(password, profile.password) && Objects.equals(createdDate, profile.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, createdDate);
    }
}
