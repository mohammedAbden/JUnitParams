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
package junitparams.internal.dependant.graph;

/**
 *
 * @author mohamed
 */
public class WrongMethodNameException extends RuntimeException {

    /**
     * Creates a new instance of <code>WrongMethodName</code> without detail
     * message.
     */
    public WrongMethodNameException() {
         this("Wrong Method name in depentency Annotation");
    }

    /**
     * Constructs an instance of <code>WrongMethodName</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public WrongMethodNameException(String msg) {
        super(msg);
    }
}
