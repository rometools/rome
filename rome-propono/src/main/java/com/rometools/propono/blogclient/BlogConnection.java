/*
 *  Copyright 2007 Dave Johnson (Blogapps project)
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
package com.rometools.propono.blogclient;

import java.util.List;

/**
 * A BlogConnection is a single-user connection to a blog server where the user has access to
 * multiple blogs, which are each represented by a Blog interface.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public interface BlogConnection {

    /** Returns collection of blogs available from this connection */
    public abstract List<Blog> getBlogs();

    /** Get blog by token */
    public abstract Blog getBlog(String token);

    /** Set appkey (optional, needed by some blog servers) */
    public void setAppkey(String appkey);
}
