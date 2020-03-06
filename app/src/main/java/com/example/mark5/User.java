package com.example.mark5;

import java.util.List;

public class User {
    private String email;
    private List<String> allergens;

    public User(String email, Object allergenList)
    {
        this.email=email;
        allergens=(List<String>)allergenList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<String> allergens) {
        this.allergens = allergens;
    }

    public String toString()
    {
        return email+" "+allergens;
    }

}
