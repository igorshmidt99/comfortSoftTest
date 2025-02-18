package comfort.soft.test.search.controller;

import comfort.soft.test.search.FindNReqDto;
import org.springframework.http.ResponseEntity;

public interface SearchController {

    ResponseEntity<String> searchMaxN(FindNReqDto dto);

}
