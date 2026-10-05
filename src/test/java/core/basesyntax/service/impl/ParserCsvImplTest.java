package core.basesyntax.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.DataParser;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ParserCsvImplTest implements TestDataProvider {
    private final DataParser dataParser = new DataParserCsvImpl();

    @Test
    void parse_validData_ok() {
        List<FruitTransaction> result = dataParser.parse(validInput());
        FruitTransaction transaction = result.get(0);
        assertEquals(1, result.size());
        assertEquals("banana", transaction.getFruit());
        assertEquals(20, transaction.getQuantity());
        assertEquals(FruitTransaction.Operation.BALANCE, transaction.getOperation());
    }

    @Test
    void parse_invalidFieldCount_notOk() {
        assertThrows(RuntimeException.class, () -> dataParser.parse(notValidFieldCount()));
    }

    @Test
    void parse_negativeQuantity_notOk() {
        assertThrows(RuntimeException.class, () -> dataParser.parse(notValidNegativeQuantity()));
    }

    @Test
    void parse_invalidOperation_notOk() {
        assertThrows(IllegalArgumentException.class, () -> dataParser.parse(notValidOperation()));
    }
}
