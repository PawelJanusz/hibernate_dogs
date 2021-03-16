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
            System.out.println("Podaj nr polecenia: " +
                    "\n 1.Add \n 2.List \n 3.Delete \n " +
                    "4.Update \n 5.Search by age range " +
                    "\n 6.Search by race \n 7.Search by pure race and name " +
                    "\n 8.Search by weight range \n Quit");
            method = scanner.nextLine();

            if (method.equals("1")){
                addDogs(dao, scanner);
            }
            if (method.equals("2")){
                listDogs(dao);
            }
            if (method.equals("3")){
                updateDog(dao, scanner);
            }
            if (method.equals("4")){
                deleteDog(dao, scanner);
            }
            if (method.equals("5")){
                findByAge(dao, scanner);
            }
            if (method.equals("6")){
                findByRace(dao, scanner);
            }
            if (method.equals("7")){
                findByPRaceAndName(dao, scanner);
            }
            if (method.equals("8")){
                findByWeightBetween(dao, scanner);
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

    private static void findByAge(DogDao dao, Scanner scanner){
        System.out.println("Podaj zakres wieku szukanych psów: ageFrom oraz ageTo");

        String line = scanner.nextLine();
        int ageFrom = Integer.parseInt(line.split(" ")[0]);
        int ageTo = Integer.parseInt(line.split(" ")[1]);

        System.out.println("Znalezione psy: ");
        dao.findByAgeBetween(ageFrom, ageTo).forEach(System.out::println);
    }

    private static void findByRace(DogDao dao, Scanner scanner){
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

    private static void findByPRaceAndName(DogDao dao, Scanner scanner){
        System.out.println("Podaj czy rasa czysta: true or false; oraz imię psa");

        String line = scanner.nextLine();
        boolean pureRace = Boolean.parseBoolean(line.split(" ")[0]);
        String name = line.split(" ")[1];

        System.out.println("Znaleziony psy: ");
        dao.findByPureRaceAndName(pureRace, name).forEach(System.out::println);
    }

    private static void findByWeightBetween(DogDao dao, Scanner scanner){
        System.out.println("Podaj zakres wagowy: weightFrom weightTo");

        String line = scanner.nextLine();
        int weightFrom = Integer.parseInt(line.split(" ")[0]);
        int weightTo = Integer.parseInt(line.split(" ")[1]);

        System.out.println("Zanlezione psy w podanym przedziale wagowym: " + weightFrom + "-" + weightTo);
        dao.findByWeightBetween(weightFrom, weightTo).forEach(System.out::println);
    }
}
