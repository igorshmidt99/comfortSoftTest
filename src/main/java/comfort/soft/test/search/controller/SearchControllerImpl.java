package comfort.soft.test.search.controller;

import comfort.soft.test.search.dto.NReqDto;
import comfort.soft.test.search.dto.NRespDto;
import comfort.soft.test.search.service.NSearchService;
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

    private final NSearchService searchService;

    @Override
    @PostMapping("/n")
    public ResponseEntity<NRespDto> searchMaxN(@RequestBody @Valid NReqDto dto) {
        String resp = searchService.search(dto.getN(), dto.getPath());
        return ResponseEntity.ok(new NRespDto(resp));
    }
}
