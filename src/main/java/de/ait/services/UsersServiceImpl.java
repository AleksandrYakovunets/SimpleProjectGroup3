package de.ait.services;

import de.ait.models.User;
import de.ait.repositories.UsersRepository;

import java.util.*;

public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<String> getNames() {
        List<User> users = usersRepository.findAll();
        List<String> names = new ArrayList<>();

        for (User user : users) {
            names.add(user.getFirstName());
        }

        return names;
    }

    @Override
    public String getLastNameOfMostAging() {
        /*
        Marsel|Sidikov|29|1.85
        Maxim|Ivanov|25|1.79
        Ruslan|Kochkin|41|1.83
        Kirill|Petrov|55|1.90

        29 -> Sidikov
        25 -> Ivanov
        41 -> Kochkin
        55 -> Petrov

        Collections.max(29, 25, 41, 55) -> 55

        return userAge.get(55) -> Petrov

         */
        List<User> users = usersRepository.findAll();
        Map<Integer, String> userAge = new HashMap<>();

        for (User user : users) {
            userAge.put(user.getAge(), user.getLastName());
        }

        int maxAge = Collections.max(userAge.keySet());

        return userAge.get(maxAge);
    }
}
