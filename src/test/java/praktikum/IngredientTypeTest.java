package praktikum;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;


public class IngredientTypeTest {

    @Test
    public void containAllValuesTest() {
        assertThat(IngredientType.values())
                .as("Проверка наличия значений")
                .containsExactlyInAnyOrder(SAUCE, FILLING);
    }

    @Test
    public void parseFromStringTest() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(IngredientType.valueOf("SAUCE"))
                .as("Проверка парсинга из строки")
                .isEqualTo(SAUCE);
        softly.assertThat(IngredientType.valueOf("FILLING"))
                .as("Проверка парсинга из строки")
                .isEqualTo(FILLING);
        softly.assertAll();
    }
}
