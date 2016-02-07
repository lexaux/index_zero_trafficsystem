package kcl.teamIndexZero.traffic.log.outputs;

import kcl.teamIndexZero.traffic.log.Log_Levels;
import kcl.teamIndexZero.traffic.log.Log_TimeStamp;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Es on 29/01/2016.
 * Formatter for '.csv' file export
 */
public class Formatter_CSV implements Formatter_Interface {
    /**
     * Constructor
     */
    protected Formatter_CSV() {
    }

    /**
     * Formats message information for CSV File output
     *
     * @param origin_name Name of the message's origin
     * @param log_level   Log level
     * @param log_number  Message number in log session
     * @param time_stamp  Time stamp for the message
     * @param objects     Message details
     * @return Formatted String
     */
    @Override
    public String format(String origin_name, int log_level, Long log_number, Log_TimeStamp time_stamp, Object... objects) {
        String s = log_number.toString() + ";" + time_stamp.getDate() + ";" + time_stamp.getTime() + ";" + Log_Levels.csvLevels[log_level] + ";" + origin_name + ";";
        for (Object o : objects) {
            s += o.toString();
        }
        return s + System.lineSeparator();
    }

    /**
     * Formats message information for CSV File output
     *
     * @param origin_name Name of the message's origin
     * @param time_stamp  Time stamp for the message
     * @param log_number  Message number in session
     * @param e           Exception raised
     * @return Formatted String
     */
    @Override
    public String format(String origin_name, Log_TimeStamp time_stamp, Long log_number, Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String s = log_number.toString() + ";" + time_stamp.getDate() + ";" + time_stamp.getTime() + ";EXCEPTION;" + origin_name + "; " + sw.toString(); //TODO check format
        return s;
    }
}
