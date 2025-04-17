package com.katem.numbertransform.service;

import org.springframework.stereotype.Service;

@Service
public class TransformerService {
    public String transform(int number) {
        StringBuilder result = new StringBuilder();
        String numStr = String.valueOf(number);

        boolean divisible = false;

        if (number % 3 == 0) {
            result.append("FOO");
            divisible = true;
        }
        if (number % 5 == 0) {
            result.append("BAR");
            divisible = true;
        }

        if (!divisible) {
            for (char digit : numStr.toCharArray()) {
                if (digit == '3') result.append("FOO");
                if (digit == '5') result.append("BAR");
                if (digit == '7') result.append("QUIX");
            }
        }

        return !result.isEmpty() ? result.toString() : numStr;
    }
}
