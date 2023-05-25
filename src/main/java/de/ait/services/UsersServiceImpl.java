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

    @Override
    public double getAverageAgeOfUsers() {
        List<User> users = usersRepository.findAll();
        double averageAge = 0;
        for (User user : users){
            averageAge += user.getAge();
        }
        return averageAge / users.size();
    }

    @Override
    public int ageOfTallestPerson() {
        List<User> users = usersRepository.findAll();
        int tmp = 0;
        double height = users.get(0).getHeight();
        for(int i = 0; i < users.size(); i++ ){
            if(height < users.get(i).getHeight()){
                height = users.get(i).getHeight();
                tmp = i;
            }
        }
        return users.get(tmp).getAge();
    }

    @Override
    public String firstNameAndLastNameOfTheLowestPerson() {
        List<User> users = usersRepository.findAll();
        int tmp = 0;
        double height = users.get(0).getHeight();
        for(int i = 0; i < users.size(); i++){
            if(height > users.get(i).getHeight()){
                height = users.get(i).getHeight();
                tmp = i;
            }
        }
        return users.get(tmp).getFirstName() + " " + users.get(tmp).getLastName();
    }
}
