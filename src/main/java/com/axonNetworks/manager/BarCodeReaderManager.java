package com.axonNetworks.manager;


import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class BarCodeReaderManager {

    public static String readQRCode(String filePath) {
        Result result = null;
        try {
            InputStream barCodeInputStream = new FileInputStream(filePath);
            BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);

            LuminanceSource source = new BufferedImageLuminanceSource(barCodeBufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Reader reader = new MultiFormatReader();
            result = reader.decode(bitmap);

        } catch (Exception ex) {

        }

        return result.getText();
    }
}
