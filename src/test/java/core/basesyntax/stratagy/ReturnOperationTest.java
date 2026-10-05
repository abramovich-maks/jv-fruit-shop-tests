package core.basesyntax.stratagy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReturnOperationTest {

    private final OperationHandler returnOperation = new ReturnOperation();

    @BeforeEach
    void setUp() {
        Storage.clear();
    }

    @Test
    void handle_returnOperation_addsQuantityExistingFruit_ok() {
        Storage.setQuantity("banana", 30);
        FruitTransaction transaction = new FruitTransaction();
        transaction.setFruit("banana");
        transaction.setQuantity(12);
        returnOperation.handle(transaction);
        assertEquals(42, Storage.getQuantity("banana"));
    }

    @Test
    void handle_returnOperation_addsQuantityNotExistingFruit_ok() {
        FruitTransaction transaction = new FruitTransaction();
        transaction.setFruit("banana");
        transaction.setQuantity(12);
        returnOperation.handle(transaction);
        assertEquals(12, Storage.getQuantity("banana"));
    }
}
