package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.util.Validator;
import java.util.Objects;

public abstract class Person extends MedicalEntity {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private String phone;
    private String email;

    protected Person(String id, String name, int age) {
        this(id, name, age, "0000000000", "unknown@example.com");
    }

    protected Person(String id, String name, int age, String phone, String email) {
        super(id);
        setName(name);
        setAge(age);
        setPhone(phone);
        setEmail(email);
    }

    public String getName() { return name; }
    public final void setName(String name) { Validator.requireText(name, "name"); this.name = name.trim(); }
    public int getAge() { return age; }
    public final void setAge(int age) { Validator.requireAge(age); this.age = age; }
    public String getPhone() { return phone; }
    public final void setPhone(String phone) { Validator.requirePhone(phone); this.phone = phone; }
    public String getEmail() { return email; }
    public final void setEmail(String email) { Validator.requireEmail(email); this.email = email; }

    @Override public String getDisplayName() { return name + " (" + getId() + ")"; }
    @Override public boolean equals(Object other) { return other instanceof Person && Objects.equals(getId(), ((Person) other).getId()); }
    @Override public int hashCode() { return Objects.hash(getId()); }
}
