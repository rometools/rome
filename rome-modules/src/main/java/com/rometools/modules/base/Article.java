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
package com.rometools.modules.base;

import java.util.Date;

/**
 * This is an interface for the GoogleBase plug in that exposes methods used for Article or News
 * entry types.
 */
public interface Article extends GlobalInterface {
    /**
     * Array of Author Names. Limit 10.
     *
     * @param authors Array of Author Names. Limit 10.
     */
    public void setAuthors(String[] authors);

    /**
     * Array of Author Names. Limit 10.
     *
     * @return Array of author names.
     */
    public String[] getAuthors();

    /**
     * Source for this article.
     *
     * @param newsSource Source for this article
     */
    public void setNewsSource(String newsSource);

    /**
     * Source for this article.
     *
     * @return source for this article.
     */
    public String getNewsSource();

    /**
     * Number of pages in the article.
     *
     * @param pages Number of pages in the article
     */
    public void setPages(Integer pages);

    /**
     * Number of pages in the article.
     *
     * @return Number of pages in the article
     */
    public Integer getPages();

    /**
     * Date article was published.
     *
     * @param publishDate Date article was published
     */
    public void setPublishDate(Date publishDate);

    /**
     * Date article was published.
     *
     * @return Date article was published
     */
    public Date getPublishDate();
}
