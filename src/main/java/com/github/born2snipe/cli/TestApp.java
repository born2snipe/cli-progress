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

public class TestApp {
    private static final int STEP_COUNT = 1000;

    public static void main(String... args) {
        MultipleProgressPrinter printer = new MultipleProgressPrinter(STEP_COUNT, System.out);
        printer.setSeparator(" ");
        printer.showCurrentPositionOfTotal();
        printer.showProgressBar().setBarCharacter("\u2588"); // fancy block character
        printer.showPercentageComplete();
        printer.showElapsedTime();

        executePrinter(printer);
    }

    private static void executePrinter(ProgressPrinter printer) {
        for (int i = 0; i < STEP_COUNT; i++) {
            printer.step();
            pause();
        }
    }

    private static void pause() {
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {

        }
    }
}
