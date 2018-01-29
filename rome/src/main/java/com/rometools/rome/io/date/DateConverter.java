package com.rometools.rome.io.date;

import java.util.Date;
import java.util.Locale;

public interface DateConverter {
    Date convert(String sDate, Locale locale);
}