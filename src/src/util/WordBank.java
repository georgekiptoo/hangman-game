package util;

import java.util.Random;

public class WordBank {

    private static final String[] WORDS = {
            "CALCULATOR", "PROGRAMARE", "ALGORITM", "VARIABILA",
            "FUNCTIE", "RECURSIVITATE", "POLIMORFISM", "MOSTENIRE",
            "INTERFATA", "COMPILATOR", "DEPANARE", "STRUCTURA",
            "TASTATURA", "MONITOR", "PROCESOR", "MEMORIE",
            "RETEA", "SECURITATE", "CRIPTOGRAFIE", "BAZA"
    };

    private static final Random rand = new Random();

    public static String getRandomWord() {
        return WORDS[rand.nextInt(WORDS.length)];
    }
}
