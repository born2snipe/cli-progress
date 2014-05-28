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

public class ProgressPrinter {
    private final int total;
    private final AtomicInteger current = new AtomicInteger(0);
    private int lengthOfLastNumber = 0;

    public ProgressPrinter(int total) {
        this.total = total;
    }

    public void step() {
        synchronized (current) {
            if (lengthOfLastNumber > 0) {
                clear();
            }
            current.incrementAndGet();
            int percentage = (int) ((((double) current.get()) / (double) total) * 100.0);

            System.out.print(percentage + "%");

            if (percentage == 100) {
                System.out.println();
            }

            lengthOfLastNumber = String.valueOf(percentage).length();
        }
    }

    private void clear() {
        // clear percent sign
        System.out.print("\b");
        for (int i = 0; i < lengthOfLastNumber; i++) {
            System.out.print("\b");
        }
    }
}
