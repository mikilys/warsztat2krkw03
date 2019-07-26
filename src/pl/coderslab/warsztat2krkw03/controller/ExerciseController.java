package pl.coderslab.warsztat2krkw03.controller;

import pl.coderslab.warsztat2krkw03.dao.ExerciseDao;
import pl.coderslab.warsztat2krkw03.dao.UserDao;
import pl.coderslab.warsztat2krkw03.model.Exercise;
import pl.coderslab.warsztat2krkw03.model.User;

import java.util.Scanner;

public class ExerciseController {

    public static void main(String[] args) {

        System.out.println("Witaj w programie narzędziowym zadań! / Welcome in Exercise management program!");
        Scanner s = new Scanner(System.in);

        while (true) {
            displayMenu();
            final String option = s.nextLine();

            if(option.equals("1")) {
                addExercise();
            }
            if(option.equals("2")) {
                editExercise();
            }
            if(option.equals("3")) {
                deleteExercise();
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

    private static void addExercise() {
        Scanner s = new Scanner(System.in);

        System.out.println("Dodaj zadanie / Add exercise");

        System.out.println("Dodaj tytuł / Enter title:");
        final String title = s.nextLine();
        System.out.println("Dodaj opis / Enter description:");
        final String description = s.nextLine();

        Exercise e = new Exercise(title, description);
        ExerciseDao.create(e);

        System.out.println("Dodano zadanie / Exercise added; id=" + e.getId());
    }

    private static void editExercise() {
        Scanner s = new Scanner(System.in);

        System.out.println("Podaj id zadania / Enter exercise id");
        final int exeId = s.nextInt();
        s.nextLine();

        Exercise e = ExerciseDao.read(exeId);

        System.out.println("Wyszukane zadanie / Found exercise:");
        System.out.println("id=" + e.getId());
        System.out.println("title=" + e.getTitle());
        System.out.println("description=" + e.getDescription());

        System.out.println();
        System.out.println("Naciśnij enter aby przejść do edycji / Press enter for edit");
        s.nextLine();

        System.out.println("Podaj nowy tytuł / Enter new title:");
        final String newTitle = s.nextLine();
        System.out.println("Podaj nowy opis / Enter new decription:");
        final String newDescription = s.nextLine();

        Exercise updateE = new Exercise (exeId, newTitle, newDescription);
        ExerciseDao.update(updateE);
    }

    private static void deleteExercise() {
        Scanner s = new Scanner(System.in);

        System.out.println("Podaj id zadania / Enter exercise id");
        final int exeId = s.nextInt();
        s.nextLine();

        ExerciseDao.delete(exeId);
        System.out.println();
        System.out.println("Usunięto zadanie / Exercise deleted");
    }

    private static void findAll() {
        System.out.println("Wszystkie zadnia / All exercises");

        Exercise[] exercises = ExerciseDao.findAll();
        for (int i=0; i<exercises.length; i++) {
            System.out.println(exercises[i]);
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
