package core.basesyntax.service.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.service.ReportGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportGeneratorImplTest {
    private ReportGenerator reportGenerator;

    @BeforeEach
    void setUp() {
        reportGenerator = new ReportGeneratorImpl();
        Storage.clear();
    }

    @Test
    void getReport_validStorage_ok() {
        Storage.setQuantity("banana", 10);
        String actualReport = reportGenerator.getReport();

        String expectedReport = "fruit,quantity" + System.lineSeparator()
                + "banana,10" + System.lineSeparator();

        Assertions.assertEquals(expectedReport, actualReport);
    }

    @Test
    void getReport_emptyStorage_onlyHeaderOk() {
        String actualReport = reportGenerator.getReport();
        String expectedReport = "fruit,quantity" + System.lineSeparator();
        Assertions.assertEquals(expectedReport, actualReport);
    }
}
