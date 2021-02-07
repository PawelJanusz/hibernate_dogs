package model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Dogs {

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

}
