/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junitparams.dependancy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.DependParamRunner;
import junitparams.Parameters;
import junitparams.dependant.DependOn;

/**
 *
 * @author mohamed
 */
@RunWith(DependParamRunner.class)
public class MainTest {

    @Test
    @DependOn(methods = {"test4"})
    @Parameters(value = {"0", "2", "4"})
    public void test2(int x) {
        Assert.assertEquals(0L, x%2);
        // just for testint 
    }

    @Test
    @DependOn(methods = {"test1"})
    public void test3() {
// just for test
    }
//testng-junit

    
    @Test
    @DependOn(methods = {"test0", "test1"})
    public void test4() {
// just for test
//        Assert.fail("Test Fail");
    }

    @Test
    @DependOn(methods = {"test0", "test2"})
    public void test5() {
        //Assert.fail("Test fail");
    }

    @Test
    public void test0() {
        // just for test
    }

    @Test
    @DependOn(methods = {})
    public void test1() {
        //Assert.fail("Test Fail");
    }
}
