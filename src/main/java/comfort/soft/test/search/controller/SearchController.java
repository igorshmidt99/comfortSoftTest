package comfort.soft.test.search.controller;

import comfort.soft.test.search.dto.NReqDto;
import comfort.soft.test.search.dto.NRespDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface SearchController {

    @Operation(requestBody = @RequestBody(content = @Content(mediaType = "application/json", examples = {
//            @ExampleObject(name = "200 request",value = "{\"path\":\"/app/tests/data.xlsx\", n:6}"),
//            @ExampleObject(name = "200 request 2",value = "{\"path\":\"/app/tests/data.xlsx\", n:1000}"),
//            @ExampleObject(name = "404 request",value = "{\"path\":\"/app/tests/qwer.xlsx\", n:1000}")
    })))
    @ApiResponses({
            @ApiResponse(description = "File exist by passed path.", responseCode = "200"),
            @ApiResponse(description = "No file founded by passed path.", responseCode = "404")
    })
    ResponseEntity<NRespDto> searchMaxN(
            @Parameter(name = "Object with file path and the desired number.", required = true)
            NReqDto dto
    );

}
