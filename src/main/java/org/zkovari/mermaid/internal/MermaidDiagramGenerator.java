/*******************************************************************************
 * Copyright 2020 Zsolt Kovari
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package org.zkovari.mermaid.internal;

import java.util.List;

import org.zkovari.mermaid.internal.domain.DependencyNode;

public class MermaidDiagramGenerator {

    private static final String NEW_LINE = System.lineSeparator();

    public String generate(List<DependencyNode> rootNodes) {
        StringBuilder stringBuilder = new StringBuilder("```mermaid").append(NEW_LINE).append("graph TD;")
                .append(NEW_LINE);
        for (DependencyNode rootNode : rootNodes) {
            appendEdges(rootNode, stringBuilder);
        }
        stringBuilder.append("```");
        return stringBuilder.toString();
    }

    private void appendEdges(DependencyNode parentNode, StringBuilder stringBuilder) {
        for (DependencyNode childNode : parentNode.getOutgoing()) {
            stringBuilder.append(toMermaidEdge(parentNode, childNode));
            stringBuilder.append(NEW_LINE);
            appendEdges(childNode, stringBuilder);
        }
    }

    private String toMermaidEdge(DependencyNode rootNode, DependencyNode dependencyNode) {
        return "  " + rootNode.getGavc().getArtifactId() + "-->" + dependencyNode.getGavc().getArtifactId() + ";";
    }

}
