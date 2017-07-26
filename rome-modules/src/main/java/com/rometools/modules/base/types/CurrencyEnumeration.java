/*
 * Copyright 2005 Robert Cooper, Temple of the Screaming Penguin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rometools.modules.base.types;

import java.util.HashMap;

/**
 * An Enumeration of valid currency types.
 */
public class CurrencyEnumeration {

    private static final HashMap<String, CurrencyEnumeration> lookup = new HashMap<String, CurrencyEnumeration>();

    // <xs:restriction base="xs:string">
    // <xs:enumeration value="AED"/>
    public static final CurrencyEnumeration AED = new CurrencyEnumeration("AED");

    // <xs:enumeration value="AFA"/>
    public static final CurrencyEnumeration AFA = new CurrencyEnumeration("AFA");

    // <xs:enumeration value="ALL"/>
    public static final CurrencyEnumeration ALL = new CurrencyEnumeration("ALL");

    // <xs:enumeration value="AMD"/>
    public static final CurrencyEnumeration AMD = new CurrencyEnumeration("AMD");

    // <xs:enumeration value="ANG"/>
    public static final CurrencyEnumeration ANG = new CurrencyEnumeration("ANG");

    // <xs:enumeration value="AOA"/>
    public static final CurrencyEnumeration AOA = new CurrencyEnumeration("AOA");

    // <xs:enumeration value="ARS"/>
    public static final CurrencyEnumeration ARS = new CurrencyEnumeration("ARS");

    // <xs:enumeration value="AUD"/>
    public static final CurrencyEnumeration AUD = new CurrencyEnumeration("AUD");

    // <xs:enumeration value="AWG"/>
    public static final CurrencyEnumeration AWG = new CurrencyEnumeration("AWG");

    // <xs:enumeration value="AZM"/>
    public static final CurrencyEnumeration AZM = new CurrencyEnumeration("AZM");

    // <xs:enumeration value="BAM"/>
    public static final CurrencyEnumeration BAM = new CurrencyEnumeration("BAM");

    // <xs:enumeration value="BBD"/>
    public static final CurrencyEnumeration BBD = new CurrencyEnumeration("BBD");

    // <xs:enumeration value="BDT"/>
    public static final CurrencyEnumeration BDT = new CurrencyEnumeration("BDT");

    // <xs:enumeration value="BGN"/>
    public static final CurrencyEnumeration BGN = new CurrencyEnumeration("BGN");

    // <xs:enumeration value="BHD"/>
    public static final CurrencyEnumeration BHD = new CurrencyEnumeration("BHD");

    // <xs:enumeration value="BIF"/>
    public static final CurrencyEnumeration BIF = new CurrencyEnumeration("BIF");

    // <xs:enumeration value="BMD"/>
    public static final CurrencyEnumeration BMD = new CurrencyEnumeration("BMD");

    // <xs:enumeration value="BND"/>
    public static final CurrencyEnumeration BND = new CurrencyEnumeration("BND");

    // <xs:enumeration value="BOB"/>
    public static final CurrencyEnumeration BOB = new CurrencyEnumeration("BOB");

    // <xs:enumeration value="BRL"/>
    public static final CurrencyEnumeration BRL = new CurrencyEnumeration("BRL");

    // <xs:enumeration value="BSD"/>
    public static final CurrencyEnumeration BSD = new CurrencyEnumeration("BSD");

    // <xs:enumeration value="BTN"/>
    public static final CurrencyEnumeration BTN = new CurrencyEnumeration("BTN");

    // <xs:enumeration value="BWP"/>
    public static final CurrencyEnumeration BWP = new CurrencyEnumeration("BWP");

    // <xs:enumeration value="BYR"/>
    public static final CurrencyEnumeration BYR = new CurrencyEnumeration("BYR");

    // <xs:enumeration value="BZD"/>
    public static final CurrencyEnumeration BZD = new CurrencyEnumeration("BZD");

    // <xs:enumeration value="CAD"/>
    public static final CurrencyEnumeration CAD = new CurrencyEnumeration("CAD");

