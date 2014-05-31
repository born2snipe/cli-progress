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

public class CountUpToTotalPrinterTest {
    private MockConsolePrintWriter mockConsolePrintWriter;
    private CountUpToTotalPrinter printer;

    @Before
    public void setUp() throws Exception {
        mockConsolePrintWriter = new MockConsolePrintWriter();
        printer = new CountUpToTotalPrinter(10, mockConsolePrintWriter);

    }

    @Test
    public void shouldAllowSettingACustomMessageFormat() {
        printer.setMessageFormat("Processing {count}/{total} records");

        printer.step();

        assertEquals("Processing 1/10 records", mockConsolePrintWriter.getOutput());
    }

    @Test
    public void shouldAllowProcessingStepsInCustomIncrements() {
        for (int i = 1; i <= 5; i++) {
            printer.step(2);
            assertEquals((i * 2) + " of 10", mockConsolePrintWriter.getOutput().trim());
        }
    }

    @Test
    public void shouldAllowProcessingStepsInIncrementsOfOne() {
        for (int i = 0; i < 9; i++) {
            printer.step();
            assertEquals((i + 1) + " of 10", mockConsolePrintWriter.getOutput());
        }
        printer.step();
        assertEquals("10 of 10\n", mockConsolePrintWriter.getOutput());
    }
}