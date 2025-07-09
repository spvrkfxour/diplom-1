package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class IngredientTest {
    private Ingredient ingredient;
    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Ingredient type = {0}, name = {1}, price = {2}")
    public static Object[][] data() {
        return new Object[][] {
                {IngredientType.SAUCE, "chili sauce", 300f},
                {IngredientType.FILLING, "dinosaur", 200f}
        };
    }

    @Before
    public void setUp() {
        ingredient = new Ingredient(type, name, price);
    }

    @Test
    public void getPriceTest() {
        assertEquals("Метод getPrice() класса Ingredient вернул некорректную цену",
                price, ingredient.getPrice(), 0.001f);
    }

    @Test
    public void getNameTest() {
        assertEquals("Метод getName() класса Ingredient вернул некорректное имя",
                name, ingredient.getName());
    }

    @Test
    public void getTypeTest() {
        assertEquals("Метод getType() класса Ingredient вернул некорректный тип enum IngredientType",
                type, ingredient.getType());
    }
}
