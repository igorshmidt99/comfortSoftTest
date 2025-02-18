package comfort.soft.test.search.service;

import comfort.soft.test.exception.NoSuchXlsx;
import lombok.extern.slf4j.Slf4j;
import org.dhatim.fastexcel.reader.Cell;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.stream.Stream;

@Slf4j
@Service
public class FileSystemSearchServiceImpl implements FileSystemSearchService {

    @Override
    public String searchN(Integer n, String path) throws NoSuchXlsx {
        log.info("Start to search {}  in {}...", n, path);
        Stream<String> fromFile = contentOf(path);
        log.info("Search completed without errors...");
        return getResp(fromFile, n);
    }

    private String getResp(Stream<String> fromFile, Integer n) {
        return fromFile
                .filter(value -> n.toString().equals(value))
                .findAny()
                .orElse("No such value.");
    }

    private Stream<String> contentOf(String path) throws NoSuchXlsx {
        Stream<String> content;
        Path fsPath = Path.of(path);
        try (var readableWorkbook = new ReadableWorkbook(fsPath.toFile())) {
            content = readableWorkbook.getFirstSheet().openStream()
                    .flatMap(Row::stream)
                    .map(Cell::getRawValue);
        } catch (NoSuchFileException e) {
            throw new NoSuchXlsx(e.getMessage());
        } catch (IOException e) {
            // TODO Обработать чтение пустого файла
            e.getMessage().contains("read");
            throw new NoSuchXlsx(e.getMessage());
        }
        return content;
    }

}