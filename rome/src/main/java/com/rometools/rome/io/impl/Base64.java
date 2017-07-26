package com.rometools.rome.io.impl;

/*
 * Copyright 2004 Sun Microsystems, Inc.
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
 *
 */

/**
 * Encodes/decodes byte arrays and Strings into/from a base 64 String.
 */
public class Base64 {

    /**
     * Encodes a String into a base 64 String. The resulting encoding is chunked at 76 bytes.
     * <p>
     *
     * @param s String to encode.
     * @return encoded string.
     *
     */
    public static String encode(String s) {
        byte[] sBytes = s.getBytes();
        sBytes = encode(sBytes);
        s = new String(sBytes);
        return s;
    }

    /**
     * Decodes a base 64 String into a String.
     * <p>
     *
     * @param s String to decode.
     * @return encoded string.
     * @throws java.lang.IllegalArgumentException thrown if the given byte array was not valid
     *             com.rometools.rome.io.impl.Base64 encoding.
     *
     */
    public static String decode(String s) throws IllegalArgumentException {
        s = s.replaceAll("\n", "");
        s = s.replaceAll("\r", "");
        byte[] sBytes = s.getBytes();
        sBytes = decode(sBytes);
        s = new String(sBytes);
        return s;
    }

    private static final byte[] ALPHASET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".getBytes();

    private static final int I6O2 = 255 - 3;
    private static final int O6I2 = 3;
    private static final int I4O4 = 255 - 15;
    private static final int O4I4 = 15;
    private static final int I2O6 = 255 - 63;
    private static final int O2I6 = 63;

    /**
     * Encodes a byte array into a base 64 byte array.
     * <p>
     *
     * @param dData byte array to encode.
     * @return encoded byte array.
     *
     */
    public static byte[] encode(final byte[] dData) {
        if (dData == null) {
            throw new IllegalArgumentException("Cannot encode null");
        }
        final byte[] eData = new byte[(dData.length + 2) / 3 * 4];

        int eIndex = 0;
        for (int i = 0; i < dData.length; i += 3) {
            int d1;
            int d2 = 0;
            int d3 = 0;
            int e1;
            int e2;
            int e3;
            int e4;
            int pad = 0;

            d1 = dData[i];
            if (i + 1 < dData.length) {
                d2 = dData[i + 1];
                if (i + 2 < dData.length) {
                    d3 = dData[i + 2];
                } else {
                    pad = 1;
                }
            } else {
                pad = 2;
            }

            e1 = ALPHASET[(d1 & I6O2) >> 2];
            e2 = ALPHASET[(d1 & O6I2) << 4 | (d2 & I4O4) >> 4];
            e3 = ALPHASET[(d2 & O4I4) << 2 | (d3 & I2O6) >> 6];
            e4 = ALPHASET[d3 & O2I6];

            eData[eIndex++] = (byte) e1;
            eData[eIndex++] = (byte) e2;
            if (pad < 2) {
                eData[eIndex++] = (byte) e3;
            } else {
                eData[eIndex++] = (byte) '=';
            }
            if (pad < 1) {
                eData[eIndex++] = (byte) e4;
            } else {
                eData[eIndex++] = (byte) '=';
            }

        }
        return eData;
    }

    private final static int[] CODES = new int[256];

    static {
        for (int i = 0; i < CODES.length; i++) {
            CODES[i] = 64;
        }
        for (int i = 0; i < ALPHASET.length; i++) {
            CODES[ALPHASET[i]] = i;
        }
    }

    /**
     * Dencodes a com.rometools.rome.io.impl.Base64 byte array.
     * <p>
     *
     * @param eData byte array to decode.
     * @return decoded byte array.
     * @throws java.lang.IllegalArgumentException thrown if the given byte array was not valid
     *             com.rometools.rome.io.impl.Base64 encoding.
     *
     */
    public static byte[] decode(final byte[] eData) {
        if (eData == null) {
            throw new IllegalArgumentException("Cannot decode null");
        }
        final byte[] cleanEData = eData.clone();
        int cleanELength = 0;
        for (final byte element : eData) {
            if (element < 256 && CODES[element] < 64) {
                cleanEData[cleanELength++] = element;
            }
        }

        int dLength = cleanELength / 4 * 3;
        switch (cleanELength % 4) {
            case 3:
                dLength += 2;
                break;
            case 2:
                dLength++;
                break;
        }

        final byte[] dData = new byte[dLength];
        int dIndex = 0;
        for (int i = 0; i < eData.length; i += 4) {
            if (i + 3 > eData.length) {
                throw new IllegalArgumentException("byte array is not a valid com.rometools.rome.io.impl.Base64 encoding");
            }
            final int e1 = CODES[cleanEData[i]];
            final int e2 = CODES[cleanEData[i + 1]];
            final int e3 = CODES[cleanEData[i + 2]];
            final int e4 = CODES[cleanEData[i + 3]];
            dData[dIndex++] = (byte) (e1 << 2 | e2 >> 4);
            if (dIndex < dData.length) {
                dData[dIndex++] = (byte) (e2 << 4 | e3 >> 2);
            }
            if (dIndex < dData.length) {
                dData[dIndex++] = (byte) (e3 << 6 | e4);
            }
        }
        return dData;
    }

    public static void main(final String[] args) throws Exception {
        final String s = "\nPGRpdiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94aHRtbCI+V2UncmUgcHJvcG9zaW5nIDxhIGhy\n"
                + "ZWY9Imh0dHA6Ly93d3cuZ29vZ2xlLmNvbS9jb3Jwb3JhdGUvc29mdHdhcmVfcHJpbmNpcGxlcy5odG1sIj5z\n"
                + "b21lIGd1aWRlbGluZXMgPC9hPnRvIGhlbHAgY3VyYiB0aGUgcHJvYmxlbSBvZiBJbnRlcm5ldCBzb2Z0d2Fy\n"
                + "ZSB0aGF0IGluc3RhbGxzIGl0c2VsZiB3aXRob3V0IHRlbGxpbmcgeW91LCBvciBiZWhhdmVzIGJhZGx5IG9u\n"
                + "Y2UgaXQgZ2V0cyBvbiB5b3VyIGNvbXB1dGVyLiBXZSd2ZSBiZWVuIGhlYXJpbmcgYSBsb3Qgb2YgY29tcGxh\n"
                + "aW50cyBhYm91dCB0aGlzIGxhdGVseSBhbmQgaXQgc2VlbXMgdG8gYmUgZ2V0dGluZyB3b3JzZS4gV2UgdGhp\n"
                + "bmsgaXQncyBpbXBvcnRhbnQgdGhhdCB5b3UgcmV0YWluIGNvbnRyb2wgb2YgeW91ciBjb21wdXRlciBhbmQg\n"
                + "dGhhdCB0aGVyZSBiZSBzb21lIGNsZWFyIHN0YW5kYXJkcyBpbiBvdXIgaW5kdXN0cnkuIExldCB1cyBrbm93\n"
                + "IGlmIHlvdSB0aGluayB0aGVzZSBndWlkZWxpbmVzIGFyZSB1c2VmdWwgb3IgaWYgeW91IGhhdmUgc3VnZ2Vz\n"
                + "dGlvbnMgdG8gaW1wcm92ZSB0aGVtLgo8YnIgLz4KPGJyIC8+Sm9uYXRoYW4gUm9zZW5iZXJnCjxiciAvPgo8\n" + "L2Rpdj4K\n";

        System.out.println(decode(s));
    }
}
