package helper;

public class FeatureHelper {
    public static final int FEATURE_MARK = 1;

    public static final int PROTECTED_MARK = 2;

    public static final int HIDDEN_MARK = 4;

    public static final int NUMERIC_MARK = 8;

    public static final int BOLD_MARK = 16;

    public static final int UNDERLINE_MARK = 32;

    public static final int BLINK_MARK = 64;

    public static final int REVERSE_MARK = 128;

    public static final int SELECTABLE_MARK = 256;

    public static final int ATTR_MASK = 65535;

    public static final int COLOR_MASK = -65536;

    public static final int COLOR_DEFAULT = 0;

    public static final int COLOR_BLACK = 1;

    public static final int COLOR_RED = 2;

    public static final int COLOR_GREEN = 3;

    public static final int COLOR_YELLOW = 4;

    public static final int COLOR_BLUE = 5;

    public static final int COLOR_MAGENTA = 6;

    public static final int COLOR_CYAN = 7;

    public static final int COLOR_WHITE = 8;

    public static final int COLOR_LIGHT_CYAN = 9;

    public static final int COLOR_PINK = 10;

    public static final int COLOR_ORANGE = 11;

    public static final int COLOR_LIGHT_GRAY = 12;

    public static final int COLOR_DARK_GRAY = 13;

    public static final int COLOR_LIGHT_GREEN = 14;

    public static final int COLOR_DARK_BLUE = 15;

    public static boolean isProtected(int paramInt) { return ((paramInt & 0x2) != 0); }

    public static boolean isHidden(int paramInt) { return ((paramInt & 0x4) != 0); }

    public static boolean isReverse(int paramInt) { return ((paramInt & 0x80) != 0); }

    public static boolean isBlink(int paramInt) { return ((paramInt & 0x40) != 0); }

    public static boolean isBold(int paramInt) { return ((paramInt & 0x10) != 0); }

    public static boolean isUnderline(int paramInt) { return ((paramInt & 0x20) != 0); }

    public static boolean isNumeric(int paramInt) { return ((paramInt & 0x8) != 0); }

    public static boolean isSelectable(int paramInt) { return ((paramInt & 0x100) != 0); }

    //public static boolean isFeature(int paramInt) { return ((paramInt & true) != 0); }

    public static int getForeground(int paramInt) { return paramInt >>> 24 & 0xFF; }

    public static int getBackground(int paramInt) { return paramInt >>> 16 & 0xFF; }
}
