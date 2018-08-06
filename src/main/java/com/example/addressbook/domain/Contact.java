package com.example.addressbook.domain;

import java.time.LocalDate;

/**
 * Created by magg on 8/3/18.
 */
public class Contact {
    private String name;
    private Gender gender;
    private LocalDate birthday;

    private Contact(ContactBuilder builder) {
        setName(builder.name);
        setGender(builder.gender);
        setBirthday(builder.birthday);
    }

    public static ContactBuilder newBuilder() {
        return new ContactBuilder();
    }

    public static ContactBuilder newBuilder(Contact copy) {
        ContactBuilder builder = new ContactBuilder();
        builder.name = copy.getName();
        builder.gender = copy.getGender();
        builder.birthday = copy.getBirthday();
        return builder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;

        Contact contact = (Contact) o;

        if (!getName().equals(contact.getName())) return false;
        if (getGender() != contact.getGender()) return false;
        return getBirthday().equals(contact.getBirthday());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getGender().hashCode();
        result = 31 * result + getBirthday().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                '}';
    }


    public static final class ContactBuilder {
        private String name;
        private Gender gender;
        private LocalDate birthday;

        private ContactBuilder() {
        }

        public ContactBuilder name(String val) {
            name = val;
            return this;
        }

        public ContactBuilder gender(Gender val) {
            gender = val;
            return this;
        }

        public ContactBuilder birthday(LocalDate val) {
            birthday = val;
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }
    }
}