    // <xs:enumeration value="CDF"/>
    public static final CurrencyEnumeration CDF = new CurrencyEnumeration("CDF");

    // <xs:enumeration value="CHF"/>
    public static final CurrencyEnumeration CHF = new CurrencyEnumeration("CHF");

    // <xs:enumeration value="CLP"/>
    public static final CurrencyEnumeration CLP = new CurrencyEnumeration("CLP");

    // <xs:enumeration value="CNY"/>
    public static final CurrencyEnumeration CNY = new CurrencyEnumeration("CNY");

    // <xs:enumeration value="CRC"/>
    public static final CurrencyEnumeration CRC = new CurrencyEnumeration("CRC");

    // <xs:enumeration value="CSD"/>
    public static final CurrencyEnumeration CSD = new CurrencyEnumeration("CSD");

    // <xs:enumeration value="CUP"/>
    public static final CurrencyEnumeration CUP = new CurrencyEnumeration("CUP");

    // <xs:enumeration value="CVE"/>
    public static final CurrencyEnumeration CVE = new CurrencyEnumeration("CVE");

    // <xs:enumeration value="CYP"/>
    public static final CurrencyEnumeration CYP = new CurrencyEnumeration("CYP");

    // <xs:enumeration value="CZK"/>
    public static final CurrencyEnumeration CZK = new CurrencyEnumeration("CZK");

    // <xs:enumeration value="DJF"/>
    public static final CurrencyEnumeration DJF = new CurrencyEnumeration("DJF");

    // <xs:enumeration value="DKK"/>
    public static final CurrencyEnumeration DKK = new CurrencyEnumeration("DKK");

    // <xs:enumeration value="DOP"/>
    public static final CurrencyEnumeration DOP = new CurrencyEnumeration("DOP");

    // <xs:enumeration value="DZD"/>
    public static final CurrencyEnumeration DZD = new CurrencyEnumeration("DZD");

    // <xs:enumeration value="EEK"/>
    public static final CurrencyEnumeration EEK = new CurrencyEnumeration("EEK");

    // <xs:enumeration value="EGP"/>
    public static final CurrencyEnumeration EGP = new CurrencyEnumeration("EGP");

    // <xs:enumeration value="ERN"/>
    public static final CurrencyEnumeration ERN = new CurrencyEnumeration("ERN");

    // <xs:enumeration value="ETB"/>
    public static final CurrencyEnumeration ETB = new CurrencyEnumeration("ETB");

    // <xs:enumeration value="EUR"/>
    public static final CurrencyEnumeration EUR = new CurrencyEnumeration("EUR");

    // <xs:enumeration value="FJD"/>
    public static final CurrencyEnumeration FJD = new CurrencyEnumeration("FJD");

    // <xs:enumeration value="FKP"/>
    public static final CurrencyEnumeration FKP = new CurrencyEnumeration("FKP");

    // <xs:enumeration value="GBP"/>
    public static final CurrencyEnumeration GBP = new CurrencyEnumeration("GBP");

    // <xs:enumeration value="GEL"/>
    public static final CurrencyEnumeration GEL = new CurrencyEnumeration("GEL");

    // <xs:enumeration value="GGP"/>
    public static final CurrencyEnumeration GGP = new CurrencyEnumeration("GGP");

    // <xs:enumeration value="GHC"/>
    public static final CurrencyEnumeration GHC = new CurrencyEnumeration("GHC");

    // <xs:enumeration value="GIP"/>
    public static final CurrencyEnumeration GIP = new CurrencyEnumeration("GIP");

    // <xs:enumeration value="GMD"/>
    public static final CurrencyEnumeration GMD = new CurrencyEnumeration("GMD");

    // <xs:enumeration value="GNF"/>
    public static final CurrencyEnumeration GNF = new CurrencyEnumeration("GNF");

    // <xs:enumeration value="GTQ"/>
    public static final CurrencyEnumeration GTQ = new CurrencyEnumeration("GTQ");

    // <xs:enumeration value="GYD"/>
    public static final CurrencyEnumeration GYD = new CurrencyEnumeration("GYD");

