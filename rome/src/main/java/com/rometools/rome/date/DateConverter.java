package com.rometools.rome.date;

import java.util.Date;
import java.util.Locale;

public interface DateConverter {
    Date convert(String sDate, Locale locale);
}
