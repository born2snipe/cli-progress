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
    private String separator = ", ";

    public MultipleProgressPrinter(int total, PrintStream printStream) {
        super(total, new Printer(printStream));
    }

    @Override
    protected void processStep(int currentStep) {
        for (int i = 0; i < progressPrinters.size(); i++) {
            progressPrinters.get(i).processStep(currentStep);
            if (isNotLastPrinter(i)) {
                printer.print(separator);
            }
        }
    }

    public PercentPrinter showPercentageComplete() {
        PercentPrinter percentPrinter = new PercentPrinter(total, printer);
        progressPrinters.add(percentPrinter);
        return percentPrinter;
    }

    public CountUpToTotalPrinter showCurrentPositionOfTotal() {
        CountUpToTotalPrinter printer = new CountUpToTotalPrinter(total, this.printer);
        progressPrinters.add(printer);
        return printer;
    }

    public CountUpToTotalPrinter showCurrentPositionOfTotal(String customMessageFormat) {
        CountUpToTotalPrinter countUpToTotalPrinter = new CountUpToTotalPrinter(total, this.printer);
        countUpToTotalPrinter.setMessageFormat(customMessageFormat);
        progressPrinters.add(countUpToTotalPrinter);
        return countUpToTotalPrinter;
    }

    public ElapsedTimePrinter showElapsedTime() {
        ElapsedTimePrinter elapsedTimePrinter = new ElapsedTimePrinter(total, printer);
        progressPrinters.add(elapsedTimePrinter);
        return elapsedTimePrinter;
    }

    private boolean isNotLastPrinter(int index) {
        return index < progressPrinters.size() - 1;
    }

    public ProgressBarPrinter showProgressBar() {
        ProgressBarPrinter progressBarPrinter = new ProgressBarPrinter(total, printer);
        progressPrinters.add(progressBarPrinter);
        return progressBarPrinter;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }
}
