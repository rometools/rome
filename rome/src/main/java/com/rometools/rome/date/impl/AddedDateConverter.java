package com.rometools.rome.date.impl;

import com.rometools.rome.date.AbstractDateConverter;
import com.rometools.rome.io.impl.PropertiesLoader;

import java.util.Date;
import java.util.Locale;

public class AddedDateConverter extends AbstractDateConverter {
    private static String[] ADDED_MASKS;

    static {
        String[] extraMasks = PropertiesLoader.getPropertiesLoader()
                .getTokenizedProperty("datetime.extra.masks", "|");
        String[] invalidMasks = {
                "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
                "HH:mm:ss yyyy-MM-dd", "HH:mm yyyy-MM-dd",
                "yy-MM-dd HH:mm:ss", "yy-MM-dd HH:mm",
                "HH:mm:ss yy-MM-dd", "HH:mm yy-MM-dd",
        };
        int totalLength = extraMasks.length + invalidMasks.length;
        int destPos = 0;
        ADDED_MASKS = new String[totalLength];
        System.arraycopy(extraMasks, 0, ADDED_MASKS, destPos, extraMasks.length);
        destPos += extraMasks.length;
        System.arraycopy(invalidMasks, 0, ADDED_MASKS, destPos, invalidMasks.length);
    }

    @Override
    public Date convert(String sDate, Locale locale) {
        if (sDate == null) return null;
        sDate = replaceSlash(sDate, "-");
        Date date = parseUsingMask(ADDED_MASKS, sDate, locale);
        if (date == null && !Locale.getDefault().equals(locale)) {
            date = parseUsingMask(ADDED_MASKS, sDate, Locale.getDefault());
        }
        return date;
    }

    private String replaceSlash(String str, String replacement) {
        return str.replace("/", replacement);
    }
}
