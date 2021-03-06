/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.ingest.common;

import org.elasticsearch.ElasticsearchParseException;
import org.elasticsearch.test.ESTestCase;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;

public class UppercaseProcessorFactoryTests extends ESTestCase {

    public void testCreate() throws Exception {
        UppercaseProcessor.Factory factory = new UppercaseProcessor.Factory();
        Map<String, Object> config = new HashMap<>();
        config.put("field", "field1");
        String processorTag = randomAsciiOfLength(10);
        UppercaseProcessor uppercaseProcessor = (UppercaseProcessor)factory.create(processorTag, config);
        assertThat(uppercaseProcessor.getTag(), equalTo(processorTag));
        assertThat(uppercaseProcessor.getField(), equalTo("field1"));
    }

    public void testCreateMissingField() throws Exception {
        UppercaseProcessor.Factory factory = new UppercaseProcessor.Factory();
        Map<String, Object> config = new HashMap<>();
        try {
            factory.create(null, config);
            fail("factory create should have failed");
        } catch(ElasticsearchParseException e) {
            assertThat(e.getMessage(), equalTo("[field] required property is missing"));
        }
    }
}
