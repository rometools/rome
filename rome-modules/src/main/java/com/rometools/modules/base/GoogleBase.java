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

import com.rometools.rome.feed.module.Module;

/**
 * This is the root interface for the Google Base module.
 *
 * <p>
 * It supports all the individual data "types". If you want to work with, for instance, just the
 * Article or Job datatype, you can cast it to the respective interface and see only methods related
 * to a particular entry type.
 * </p>
 */
public interface GoogleBase extends Module, Article, Course, Event, Job, Person, Product, Review, ScholarlyArticle, Service, Travel, Unknown, Vehicle, Wanted,
        Housing {
    /** This is the URI for the Google Base Schema. */
    public static final String URI = "http://base.google.com/ns/1.0";
}
