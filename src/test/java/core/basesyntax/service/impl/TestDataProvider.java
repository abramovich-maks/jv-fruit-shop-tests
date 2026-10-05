package core.basesyntax.service.impl;

import java.util.List;

public interface TestDataProvider {

    default List<String> validInput() {
        return List.of(
                "type,fruit,quantity",
                "b,banana,20"
        );
    }

    default List<String> notValidFieldCount() {
        return List.of(
                "type,fruit,quantity",
                "b,banana"
        );
    }

    default List<String> notValidNegativeQuantity() {
        return List.of(
                "type,fruit,quantity",
                "b,banana,-10"
        );
    }

    default List<String> notValidOperation() {
        return List.of(
                "type,fruit,quantity",
                "XXX,banana,100"
        );
    }
}
