package com.huonggiang.lab08;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testAppInitialization() {
        App app = new App();
        assertNotNull(app);
    }

    @Test
    public void testAddition() {
        assertEquals(30, 15 + 15);
    }
}