package mvc.com.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mvc.com.annotations.Adult;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserCreateDto {
    @NotNull
    @Size(min = 6, max = 15)
    private String username;

    @NotNull
    @Size(min = 6, max = 15)
    private String userPassword;

    @NotNull
    @Adult
    @Max(100)
    private Integer age;
}
