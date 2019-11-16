package utils;

public interface Loggable {
    public static final int ALL_LEVEL = 0;

    public static final int TRACE_LEVEL = 1;

    public static final int WATCH_LEVEL = 2;

    public static final int DEBUG_LEVEL = 3;

    public static final int INFO_LEVEL = 4;

    public static final int WARN_LEVEL = 5;

    public static final int ERROR_LEVEL = 6;

    public static final int FATAL_LEVEL = 7;

    public static final int OFF_LEVEL = 8;

    public static final int INVALID_LEVEL = 9;

    void trace(String paramString);

    void watch(String paramString);

    void debug(String paramString);

    void info(String paramString);

    void warn(String paramString);

    void error(String paramString);

    void fatal(String paramString);

    boolean isTraceEnabled();

    boolean isWatchEnabled();

    boolean isDebugEnabled();

    boolean isInfoEnabled();

    boolean isWarnEnabled();

    boolean isErrorEnabled();

    boolean isFatalEnabled();

    void setLevel(int paramInt);
}

