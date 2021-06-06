package junitparams.internal;


import org.junit.runners.model.TestClass;

import junitparams.internal.dependant.graph.DependentGraph;

public class DependParameterisedTestClassRunner extends ParameterisedTestClassRunner {

    private DependentGraph dependencyGraph;

    public DependParameterisedTestClassRunner(TestClass testClass) {
        super(testClass);
    }

    @Override
    protected void computeTestMethods(TestClass testClass) {
        dependencyGraph = new DependentGraph(testClass);
        setTestMethodsList(dependencyGraph.topogicalSort());
    }

    public DependentGraph getDependencyGraph() {
        return dependencyGraph;
    }

}
