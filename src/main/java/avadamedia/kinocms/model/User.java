package avadamedia.kinocms.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends MappedEntity {

    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String nickname;
    @Column
    private String email;
    @Column
    private String address;
    @Column
    private String password;
    @Column
    private Integer cardNumber;
    @Column
    @Enumerated(EnumType.STRING)
    private Language language;
    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column
    private String phoneNumber;
    @Column
    private LocalDate dob;
    @Column
    @Enumerated(EnumType.STRING)
    private City city;

    public enum Language {
        RUSSIAN,
        UKRAINIAN
    }

    public enum Gender {
        MALE,
        FEMALE
    }

    private enum City {
        ODESSA,
        KIEV
    }

}
