package com.axonNetworks.pages;

import com.axonNetworks.utils.Methods;
import com.codeborne.selenide.Condition;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import com.axonNetworks.pojo.Weather;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class WeatherPages {
    private Methods methods = new Methods();

    private String searchTextBox = "input#LocationSearch_input";

    private String searchOpt = "//button[contains(text(), 'Singapore, Singapore')]";

    private String next10Days = "//span[contains(text(), '10 Day')]";

    private String date = "(//h3[@data-testid='daypartName'])[";

    private String temperature = "(//span[@data-testid='TemperatureValue'])[";

    private String humidity = "(//span[@data-testid='PercentageValue'])[";


    private String carCheckBox = "//*[@id=\"WxuPollCard-sidebar-6e5c1943-8ee9-46e7-9d61-812225cc4795\"]/section/div/div/div/div[2]/div/div/div[1]/label/div/span/svg";

    public void inputSearchCriteria(String weatherCountry) {
        methods.waitUntilPageIsReady();
        //$(searchTextBox).shouldBe(Condition.interactable);
        $(searchTextBox).click();
        $(searchTextBox).sendKeys(weatherCountry);
        $x(searchOpt).click();
    }

    public void navigateNext10Days() {
        $x(next10Days).click();
    }

    public ArrayList<Weather> capture10DaysWeather() {
        ArrayList<Weather> resultList = new ArrayList<Weather>();

        try {
            FileWriter out = new FileWriter(System.getProperty("user.dir") + "\\result\\" + "result.csv");

            int highTempArray[] = {1, 5, 9, 13, 17, 21, 25, 29, 33, 37, 41}, highTempLoop = 0, lowTempLoop = 0;
            int humidityArray[] = {1, 6, 11, 16, 21, 26, 31, 36, 41, 46, 51};

            for (int i = 1; i < 11; i++) {
                Weather weaObj = new Weather();
                // Date
                date = "(//h3[@data-testid='daypartName'])[";
                date +=  String.valueOf(i) + ']';
                if (i == 1) {
                    weaObj.setDate("Today");
                } else {
                    weaObj.setDate($x(date).getText());
                }

                // High Temp
                temperature = "(//span[@data-testid='TemperatureValue'])[";
                highTempLoop = highTempArray[i];
                temperature += String.valueOf(highTempLoop) + "]";
                weaObj.setHighTemperature($x(temperature).getText());

                lowTempLoop = highTempLoop + 1;
                temperature = "(//span[@data-testid='TemperatureValue'])[";
                temperature += String.valueOf(lowTempLoop) + "]";
                weaObj.setLowTemperature($x(temperature).getText());

                humidity = "(//span[@data-testid='PercentageValue'])[";
                humidity += String.valueOf(humidityArray[i]) + "]";
                weaObj.setHumidity($x(humidity).getText());


                resultList.add(weaObj);
            }

            try(CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT)) {

                for (int i = 0; i < 10; i++) {
                    printer.printRecord(resultList.get(i).getDate(), resultList.get(i).getHighTemperature(),
                            resultList.get(i).getLowTemperature(), resultList.get(i).getHumidity());
                }

                printer.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return resultList;
    }

    public void createSummaryReport(ArrayList<Weather> arrayList) {
        int totalHighTemperature = 0, totalLowTemperature = 0, totalHumidity = 0;
        int averageHighTemperature = 0, averageLowTemperature = 0, averageHumidity = 0;

        for (Weather aWeather : arrayList) {
            totalHighTemperature += Integer.valueOf(aWeather.getHighTemperature().replaceAll("[^0-9]", ""));
            totalLowTemperature += Integer.valueOf(aWeather.getLowTemperature().replaceAll("[^0-9]", ""));
            totalHumidity += Integer.valueOf(aWeather.getHumidity().replaceAll("[^0-9]", ""));
        }

        if(totalHighTemperature != 0) { averageHighTemperature =  totalHighTemperature / 10; }
        if(totalLowTemperature != 0) { averageLowTemperature = totalLowTemperature / 10; }
        if(totalHumidity != 0) { averageHumidity = totalHumidity / 10; }

        try {
            FileWriter out = new FileWriter(System.getProperty("user.dir") + "\\result\\" + "summaryReport.csv");

            try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT)) {
                printer.printRecords(averageHighTemperature, averageLowTemperature,
                        averageHumidity);

                printer.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void submitShoppingList() {
        $x(carCheckBox).click();
    }

}
