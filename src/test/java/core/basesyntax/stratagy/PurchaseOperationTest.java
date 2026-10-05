package core.basesyntax.stratagy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PurchaseOperationTest {

    private final OperationHandler purchaseHandler = new PurchaseOperation();

    @BeforeEach
    void setUp() {
        Storage.clear();
    }

    @Test
    void handle_purchaseOperationNewQuantityIsPositive_ok() {
        Storage.setQuantity("banana", 30);
        FruitTransaction transaction = new FruitTransaction();
        transaction.setFruit("banana");
        transaction.setQuantity(12);
        transaction.setOperation(FruitTransaction.Operation.PURCHASE);
        purchaseHandler.handle(transaction);
        assertEquals(18, Storage.getQuantity("banana"));
    }

    @Test
    void handle_purchaseOperation_notEnoughFruit_notOk() {
        Storage.setQuantity("banana", 30);
        FruitTransaction transaction = new FruitTransaction();
        transaction.setFruit("banana");
        transaction.setQuantity(35);
        transaction.setOperation(FruitTransaction.Operation.PURCHASE);
        assertThrows(RuntimeException.class, () -> purchaseHandler.handle(transaction));
    }
}
