package avadamedia.kinocms.model;

import avadamedia.kinocms.model.common.MappedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

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
    @Column(name = "card_number")
    private Integer cardNumber;
    @Column
    @Enumerated(EnumType.ORDINAL)
    private Language language;
    @Column
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;
    @Column(name = "phone_number")
    private String phoneNumber;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column
    private Date birthday;
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

}
