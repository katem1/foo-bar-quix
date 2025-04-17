package com.katem.numbertransform.service;

import org.springframework.stereotype.Service;

@Service
public class TransformerService {
    public String transform(int number) {
        StringBuilder result = new StringBuilder();

        // Partie "divisible par"
        if (number % 3 == 0) result.append("FOO");
        if (number % 5 == 0) result.append("BAR");

        // Partie "contient"
        String digits = String.valueOf(number);
        for (char digit : digits.toCharArray()) {
            if (digit == '3') result.append("FOO");
            if (digit == '5') result.append("BAR");
            if (digit == '7') result.append("QUIX");
        }

        return result.length() > 0 ? result.toString() : String.valueOf(number);
    }
}
