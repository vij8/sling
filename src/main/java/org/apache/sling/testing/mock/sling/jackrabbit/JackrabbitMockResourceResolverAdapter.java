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
package org.apache.sling.testing.mock.sling.jackrabbit;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.testing.jcr.RepositoryProvider;
import org.apache.sling.jcr.api.SlingRepository;
import org.apache.sling.testing.mock.sling.context.NodeTypeDefinitionScanner;
import org.apache.sling.testing.mock.sling.spi.ResourceResolverTypeAdapter;

/**
 * Resource resolver type adapter for Jackrabbit repository.
 */
public class JackrabbitMockResourceResolverAdapter implements ResourceResolverTypeAdapter {

    @Override
    public ResourceResolverFactory newResourceResolverFactory() {
        return null;
    }

    @Override
    public SlingRepository newSlingRepository() {
        try {
            SlingRepository slingRepository =  RepositoryProvider.instance().getRepository();
            registerJcrNodeTypes(slingRepository);
            return slingRepository;
        } catch (RepositoryException ex) {
            throw new RuntimeException("Unable to get jackrabbit SlingRepository instance.", ex);
        }
    }

    /**
     * Registers all JCR node types found in classpath.
     * @param slingRepository Sling repository
     */
    @SuppressWarnings("deprecation")
    private static void registerJcrNodeTypes(SlingRepository slingRepository) {
      Session session = null;
      try {
          session =  slingRepository.loginAdministrative(null);
          NodeTypeDefinitionScanner.get().register(session);
      }
      catch (RepositoryException ex) {
          throw new RuntimeException("Error registering JCR nodetypes: " + ex.getMessage(), ex);
      }
      finally {
          if (session != null) {
              session.logout();
          }
      }
    }
    
}