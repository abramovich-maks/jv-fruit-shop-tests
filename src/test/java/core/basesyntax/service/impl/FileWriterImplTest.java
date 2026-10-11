package core.basesyntax.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.service.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileWriterImplTest {
    private final FileWriter fileWriter = new FileWriterImpl();

    @Test
    void write_successfully_ok() throws IOException {
        String expectedReport = "fruit,quantity\nbanana,10";
        fileWriter.write(expectedReport, "src/test/resources/finalReport.csv");
        String actualReport = Files.readString(Path.of("src/test/resources/finalReport.csv"));
        Assertions.assertEquals(expectedReport, actualReport);
    }

    @Test
    void write_directoryDoesNotExist_throwsRuntimeException() {
        assertThrows(RuntimeException.class,
                () -> fileWriter.write("fruit,quantity\nbanana,10",
                        "qqq/finalReport.csv"));
    }
}
