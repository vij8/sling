/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.sling.contextaware.config.impl;

import static org.junit.Assert.assertEquals;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.contextaware.config.ConfigurationBuilder;
import org.apache.sling.contextaware.config.example.SimpleConfig;
import org.apache.sling.contextaware.config.resource.impl.ConfigurationResourceResolverImpl;
import org.apache.sling.testing.mock.sling.junit.SlingContext;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;

public class ConfigurationBuilderAdapterFactoryTest {
    
    @Rule
    public SlingContext context = new SlingContext();
    
    private Resource site1Page1;
    
    @Before
    public void setUp() {
        context.registerInjectActivateService(new ConfigurationResourceResolverImpl());
        context.registerInjectActivateService(new ConfigurationResolverImpl());
        context.registerInjectActivateService(new ConfigurationBuilderAdapterFactory());

        // config resource
        context.create().resource("/config/content/site1/sling:configs/org.apache.sling.contextaware.config.example.SimpleConfig", ImmutableMap.<String, Object>builder()
                .put("stringParam", "configValue1")
                .put("intParam", 111)
                .put("boolParam", true)
                .build());

        // content resources
        context.create().resource("/content/site1", ImmutableMap.<String, Object>builder()
                .put("sling:config", "/config/content/site1")
                .build());
        site1Page1 = context.create().resource("/content/site1/page1");
    }

    @Test
    public void testGetAdapter() {
        ConfigurationBuilder cfgBuilder = site1Page1.adaptTo(ConfigurationBuilder.class);
        SimpleConfig cfg = cfgBuilder.as(SimpleConfig.class);

        assertEquals("configValue1", cfg.stringParam());
        assertEquals(111, cfg.intParam());
        assertEquals(true, cfg.boolParam());
    }

}
