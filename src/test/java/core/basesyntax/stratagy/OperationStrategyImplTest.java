package core.basesyntax.stratagy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.FruitTransaction;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class OperationStrategyImplTest {

    @Test
    void getOperationHandler_validOperation_returnsHandler() {
        OperationHandler expectedHandler = new BalanceOperation();
        Map<FruitTransaction.Operation, OperationHandler> operationHandlers = new HashMap<>();
        operationHandlers.put(FruitTransaction.Operation.BALANCE, expectedHandler);

        OperationStrategy operationStrategy = new OperationStrategyImpl(operationHandlers);
        OperationHandler result = operationStrategy
                .getOperationHandler(FruitTransaction.Operation.BALANCE);
        assertEquals(expectedHandler, result);
    }

    @Test
    void getOperationHandler_unknownOperation_throwsException() {
        Map<FruitTransaction.Operation, OperationHandler> operationHandlers = new HashMap<>();
        OperationStrategy strategy = new OperationStrategyImpl(operationHandlers);

        assertThrows(RuntimeException.class,
                () -> strategy.getOperationHandler(FruitTransaction.Operation.BALANCE));
    }
}
