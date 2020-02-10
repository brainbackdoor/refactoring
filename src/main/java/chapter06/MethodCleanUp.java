package chapter06;

import java.util.Iterator;
import java.util.List;

public class MethodCleanUp {

    public void printOwing(List samples) {
        printBanner();

        double result = sum(samples);

        printDetails(result);
    }

    private double sum(List samples) {
        double outstanding = 0.0;
        Iterator iterator = samples.listIterator();

        while (iterator.hasNext()) {
            String sample = String.valueOf(iterator.next());
            outstanding += Integer.parseInt(sample);
        }
        return outstanding;
    }

    private void printDetails(double outstanding) {
        System.out.println("고객명: CU");
        System.out.println("외상액: " + outstanding);
    }

    private void printBanner() {
        System.out.println("---------");
        System.out.println("고객 외상");
        System.out.println("---------");
    }

    int numberOfLateDeliveries;

    public int getRating() {
        return numberOfLateDeliveries > 5 ? 2 : 1;
    }

    String platform;
    String browser;
    Integer resize;

    void temp() {
        if ((platform.toUpperCase().indexOf("MAC") > -1) &&
                (browser.toUpperCase().indexOf("IE") > -1) &&
                wasInitialized() && resize > 0) {
        }

        final boolean isMacOs = platform.toUpperCase().indexOf("MAC") > -1;
        final boolean isIEBrowser = browser.toUpperCase().indexOf("IE") > -1;
        final boolean wasResized = resize > 0;

        if (isMacOs && isIEBrowser && wasInitialized() && wasResized) {

        }

    }

}

    private boolean wasInitialized() {
        return false;
    }
