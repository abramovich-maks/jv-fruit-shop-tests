package core.basesyntax.service.impl;

import static core.basesyntax.model.FruitTransaction.Operation.BALANCE;
import static core.basesyntax.model.FruitTransaction.Operation.PURCHASE;
import static core.basesyntax.model.FruitTransaction.Operation.RETURN;
import static core.basesyntax.model.FruitTransaction.Operation.SUPPLY;
import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.ShopService;
import core.basesyntax.stratagy.BalanceOperation;
import core.basesyntax.stratagy.OperationHandler;
import core.basesyntax.stratagy.OperationStrategy;
import core.basesyntax.stratagy.OperationStrategyImpl;
import core.basesyntax.stratagy.PurchaseOperation;
import core.basesyntax.stratagy.ReturnOperation;
import core.basesyntax.stratagy.SupplyOperation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShopServiceImplTest {

    private ShopService shopService;

    @BeforeEach
    void setUp() {
        Storage.clear();

        Map<FruitTransaction.Operation, OperationHandler> operationHandlers = new HashMap<>();
        operationHandlers.put(BALANCE, new BalanceOperation());
        operationHandlers.put(PURCHASE, new PurchaseOperation());
        operationHandlers.put(RETURN, new ReturnOperation());
        operationHandlers.put(SUPPLY, new SupplyOperation());
        OperationStrategy strategy = new OperationStrategyImpl(operationHandlers);
        shopService = new ShopServiceImpl(strategy);
    }

    @Test
    void service_multipleOperationsUpdateStorage() {
        List<FruitTransaction> list = List.of(
                new FruitTransaction()
                        .setOperation(BALANCE)
                        .setFruit("apple")
                        .setQuantity(10),
                new FruitTransaction()
                        .setOperation(SUPPLY)
                        .setFruit("apple")
                        .setQuantity(5),
                new FruitTransaction()
                        .setOperation(PURCHASE)
                        .setFruit("apple")
                        .setQuantity(3),
                new FruitTransaction()
                        .setOperation(RETURN)
                        .setFruit("apple")
                        .setQuantity(2)
        );
        shopService.process(list);
        int expected = 14;
        assertEquals(expected, Storage.getQuantity("apple"));
    }
}
