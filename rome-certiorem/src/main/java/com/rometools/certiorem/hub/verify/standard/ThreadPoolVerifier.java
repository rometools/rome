/**
 *
 *  Copyright (C) The ROME Team  2011
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.rometools.certiorem.hub.verify.standard;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.rometools.certiorem.hub.data.Subscriber;

/**
 * Uses a ThreadPoolExecutor to do async verifications.
 *
 * @author robert.cooper
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
public class ThreadPoolVerifier extends AbstractVerifier {
    protected final ThreadPoolExecutor exeuctor;

    protected ThreadPoolVerifier(final ThreadPoolExecutor executor) {
        exeuctor = executor;
    }

    public ThreadPoolVerifier() {
        this(2, 5, 5);
    }

    public ThreadPoolVerifier(final int startPoolSize, final int maxPoolSize, final int queueSize) {
        exeuctor = new ThreadPoolExecutor(startPoolSize, maxPoolSize, 300, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(queueSize));
    }

    @Override
    public void verifySubscribeAsyncronously(final Subscriber subscriber, final VerificationCallback callback) {
        exeuctor.execute(new Runnable() {
            @Override
            public void run() {
                callback.onVerify(verifySubcribeSyncronously(subscriber));
            }
        });
    }

    @Override
    public void verifyUnsubscribeAsyncronously(final Subscriber subscriber, final VerificationCallback callback) {
        exeuctor.execute(new Runnable() {
            @Override
            public void run() {
                callback.onVerify(verifyUnsubcribeSyncronously(subscriber));
            }
        });
    }
}
