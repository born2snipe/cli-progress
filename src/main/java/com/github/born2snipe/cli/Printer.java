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

public class Printer {
    private final PrintStream printStream;
    private int totalPrinted;

    public Printer(PrintStream printStream) {
        this.printStream = printStream;
    }

    public synchronized void println(String text) {
        print(text);
        println();
    }

    public synchronized void print(String textToPrint) {
        printStream.print(textToPrint);
        printStream.flush();
        totalPrinted += textToPrint.length();
    }

    public synchronized void clearAll() {
        clear(totalPrinted);
    }

    public synchronized void clear(int numberOfCharactersToRemove) {
        for (int i = 0; i < numberOfCharactersToRemove; i++) {
            printStream.print("\b");
            totalPrinted--;
        }
        printStream.flush();
    }

    public synchronized void println() {
        printStream.print(System.getProperty("line.separator"));
        printStream.flush();
    }
}
