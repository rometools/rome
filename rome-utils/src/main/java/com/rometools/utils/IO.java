/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rometools.utils;

import java.io.Closeable;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to handle I/O operations.
 */
public final class IO {

    private static final Logger LOG = LoggerFactory.getLogger(IO.class);

    private IO() {
    }

    /**
     * Closes a {@link Closeable} object without suppressing a possible {@link IOException}.
     *
     * @param closeable The {@link Closeable} to close
     * @throws IOException when the {@link Closeable} can't be closed
     */
    public static void close(final Closeable closeable) throws IOException {
        if (closeable != null) {
            closeable.close();
        }
    }

    /**
     * Closes a {@link Closeable} object and suppresses a possible {@link IOException}.
     *
     * @param closeable The {@link Closeable} to close
     */
    public static void closeQuietly(final Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (final IOException e) {
                LOG.warn("Unable to close resource", e);
            }
        }
    }

}
