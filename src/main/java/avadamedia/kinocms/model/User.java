package avadamedia.kinocms.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    @Enumerated(EnumType.ORDINAL)
    private Language language;
    @Column
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;
    @Column
    private String phoneNumber;
    @Column
    private LocalDate dob;
    @Column
    private LocalDate doc;
    @Column
    private String city;

    public enum Language {
        RU,
        UA
    }

    public enum Gender {
        M,
        F
    }

    private enum City {
        Odessa,
        Kiev
    }

}
