package comfort.soft.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import comfort.soft.test.search.dto.NReqDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;

import java.util.stream.Stream;

import static comfort.soft.test.data.TestData.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EndToEndTests {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ObjectWriter writer = objectMapper.writerFor(NReqDto.class);
    private final String uri = "/v1/search/n";
    MockHttpServletRequestBuilder post = post(uri).contentType(MediaType.APPLICATION_JSON);
    JsonPathResultMatchers respBody = jsonPath("$.n");


    @ParameterizedTest
    @ArgumentsSource(value = SuccessCases.class)
    void successCases(NReqDto nReqDto) throws Exception {
        String body = writer.writeValueAsString(nReqDto);
        performOk(body, nReqDto.getN().toString());
    }

    @ParameterizedTest
    @ArgumentsSource(value = FailedCases.class)
    void failedCases(NReqDto nReqDto) throws Exception {
        String body = writer.writeValueAsString(nReqDto);
        performOk(body, noSuchValue);
    }

    @Test
    void noSuchFileCase() throws Exception {
        String body = writer.writeValueAsString(noSuchFile);
        MvcResult mvcResult = mockMvc.perform(post.content(body))
                .andExpect(status().isNotFound()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        assertFalse(contentAsString.isBlank());
    }

    private void performOk(String body, String expectedValue) throws Exception {
        mockMvc.perform(post.content(body))
                .andExpect(respBody.value(expectedValue))
                .andExpect(status().isOk());
    }


    static class SuccessCases implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            Arguments dtos = Arguments.of(foundSix, withTextInjection, onlyNumbers);
            return Stream.of(dtos);
        }

    }

    static class FailedCases implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(Arguments.of(isNotExist, emptyFile));
        }
    }

}
