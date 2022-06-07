package com.magnus.futbot.database.entities;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;

@Document(collection = "profile")
public class ProfileDocument {

    private ObjectId _id;
    private String email;

    private String password;

    public ProfileDocument() {
    }

    public ProfileDocument(String email, String password, Date createdDate) {
        this.email = email;
        this.password = password;
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

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileDocument profileDocument = (ProfileDocument) o;
        return Objects.equals(email, profileDocument.email) && Objects.equals(password, profileDocument.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%s, email='%s', createdDate='%s']",
                _id, email, "asd");
    }
}
