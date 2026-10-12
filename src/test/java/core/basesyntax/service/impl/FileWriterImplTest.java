package core.basesyntax.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.service.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class FileWriterImplTest {
    private final FileWriter fileWriter = new FileWriterImpl();
    @TempDir
    private Path tempDir;

    @Test
    void write_successfully_ok() throws IOException {
        String expectedReport = "fruit,quantity\nbanana,10";
        Path csvFilePath = tempDir.resolve("finalReport.csv");
        fileWriter.write(expectedReport, csvFilePath.toString());
        String actualReport = Files.readString(csvFilePath);
        Assertions.assertEquals(expectedReport, actualReport);
    }

    @Test
    void write_directoryDoesNotExist_throwsRuntimeException() {
        Path missingPath = tempDir.resolve("qqq").resolve("finalReport.csv");
        assertThrows(RuntimeException.class,
                () -> fileWriter.write("fruit,quantity\nbanana,10",
                        missingPath.toString()));
    }
}
