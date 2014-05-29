/**
 *
 * Copyright to the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package com.github.born2snipe.cli;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MockConsolePrintWriter extends PrintStream {
    private String output = "";

    public MockConsolePrintWriter() {
        super(new ByteArrayOutputStream());
    }

    @Override
    public void print(String s) {
        for (char c : s.toCharArray()) {
            switch (c) {
                case '\b':
                    output = output.substring(0, output.length() - 1);
                    break;
                default:
                    output += c;
            }
        }
    }

    public String getOutput() {
        return output;
    }
}
