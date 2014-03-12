package me.xyzlast.bh.utils;

import java.util.ArrayList;
import java.util.List;


public class OfficeCounter {

    private class InOutLogger {
        private final int inValue;
        private final int outValue;
        public InOutLogger(String logValue) {
            String[] logNumberStrings = splitLogMessage(logValue);
            if(logNumberStrings.length != 6) {
                throw new IllegalArgumentException();
            }
            inValue = convertStringsToSec(logNumberStrings, 0);
            outValue = convertStringsToSec(logNumberStrings, 3);
        }

        public boolean isExist(int sec) {
            return sec >= inValue && sec < outValue;
        }
    }

    public static String[] splitLogMessage(String logValue) {
        return logValue.split("[:\\s]");
    }

    public static int convertStringsToSec(String[] numbers, int startIndex) {
        return 60 * 60 * Integer.parseInt(numbers[startIndex]) +
                60 * Integer.parseInt(numbers[1 + startIndex]) +
                Integer.parseInt(numbers[2 + startIndex]);
    }

    private List<InOutLogger> inOutLoggers = new ArrayList<>();

    public void add(String logValue) {
        inOutLoggers.add(new InOutLogger(logValue));
    }

    public int count(String timeString) {
        int sec = convertStringsToSec(splitLogMessage(timeString), 0);
        int count = 0;
        for(InOutLogger inOutLogger : inOutLoggers) {
            if(inOutLogger.isExist(sec)) {
                count++;
            }
        }
        return count;
    }
}
