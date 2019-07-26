package pl.coderslab.warsztat2krkw03.controller;

//program narzedziowy

import pl.coderslab.warsztat2krkw03.dao.UserDao;
import pl.coderslab.warsztat2krkw03.model.User;

import java.util.Arrays;
import java.util.Scanner;

public class UserController {

    public static void main(String[] args) {

        System.out.println("Witaj w programie narzędziowym Usera! / Welcome in User management program!");
        Scanner s = new Scanner(System.in);

        while (true) {
            displayMenu();
            final String option = s.nextLine();

            if(option.equals("1")) {
                addUser();
            }
            if(option.equals("2")) {
                editUser();
            }
            if(option.equals("3")) {
                deleteUser();
            }
            if(option.equals("4")) {
                findAll();
            }
            if(option.equals("0")) {
                break;
            }
        }
        System.out.println("Zakończono pracę progamu / End of program");

    }

    private static void addUser() {
        Scanner s = new Scanner(System.in);

        System.out.println("Dodaj użytkownika / Add user");

        System.out.println("Dodaj nazwę / Enter name:");
        final String username = s.nextLine();
        System.out.println("Dodaj email / Enter email:");
        final String email = s.nextLine();
        System.out.println("Podaj hasło / Enter password:");
        final String password = s.nextLine();

        User u = new User(username, email, password);
        UserDao.create(u);

        System.out.println("Dodano Usera / User added; id=" + u.getId());
    }

    private static void editUser() {
        Scanner s = new Scanner(System.in);

        System.out.println("Podaj id usera / Enter user id");
        final int userId = s.nextInt();
        s.nextLine();

        User u = UserDao.read(userId);

        System.out.println("Wyszukany user / Found user:");
        System.out.println("id=" + u.getId());
        System.out.println("name=" + u.getUsername());
        System.out.println("email=" + u.getEmail());

        System.out.println();
        System.out.println("Naciśnij enter aby przejść do edycji / Press enter for edit");
        s.nextLine();

        System.out.println("Podaj nową nazwę / Enter new name:");
        final String newUsername = s.nextLine();
        System.out.println("Podaj nowy email / Enter new email:");
        final String newEmail = s.nextLine();
        System.out.println("Podaj nowe hasło / Enter new password:");
        final String newPassword = s.nextLine();

        User updateU = new User (userId, newUsername, newEmail, newPassword);
        UserDao.update(updateU);
    }

    private static void deleteUser() {
        Scanner s = new Scanner(System.in);

        System.out.println("Podaj id usera / Enter user id");
        final int userId = s.nextInt();
        s.nextLine();

        UserDao.delete(userId);
        System.out.println();
        System.out.println("Usunięto Usera / User deleted");
    }

    private static void findAll() {
        System.out.println("Wszyscy userzy / All users");

        User[] users = UserDao.findAll();
        for (int i=0; i<users.length; i++) {
            System.out.println(users[i]);
        }
    }

    private static void displayMenu() {
        System.out.println();
        System.out.println("Wybierz opcje i naciśnij 'enter' / Choose option nad press 'enter'");
        System.out.println("Opcje: 1 - dadaj / add");
        System.out.println("Opcje: 2 - odszukaj / find");
        System.out.println("Opcje: 3 - usuń / delete");
        System.out.println("Opcje: 4 - wyświetl wszystko / show all");
        System.out.println("Opcje: 0 - wyjście / quit");
        System.out.println();
    }

}
