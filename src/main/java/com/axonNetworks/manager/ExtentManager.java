package com.axonNetworks.manager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private ExtentSparkReporter sparkReporter;
    private ExtentReports extentReports;

    public ExtentManager() {
        sparkReporter = new ExtentSparkReporter("report/TestReport-Spart.html");
        sparkReporter.config().setReportName("Test Automation Report");
        sparkReporter.config().setDocumentTitle("Test Automation Report");
        sparkReporter.config().setTheme(Theme.DARK);
        extentReports = new ExtentReports();
        extentReports.setSystemInfo("Company Name","");
        extentReports.setSystemInfo("Test Lead","");

        extentReports.attachReporter(sparkReporter);

    }

    public ExtentReports getExtentReports() {
        return extentReports;
    }





}
