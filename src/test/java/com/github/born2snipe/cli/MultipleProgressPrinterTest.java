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

public class MultipleProgressPrinterTest {
    private MockConsolePrintWriter mockPrintWriter;
    private MultipleProgressPrinter printer;

    @Before
    public void setUp() throws Exception {
        mockPrintWriter = new MockConsolePrintWriter();
        printer = new MultipleProgressPrinter(100, mockPrintWriter);
    }

    @Test
    public void shouldAllowSettingACustomMessageFormatForTheCurrentPositionPrinting() {
        printer.showCurrentPositionOfTotal("Processing {count}-{total}...");
        printer.step();
        assertEquals("Processing 1-100...", mockPrintWriter.getOutput());
    }

    @Test
    public void shouldAllowPrintingMultipleProgressPrinters() {
        printer.showCurrentPositionOfTotal();
        printer.showPercentageComplete();

        for (int i = 1; i <= 99; i++) {
            printer.step();
            assertEquals(i + " of 100, " + i + "%", mockPrintWriter.getOutput());
        }

        printer.step();
        assertEquals("100 of 100, 100%\n", mockPrintWriter.getOutput());

    }

}