    // <xs:enumeration value="HKD"/>
    public static final CurrencyEnumeration HKD = new CurrencyEnumeration("HKD");

    // <xs:enumeration value="HNL"/>
    public static final CurrencyEnumeration HNL = new CurrencyEnumeration("HNL");

    // <xs:enumeration value="HRK"/>
    public static final CurrencyEnumeration HRK = new CurrencyEnumeration("HRK");

    // <xs:enumeration value="HTG"/>
    public static final CurrencyEnumeration HTG = new CurrencyEnumeration("HTG");

    // <xs:enumeration value="HUF"/>
    public static final CurrencyEnumeration HUF = new CurrencyEnumeration("HUF");

    // <xs:enumeration value="IDR"/>
    public static final CurrencyEnumeration IDR = new CurrencyEnumeration("IDR");

    // <xs:enumeration value="ILS"/>
    public static final CurrencyEnumeration ILS = new CurrencyEnumeration("ILS");

    // <xs:enumeration value="IMP"/>
    public static final CurrencyEnumeration IMP = new CurrencyEnumeration("IMP");

    // <xs:enumeration value="INR"/>
    public static final CurrencyEnumeration INR = new CurrencyEnumeration("INR");

    // <xs:enumeration value="IQD"/>
    public static final CurrencyEnumeration IQD = new CurrencyEnumeration("IQD");

    // <xs:enumeration value="IRR"/>
    public static final CurrencyEnumeration IRR = new CurrencyEnumeration("IRR");

    // <xs:enumeration value="ISK"/>
    public static final CurrencyEnumeration ISK = new CurrencyEnumeration("ISK");

    // <xs:enumeration value="JEP"/>
    public static final CurrencyEnumeration JEP = new CurrencyEnumeration("JEP");

    // <xs:enumeration value="JMD"/>
    public static final CurrencyEnumeration JMD = new CurrencyEnumeration("JMD");

    // <xs:enumeration value="JOD"/>
    public static final CurrencyEnumeration JOD = new CurrencyEnumeration("JOD");

    // <xs:enumeration value="JPY"/>
    public static final CurrencyEnumeration JPY = new CurrencyEnumeration("JPY");

    // <xs:enumeration value="KES"/>
    public static final CurrencyEnumeration KES = new CurrencyEnumeration("KES");

    // <xs:enumeration value="KGS"/>
    public static final CurrencyEnumeration KGS = new CurrencyEnumeration("KGS");

    // <xs:enumeration value="KHR"/>
    public static final CurrencyEnumeration KHR = new CurrencyEnumeration("KHR");

    // <xs:enumeration value="KMF"/>
    public static final CurrencyEnumeration KMF = new CurrencyEnumeration("KMF");

    // <xs:enumeration value="KPW"/>
    public static final CurrencyEnumeration KPW = new CurrencyEnumeration("KPW");

    // <xs:enumeration value="KRW"/>
    public static final CurrencyEnumeration KRW = new CurrencyEnumeration("KRW");

    // <xs:enumeration value="KWD"/>
    public static final CurrencyEnumeration KWD = new CurrencyEnumeration("KWD");

    // <xs:enumeration value="KYD"/>
    public static final CurrencyEnumeration KYD = new CurrencyEnumeration("KYD");

    // <xs:enumeration value="KZT"/>
    public static final CurrencyEnumeration KZT = new CurrencyEnumeration("KZT");

    // <xs:enumeration value="LAK"/>
    public static final CurrencyEnumeration LAK = new CurrencyEnumeration("LAK");

    // <xs:enumeration value="LBP"/>
    public static final CurrencyEnumeration LBP = new CurrencyEnumeration("LBP");

    // <xs:enumeration value="LKR"/>
    public static final CurrencyEnumeration LKR = new CurrencyEnumeration("LKR");

    // <xs:enumeration value="LRD"/>
    public static final CurrencyEnumeration LRD = new CurrencyEnumeration("LRD");

    // <xs:enumeration value="LSL"/>
    public static final CurrencyEnumeration LSL = new CurrencyEnumeration("LSL");

