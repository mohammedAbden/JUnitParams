/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junitparams.internal.dependant.graph;

/**
 *
 * @author mohamed
 */
public class CyclicDetectedException extends RuntimeException {

    /**
     * Creates a new instance of <code>CyclicDetected</code> without detail
     * message.
     */
    public CyclicDetectedException() {
        this("Cyclic Detected");
    }

    /**
     * Constructs an instance of <code>CyclicDetected</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public CyclicDetectedException(String msg) {
        super(msg);
    }
    
}
