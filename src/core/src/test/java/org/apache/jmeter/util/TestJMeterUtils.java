/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.jmeter.util;

import static org.junit.Assert.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestJMeterUtils {

    @Test
    public void testGetResourceFileAsText() throws Exception{
        String sep = System.getProperty("line.separator");
        assertEquals("line one" + sep + "line two" + sep, JMeterUtils.getResourceFileAsText("resourcefile.txt"));
    }

    @Test
    public void testGetResourceFileAsTextWithMisingResource() throws Exception{
        assertEquals("", JMeterUtils.getResourceFileAsText("not_existant_resourcefile.txt"));
    }

    @Test
    public void testGesResStringDefaultWithNonExistantKey() throws Exception {
        assertEquals("[res_key=noValidKey]", JMeterUtils.getResString("noValidKey"));
    }

    @Test
    public void testGetArrayPropDefault() throws Exception {
        Path props = Files.createTempFile("testGetArrayPropDefault", ".properties");
        JMeterUtils.loadJMeterProperties(props.toString());
        JMeterUtils.getJMeterProperties().setProperty("testGetArrayPropDefaultEmpty", "    ");
        JMeterUtils.getJMeterProperties().setProperty("testGetArrayPropDefault", " Tolstoi  Dostoievski    Pouchkine       Gorki ");
        Assertions.assertArrayEquals(new String[]{"Tolstoi", "Dostoievski", "Pouchkine", "Gorki"},
                JMeterUtils.getArrayPropDefault("testGetArrayPropDefault", null));
        Assertions.assertArrayEquals(new String[]{"Gilels", "Richter"},
                JMeterUtils.getArrayPropDefault("testGetArrayPropDefaultMissing", new String[]{"Gilels", "Richter"}));
        Assertions.assertArrayEquals(null,
                JMeterUtils.getArrayPropDefault("testGetArrayPropDefaultEmpty", null));
    }
}
