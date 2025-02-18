package comfort.soft.test.search.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import comfort.soft.test.search.FindNReqDto;
import comfort.soft.test.search.service.FileSystemSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class SearchControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private FileSystemSearchService searchService;

    private MockHttpServletRequestBuilder searchReq;

    @BeforeEach
    void test() throws Exception {
        searchReq = post("/v1/search/n")
                .contentType(MediaType.APPLICATION_JSON);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "v/v/dir/file.xlsxx",
            "v/v/dir/file.xlsxX",
            "v/v/dir//file.XLSX",
            "file", "", "   ", "dir.", "C.XLSX", "for.xlsx3xxx"})
    void invalidInputShouldReturn404(String path) throws Exception {
        FindNReqDto findNReqDto = new FindNReqDto(path, 0);
        String body = objectMapper.writerFor(FindNReqDto.class).writeValueAsString(findNReqDto);

        mockMvc.perform(searchReq.content(body))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @ValueSource(strings = {"v/v/dir/file.xlsx", "file.xlsx", "v123/v123/dir123/file123.xlsx", "v/v/DIR123/FILE123.xlsx"})
    void validInputShouldReturn200(String path) throws Exception {
        FindNReqDto findNReqDto = new FindNReqDto(path, 0);
        String body = objectMapper.writerFor(FindNReqDto.class)
                .writeValueAsString(findNReqDto);
//        when(searchService.search(findNReqDto.getN(), findNReqDto.getPath())).thenReturn(0);
        mockMvc.perform(searchReq.content(body)).andExpect(status().isOk());
    }
}