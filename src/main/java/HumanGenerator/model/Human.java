package HumanGenerator.model;

import HumanGenerator.enums.Gender;

import java.time.LocalDate;

public class Human {
    
    private String name;
    private String surname;
    private String patronymic;
    private String country;
    private int age;
    private LocalDate birthDay;
    private String inn;
    private Gender gender;
    private int mailIndex;
    private String region;
    private String town;
    private String street;
    private String numberHouse;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getCountry() {
        return country;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public String getInn() {
        return inn;
    }

    public Gender getGender() {
        return gender;
    }

    public int getMailIndex() {
        return mailIndex;
    }

    public String getRegion() {
        return region;
    }

    public String getTown() {
        return town;
    }

    public String getStreet() {
        return street;
    }

    public String getNumberHouse() {
        return numberHouse;
    }

    public int getNumberFlat() {
        return numberFlat;
    }

    
    private int numberFlat;

    private Human() {
    }

    public static class Builder {
        final Human human;

        public Builder() {
            human = new Human();
        }

        public Builder setName(String name) {
            human.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            human.surname = surname;
            return this;
        }

        public Builder setPatronymic(String patronymic) {
            human.patronymic = patronymic;
            return this;
        }

        public Builder setCountry(String country) {
            human.country = country;
            return this;
        }

        public Builder setBirthday(LocalDate birthday) {
            human.birthDay = birthday;
            return this;
        }

        public Builder setAge(int age) {
            human.age = age;
            return this;
        }

        public Builder setInn(String inn) {
            human.inn = inn;
            return this;
        }

        public Builder setGender(Gender gender) {
            human.gender = gender;
            return this;
        }

        public Builder setMailIndex(int mailIndex) {
            human.mailIndex = mailIndex;
            return this;
        }

        public Builder setRegion(String region) {
            human.region = region;
            return this;
        }

        public Builder setTown(String town) {
            human.town = town;
            return this;
        }

        public Builder setStreet(String street) {
            human.street = street;
            return this;
        }

        public Builder setNumberHouse(String numberHouse) {
            human.numberHouse = numberHouse;
            return this;
        }

        public Builder setNumberFlat(int numberFlat) {
            human.numberFlat = numberFlat;
            return this;
        }

        public Human build() {
            return human;
        }
    }
}

