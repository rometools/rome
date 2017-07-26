/*
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
package com.rometools.modules.cc.types;

import static org.junit.Assert.assertFalse;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LicenseTest {

    private static final Logger LOG = LoggerFactory.getLogger(LicenseTest.class);

    @Before
    public void setUp() {
        // As the looked up licenses are held statically we need to clear this between tests.
        License.clear();
    }

    @Test(timeout = 1000)
    public void testConcurrent() throws InterruptedException {
        final AtomicBoolean run = new AtomicBoolean(true);
        final AtomicLong type = new AtomicLong(0);
        // Tracking any problems.
        final AtomicBoolean hadProblem = new AtomicBoolean(false);
        final AtomicBoolean hadException = new AtomicBoolean(false);

        // This thread keeps on adding new licenses (not very realistic but shows the bug)
        final Thread addNew = new Thread() {
            @Override
            public void run() {
                try {
                    while (run.get()) {
                        final License license = License.findByValue("http://creativecommons.org/licenses/" + type.incrementAndGet() + "/1");
                        if (license == null) {
                            hadProblem.set(true);
                        }
                    }
                } catch (final Exception e) {
                    LOG.error("Exception in add-new thread", e);
                    hadException.set(true);
                }
            }
        };

        // This thread attempts to get ones we know have already been put in.
        final Thread getExisting = new Thread() {
            @Override
            public void run() {
                final Random rnd = new Random();
                try {
                    while (run.get()) {
                        if (type.intValue() == 0) {
                            continue;
                        }

                        final License license = License.findByValue("http://creativecommons.org/licenses/" + rnd.nextInt(type.intValue()) + "/1");
                        if (license == null) {
                            hadProblem.set(true);
                        }
                    }
                } catch (final Exception e) {
                    LOG.error("Exception in get-existing thread", e);
                    hadException.set(true);
                }
            }
        };

        addNew.start();
        getExisting.start();
        // Let them do some stuff.
        Thread.sleep(400);
        // Get them to both stop.
        run.set(false);
        // Allow them a little time to stop.
        addNew.join(50);
        getExisting.join(50);
        // Check we didn't have any problems and they have both stopped.
        assertFalse(hadProblem.get());
        assertFalse(hadException.get());
        assertFalse(addNew.isAlive());
        assertFalse(getExisting.isAlive());
    }
}
