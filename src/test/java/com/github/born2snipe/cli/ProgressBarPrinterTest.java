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

public class ProgressBarPrinterTest {
    private MockConsolePrintWriter mockConsolePrintWriter;
    private ProgressBarPrinter printer;

    @Before
    public void setUp() throws Exception {
        mockConsolePrintWriter = new MockConsolePrintWriter();
        printer = newPrinter(10);
    }

    @Test
    public void shouldAllowPrintingALineOfTextAndReprintingTheProgress() {
        printer.step();
        printer.println("message");

        assertEquals("message     \n[#         ]", mockConsolePrintWriter.getOutput());
    }

    @Test
    public void shouldAllowTheEmptyCharacterToBeConfigurable() {
        printer.setEmptyCharacter(".");
        printer.step();

        assertOutput("[#.........]");
    }

    @Test
    public void shouldAllowTheBarCharacterToBeConfigurable() {
        printer.setBarCharacter("*");
        printer.step();

        assertOutput("[*         ]");
    }

    @Test
    public void shouldAllowMakingTheBarSizeConfigurable() {
        printer.setBarSize(5);

        printer.step(100);

        assertOutput("[#####]\n");
    }

    @Test
    public void shouldAllowATotalSizeWayBiggerThan10() {
        printer.step(100);
        assertOutput("[##########]\n");
    }

    @Test
    public void shouldAllowTheProgressBarToBeCompleted() {
        printer.step(10);

        assertOutput("[##########]\n");
    }

    @Test
    public void shouldAllowDisplayingAProgressBar() {
        printer.step(1);

        assertOutput("[#         ]");
    }

    @Test
    public void shouldNotDisplayAnythingUntilProgressIsMade() {
        printer.step(0);

        assertOutput("");
    }

    private ProgressBarPrinter newPrinter(int total) {
        return new ProgressBarPrinter(total, mockConsolePrintWriter);
    }

    private void assertOutput(String expected) {
        assertEquals(expected, mockConsolePrintWriter.getOutput());
    }
}