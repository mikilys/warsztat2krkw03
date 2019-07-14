package pl.coderslab.warsztat2krkw03.controller;

//program narzedziowy

import pl.coderslab.warsztat2krkw03.dao.UserDao;
import pl.coderslab.warsztat2krkw03.model.User;

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
        System.out.println("Podaj hasło /Enter password:");
        final String password = s.nextLine();

        User u = new User(username, email, password);
        UserDao.create(u);

        System.out.println("Dodano Usera / User added; id=" + u.getId());
    }

    private static void editUser() {
        System.out.println("Wyedytowano Usera / User edited");
    }

    private static void deleteUser() {
        System.out.println("Usunięto Usera / User deleted");
    }

    private static void displayMenu() {
        System.out.println();
        System.out.println("Wybierz opcje i naciśnij 'enter' / Choose option nad press 'enter'");
        System.out.println("Opcje: 1 - dadaj / add");
        System.out.println("Opcje: 2 - edytuj / edit");
        System.out.println("Opcje: 3 - usuń / delete");
        System.out.println("Opcje: 0 - wyjście / quit");
    }

}
