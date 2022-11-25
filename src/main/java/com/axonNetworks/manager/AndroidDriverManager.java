package com.axonNetworks.manager;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@ParametersAreNonnullByDefault
public class AndroidDriverManager implements WebDriverProvider {
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        File appDir = new File(System.getProperty("user.dir"));
        File app = new File(appDir, "..\\application\\Flipkart.apk");

        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setPlatformVersion("9.0"); // it seems calculator app is not available in later Android versions
        options.setApp(app.getAbsolutePath());
        options.setAppPackage("com.android.calculator2");
        options.setAppActivity("com.android.calculator2.Calculator");
        options.setNewCommandTimeout(Duration.ofSeconds(15));
        options.setFullReset(false);

        try {
            return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
