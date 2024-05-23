package br.edu.ifpb.easyschoolback.model.entities.types;

public enum GradeType {

    EXAM("Exam"),
    HOMEWORK("Homework"),
    PROJECT("Project");

    private final String type;

    GradeType(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
