import dao.DogDao;
import model.Dog;
import model.Race;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DogDao dao = new DogDao();

        String method;
        do {
            System.out.println("Podaj polecenie do wykonania: [Add/List/Delete/Update/Quit]");
            method = scanner.nextLine();

            if (method.equalsIgnoreCase("Add")){
                addDogs(dao, scanner);
            }


        }while (!method.equalsIgnoreCase("quit"));
    }

    private static void addDogs(DogDao dao, Scanner scanner){
        System.out.println("Podaj dane: IMIE WIEK_PSA IMIE_WLASICIELA WAGA CZY_RASA_CZYSTA RASA");
        String line = scanner.nextLine();
        String[] words = line.split(" ");

        Dog dog = Dog.builder()
                .name(words[0])
                .age(Integer.parseInt(words[1]))
                .ownerName(words[2])
                .weight(Integer.parseInt(words[3]))
                .pureRace(Boolean.parseBoolean(words[4]))
                .race(Race.valueOf(words[5].toUpperCase()))
                .build();

        dao.saveOrUpdate(dog);



    }
}
