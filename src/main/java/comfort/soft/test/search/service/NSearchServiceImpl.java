package comfort.soft.test.search.service;

import comfort.soft.test.parser.NoSuchXlsx;
import comfort.soft.test.parser.XlsxParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class NSearchServiceImpl implements NSearchService {

    private final XlsxParser parser;

    @Override
    public String search(Integer n, String path) throws NoSuchXlsx {
        log.info("Start to search {}  in {}...", n, path);
        int columnPosition = 0;
        Map<String, Object> xlsxContent = parser.getCellsOn(columnPosition, path);
        log.info("Search has been completed without errors...");
        return getN(xlsxContent, n);
    }

    private String getN(Map<String, Object> xlsxContent, Integer n) {
        String noValueAnswer = "No such value.";
        String nAsString = n.toString();

        if (xlsxContent.get(nAsString) != null) {
            return nAsString;
        }
        return noValueAnswer;
    }

}