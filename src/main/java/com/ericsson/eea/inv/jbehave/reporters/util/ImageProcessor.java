package com.ericsson.eea.inv.jbehave.reporters.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static junit.framework.Assert.assertFalse;

/**
 * Created by Attila on 2015.11.01..
 */
@Component
public final class ImageProcessor {
    private static WebDriver driver;

    @Autowired
    public ImageProcessor(WebDriver driver) {
        this.driver = driver;
    }

    public static void takeScreenshot(String screenshotName) throws IOException {
        if (Optional.ofNullable(driver.getWindowHandle()).isPresent()) {
            FileUtils.copyFile(getScreenShotAsFile(), new File("jbehave-screenShotsOnError/" + getStringWithoutSpecialCharacters(screenshotName).substring(0, 100) + "_error.png"));
        }
    }

    public static void takeScreenshotOfWebElement(WebElement webElement, String imageName) throws IOException {
        ImageIO.write(getWebElementFromScreenshot(webElement, getScreenShotAsFile()), "png", new File("jbehave-screenShotsOnError/" + imageName + "_actual.png"));
    }

    public static void takeScreenshotOfWebElementAndCompareWithExpected(WebElement webElement, String imageName) throws IOException {
        ImageProcessor.takeScreenshotOfWebElement(webElement, imageName);
        assertFalse("Image " + imageName + " differs from expected",ImageProcessor.isImageDifferFromExpected(imageName));
    }

    private static File getScreenShotAsFile() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }

    private static BufferedImage getWebElementFromScreenshot(WebElement webElement, File tempFile) throws IOException {
        BufferedImage image = ImageIO.read(tempFile);
        Point point = webElement.getLocation();
        return image.getSubimage(
                point.getX(), point.getY(),
                webElement.getSize().getWidth(), webElement.getSize().getHeight());
    }


    private static String getStringWithoutSpecialCharacters(String screenshotName) {
        return screenshotName.replaceAll("[^a-zA-Z_-]+", "");
    }

    public static boolean isImageDifferFromExpected(String imageName) throws IOException {

        BufferedImage img1 = ImageIO.read(ImageProcessor.class.getResourceAsStream("/expectedImages/" + imageName + "_expected.png"));
        BufferedImage img2 = ImageIO.read(new File("jbehave-screenShotsOnError/" + imageName + "_actual.png"));

        boolean isDiffer = false;
        int width1 = img1.getWidth();
        int width2 = img2.getWidth();
        int height1 = img1.getHeight();
        int height2 = img2.getHeight();
        if ((width1 != width2) || (height1 != height2)) {
            System.err.println("Error: Images dimensions mismatch");

        }

        int width = width1;
        int height = height1;

        BufferedImage outImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int diff;
        int result;
        for (int i = 0; i < height1; i++) {
            for (int j = 0; j < width1; j++) {
                int rgb1 = img1.getRGB(j, i);
                int rgb2 = img2.getRGB(j, i);
                int r1 = (rgb1 >> 16) & 0xff;
                int g1 = (rgb1 >> 8) & 0xff;
                int b1 = (rgb1) & 0xff;
                int r2 = (rgb2 >> 16) & 0xff;
                int g2 = (rgb2 >> 8) & 0xff;
                int b2 = (rgb2) & 0xff;
                diff = Math.abs(r1 - r2);
                diff += Math.abs(g1 - g2);
                diff += Math.abs(b1 - b2);
                diff /= 3;
                result = (diff << 16) | (diff << 8) | diff;
                if (result != 0)
                    isDiffer = true;
                outImg.setRGB(j, i, result);
            }
        }
        if (isDiffer) {

            ImageIO.write(outImg, "png", new File("jbehave-screenShotsOnError/" + imageName + "_diff.png"));
            System.err.println("Images differ, See under ScreenShot Error folder!");
        }

        return isDiffer;

    }

}
