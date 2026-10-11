package core.basesyntax.service.impl;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.OperationHandler;

public class TestOperationHandler implements OperationHandler {
    private int callCount = 0;
    private FruitTransaction transaction;

    @Override
    public void handle(final FruitTransaction transaction) {
        this.transaction = transaction;
        callCount++;
    }

    public int getCallCount() {
        return callCount;
    }

    FruitTransaction getTransaction() {
        return transaction;
    }
}
