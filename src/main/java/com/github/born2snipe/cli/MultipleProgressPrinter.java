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
import java.util.ArrayList;

public class MultipleProgressPrinter extends ProgressPrinter {
    private ArrayList<ProgressPrinter> progressPrinters = new ArrayList<ProgressPrinter>();

    public MultipleProgressPrinter(int total, PrintStream printStream) {
        super(total, new Printer(printStream));
    }

    @Override
    protected void processStep(int currentStep) {
        for (int i = 0; i < progressPrinters.size(); i++) {
            progressPrinters.get(i).processStep(currentStep);
            if (isNotLastPrinter(i)) {
                printer.print(", ");
            }
        }
    }

    public void showPercentageComplete() {
        progressPrinters.add(new PercentPrinter(total, printer));
    }

    public void showCurrentPositionOfTotal() {
        progressPrinters.add(new CountUpToTotalPrinter(total, printer));
    }

    public void showCurrentPositionOfTotal(String customMessageFormat) {
        CountUpToTotalPrinter printer = new CountUpToTotalPrinter(total, this.printer);
        printer.setMessageFormat(customMessageFormat);
        progressPrinters.add(printer);
    }

    public void showElapsedTime() {
        progressPrinters.add(new ElapsedTimePrinter(total, printer));
    }

    private boolean isNotLastPrinter(int index) {
        return index < progressPrinters.size() - 1;
    }
}
