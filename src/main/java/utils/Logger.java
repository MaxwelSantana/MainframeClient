package utils;

import config.JagacyProperties;
import exception.JagacyException;

public final class Logger implements Loggable {

    public static final String NL = System.getProperty("line.separator");

    public Logger(JagacyProperties paramJagacyProperties) throws JagacyException {

    }

    public void trace(String paramString) {

    }

    public void watch(String paramString) {

    }

    public void debug(String paramString) {

    }

    public void info(String paramString) {

    }

    public void warn(String paramString) {

    }

    public void error(String paramString) {

    }

    public void fatal(String paramString) {

    }

    public boolean isTraceEnabled() {
        return false;
    }

    public boolean isWatchEnabled() {
        return false;
    }

    public boolean isDebugEnabled() {
        return false;
    }

    public boolean isInfoEnabled() {
        return false;
    }

    public boolean isWarnEnabled() {
        return false;
    }

    public boolean isErrorEnabled() {
        return false;
    }

    public boolean isFatalEnabled() {
        return false;
    }

    public void setLevel(int paramInt) {

    }

    /*
    private config.JagacyProperties for;

    private BigInteger void = null;

    private int e = 6;

    private boolean d;

    private PrintStream char;

    private String if;

    private int long = -1;

    private String try;

    private SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");

    private SimpleDateFormat case = new SimpleDateFormat("yyyy-MM-dd");

    private boolean if(String paramString) throws Exception {
        boolean bool = false;
        String str = this.for.get("logFile.options", "");
        if (!utils.Util.isEmpty(str)) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, ";");
            while (stringTokenizer.hasMoreTokens()) {
                String str1 = stringTokenizer.nextToken();
                StringTokenizer stringTokenizer1 = new StringTokenizer(str1, "=");
                if (stringTokenizer1.countTokens() == 2 && stringTokenizer1.nextToken().equalsIgnoreCase(paramString)) {
                    String str2 = stringTokenizer1.nextToken().toLowerCase();
                    if (str2.equals("true")) {
                        bool = true;
                        break;
                    }
                    if (str2.equals("false")) {
                        bool = false;
                        break;
                    }
                    throw new InvalidFormatException("Could not parse boolean option " + paramString + "=" + str2);
                }
            }
        }
        return bool;
    }

    private String a(String paramString) {
        String str = "." + this.case.format(new Date());
        int i = paramString.lastIndexOf(".");
        if (i == -1)
            i = paramString.length();
        return String.valueOf(paramString.substring(0, i)) + str + paramString.substring(i);
    }

    private PrintStream a(Exception[] paramArrayOfException) {
        if (this.long >= 0) {
            int i = Calendar.getInstance().get(6);
            if (this.long != i) {
                this.long = i;
                try {
                    if (this.char != null)
                    this.char.close();
                    this.char = new PrintStream(new FileOutputStream(a(this.try), if("append")), true);
                } catch (Exception exception) {
                    if (paramArrayOfException != null) {
                        paramArrayOfException[0] = exception;
                    } else {
                        exception.printStackTrace();
                    }
                }
            }
        }
        return this.char;
    }

    public utils.Logger(config.JagacyProperties paramJagacyProperties) throws exception.JagacyException {
        this.for = paramJagacyProperties;
        String str = paramJagacyProperties.get("logFile", "out");
        if (str.equals("out")) {
            this.char = System.out;
        } else if (str.equals("err")) {
            this.char = System.err;
        } else {
            try {
                if (if("date")) {
                    this.long = 0;
                    this.try = str;
                    Exception[] arrayOfException = new Exception[1];
                    this.char = a(arrayOfException);
                    if (arrayOfException[0] != null)
                        throw new exception.JagacyException("Could not open " + str, arrayOfException[0]);
                } else {
                    try {
                        this.char = new PrintStream(new FileOutputStream(str, if("append")), true);
                    } catch (FileNotFoundException fileNotFoundException) {
                        throw new exception.JagacyException("Could not open " + str, fileNotFoundException);
                    }
                }
            } catch (Exception exception) {
                throw new exception.JagacyException(exception);
            }
        }
        this.d = paramJagacyProperties.getBoolean("jagacy.logLevel.poll", false);
        exception.JagacyException[] arrayOfJagacyException = new exception.JagacyException[1];
        if (a(arrayOfJagacyException) == 9)
            throw arrayOfJagacyException[0];
    }

    private int a(exception.JagacyException[] paramArrayOfJagacyException) {
        if (this.d || paramArrayOfJagacyException != null) {
            BigInteger bigInteger = this.for.a();
            if (!bigInteger.equals(this.void)) {
                this.void = bigInteger;
                String str = "";
                try {
                    if (this.d) {
                        str = this.for.try("logLevel", "warn");
                    } else {
                        str = this.for.get("logLevel", "warn");
                    }
                    str = str.toLowerCase();
                    if (str.equals("all")) {
                        this.e = 0;
                    } else if (str.equals("trace")) {
                        this.e = 1;
                    } else if (str.equals("watch")) {
                        this.e = 2;
                    } else if (str.equals("debug")) {
                        this.e = 3;
                    } else if (str.equals("info")) {
                        this.e = 4;
                    } else if (str.equals("warn")) {
                        this.e = 5;
                    } else if (str.equals("error")) {
                        this.e = 6;
                    } else if (str.equals("fatal")) {
                        this.e = 7;
                    } else if (str.equals("off")) {
                        this.e = 8;
                    } else {
                        throw new exception.JagacyException(6, "Invalid logLevel " + str);
                    }
                } catch (exception.JagacyException jagacyException) {
                    if (paramArrayOfJagacyException == null) {
                        if (!str.equals(this.if)) {
                            this.char.print(a());
                            this.char.print(" (ERROR): ");
                            this.char.println(String.valueOf(jagacyException));
                        }
                    } else {
                        paramArrayOfJagacyException[0] = jagacyException;
                    }
                    this.e = 9;
                    this.if = str;
                }
            }
        }
        return this.e;
    }

    private String a() { return this.a.format(new Date()); }

    public synchronized void trace(String paramString) {
        if (isTraceEnabled()) {
            this.char = a(null);
            this.char.print(a());
            this.char.print(" (TRACE): ");
            this.char.println(paramString);
        }
    }

    public synchronized void watch(String paramString) {
        if (isWatchEnabled()) {
            this.char = a(null);
            this.char.print(a());
            this.char.print(" (TRACE): ");
            this.char.println(paramString);
        }
    }

    public synchronized void debug(String paramString) {
        if (isDebugEnabled()) {
            this.char = a(null);
            this.char.print(a());
            this.char.print(" (DEBUG): ");
            this.char.println(paramString);
        }
    }

    public synchronized void info(String paramString) {
        if (isInfoEnabled()) {
            this.char = a(null);
            this.char.print(a());
            this.char.print(" (INFO): ");
            this.char.println(paramString);
        }
    }

    public synchronized void warn(String paramString) {
        if (isWarnEnabled()) {
            this.char = a(null);
            this.char.print(a());
            this.char.print(" (WARN): ");
            this.char.println(paramString);
        }
    }

    public synchronized void error(String paramString) {
        if (isErrorEnabled()) {
            this.char = a(null);
            this.char.print(a());
            this.char.print(" (ERROR): ");
            this.char.println(paramString);
        }
    }

    public synchronized void fatal(String paramString) {
        if (isFatalEnabled()) {
            this.char = a(null);
            this.char.print(a());
            this.char.print(" (FATAL): ");
            this.char.println(paramString);
        }
    }

    public boolean isTraceEnabled() { return (a(null) <= 1); }

    public boolean isWatchEnabled() { return (a(null) <= 2); }

    public boolean isDebugEnabled() { return (a(null) <= 3); }

    public boolean isInfoEnabled() { return (a(null) <= 4); }

    public boolean isWarnEnabled() { return (a(null) <= 5); }

    public boolean isErrorEnabled() { return (a(null) <= 6); }

    public boolean isFatalEnabled() { return (a(null) <= 7); }

    public void setLevel(int paramInt) {
        if (paramInt < 0 || paramInt >= 9)
            throw new IllegalArgumentException("Invalid level: " + paramInt);
        this.e = paramInt;
    }

    protected void finalize() {
        if (this.char != null && this.char != System.out && this.char != System.err)
        this.char.close();
    }
     */
}