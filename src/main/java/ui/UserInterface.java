package ui;

import exception.JagacyException;

public interface UserInterface {
    public static final int INFO_LEVEL = 1;

    public static final int WARN_LEVEL = 2;

    public static final int ERROR_LEVEL = 0;

    void update() throws JagacyException;

    void resetTime();

    void beginTime();

    void endTime();

    void setLocked(boolean paramBoolean);

    void notify(int paramInt, String paramString1, String paramString2);

    void close();

    boolean waitForUnlock(int paramInt) throws JagacyException;

    boolean waitForChange(int paramInt) throws JagacyException;

    boolean waitForPosition(int paramInt1, int paramInt2, String paramString, int paramInt3) throws JagacyException;

    //boolean waitForCursor(Location paramLocation, int paramInt) throws JagacyException;
}
