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

import java.net.URL;

import com.rometools.modules.base.types.IntUnit;

/**
 * This interface contains all the other schema elements that the document doesn't associate with a
 * particular type.
 */
public interface Unknown extends GlobalInterface {
    public void setLicenses(String[] licenses);

    public String[] getLicenses();

    public void setOperatingSystems(String systems);

    public String getOperatingSystems();

    public void setProgrammingLanguages(String[] languages);

    public String[] getProgrammingLanguages();

    public void setRelatedLinks(URL[] links);

    public URL[] getRelatedLinks();

    public void setSquareFootages(IntUnit[] squareFootages);

    public IntUnit[] getSquareFootages();

    public void setSubjectAreas(String[] subjectAreas);

    public String[] getSubjectAreas();
}
