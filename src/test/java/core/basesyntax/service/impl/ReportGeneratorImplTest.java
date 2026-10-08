package core.basesyntax.service.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.ReportGenerator;
import core.basesyntax.stratagy.BalanceOperation;
import core.basesyntax.stratagy.OperationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportGeneratorImplTest {
    private ReportGenerator reportGenerator;
    private OperationHandler handler;

    @BeforeEach
    void setUp() {
        reportGenerator = new ReportGeneratorImpl();
        handler = new BalanceOperation();
        Storage.clear();
    }

    @Test
    void getReport_validStorage_ok() {
        FruitTransaction transaction = new FruitTransaction();
        transaction.setFruit("banana");
        transaction.setQuantity(10);
        transaction.setOperation(FruitTransaction.Operation.BALANCE);
        handler.handle(transaction);
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
