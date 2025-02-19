package comfort.soft.test.parser;

import lombok.extern.slf4j.Slf4j;
import org.dhatim.fastexcel.reader.Cell;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class XlsxParserImpl implements XlsxParser {

    @Override
    public Map<String, Object> getCellsOn(int columnPosition, String path) {
        Map<String, Object> content = new HashMap<>();
        Path fsPath = Path.of(path);

        log.info("Trying to open a xlsx...");
        try (var readableWorkbook = new ReadableWorkbook(fsPath.toFile())) {
            readableWorkbook.getFirstSheet()
                    .openStream()
                    .map(row -> row.getCell(0))
                    .filter(Objects::nonNull)
                    .map(Cell::getRawValue)
                    .forEach(cellValue -> content.put(cellValue, new Object()));
        } catch (NoSuchFileException e) {
            throw new NoSuchXlsx(e.getMessage());
        } catch (IOException ignored) {
            log.info("Founded xlsx with given path is empty.");
        }
        return content;
    }

}
