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

import java.io.PrintStream;

public class ElapsedTimePrinter extends ProgressPrinter {
    private final Object lock = new Object();
    private Long start;

    public ElapsedTimePrinter(int total) {
        this(total, System.out);
    }

    public ElapsedTimePrinter(int total, PrintStream stream) {
        super(total, new Printer(stream));
    }

    public ElapsedTimePrinter(int total, Printer printer) {
        super(total, printer);
    }

    @Override
    protected void processStep(int currentStep) {
        synchronized (lock) {
            initializeStartAsNeeded();

            long elapsed = System.currentTimeMillis() - start;

            printer.print("Elapsed: " + ElapsedTimeFormatter.formatMillis(elapsed));
        }
    }

    private void initializeStartAsNeeded() {
        if (start == null) {
            start = System.currentTimeMillis();
        }
    }
}
