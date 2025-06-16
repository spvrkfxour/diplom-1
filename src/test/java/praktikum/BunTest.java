package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class BunTest {
    private Bun bun;
    private final String VALID_NAME = "White";
    private final float VALID_PRICE = 35.0f;

    @Before
    public void setUp() {
        bun = new Bun(VALID_NAME, VALID_PRICE);
    }

    @Test
    public void getNameTest() {
        assertEquals("Метод getName() класса Bun вернул некорректное имя", VALID_NAME, bun.getName());
    }

    @Test
    public void getPriceTest() {
        assertEquals("Метод getPrice() класса Bun вернул некорректную цену", VALID_PRICE, bun.getPrice(), 0.001f);
    }
}
