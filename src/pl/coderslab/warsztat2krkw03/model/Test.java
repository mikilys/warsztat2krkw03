package pl.coderslab.warsztat2krkw03.model;

import pl.coderslab.warsztat2krkw03.dao.UserDao;

public class Test {

    public static void main(String[] args) {

        User u = new User("admin1", "admin1@gmail.com", "admin1");
        UserDao.create(u);

        //test sprawdzajacy dzialanie klasy
        System.out.println(u.getId());
        System.out.println(u.getPassword());

    }

}
