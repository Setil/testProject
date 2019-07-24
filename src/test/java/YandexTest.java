import com.codeborne.selenide.Selenide;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class YandexTest {
    private static final Logger logger = LogManager.getLogger(YandexTest.class);

    @BeforeEach
    void setUp() {
        Selenide.open(Settings.getInstance().getBaseUrl());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Погода", "Липецк", "Лото"})
    @DisplayName("Получение первого результата в выпадающем списке")
    void getFirstPopupInfoTest(String searchString) {
        YandexMain yandexMain = new YandexMain();
        yandexMain.setSearchField(searchString);
        String mainText = yandexMain.getFirstResultMainText();
        String factText = yandexMain.getFirstResultFactText();
        logger.info(String.format("Введено: [%s]. Первый результат: [%s]",
                searchString,
                mainText +
                        (
                        !factText.isEmpty()
                        ?" " + factText
                        :""
                        )
        ));
    }

    @Test
    @DisplayName("Проверка вкладки Картинки")
    void verifyImagesTab() {
        new YandexMain()
                .verifyImagesTabVisible();
    }
}
