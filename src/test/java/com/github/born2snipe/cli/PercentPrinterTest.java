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

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PercentPrinterTest {
    @Test
    public void shouldPrintThePercentComplete() {
        MockConsolePrintWriter mockConsolePrintWriter = new MockConsolePrintWriter();
        PercentPrinter percentPrinter = new PercentPrinter(100, mockConsolePrintWriter);

        for (int i = 0; i < 99; i++) {
            percentPrinter.step();
            assertEquals((i + 1) + "%", mockConsolePrintWriter.getOutput());
        }

        percentPrinter.step();
        assertEquals("100%" + System.getProperty("line.separator"), mockConsolePrintWriter.getOutput());
    }
}