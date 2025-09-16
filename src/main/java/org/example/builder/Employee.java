package org.example.builder;

import java.util.Date;


public class Employee {
    public static class Builder {
        private String name;
        private String email;
        private Date date;
        private Gender gender;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }

    private final String name;
    private final String email;
    private final Date date;
    private final Gender gender;

    Employee (Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.date = builder.date;
        this.gender = builder.gender;
    }

    public static Builder builder () {
        return new Builder();
    }


    public String toString() {
        return name + " " + email + " " + date + " " + gender;

    }
}
