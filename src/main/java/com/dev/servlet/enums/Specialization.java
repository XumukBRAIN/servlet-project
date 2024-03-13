package com.dev.servlet.enums;

public enum Specialization {
    ALGEBRA("Алгебра"),
    GEOMETRY("Геометрия"),
    RUSSIAN("Русский язык"),
    ENGLISH("Английский язык"),
    PHYSICS("Физика"),
    CHEMISTRY("Химия"),
    COMPUTER_SCIENCE("Информатика"),
    HISTORY("История"),
    SOCIAL_SCIENCE("Обществознание");

    private final String title;

    Specialization(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
