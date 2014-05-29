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
import java.util.concurrent.atomic.AtomicInteger;

public class PercentPrinter {
    private final int total;
    private final AtomicInteger current = new AtomicInteger(0);
    private final Printer printer;

    public PercentPrinter(int total) {
        this(total, System.out);
    }

    public PercentPrinter(int total, PrintStream printStream) {
        this.total = total;
        this.printer = new Printer(printStream);
    }

    public void step() {
        synchronized (current) {
            printer.clearAll();

            current.incrementAndGet();
            int percentage = (int) Math.round(((((double) current.get()) / (double) total) * 100.0));

            printer.print(percentage + "%");

            if (percentage == 100) {
                printer.println();
            }
        }
    }
}
