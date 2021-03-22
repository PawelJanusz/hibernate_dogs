package controller;

import dao.DogDao;
import model.Dog;
import model.Race;

import java.util.Optional;
import java.util.Scanner;

public class DogController {

    //create
    public void addDogs(DogDao dao, Scanner scanner){
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

    //read
    public void listDogs(DogDao dao){
        System.out.println("Lista psów");

        dao.getAll().stream().forEach(System.out::println);
    }

    //update
    public void updateDog(DogDao dao, Scanner scanner){
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

    //delete
    public void deleteDog(DogDao dao, Scanner scanner){
        System.out.println("Podaj id psa");

        Long id = Long.valueOf(scanner.nextLine());
        Optional<Dog> dogOptional = dao.findById(id);
        if (dogOptional.isPresent()){
            Dog dog = dogOptional.get();

            dao.delete(dog);
        }
    }

    public void findByAge(DogDao dao, Scanner scanner){
        System.out.println("Podaj zakres wieku szukanych psów: ageFrom oraz ageTo");

        String line = scanner.nextLine();
        int ageFrom = Integer.parseInt(line.split(" ")[0]);
        int ageTo = Integer.parseInt(line.split(" ")[1]);

        System.out.println("Znalezione psy: ");
        dao.findByAgeBetween(ageFrom, ageTo).forEach(System.out::println);
    }

    public void findByRace(DogDao dao, Scanner scanner){
        System.out.println("Podaj nazwę rasy: LABRADOR,\n" +
                "    HUSKY,\n" +
                "    GOLDEN_RETRIEVER,\n" +
                "    MOPS,\n" +
                "    DACHSHUND,\n" +
                "    CHIHUAHUA,\n" +
                "    MONGREL,\n" +
                "    ROTTWEILER,\n" +
                "    BERNARDINE,\n" +
                "    RATTER");

        String line = scanner.nextLine();

        Race race = Race.valueOf(line.toUpperCase());

        System.out.println("Znalezione psy: ");
        dao.findByRaceName(race).forEach(System.out::println);
    }

    public void findByPRaceAndName(DogDao dao, Scanner scanner){
        System.out.println("Podaj czy rasa czysta: true or false; oraz imię psa");

        String line = scanner.nextLine();
        boolean pureRace = Boolean.parseBoolean(line.split(" ")[0]);
        String name = line.split(" ")[1];

        System.out.println("Znaleziony psy: ");
        dao.findByPureRaceAndName(pureRace, name).forEach(System.out::println);
    }

    public void findByWeightBetween(DogDao dao, Scanner scanner){
        System.out.println("Podaj zakres wagowy: weightFrom weightTo");

        String line = scanner.nextLine();
        int weightFrom = Integer.parseInt(line.split(" ")[0]);
        int weightTo = Integer.parseInt(line.split(" ")[1]);

        System.out.println("Zanlezione psy w podanym przedziale wagowym: " + weightFrom + "-" + weightTo);
        dao.findByWeightBetween(weightFrom, weightTo).forEach(System.out::println);
    }
}