    // <xs:enumeration value="LTL"/>
    public static final CurrencyEnumeration LTL = new CurrencyEnumeration("LTL");

    // <xs:enumeration value="LVL"/>
    public static final CurrencyEnumeration LVL = new CurrencyEnumeration("LVL");

    // <xs:enumeration value="LYD"/>
    public static final CurrencyEnumeration LYD = new CurrencyEnumeration("LYD");

    // <xs:enumeration value="MAD"/>
    public static final CurrencyEnumeration MAD = new CurrencyEnumeration("MAD");

    // <xs:enumeration value="MDL"/>
    public static final CurrencyEnumeration MDL = new CurrencyEnumeration("MDL");

    // <xs:enumeration value="MGA"/>
    public static final CurrencyEnumeration MGA = new CurrencyEnumeration("MGA");

    // <xs:enumeration value="MKD"/>
    public static final CurrencyEnumeration MKD = new CurrencyEnumeration("MKD");

    // <xs:enumeration value="MMK"/>
    public static final CurrencyEnumeration MMK = new CurrencyEnumeration("MMK");

    // <xs:enumeration value="MNT"/>
    public static final CurrencyEnumeration MNT = new CurrencyEnumeration("MNT");

    // <xs:enumeration value="MOP"/>
    public static final CurrencyEnumeration MOP = new CurrencyEnumeration("MOP");

    // <xs:enumeration value="MRO"/>
    public static final CurrencyEnumeration MRO = new CurrencyEnumeration("MRO");

    // <xs:enumeration value="MTL"/>
    public static final CurrencyEnumeration MTL = new CurrencyEnumeration("MTL");

    // <xs:enumeration value="MUR"/>
    public static final CurrencyEnumeration MUR = new CurrencyEnumeration("MUR");

    // <xs:enumeration value="MVR"/>
    public static final CurrencyEnumeration MVR = new CurrencyEnumeration("MVR");

    // <xs:enumeration value="MWK"/>
    public static final CurrencyEnumeration MWK = new CurrencyEnumeration("MWK");

    // <xs:enumeration value="MXN"/>
    public static final CurrencyEnumeration MXN = new CurrencyEnumeration("MXN");

    // <xs:enumeration value="MYR"/>
    public static final CurrencyEnumeration MYR = new CurrencyEnumeration("MYR");

    // <xs:enumeration value="MZM"/>
    public static final CurrencyEnumeration MZM = new CurrencyEnumeration("MZM");

    // <xs:enumeration value="NAD"/>
    public static final CurrencyEnumeration NAD = new CurrencyEnumeration("NAD");

    // <xs:enumeration value="NGN"/>
    public static final CurrencyEnumeration NGN = new CurrencyEnumeration("NGN");

    // <xs:enumeration value="NIO"/>
    public static final CurrencyEnumeration NIO = new CurrencyEnumeration("NIO");

    // <xs:enumeration value="NOK"/>
    public static final CurrencyEnumeration NOK = new CurrencyEnumeration("NOK");

    // <xs:enumeration value="NPR"/>
    public static final CurrencyEnumeration NPR = new CurrencyEnumeration("NPR");

    // <xs:enumeration value="NZD"/>
    public static final CurrencyEnumeration NZD = new CurrencyEnumeration("NZD");

    // <xs:enumeration value="OMR"/>
    public static final CurrencyEnumeration OMR = new CurrencyEnumeration("OMR");

    // <xs:enumeration value="PAB"/>
    public static final CurrencyEnumeration PAB = new CurrencyEnumeration("PAB");

    // <xs:enumeration value="PEN"/>
    public static final CurrencyEnumeration PEN = new CurrencyEnumeration("PEN");

    // <xs:enumeration value="PGK"/>
    public static final CurrencyEnumeration PGK = new CurrencyEnumeration("PGK");

    // <xs:enumeration value="PHP"/>
    public static final CurrencyEnumeration PHP = new CurrencyEnumeration("PHP");

