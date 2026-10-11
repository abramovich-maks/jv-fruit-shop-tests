package core.basesyntax.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.service.FileReader;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FileReaderCsvImplTest {
    private final FileReader fileReader = new FileReaderCsvImpl();

    @Test
    void read_fileExists_ok() {
        List<String> strings = fileReader.readFromFile("src/test/resources/testStorage.csv");
        List<String> expectedLines = List.of(
                "type,fruit,quantity",
                "b,banana,20",
                "b,apple,100",
                "s,banana,100",
                "p,banana,13",
                "r,apple,10",
                "p,apple,20",
                "p,banana,5",
                "s,banana,50"
        );
        assertFalse(strings.isEmpty());
        assertEquals(expectedLines, strings);
    }

    @Test
    void read_fileDoesNotExist_throwsRuntimeException() {
        assertThrows(RuntimeException.class,
                () -> fileReader.readFromFile("src/testStorage.csv"));
    }
}
