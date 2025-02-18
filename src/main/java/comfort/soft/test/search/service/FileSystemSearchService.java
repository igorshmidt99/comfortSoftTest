package comfort.soft.test.search.service;

import comfort.soft.test.exception.NoSuchXlsx;

public interface FileSystemSearchService {

    String searchN(Integer n, String path) throws NoSuchXlsx;
}
