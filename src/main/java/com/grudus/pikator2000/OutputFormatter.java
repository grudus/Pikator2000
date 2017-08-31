package com.grudus.pikator2000;

import com.grudus.pikator2000.browser.Offer;

import java.time.LocalTime;
import java.util.List;

public class OutputFormatter {

    public static String format(List<Offer> offers) {
        StringBuilder builder = new StringBuilder(LocalTime.now().toString())
                .append(" - ");
        final int size = offers.size();

        for (int i = 0; i < size; i++) {
            Offer offer = offers.get(i);
            builder.append(offer);
            if (i < size - 1)
                builder.append("\n\t\t");
        }
        return builder.toString();
    }
}
