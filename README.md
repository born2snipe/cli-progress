
# CLI-Progress

This is a simple Java API for printing progress for CLI apps

![sampleoutput](http://i.imgur.com/yAcr9DG.gif)

## Classes of interest

- [PercentPrinter][1] - allows printing the current percentage of the progress
- [CountUpToTotalPrinter][2] - allows printing the current count of the total (support [custom message formatting][5])
- [MultipleProgressPrinter][3] - allows printing multiple progress indicators at the same time
- [ElapsedTimePrinter][4] - allows printing the current elapsed time
- [ProgressBarPrinter][5] - allows printing a progress bar

*Note*: The JAR is an executable JAR and you can see what type of console output to expect

[1]: https://github.com/born2snipe/cli-progress/blob/master/src/main/java/com/github/born2snipe/cli/PercentPrinter.java "percent"
[2]: https://github.com/born2snipe/cli-progress/blob/master/src/main/java/com/github/born2snipe/cli/CountUpToTotalPrinter.java "countUp"
[3]: https://github.com/born2snipe/cli-progress/blob/master/src/main/java/com/github/born2snipe/cli/MultipleProgressPrinter.java "multi"
[4]: https://github.com/born2snipe/cli-progress/blob/master/src/main/java/com/github/born2snipe/cli/ElapsedTimePrinter.java "elapsed"
[5]: https://github.com/born2snipe/cli-progress/blob/master/src/main/java/com/github/born2snipe/cli/ProgressBarPrinter.java "progress-bar"
