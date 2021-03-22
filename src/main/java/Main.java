import controller.DogController;
import dao.DogDao;
import model.Dog;
import model.Race;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DogDao dao = new DogDao();
        DogController dog = new DogController();

        String method;
        do {
            System.out.println("Podaj nr polecenia: " +
                    "\n 1.Add \n 2.List \n 3.Delete \n " +
                    "4.Update \n 5.Search by age range " +
                    "\n 6.Search by race \n 7.Search by pure race and name " +
                    "\n 8.Search by weight range \n Quit");
            method = scanner.nextLine();

            if (method.equals("1")){
                dog.addDogs(dao, scanner);
            }
            if (method.equals("2")){
                dog.listDogs(dao);
            }
            if (method.equals("3")){
                dog.updateDog(dao, scanner);
            }
            if (method.equals("4")){
                dog.deleteDog(dao, scanner);
            }
            if (method.equals("5")){
                dog.findByAge(dao, scanner);
            }
            if (method.equals("6")){
                dog.findByRace(dao, scanner);
            }
            if (method.equals("7")){
                dog.findByPRaceAndName(dao, scanner);
            }
            if (method.equals("8")){
                dog.findByWeightBetween(dao, scanner);
            }


        }while (!method.equalsIgnoreCase("quit"));
    }
}
