/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junitparams.internal.dependant.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.junit.Test;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.TestClass;

import junitparams.dependant.DependOn;
import junitparams.internal.TestMethod;
import junitparams.internal.TestMethodTracking;


public class DependentGraph {

    private static final HashSet<String> EMPTYSET = new HashSet<String>();
    private final HashMap<TestMethodTracking, HashSet<String>> adjList;
    private final HashMap<String, TestMethodTracking> methodByName;
    private final List<TestMethod> topogicalSortResult;
    private final Stack<String> cyclicTracking;

   public DependentGraph(TestClass testClass) {
        this.adjList = new HashMap<TestMethodTracking, HashSet<String>>();
        this.methodByName = new HashMap<String, TestMethodTracking>();
        this.topogicalSortResult = new LinkedList<TestMethod>();
        this.cyclicTracking = new Stack<String>();
        extractTestMethod(testClass.getAnnotatedMethods(Test.class), testClass);
    }

    private void extractTestMethod(List<FrameworkMethod> testClassMethod, TestClass testClass) {
        for (FrameworkMethod method : testClassMethod) {
            TestMethodTracking testMethod = new TestMethodTracking(method, testClass);
            methodByName.put(testMethod.name(), testMethod);
            adjList.put(testMethod, getMethodDependenciesIfFound(testMethod));
        }
    }

    private HashSet<String> getMethodDependenciesIfFound(TestMethodTracking method) {
        return method.frameworkMethod().getMethod().isAnnotationPresent(DependOn.class)
                ? new HashSet<String>( Arrays.asList(method.getAnnotation(DependOn.class).methods()))
                : EMPTYSET;
    }

    public List<TestMethod> topogicalSort() {
        for (TestMethodTracking method : this.adjList.keySet()) {
            topogicalSortDFS(method);
        }
        return topogicalSortResult;
    }

    private void topogicalSortDFS(TestMethodTracking method) {
        if (method.isNeedDependency()) {
            throw new CyclicDetectedException(getTrackedMethod());
        }
        if (method.isNotVisited()) {
            method.setNeedDependency();
            cyclicTracking.push(method.name());
            for (String nextMethod : adjList.get(method)) {
                topogicalSortDFS((TestMethodTracking) getMethodByNameIfFound(nextMethod));
            }
            cyclicTracking.pop();
            method.setPass();
            topogicalSortResult.add(method);
        }
    }

    private String getTrackedMethod() {
        StringBuilder msg = new StringBuilder();
        for (String methodName : cyclicTracking) {
            msg.append(methodName).append(" -> ");
        }
        return msg.toString();
    }

    public TestMethodTracking getMethodByNameIfFound(String name) {
        TestMethodTracking method = methodByName.get(name);
        if (method == null) {
            throw new WrongMethodNameException("No Method By Name (" + name + ")");
        }
        return method;
    }

    public void setForceIgnoreForMethodDependOn(String methodName) {
      //need to refactory(refactory done using hashSet)
        for (Map.Entry<TestMethodTracking, HashSet<String>> entry : adjList.entrySet()) {
            TestMethodTracking key = entry.getKey();
            HashSet<String> value = entry.getValue();
            
            if(value.contains(methodName))
            
           /* if (isContains(value, methodName))*/ {
                key.setForceIgnore();
                setForceIgnoreForMethodDependOn(key.name());
            }
        }
    }

  /*  private boolean isContains(String[] dependency, String methodName) {
        for (String name : dependency) {
            if (name.equals(methodName)) {
                return true;
            }
        }
        return false;
    }*/
}
