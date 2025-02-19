package comfort.soft.test.search.service;

import comfort.soft.test.parser.NoSuchXlsx;
import comfort.soft.test.parser.XlsxParserImpl;
import org.junit.jupiter.api.Test;

import static comfort.soft.test.data.PathsToTestFiles.*;
import static comfort.soft.test.data.TestData.noSuchValue;
import static org.junit.jupiter.api.Assertions.*;

class SearchServiceImplTest {

    private final NSearchService searchService = new NSearchServiceImpl(new XlsxParserImpl());

    @Test
    void foundSixSuccessfully() {
        Integer n = 6;
        String actual = searchService.search(n, DATA.path);
        assertEquals(n.toString(), actual);
    }

    @Test
    void valueIsntExist() {
        Integer n = 100;
        String actual = searchService.search(n, DATA.path);

        assertEquals(noSuchValue, actual);
    }

    @Test
    void noSuchFile() {
        Integer n = 100;

        assertThrows(NoSuchXlsx.class, () -> searchService.search(n, NO_SUCH_FILE.path));
    }

    @Test
    void foundEmpty() {
        Integer n = 0;
        String emptyAnswer = searchService.search(n, EMPTY.path);

        assertEquals(noSuchValue, emptyAnswer);
    }

    @Test
    void multicolumnTextInjectionsShouldUseOnlyFirstColumn() {
        Integer n = 2;
        String actual = searchService.search(n, BROKEN_2.path);

        assertEquals(n.toString(), actual);
    }

    @Test
    void multicolumnOnlyNumbersFileShouldReturnValue() {
        Integer n = 123;
        String actual = searchService.search(n, BROKEN.path);

        assertEquals(n.toString(), actual);
    }

}