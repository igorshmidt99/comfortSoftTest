package comfort.soft.test.search.service;

import comfort.soft.test.parser.NoSuchXlsx;

public interface NSearchService {

    String search(Integer n, String path) throws NoSuchXlsx;
}
