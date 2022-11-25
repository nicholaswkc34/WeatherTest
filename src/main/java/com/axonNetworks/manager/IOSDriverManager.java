package com.axonNetworks.manager;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

@ParametersAreNonnullByDefault
public class IOSDriverManager implements WebDriverProvider {
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        File appDir = new File(System.getProperty("user.dir"));
        File app = new File(appDir, "..\\application\\Flipkart.app");

        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setDeviceName("");
        options.setPlatformVersion("14.0");
        options.setApp(app.getAbsolutePath());

        try {
            return new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
