package comfort.soft.test.search;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class FindNReqDto {

    @NotBlank
    @Pattern(regexp = "[/\\w]*(\\.xlsx)$")
    private String path;

    private Integer n;

}
