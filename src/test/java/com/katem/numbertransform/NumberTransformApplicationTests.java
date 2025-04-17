package com.katem.numbertransform;

import com.katem.numbertransform.service.TransformerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class NumberTransformApplicationTests {

    private final TransformerService transformerService = new TransformerService();

    @Test
    void testSimpleNumbers() {
        assertEquals("1", transformerService.transform(1));
        assertEquals("FOOFOO", transformerService.transform(3));
        assertEquals("BARBAR", transformerService.transform(5));
        assertEquals("QUIX", transformerService.transform(7));
        assertEquals("FOO", transformerService.transform(9));
    }

    @Test
    void testDoubleConditions() {
        assertEquals("FOOBARBAR", transformerService.transform(15));
        assertEquals("FOOBAR", transformerService.transform(51));
        assertEquals("BARFOO", transformerService.transform(53));
        assertEquals("FOOFOOFOO", transformerService.transform(33));
    }

    @Test
    void testNoMatch() {
        assertEquals("2", transformerService.transform(2));
        assertEquals("4", transformerService.transform(4));
        assertEquals("8", transformerService.transform(8));
    }

}
