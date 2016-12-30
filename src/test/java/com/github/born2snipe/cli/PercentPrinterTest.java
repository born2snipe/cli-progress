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

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PercentPrinterTest {
    private MockConsolePrintWriter mockConsolePrintWriter;
    private PercentPrinter printer;

    @Before
    public void setUp() throws Exception {
        mockConsolePrintWriter = new MockConsolePrintWriter();
        printer = new PercentPrinter(100, mockConsolePrintWriter);
    }

    @Test
    public void shouldAllowPrintingALineOfTextAndReprintingTheProgress() {
        printer.step();
        printer.println("message");

        assertEquals("message\n1%", mockConsolePrintWriter.getOutput());
    }

    @Test
    public void shouldOnlyPrintANewLineWhenWeHaveReachedTheEnd() {
        printer = new PercentPrinter(1545, mockConsolePrintWriter);
        for (int i = 0; i < 1545; i++) {
            printer.step();
        }
        assertEquals(1, mockConsolePrintWriter.getNumberLineEndings());
    }

    @Test
    public void shouldPrintThePercentComplete() {
        for (int i = 0; i < 99; i++) {
            printer.step();
            assertEquals((i + 1) + "%", mockConsolePrintWriter.getOutput());
        }

        printer.step();
        assertEquals("100%" + System.getProperty("line.separator"), mockConsolePrintWriter.getOutput());
    }

    @Test
    public void shouldNotPrintAnythingIfWeAttemptToStepPastTheEnd() {
        for (int i = 0; i < 100; i++) {
            printer.step();
        }

        printer.step();
        assertEquals("100%\n", mockConsolePrintWriter.getOutput());
    }
}