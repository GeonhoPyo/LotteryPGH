package com.example.lotterypgh.Tool;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class Dlog {
    static final String TAG = "InfoCar";

    static final int MAX_LENGTH = 4000;
    /** Log Level Error **/
    public static final <T> void e(T message) {
        String msg = String.valueOf(message);
        if (msg.length() > MAX_LENGTH){
            Log.e(TAG, buildLogMsg(msg.substring(0, MAX_LENGTH)));
            Log.e(TAG, msg.substring(MAX_LENGTH));
        }else{
            Log.e(TAG, buildLogMsg(msg));
        }
    }
    /** Log Level Warning **/
    public static final <T> void w(T message) {
        if (BaseApplication.DEBUG){
            String msg = String.valueOf(message);
            if (msg.length() > MAX_LENGTH){
                Log.w(TAG, buildLogMsg(msg.substring(0, MAX_LENGTH)));
                Dlog.w(msg.substring(MAX_LENGTH));
            }else{
                Log.w(TAG, buildLogMsg(msg));
            }
        }
    }
    /** Log Level Information **/
    public static final <T> void i(T message) {
        if (BaseApplication.DEBUG){
            String msg = String.valueOf(message);
            if (msg.length() > MAX_LENGTH){
                Log.i(TAG, buildLogMsg(msg.substring(0, MAX_LENGTH)));
                Dlog.i(msg.substring(MAX_LENGTH));
            }else{
                Log.i(TAG, buildLogMsg(msg));
            }
        }
    }
    /** Log Level Debug **/
    public static final <T> void d(T message) {
        if (BaseApplication.DEBUG){
            String msg = String.valueOf(message);
            if (msg.length() > MAX_LENGTH){
                Log.d(TAG, buildLogMsg(msg.substring(0, MAX_LENGTH)));
                Dlog.d(msg.substring(MAX_LENGTH));
            }else{
                Log.d(TAG, buildLogMsg(msg));
            }
        }
    }
    /** Log Level Verbose **/
    public static final <T> void v(T message) {
        if (BaseApplication.DEBUG){
            String msg = String.valueOf(message);
            if (msg.length() > MAX_LENGTH){
                Log.v(TAG, buildLogMsg(msg.substring(0, MAX_LENGTH)));
                Dlog.v(msg.substring(MAX_LENGTH));
            }else{
                Log.v(TAG, buildLogMsg(msg));
            }
        }
    }


    public static String buildLogMsg(String message) {

        StackTraceElement ste = Thread.currentThread().getStackTrace()[4];

        StringBuilder sb = new StringBuilder();

        try {
            sb.append("[")
                    .append(ste.getMethodName())
                    .append("()")
                    .append("]")
                    .append(" :: ")
                    .append(message)
                    .append(" (")
                    .append(ste.getFileName())
                    .append(":")
                    .append(ste.getLineNumber())
                    .append(")");
        }catch (Exception e){
            Writer writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));
            String logToString = new Time_DataBridge().getServerRealTime()+"/"+writer.toString();

            sb.append("[")
                    .append(ste.getMethodName())
                    .append("()")
                    .append("]")
                    .append(" :: ")
                    .append(logToString)
                    .append(" (")
                    .append(ste.getFileName())
                    .append(":")
                    .append(ste.getLineNumber())
                    .append(")");
        }

        return sb.toString();

    }
}
