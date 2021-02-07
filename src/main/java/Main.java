import dao.DogDao;
import model.Dog;
import model.Race;

import java.util.Optional;
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
            if (method.equalsIgnoreCase("List")){
                listDogs(dao);
            }
            if (method.equalsIgnoreCase("Update")){
                updateDog(dao, scanner);
            }
            if (method.equalsIgnoreCase("Delete")){
                deleteDog(dao, scanner);
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

    private static void deleteDog(DogDao dao, Scanner scanner){
        System.out.println("Podaj id psa");

        Long id = Long.valueOf(scanner.nextLine());
        Optional<Dog> dogOptional = dao.findById(id);
        if (dogOptional.isPresent()){
            Dog dog = dogOptional.get();

            dao.delete(dog);
        }
    }

    private static void listDogs(DogDao dao){
        System.out.println("Lista psów");

        dao.getAll().stream().forEach(System.out::println);
    }

    private static void updateDog(DogDao dao, Scanner scanner){
        System.out.println("Podaj identyfikator psa");
        Long id = Long.valueOf(scanner.nextLine());

        Optional<Dog> dogOptional = dao.findById(id);
        if (dogOptional.isPresent()) {
            Dog dog = dogOptional.get();
            System.out.println("Edytujesz rekord: " + dog);

            System.out.println("Podaj nowe dane psa dla wybranego rekordu: " +
                    "IMIE WIEK_PSA IMIE_WLASICIELA WAGA CZY_RASA_CZYSTA RASA");
            String line = scanner.nextLine();
            String[] words = line.split(" ");

            Dog dog1 = Dog.builder()
                    .name(words[0])
                    .age(Integer.parseInt(words[1]))
                    .ownerName(words[2])
                    .weight(Integer.parseInt(words[3]))
                    .pureRace(Boolean.parseBoolean(words[4]))
                    .race(Race.valueOf(words[5].toUpperCase()))
                    .id(id)
                    .build();

            dao.saveOrUpdate(dog1);
        } else
            System.err.println("ERROR, pies z takim ID nie istnieje");
    }
}
