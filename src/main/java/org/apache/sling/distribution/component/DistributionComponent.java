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
package org.apache.sling.distribution.component;

import aQute.bnd.annotation.ConsumerType;

/**
 * Marker interface for Sling Distribution components like: {@link org.apache.sling.distribution.agent.DistributionAgent}s,
 * {@link org.apache.sling.distribution.trigger.DistributionTrigger}s, {@link org.apache.sling.distribution.packaging.DistributionPackageExporter}s
 * and {@link org.apache.sling.distribution.packaging.DistributionPackageImporter}s.
 */
@ConsumerType
public interface DistributionComponent {

}
