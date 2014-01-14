package org.rometools.feed.module.cc.types;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.assertFalse;

/**
 * @author Matthew Buckett
 */
public class LicenseTest {

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
        Thread addNew = new Thread(){
            @Override
            public void run() {
                try {
                    while(run.get()) {
                        License license = License.findByValue("http://creativecommons.org/licenses/"+
                                type.incrementAndGet()+ "/1");
                        if (license == null) {
                            hadProblem.set(true);
                        }
                    }
                } catch (Exception e) {
                    hadException.set(true);
                }
            }
        };

        // This thread attempts to get ones we know have already been put in.
        Thread getExisting = new Thread() {
            @Override
            public void run() {
                Random rnd = new Random();
                try {
                    while(run.get()) {
                        License license = License.findByValue("http://creativecommons.org/licenses/"+
                                rnd.nextInt(type.intValue())+"/1");
                        if (license == null) {
                            hadProblem.set(true);
                        }
                    }
                } catch (Exception e) {
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
