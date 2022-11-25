package com.axonNetworks.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

@Component
public class Methods {

    public void waitUntilPageIsReady() {
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        boolean isJQuery = (boolean) js.executeScript("if (window.jQuery) { return true; } else { return false; }");

        await().atMost(15, SECONDS).pollInterval(1, SECONDS).until(() -> {
            if (js.executeScript("return document.readyState").toString().equals("complete")) {
                if (!isJQuery) {
                    return true;
                } else if ((Long) js.executeScript("return jQuery.active") == 0) {
                    return true;
                }
            }
            return false;
        });
    }


    public void setValue(String text, String value) {
        waitUntilPageIsReady();
        $(text).setValue(value);
    }


    public void searchByElement(SelenideElement element) {
        waitUntilPageIsReady();
        $(element).shouldBe(Condition.visible);
    }

    public boolean isVisible(String text) {
        waitUntilPageIsReady();
        return $(text).isDisplayed();
    }

    public boolean isVisible(SelenideElement element) {
        return element.isDisplayed();
    }

    public void shouldNotVisible(SelenideElement element) {
        element.shouldNotBe(Condition.visible);
    }

    public boolean isDisplayed(String text) {
        waitUntilPageIsReady();
        return $(byText(text)).isDisplayed();
    }

    public boolean isSelected(String element) {
        return $(element).isSelected();
    }

    public boolean isSelected(SelenideElement element) {
        return element.isSelected();
    }

    public void waitUntilIsVisible(SelenideElement element) {
        waitUntilPageIsReady();
        element.shouldHave(visible);
    }

    public void waitUntilIsVisible(SelenideElement element, Integer mseconds) {
        waitUntilPageIsReady();
        element.shouldHave(visible, Duration.ofMillis(mseconds));
    }

    public void refresh() {
        WebDriverRunner.getWebDriver().navigate().refresh();
    }

    public void scrollPageUp() {
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript("window.scrollBy(0,-3000)", "");
        waitUntilPageIsReady();
    }












}
