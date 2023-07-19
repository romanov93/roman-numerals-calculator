package ru.romanov.calculator;

public class Quote {

    private final String latinText;
    private final String translate;

    public Quote (String latinText, String translate) {
        this.latinText = latinText;
        this.translate = translate;
    }

    public String getLatinText() {
        return latinText;
    }

    public String getTranslate() {
        return translate;
    }


}
