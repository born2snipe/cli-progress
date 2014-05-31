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

import java.util.concurrent.atomic.AtomicInteger;

public abstract class ProgressPrinter {
    protected final int total;
    protected final Printer printer;
    private final AtomicInteger current = new AtomicInteger(0);

    protected ProgressPrinter(int total, Printer printer) {
        this.total = total;
        this.printer = printer;
    }

    public void step(int numberOfSteps) {
        for (int i = 0; i < numberOfSteps; i++) {
            step();
        }
    }

    public void step() {
        synchronized (current) {
            if (current.get() < total) {
                printer.clearAll();

                processStep(current.incrementAndGet());

                if (current.get() == total) {
                    printer.println();
                }
            }
        }
    }

    protected abstract void processStep(int currentStep);
}
