package comfort.soft.test.search.controller;

import comfort.soft.test.search.FindNReqDto;
import comfort.soft.test.search.service.FileSystemSearchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/search")
@RequiredArgsConstructor
public class SearchControllerImpl implements SearchController {

    private final FileSystemSearchService searchService;

    @Override
    @PostMapping("/n")
    public ResponseEntity<String> searchMaxN(@RequestBody @Valid FindNReqDto dto) {
        String resp = searchService.searchN(dto.getN(), dto.getPath());
        return ResponseEntity.ok(resp);
    }
}
