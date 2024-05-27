package br.edu.ifpb.easyschoolback.model.exception;

import java.util.Map;

public class MaximumCapacityReachedException extends SchoolBusinessException {

    public MaximumCapacityReachedException() {
        super(
                "Ops! A capacidade máxima para este curso foi atingida",
                Map.of()
        );
    }

    public MaximumCapacityReachedException(final String message) {
        super(
                message,
                Map.of()
        );
    }
}