    // <xs:enumeration value="PKR"/>
    public static final CurrencyEnumeration PKR = new CurrencyEnumeration("PKR");

    // <xs:enumeration value="PLN"/>
    public static final CurrencyEnumeration PLN = new CurrencyEnumeration("PLN");

    // <xs:enumeration value="PYG"/>
    public static final CurrencyEnumeration PYG = new CurrencyEnumeration("PYG");

    // <xs:enumeration value="QAR"/>
    public static final CurrencyEnumeration QAR = new CurrencyEnumeration("QAR");

    // <xs:enumeration value="RON"/>
    public static final CurrencyEnumeration RON = new CurrencyEnumeration("RON");

    // <xs:enumeration value="RUB"/>
    public static final CurrencyEnumeration RUB = new CurrencyEnumeration("RUB");

    // <xs:enumeration value="RWF"/>
    public static final CurrencyEnumeration RWF = new CurrencyEnumeration("MOP");

    // <xs:enumeration value="SAR"/>
    public static final CurrencyEnumeration SAR = new CurrencyEnumeration("SAR");

    // <xs:enumeration value="SBD"/>
    public static final CurrencyEnumeration SBD = new CurrencyEnumeration("SBD");

    // <xs:enumeration value="SCR"/>
    public static final CurrencyEnumeration SCR = new CurrencyEnumeration("SCR");

    // <xs:enumeration value="SDD"/>
    public static final CurrencyEnumeration SDD = new CurrencyEnumeration("SDD");

    // <xs:enumeration value="SEK"/>
    public static final CurrencyEnumeration SEK = new CurrencyEnumeration("SEK");

    // <xs:enumeration value="SGD"/>
    public static final CurrencyEnumeration SGD = new CurrencyEnumeration("SGD");

    // <xs:enumeration value="SHP"/>
    public static final CurrencyEnumeration SHP = new CurrencyEnumeration("SHP");

    // <xs:enumeration value="SIT"/>
    public static final CurrencyEnumeration SIT = new CurrencyEnumeration("SIT");

    // <xs:enumeration value="SKK"/>
    public static final CurrencyEnumeration SKK = new CurrencyEnumeration("SKK");

    // <xs:enumeration value="SLL"/>
    public static final CurrencyEnumeration SLL = new CurrencyEnumeration("SLL");

    // <xs:enumeration value="SOS"/>
    public static final CurrencyEnumeration SOS = new CurrencyEnumeration("SOS");

    // <xs:enumeration value="SPL"/>
    public static final CurrencyEnumeration SPL = new CurrencyEnumeration("SPL");

    // <xs:enumeration value="SRD"/>
    public static final CurrencyEnumeration SRD = new CurrencyEnumeration("SRD");

    // <xs:enumeration value="STD"/>
    public static final CurrencyEnumeration STD = new CurrencyEnumeration("STD");

    // <xs:enumeration value="SVC"/>
    public static final CurrencyEnumeration SVC = new CurrencyEnumeration("SVC");

    // <xs:enumeration value="SYP"/>
    public static final CurrencyEnumeration SYP = new CurrencyEnumeration("SYP");

    // <xs:enumeration value="SZL"/>
    public static final CurrencyEnumeration SZL = new CurrencyEnumeration("SZL");

    // <xs:enumeration value="THB"/>
    public static final CurrencyEnumeration THB = new CurrencyEnumeration("THB");

    // <xs:enumeration value="TJS"/>
    public static final CurrencyEnumeration TJS = new CurrencyEnumeration("TJS");

    // <xs:enumeration value="TMM"/>
    public static final CurrencyEnumeration TMM = new CurrencyEnumeration("TMM");

    // <xs:enumeration value="TND"/>
    public static final CurrencyEnumeration TND = new CurrencyEnumeration("TND");

    // <xs:enumeration value="TOP"/>
    public static final CurrencyEnumeration TOP = new CurrencyEnumeration("TOP");

    // <xs:enumeration value="TRL"/>
    public static final CurrencyEnumeration TRL = new CurrencyEnumeration("TRL");

