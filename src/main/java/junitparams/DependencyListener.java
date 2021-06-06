/*
 * Copyright 2017 Pragmatists.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package junitparams;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import junitparams.internal.TestMethodTracking;
import junitparams.internal.dependant.graph.DependentGraph;

/**
 *
 * @author mohamed
 */
class DependencyListener extends RunListener {

    private final DependentGraph graph;

    public DependencyListener(DependentGraph graph) {
        super();
        this.graph = graph;
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        String methodName = failure.getDescription().getMethodName();
        if (methodName.contains("(")) {
            methodName = methodName.substring(0, methodName.indexOf('('));
        }
        TestMethodTracking method = graph.getMethodByNameIfFound(methodName);
        method.setFail();
        graph.setForceIgnoreForMethodDependOn(methodName);
        System.out.println("testFailure " + method.getMethodStatus());
    }

    @Override
    public void testIgnored(Description description) throws Exception {
        String methodName = description.getMethodName();
        System.out.println("testIgnored " + methodName);
    }

}
