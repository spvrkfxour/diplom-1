package praktikum;

import org.assertj.core.api.SoftAssertions;
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
        SoftAssertions softly = new SoftAssertions();
        burger.setBuns(bun);

        softly.assertThat(burger.bun)
                .as("Проверка значения bun в burger")
                .isNotNull()
                .isSameAs(bun);
        softly.assertAll();
    }

    @Test
    public void addIngredientTest() {
        SoftAssertions softly = new SoftAssertions();
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);

        softly.assertThat(burger.ingredients)
                .as("Проверка добавления ингредиентов")
                .hasSize(2)
                .containsExactly(ingredient, ingredient);
        softly.assertAll();
    }

    @Test
    public void removeIngredientTest() {
        SoftAssertions softly = new SoftAssertions();
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);

        softly.assertThat(burger.ingredients)
                .as("Проверка удаления ингредиента")
                .hasSize(1)
                .containsExactly(ingredient);
        softly.assertAll();
    }

    @Test
    public void moveIngredientTest() {
        SoftAssertions softly = new SoftAssertions();
        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);
        burger.addIngredient(first);
        burger.addIngredient(second);

        softly.assertThat(burger.ingredients)
                .as("Проверка начального состояния")
                .containsExactly(first, second);

        burger.moveIngredient(0,1);

        softly.assertThat(burger.ingredients)
                .as("Проверка после перемещения")
                .containsExactly(second, first);
        softly.assertAll();
    }

    @Test
    public void getPriceTest() {
        float bunPrice = 200f;
        float ingredientPrice = 100f;
        int ingredientsCount = 2;

        when(bun.getPrice()).thenReturn(bunPrice);
        when(ingredient.getPrice()).thenReturn(ingredientPrice);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);

        float expectedPrice = bunPrice * 2 + ingredientPrice * ingredientsCount;
        float actualPrice = burger.getPrice();

        assertEquals("Метод getPrice() класса Burger неверно рассчитал стоимость",
                expectedPrice, actualPrice, 0.001f);
    }

    @Test
    public void getReceiptTest() {
        SoftAssertions softly = new SoftAssertions();

        final String bunName = "black bun";
        final float bunPrice = 100f;
        final IngredientType ingredientType = IngredientType.SAUCE;
        final String ingredientName = "sour cream";
        final float ingredientPrice = 200f;

        when(bun.getName()).thenReturn(bunName);
        when(bun.getPrice()).thenReturn(bunPrice);
        when(ingredient.getType()).thenReturn(ingredientType);
        when(ingredient.getName()).thenReturn(ingredientName);
        when(ingredient.getPrice()).thenReturn(ingredientPrice);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();
        String[] lines = receipt.split("\n");

        softly.assertThat(lines[0].trim())
                .as("Некорректный формат верхней булочки")
                .isEqualTo("(==== black bun ====)");

        softly.assertThat(lines[1].trim())
                .as("Некорректный формат первого ингредиента")
                .isEqualTo("= sauce sour cream =");

        softly.assertThat(lines[2].trim())
                .as("Некорректный формат второго ингредиента")
                .isEqualTo("= sauce sour cream =");

        softly.assertThat(lines[3].trim())
                .as("Некорректный формат нижней булочки")
                .isEqualTo("(==== black bun ====)");

        softly.assertThat(lines[4])
                .as("Должна быть пустая строка перед ценой")
                .isBlank();

        softly.assertThat(lines[5])
                .as("Некорректная строка с ценой")
                .startsWith("Price: ");

        softly.assertAll();
    }
}
