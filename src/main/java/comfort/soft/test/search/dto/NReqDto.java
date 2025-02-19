package comfort.soft.test.search.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class NReqDto {

    @NotBlank
    @Pattern(regexp = "[/\\w]*(\\.xlsx)$")
    @Schema(name = "path", examples = {"/app/data.xlsx","/app/data.xlsx","/app/qwer.xlsx"})
    private String path;

    @Schema(name = "n",examples = {"6","1000","1000"})
    private Integer n;

}
