package com.spyrosoft.campfire.entities;

public enum Authorities {
    USER("USER"), ADMIN("ADMIN");

    String name;

    Authorities(String name) {
        this.name = name;
    }
}
