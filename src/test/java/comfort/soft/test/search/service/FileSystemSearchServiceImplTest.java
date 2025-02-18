package comfort.soft.test.search.service;

import comfort.soft.test.exception.NoSuchXlsx;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileSystemSearchServiceImplTest {


    private final FileSystemSearchService fileSystemSearchService = new FileSystemSearchServiceImpl();
    private final String ifDoesNotFound = "No such value.";

    // полный
    // пустой
    // Полный, но без n
    // Не находит
    // Поломать файл и добавить строчки

    @Test
    void foundSixSuccessfully() {
        var path = "src/test/resources/data.xlsx";
        Integer n = 6;
        String actual = fileSystemSearchService.searchN(n, path);
        assertEquals(n.toString(), actual);
    }

    @Test
    void valueIsntExist() {
        var path = "src/test/resources/data.xlsx";
        Integer n = 100;
        String actual = fileSystemSearchService.searchN(n, path);
        assertNotEquals(n.toString(), actual);
        assertEquals(ifDoesNotFound, actual);
    }

    @Test
    void noSuchFile() {
        var path = "src/test/resources/qwer.xlsxx";
        Integer n = 100;
        assertThrows(NoSuchXlsx.class, () -> fileSystemSearchService.searchN(n, path));
    }

    @Test
    void foundEmpty() {
        var path = "src/test/resources/empty.xlsx";
        Integer n = 0;
        String emptyAnswer = fileSystemSearchService.searchN(n, path);
        assertEquals(ifDoesNotFound, emptyAnswer);
    }

}