    // <xs:enumeration value="TRY"/>
    public static final CurrencyEnumeration TRY = new CurrencyEnumeration("TRY");

    // <xs:enumeration value="TTD"/>
    public static final CurrencyEnumeration TTD = new CurrencyEnumeration("TTD");

    // <xs:enumeration value="TVD"/>
    public static final CurrencyEnumeration TVD = new CurrencyEnumeration("TVD");

    // <xs:enumeration value="TWD"/>
    public static final CurrencyEnumeration TWD = new CurrencyEnumeration("TWD");

    // <xs:enumeration value="TZS"/>
    public static final CurrencyEnumeration TZS = new CurrencyEnumeration("TZS");

    // <xs:enumeration value="UAH"/>
    public static final CurrencyEnumeration UAH = new CurrencyEnumeration("UAH");

    // <xs:enumeration value="UGX"/>
    public static final CurrencyEnumeration UGX = new CurrencyEnumeration("UGX");

    // <xs:enumeration value="USD"/>
    public static final CurrencyEnumeration USD = new CurrencyEnumeration("USD");

    // <xs:enumeration value="UYU"/>
    public static final CurrencyEnumeration UYU = new CurrencyEnumeration("UYU");

    // <xs:enumeration value="UZS"/>
    public static final CurrencyEnumeration UZS = new CurrencyEnumeration("UZS");

    // <xs:enumeration value="VEB"/>
    public static final CurrencyEnumeration VEB = new CurrencyEnumeration("VEB");

    // <xs:enumeration value="VND"/>
    public static final CurrencyEnumeration VND = new CurrencyEnumeration("VND");

    // <xs:enumeration value="VUV"/>
    public static final CurrencyEnumeration VUV = new CurrencyEnumeration("VUV");

    // <xs:enumeration value="WST"/>
    public static final CurrencyEnumeration WST = new CurrencyEnumeration("WST");

    // <xs:enumeration value="XAF"/>
    public static final CurrencyEnumeration XAF = new CurrencyEnumeration("XAF");

    // <xs:enumeration value="XAG"/>
    public static final CurrencyEnumeration XAG = new CurrencyEnumeration("XAG");

    // <xs:enumeration value="XAU"/>
    public static final CurrencyEnumeration XAU = new CurrencyEnumeration("XAU");

    // <xs:enumeration value="XCD"/>
    public static final CurrencyEnumeration XCD = new CurrencyEnumeration("XCD");

    // <xs:enumeration value="XDR"/>
    public static final CurrencyEnumeration XDR = new CurrencyEnumeration("XDR");

    // <xs:enumeration value="XOF"/>
    public static final CurrencyEnumeration XOF = new CurrencyEnumeration("XOF");

    // <xs:enumeration value="XPD"/>
    public static final CurrencyEnumeration XPD = new CurrencyEnumeration("XPD");

    // <xs:enumeration value="XPF"/>
    public static final CurrencyEnumeration XPF = new CurrencyEnumeration("XPF");

    // <xs:enumeration value="XPT"/>
    public static final CurrencyEnumeration XPT = new CurrencyEnumeration("XPT");

    // <xs:enumeration value="YER"/>
    public static final CurrencyEnumeration YER = new CurrencyEnumeration("YER");

    // <xs:enumeration value="ZAR"/>
    public static final CurrencyEnumeration ZAR = new CurrencyEnumeration("ZAR");

    // <xs:enumeration value="ZMK"/>
    public static final CurrencyEnumeration ZMK = new CurrencyEnumeration("ZMK");

    // <xs:enumeration value="ZWD"/>
    public static final CurrencyEnumeration ZWD = new CurrencyEnumeration("ZWD");

    // </xs:restriction>
    private final String value;

    private CurrencyEnumeration(final String value) {
        this.value = value;
        lookup.put(value, this);
    }

    public String getValue() {
        return value;
    }

    @Override
    public Object clone() {
        return this;
    }

    public static CurrencyEnumeration findByValue(final String value) {
        return lookup.get(value.trim().toUpperCase());
    }

    @Override
    public String toString() {
        return value;
    }
}
