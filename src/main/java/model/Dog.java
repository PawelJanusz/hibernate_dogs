package model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String ownerName;
    private int weight;
    private boolean pureRace;

    @Enumerated(value = EnumType.STRING)
    private Race race;

    public Dog(String name, int age, String ownerName, int weight, boolean pureRace, Race race) {
        this.name = name;
        this.age = age;
        this.ownerName = ownerName;
        this.weight = weight;
        this.pureRace = pureRace;
        this.race = race;
    }
}
