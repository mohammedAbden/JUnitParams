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
package junitparams.internal;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.TestClass;

/**
 *
 * @author mohamed
 */
public class TestMethodTracking extends TestMethod {

    private MethodStatus methodStatus;

    public MethodStatus getMethodStatus() {
        return methodStatus;
    }

    public TestMethodTracking(FrameworkMethod method, TestClass testClass) {
        super(method, testClass);
        methodStatus = MethodStatus.NOT_VISITED;
    }

    @Override
    public boolean isIgnored() {
        return super.isIgnored() || this.isForceIgnore(); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isNotVisited() {
        return methodStatus == MethodStatus.NOT_VISITED;
    }

    public void setPass() {
        methodStatus = MethodStatus.PASS;
    }

    public boolean isNeedDependency() {
        return methodStatus == MethodStatus.NEED_DEPENDENCY;
    }

    public void setNeedDependency() {
        methodStatus = MethodStatus.NEED_DEPENDENCY;
    }

    public void setFail() {
        methodStatus = MethodStatus.FAIL;
    }

    public void setForceIgnore() {
        methodStatus = MethodStatus.FORCE_IGNORE;
    }

    boolean isForceIgnore() {
        return methodStatus == MethodStatus.FORCE_IGNORE;
    }

    private enum MethodStatus {
        NOT_VISITED, NEED_DEPENDENCY, READY,
        PASS, FAIL, FORCE_IGNORE
    }

}
