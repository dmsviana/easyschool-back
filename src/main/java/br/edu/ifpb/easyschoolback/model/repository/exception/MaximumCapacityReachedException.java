package br.edu.ifpb.easyschoolback.model.repository.exception;

public class MaximumCapacityReachedException extends RuntimeException {

    public MaximumCapacityReachedException(final String message) {
        super(message);
    }
}
