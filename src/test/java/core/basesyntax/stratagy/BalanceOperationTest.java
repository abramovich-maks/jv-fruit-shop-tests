package core.basesyntax.stratagy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BalanceOperationTest {

    private final OperationHandler handler = new BalanceOperation();

    @BeforeEach
    void setUp() {
        Storage.clear();
    }

    @Test
    void handle_balanceOperation_setsQuantity() {
        FruitTransaction transaction = new FruitTransaction();
        transaction.setFruit("banana");
        transaction.setQuantity(10);
        transaction.setOperation(FruitTransaction.Operation.BALANCE);
        handler.handle(transaction);
        assertEquals(10, Storage.getQuantity("banana"));
    }
}
