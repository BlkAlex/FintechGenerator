package HumanGenerator.generators;

import HumanGenerator.InputParameters;
import HumanGenerator.enums.Gender;
import HumanGenerator.generators.localGenerator.Generator;
import HumanGenerator.model.Human;
import HumanGenerator.model.UserPojo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class HumanCreator {
    public static Human getHuman() {
        Gender gender = Generator.getRandomGender();
        LocalDate birthdayDate = Generator.getRandomDate(InputParameters.MIN_YEAR_OF_BIRTH);
        return new Human.Builder()
                .setGender(gender)
                .setName(Generator.getRandomStringFromList((gender == Gender.MALE) ? Generator.KeysGlossary.MALE_NAMES : Generator.KeysGlossary.FEMALE_NAMES))
                .setSurname(Generator.getRandomStringFromList((gender == Gender.MALE) ? Generator.KeysGlossary.MALE_SURNAMES : Generator.KeysGlossary.FEMALE_SURNAMES))
                .setPatronymic(Generator.getRandomStringFromList((gender == Gender.MALE) ? Generator.KeysGlossary.MALE_PATRONYMIC : Generator.KeysGlossary.FEMALE_PATRONYMIC))
                .setCountry(Generator.getRandomStringFromList(Generator.KeysGlossary.COUNTRIES))
                .setRegion(Generator.getRandomStringFromList(Generator.KeysGlossary.REGIONS))
                .setTown(Generator.getRandomStringFromList(Generator.KeysGlossary.TOWNS))
                .setTownOfBirthday(Generator.getRandomStringFromList(Generator.KeysGlossary.TOWNS))
                .setStreet(Generator.getRandomStringFromList(Generator.KeysGlossary.STREETS))
                .setBirthday(birthdayDate)
                .setAge(Generator.getAgeByDate(birthdayDate))
                .setInn(Generator.getRandomINN(InputParameters.REGION_INN))
                .setMailIndex(Generator.getRand(InputParameters.START_RANGE_MAIL_INDEX, InputParameters.END_RANGE_MAIL_INDEX))
                .setNumberHouse(Generator.getRandomNumberHouse(InputParameters.MAX_NUMBER_HOUSE))
                .setNumberFlat(Generator.getRand(1, InputParameters.MAX_NUMBER_FLAT))
                .build();
    }

    public static Human getHumanFromUserPojo(UserPojo userPojo) {
        Human localGeneratedHuman = getHuman();
        Gender gender = userPojo.getGender().toLowerCase().equals("m") ? Gender.MALE :
                userPojo.getGender().toLowerCase().equals("w") ? Gender.FEMALE : null;
        if (gender == null)
            gender = localGeneratedHuman.getGender();
        return new Human.Builder()
                .setGender(gender)
                .setName(userPojo.getFname() == null ? localGeneratedHuman.getName() : userPojo.getFname())
                .setSurname(userPojo.getLname() == null ? localGeneratedHuman.getSurname() : userPojo.getLname())
                .setPatronymic(userPojo.getPatronymic() == null ? localGeneratedHuman.getPatronymic() : userPojo.getPatronymic())
                .setCountry(userPojo.getCounty() == null ? localGeneratedHuman.getCountry() : userPojo.getCounty())
                .setRegion(userPojo.getRegion() == null ? localGeneratedHuman.getRegion() : userPojo.getRegion())
                .setTown(userPojo.getCity() == null ? localGeneratedHuman.getTown() : userPojo.getCity())
                .setTownOfBirthday(userPojo.getCityOfBirthday() == null ? localGeneratedHuman.getTown() : userPojo.getCityOfBirthday())
                .setStreet(userPojo.getStreet() == null ? localGeneratedHuman.getStreet() : userPojo.getStreet())
                .setBirthday(userPojo.getDate() == null ? localGeneratedHuman.getBirthDay() : LocalDate.parse(userPojo.getDate(), DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.forLanguageTag("ru"))))
                .setAge(userPojo.getAge() == null ? localGeneratedHuman.getAge() : Integer.parseInt(userPojo.getAge()))
                .setInn(userPojo.getInn() == null ? localGeneratedHuman.getInn() : userPojo.getInn())
                .setMailIndex(
                        (userPojo.getPostcode() == null || (Integer.parseInt(userPojo.getPostcode()) > InputParameters.END_RANGE_MAIL_INDEX || Integer.valueOf(userPojo.getPostcode()) < InputParameters.START_RANGE_MAIL_INDEX))
                                ? localGeneratedHuman.getMailIndex() : Integer.parseInt(userPojo.getPostcode())
                )
                .setNumberHouse(userPojo.getHouse() == 0 ? localGeneratedHuman.getNumberHouse() : String.valueOf(userPojo.getHouse()))
                .setNumberFlat(userPojo.getApartment() == 0 ? localGeneratedHuman.getNumberFlat() : userPojo.getApartment())
                .build();
    }
}
