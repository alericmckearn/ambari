/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ambari.server.controller.ganglia;

import org.apache.ambari.server.controller.internal.PropertyInfo;
import org.apache.ambari.server.controller.spi.Resource;
import org.apache.ambari.server.controller.utilities.StreamProvider;

import java.util.Map;

/**
 * Ganglia property provider implementation for component resources.
 */
public class GangliaComponentPropertyProvider extends GangliaPropertyProvider {


  // ----- Constructors ------------------------------------------------------

  public GangliaComponentPropertyProvider(Map<String, Map<String, PropertyInfo>> componentMetrics,
                                          StreamProvider streamProvider,
                                          GangliaHostProvider hostProvider,
                                          String clusterNamePropertyId,
                                          String componentNamePropertyId) {

    super(componentMetrics, streamProvider, hostProvider,
        clusterNamePropertyId, null, componentNamePropertyId);
  }


  // ----- GangliaPropertyProvider -------------------------------------------

  @Override
  protected String getHostName(Resource resource) {
    return "__SummaryInfo__";
  }

  @Override
  protected String getComponentName(Resource resource) {
    return (String) resource.getPropertyValue(getComponentNamePropertyId());
  }

  @Override
  protected String getGangliaClusterName(Resource resource, String clusterName) {
    return GANGLIA_CLUSTER_NAMES.get(getComponentName(resource));
  }
}
