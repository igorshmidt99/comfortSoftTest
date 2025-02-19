package comfort.soft.test.search.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import comfort.soft.test.search.dto.NReqDto;
import comfort.soft.test.search.service.NSearchService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class SearchControllerImplInputTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private NSearchService searchService;

    private MockHttpServletRequestBuilder searchReq = post("/v1/search/n")
            .contentType(MediaType.APPLICATION_JSON);

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ObjectWriter writer = objectMapper.writerFor(NReqDto.class);

    @ParameterizedTest
    @ValueSource(strings = {
            "v/v/dir/file.xlsxx",
            "v/v/dir/file.xlsxX",
            "v/v/dir//file.XLSX",
            "file", "", "   ", "dir.", "C.XLSX", "for.xlsx3xxx"})
    void invalidInputShouldReturn404(String path) throws Exception {
        NReqDto findNReqDto = new NReqDto(path, 0);
        String body = writer.writeValueAsString(findNReqDto);

        mockMvc.perform(searchReq.content(body))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @ValueSource(strings = {"v/v/dir/file.xlsx", "file.xlsx", "v123/v123/dir123/file123.xlsx", "v/v/DIR123/FILE123.xlsx"})
    void validInputShouldReturn200(String path) throws Exception {
        NReqDto findNReqDto = new NReqDto(path, 0);
        String body = writer.writeValueAsString(findNReqDto);
        mockMvc.perform(searchReq.content(body)).andExpect(status().isOk());
    }
}