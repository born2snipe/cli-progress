/**
 * Copyright to the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at:
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
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

    @Before
    public void setUp() throws Exception {
        mockConsolePrintWriter = new MockConsolePrintWriter();
    }

    @Test
    public void shouldAllowTheEmptyCharacterToBeConfigurable() {
        ProgressBarPrinter printer = newPrinter(10);
        printer.setEmptyCharacter(".");
        printer.step();

        assertOutput("[#.........]");
    }

    @Test
    public void shouldAllowTheBarCharacterToBeConfigurable() {
        ProgressBarPrinter printer = newPrinter(10);
        printer.setBarCharacter("*");
        printer.step();

        assertOutput("[*         ]");
    }

    @Test
    public void shouldAllowMakingTheBarSizeConfigurable() {
        ProgressBarPrinter printer = newPrinter(100);
        printer.setBarSize(5);

        printer.step(100);

        assertOutput("[#####]\n");
    }

    @Test
    public void shouldAllowATotalSizeWayBiggerThan10() {
        ProgressBarPrinter printer = newPrinter(100);
        printer.step(100);
        assertOutput("[##########]\n");
    }

    @Test
    public void shouldAllowTheProgressBarToBeCompleted() {
        ProgressBarPrinter printer = newPrinter(10);

        printer.step(10);

        assertOutput("[##########]\n");
    }

    @Test
    public void shouldAllowDisplayingAProgressBar() {
        ProgressBarPrinter printer = newPrinter(10);

        printer.step(1);

        assertOutput("[#         ]");
    }

    @Test
    public void shouldNotDisplayAnythingUntilProgressIsMade() {
        ProgressBarPrinter printer = newPrinter(10);

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