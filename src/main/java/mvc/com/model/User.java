package mvc.com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @SequenceGenerator(name = "userSeqGen", sequenceName = "users_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "userSeqGen")
    private Long id;

    /*@NotNull
    @Email
    @Size(min = 10, max = 20)
    @AssertFalse// тру или фолс лежит
    @AssertTrue*/
   /* @DecimalMax("10.3")
    @DecimalMin("10.3")
    @Digits(integer = 3, fraction = 1) -число целое и его дробная часть
    @Future - время в будущем
    @FutureOrPresent - время в будущем или настоящем
    @Past - время в прошлом
    @PastOrPresent - понятно
    @Max(100) - макс число
    @Min(18) - мин
    @Negative - с минусом
    @NegativeOrZero - с минусом или ноль
    @Pattern(regexp = "[a-z]+") - реджекс
    */
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp created;

    @Column(name = "changed")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp changed;

    @Column(name = "age")
    private Integer age;
}