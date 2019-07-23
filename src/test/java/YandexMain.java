import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;

import static com.codeborne.selenide.Selenide.$;

public class YandexMain {
    private SelenideElement searchField = $("#text");
    private SelenideElement searchPopupFirstResult = $(".popup__content li:nth-child(1)");
    private SelenideElement resultMainText = searchPopupFirstResult.$(".suggest2-item__text");
    private SelenideElement resultFactText = searchPopupFirstResult.$(".suggest2-item__fact");

    public YandexMain setSearchField(String text){
        searchField
                .shouldBe(Condition.visible.because("Поле поиска должно быть видимым"))
                .setValue(text);
        return this;
    }

    public String getFirstResultMainText(){
        return resultMainText
                .shouldBe(Condition.visible.because("Не отобразилась выпадающая подсказка к тексту"))
                .text();
    }

    public String getFirstResultFactText(){
        try {
            return resultFactText
                    .shouldBe(Condition.visible)
                    .text();
        } catch (ElementNotFound e) {
            return "";
        }
    }
}
