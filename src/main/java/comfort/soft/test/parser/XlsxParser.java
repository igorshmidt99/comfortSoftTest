package comfort.soft.test.parser;

import java.util.Map;

public interface XlsxParser {

    Map<String, Object> getCellsOn(int columnPosition, String path);

}