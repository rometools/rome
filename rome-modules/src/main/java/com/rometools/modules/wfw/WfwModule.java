/*
 * Copyright 2018 Tom G., tomgapplicationsdevelopment@gmail.com
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

package com.rometools.modules.wfw;

import com.rometools.rome.feed.module.Module;

public interface WfwModule extends Module {
    public static final String URI = "http://wellformedweb.org/CommentAPI/";

    public String getCommentRss();

    public void setCommentRss(final String commentRss);
}
