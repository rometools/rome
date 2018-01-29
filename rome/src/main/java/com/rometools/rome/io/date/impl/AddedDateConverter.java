package com.rometools.rome.io.date.impl;

import com.rometools.rome.io.date.AbstractDateConverter;
import com.rometools.rome.io.impl.PropertiesLoader;

import java.util.Date;
import java.util.Locale;

public class AddedDateConverter extends AbstractDateConverter {
    private static String[] ADDED_MASKS;

    @SuppressWarnings("unused")
    private static final String[] masks = {"yyyy-MM-dd'T'HH:mm:ss.SSSz", "yyyy-MM-dd't'HH:mm:ss.SSSz", // invalid
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd't'HH:mm:ss.SSS'z'", // invalid
            "yyyy-MM-dd'T'HH:mm:ssz", "yyyy-MM-dd't'HH:mm:ssz", // invalid
            "yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd't'HH:mm:ss'z'", // invalid
            "yyyy-MM-dd'T'HH:mmz", // invalid
            "yyyy-MM-dd't'HH:mmz", // invalid
            "yyyy-MM-dd'T'HH:mm'Z'", // invalid
            "yyyy-MM-dd't'HH:mm'z'", // invalid
            "yyyy-MM-dd", "yyyy-MM", "yyyy"};

    static {
        String[] extraMasks = PropertiesLoader.getPropertiesLoader().getTokenizedProperty("datetime.extra.masks", "|");
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
