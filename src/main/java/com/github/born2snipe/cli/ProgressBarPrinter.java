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

public class ProgressBarPrinter extends ProgressPrinter {
    private String barCharacter = "#";
    private int barSize = 10;
    private String emptyCharacter = " ";

    public ProgressBarPrinter(int total) {
        this(total, System.out);
    }

    public ProgressBarPrinter(int total, PrintStream printStream) {
        super(total, new Printer(printStream));
    }

    public ProgressBarPrinter(int total, Printer printer) {
        super(total, printer);
    }

    @Override
    protected void processStep(int currentStep) {
        int bars = (int) (((float) currentStep) / ((float) total) * barSize);

        printer.print("[");

        for (int i = 0; i < bars; i++)
            printer.print(barCharacter);

        for (int i = 0; i < barSize - bars; i++)
            printer.print(emptyCharacter);

        printer.print("]");
    }

    public void setBarCharacter(String barCharacter) {
        this.barCharacter = barCharacter;
    }

    public void setBarSize(int barSize) {
        this.barSize = barSize;
    }

    public void setEmptyCharacter(String emptyCharacter) {
        this.emptyCharacter = emptyCharacter;
    }
}
