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

public class PrinterTest {
    private MockConsolePrintWriter mockConsolePrintWriter;
    private Printer printer;

    @Before
    public void setUp() throws Exception {
        mockConsolePrintWriter = new MockConsolePrintWriter();
        printer = new Printer(mockConsolePrintWriter);
    }

    @Test
    public void shouldAllowPrintingNewLines() {
        printer.println();

        assertEquals(System.getProperty("line.separator"), mockConsolePrintWriter.getOutput());
    }

    @Test
    public void shouldAllowClearingAfterEverythingHasAlreadyBeenCleared() {
        printer.print("1234");
        printer.clearAll();
        printer.clearAll();

        assertEquals("", mockConsolePrintWriter.getOutput());
    }

    @Test
    public void shouldAllowClearingEvenWhenNothingHasBeenPrinted() {
        printer.clearAll();
    }

    @Test
    public void shouldAllowClearingASpecificNumberOfCharacters() {
        printer.print("1234");
        printer.clear(2);

        assertEquals("12", mockConsolePrintWriter.getOutput());
    }

    @Test
    public void shouldAllowClearingAllThatHasBeenPrinted() {
        printer.print("1234");
        printer.clearAll();

        assertEquals("", mockConsolePrintWriter.getOutput());
    }
}