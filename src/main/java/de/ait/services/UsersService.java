package de.ait.services;

import java.util.List;

public interface UsersService {
    List<String> getNames();

    String getLastNameOfMostAging();

    double getAverageAgeOfUsers();

    int ageOfTallestPerson();

    String firstNameAndLastNameOfTheLowestPerson();
}
