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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class MockConsolePrintWriter extends PrintStream {
    private ArrayList<String> lines = new ArrayList<String>();
    private String currentLine = "";
    private int numberLineEndings;
    private int cursorLocation;

    public MockConsolePrintWriter() {
        super(new ByteArrayOutputStream());
    }

    @Override
    public void print(String s) {
        for (char c : s.toCharArray()) {
            if (isBackspace(c)) {
                cursorLocation--;
                if (cursorLocation < 0) {
                    cursorLocation = 0;
                }
            } else if (isNewLine(c)) {
                numberLineEndings++;
                cursorLocation = 0;
                lines.add(currentLine);
                currentLine = "";
            } else {
                if (isCursorAtTheEndOfTheCurrentLine()) {
                    currentLine += c;
                } else {
                    String starting = currentLine.substring(0, cursorLocation);
                    String ending = "";
                    if (currentLine.length() > 1) {
                        ending = currentLine.substring(cursorLocation + 1);
                    }

                    currentLine = starting + c;
                    currentLine += ending;
                }
                cursorLocation++;
            }
        }
    }

    public int getNumberLineEndings() {
        return numberLineEndings;
    }

    public String getOutput() {
        String output = "";
        for (String line : lines) {
            output += line;
            output += "\n";
        }
        output += currentLine;
        return output;
    }

    @Override
    public String toString() {
        return getOutput();
    }

    private boolean isCursorAtTheEndOfTheCurrentLine() {
        return cursorLocation == currentLine.length()
//                || cursorLocation == 0
                ;
    }

    private boolean isNewLine(char c) {
        return c == '\n';
    }

    private boolean isBackspace(char c) {
        return c == '\b';
    }
}
