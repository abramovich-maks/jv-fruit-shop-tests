package core.basesyntax.service.impl;

import static core.basesyntax.model.FruitTransaction.Operation.BALANCE;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.ShopService;
import core.basesyntax.strategy.OperationHandler;
import core.basesyntax.strategy.OperationStrategy;
import core.basesyntax.strategy.OperationStrategyImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopServiceImplTest {

    @Test
    void process_validTransaction_ok() {
        Map<FruitTransaction.Operation, OperationHandler> operationHandlers = new HashMap<>();
        TestOperationHandler testOperationHandler = new TestOperationHandler();
        operationHandlers.put(BALANCE, testOperationHandler);
        OperationStrategy strategy = new OperationStrategyImpl(operationHandlers);
        ShopService shopService = new ShopServiceImpl(strategy);
        FruitTransaction expectedTransaction = new FruitTransaction()
                .setOperation(BALANCE)
                .setFruit("apple")
                .setQuantity(10);
        List<FruitTransaction> transactions = List.of(
                expectedTransaction);

        shopService.process(transactions);

        int actualCallCount = testOperationHandler.getCallCount();
        FruitTransaction actualTransaction = testOperationHandler.getTransaction();
        int expectedCount = 1;
        Assertions.assertEquals(expectedCount, actualCallCount);
        Assertions.assertSame(expectedTransaction, actualTransaction);
    }
}
