package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Burger burger;

    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(bun);
        assertNotNull("В burger значение bun - null", burger.bun);
        assertEquals("Значение bun в burger не равно переданному", bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);

        assertEquals("Размер списка не равно 2", 2, burger.ingredients.size());
        assertEquals("1 элемент списка не равен добавленному", ingredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);

        assertEquals("Размер списка не равен 1", 1, burger.ingredients.size());
        assertEquals("Элемент списка не равен добавленному", ingredient, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientTest() {
        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);

        burger.addIngredient(first);
        burger.addIngredient(second);

        assertEquals("1 элемент списка не Ingredient first", first, burger.ingredients.get(0));
        burger.moveIngredient(0,1);
        assertEquals("2 элемент списка не Ingredient first", first, burger.ingredients.get(1));
    }

    @Test
    public void getPriceTest() {
        when(bun.getPrice()).thenReturn(200f);
        when(ingredient.getPrice()).thenReturn(100f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);
        assertEquals("Метод getPrice() класса Burger неверно рассчитал стоимость",
                200f * 2 + 100f * 2, burger.getPrice(), 0.001f);
    }

    @Test
    public void getReceiptTest() {

        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient.getName()).thenReturn("sour cream");
        when(ingredient.getPrice()).thenReturn(200f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();
        String[] lines = receipt.split("\n");

        assertEquals("(==== black bun ====)", lines[0].trim());
        assertEquals("= sauce sour cream =", lines[1].trim());
        assertEquals("= sauce sour cream =", lines[2].trim());
        assertEquals("(==== black bun ====)", lines[3].trim());
        assertTrue(lines[4].isBlank());
        assertTrue(lines[5].startsWith("Price: "));
        assertTrue(lines[5].contains("600,000000"));
    }
}
