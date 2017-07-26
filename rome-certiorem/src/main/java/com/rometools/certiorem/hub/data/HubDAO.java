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

package com.rometools.certiorem.hub.data;

import java.util.List;

/**
 *
 * @author robert.cooper
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
public interface HubDAO {

    public List<? extends Subscriber> subscribersForTopic(String topic);

    public Subscriber addSubscriber(Subscriber subscriber);

    public Subscriber findSubscriber(String topic, String callbackUrl);

    public void removeSubscriber(String topic, String callback);

    public void handleSummary(String topic, SubscriptionSummary summary);

    public List<? extends SubscriptionSummary> summariesForTopic(String topic);

}
