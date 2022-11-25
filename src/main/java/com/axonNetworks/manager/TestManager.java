package com.axonNetworks.manager;


import com.aventstack.extentreports.ExtentTest;
import com.axonNetworks.pages.WeatherPages;

public abstract class TestManager {

    protected DAOManager daoManager;
    protected MFAManager mfaManager;
    protected UICompareManager uiCompareManager;
    protected CryptoManager cryptoManager;
    protected SpringInitManager springInitManager;
    protected MailerManager mailerManager;
    protected ExtentManager extentManager;
    protected CaptchaManager captchaManager;
    protected PropertiesManager propertiesManager;
    protected BrokenLinkChecker brokenLinkChecker;

    protected ExtentTest extentTest;

    // ==========================================================================
    protected WeatherPages weatherPages = new WeatherPages();

    public TestManager() {
        daoManager = new DAOManager();
        mfaManager = new MFAManager();
        mailerManager = new MailerManager();
        extentManager = new ExtentManager();
        //uiCompareManager = new UICompareManager();
        cryptoManager = CryptoManager.getInstance(System.getProperty("user.dir") + "\\credential\\salt.txt");
        springInitManager = new SpringInitManager();
        captchaManager = new CaptchaManager();
        propertiesManager = PropertiesManager.createInstance();
        brokenLinkChecker = new BrokenLinkChecker();
    }





}

//================================================================================================================
/*
Aqua Web Training
https://www.youtube.com/watch?v=u94Pf9--Vc4



 */

