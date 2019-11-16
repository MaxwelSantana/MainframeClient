package utils;

import java.util.HashMap;
import java.util.Map;

public abstract class Key {
    public static Key ENTER;

    public static Key ENTER_WAIT;

    public static Key PF1;

    public static Key PF1_WAIT;

    public static Key PF2;

    public static Key PF2_WAIT;

    public static Key PF3;

    public static Key PF3_WAIT;

    public static Key PF4;

    public static Key PF4_WAIT;

    public static Key PF5;

    public static Key PF5_WAIT;

    public static Key PF6;

    public static Key PF6_WAIT;

    public static Key PF7;

    public static Key PF7_WAIT;

    public static Key PF8;

    public static Key PF8_WAIT;

    public static Key PF9;

    public static Key PF9_WAIT;

    public static Key PF10;

    public static Key PF10_WAIT;

    public static Key PF11;

    public static Key PF11_WAIT;

    public static Key PF12;

    public static Key PF12_WAIT;

    public static Key PF13;

    public static Key PF13_WAIT;

    public static Key PF14;

    public static Key PF14_WAIT;

    public static Key PF15;

    public static Key PF15_WAIT;

    public static Key PF16;

    public static Key PF16_WAIT;

    public static Key PF17;

    public static Key PF17_WAIT;

    public static Key PF18;

    public static Key PF18_WAIT;

    public static Key PF19;

    public static Key PF19_WAIT;

    public static Key PF20;

    public static Key PF20_WAIT;

    public static Key PF21;

    public static Key PF21_WAIT;

    public static Key PF22;

    public static Key PF22_WAIT;

    public static Key PF23;

    public static Key PF23_WAIT;

    public static Key PF24;

    public static Key PF24_WAIT;

    public static Key PA1;

    public static Key PA1_WAIT;

    public static Key PA2;

    public static Key PA2_WAIT;

    public static Key PA3;

    public static Key PA3_WAIT;

    public static Key CLEAR;

    public static Key CLEAR_WAIT;

    public static Key ATTN;

    public static Key ATTN_WAIT;

    public static Key SYSREQ;

    public static Key SYSREQ_WAIT;

    public static Key CURSOR_SELECT;

    public static Key CURSOR_SELECT_WAIT;

    public static Key KEYPAD_0;

    public static Key KEYPAD_1;

    public static Key KEYPAD_2;

    public static Key KEYPAD_3;

    public static Key KEYPAD_4;

    public static Key KEYPAD_5;

    public static Key KEYPAD_6;

    public static Key KEYPAD_7;

    public static Key KEYPAD_8;

    public static Key KEYPAD_9;

    public static Key KEYPAD_HYPHEN;

    public static Key KEYPAD_COMMA;

    public static Key KEYPAD_PERIOD;

    public static Key KEYPAD_RETURN;

    public static Key UP_ARROW;

    public static Key DOWN_ARROW;

    public static Key LEFT_ARROW;

    public static Key RIGHT_ARROW;

    public static Key HOME;

    public static Key NEWLINE;

    public static Key REVERSE_NEWLINE;

    public static Key TAB;

    public static Key BACK_TAB;

    public static Key DELETE;

    public static Key INSERT;

    public static Key ERASE_EOF;

    public static Key ERASE_INPUT;

    public static Key DUPLICATE;

    public static Key BACKSPACE;

    public static Key RESET;

    public static Key ESCAPE;

    public static int ENTER_ID;

    public static int PF1_ID;

    public static int PF2_ID;

    public static int PF3_ID;

    public static int PF4_ID;

    public static int PF5_ID;

    public static int PF6_ID;

    public static int PF7_ID;

    public static int PF8_ID;

    public static int PF9_ID;

    public static int PF10_ID;

    public static int PF11_ID;

    public static int PF12_ID;

    public static int PF13_ID;

    public static int PF14_ID;

    public static int PF15_ID;

    public static int PF16_ID;

    public static int PF17_ID;

    public static int PF18_ID;

    public static int PF19_ID;

    public static int PF20_ID;

    public static int PF21_ID;

    public static int PF22_ID;

    public static int PF23_ID;

    public static int PF24_ID;

    public static int PA1_ID;

    public static int PA2_ID;

    public static int PA3_ID;

    public static int CLEAR_ID;

    public static int ATTN_ID;

    public static int SYSREQ_ID;

    public static int CURSOR_SELECT_ID;

    public static int KEYPAD_0_ID;

    public static int KEYPAD_1_ID;

    public static int KEYPAD_2_ID;

    public static int KEYPAD_3_ID;

    public static int KEYPAD_4_ID;

    public static int KEYPAD_5_ID;

    public static int KEYPAD_6_ID;

    public static int KEYPAD_7_ID;

    public static int KEYPAD_8_ID;

    public static int KEYPAD_9_ID;

    public static int KEYPAD_HYPHEN_ID;

    public static int KEYPAD_COMMA_ID;

    public static int KEYPAD_PERIOD_ID;

    public static int KEYPAD_RETURN_ID;

    public static int UP_ARROW_ID;

    public static int DOWN_ARROW_ID;

    public static int LEFT_ARROW_ID;

    public static int RIGHT_ARROW_ID;

    public static int HOME_ID;

    public static int NEWLINE_ID;

    public static int REVERSE_NEWLINE_ID;

    public static int TAB_ID;

    public static int BACK_TAB_ID;

    public static int DELETE_ID;

    public static int INSERT_ID;

    public static int ERASE_EOF_ID;

    public static int ERASE_INPUT_ID;

    public static int DUPLICATE_ID;

    public static int BACKSPACE_ID;

    public static int RESET_ID;

    public static int ESCAPE_ID;

    public static int MAX_ID;

    public static String ENTER_NAME = "ENTER";

    public static String PF1_NAME = "PF1";

    public static String PF2_NAME = "PF2";

    public static String PF3_NAME = "PF3";

    public static String PF4_NAME = "PF4";

    public static String PF5_NAME = "PF5";

    public static String PF6_NAME = "PF6";

    public static String PF7_NAME = "PF7";

    public static String PF8_NAME = "PF8";

    public static String PF9_NAME = "PF9";

    public static String PF10_NAME = "PF10";

    public static String PF11_NAME = "PF11";

    public static String PF12_NAME = "PF12";

    public static String PF13_NAME = "PF13";

    public static String PF14_NAME = "PF14";

    public static String PF15_NAME = "PF15";

    public static String PF16_NAME = "PF16";

    public static String PF17_NAME = "PF17";

    public static String PF18_NAME = "PF18";

    public static String PF19_NAME = "PF19";

    public static String PF20_NAME = "PF20";

    public static String PF21_NAME = "PF21";

    public static String PF22_NAME = "PF22";

    public static String PF23_NAME = "PF23";

    public static String PF24_NAME = "PF24";

    public static String PA1_NAME = "PA1";

    public static String PA2_NAME = "PA2";

    public static String PA3_NAME = "PA3";

    public static String CLEAR_NAME = "CLEAR";

    public static String ATTN_NAME = "ATTN";

    public static String SYSREQ_NAME = "SYSREQ";

    public static String CURSOR_SELECT_NAME = "CURSOR SELECT";

    public static String KEYPAD_0_NAME = "KEYPAD 0";

    public static String KEYPAD_1_NAME = "KEYPAD 1";

    public static String KEYPAD_2_NAME = "KEYPAD 2";

    public static String KEYPAD_3_NAME = "KEYPAD 3";

    public static String KEYPAD_4_NAME = "KEYPAD 4";

    public static String KEYPAD_5_NAME = "KEYPAD 5";

    public static String KEYPAD_6_NAME = "KEYPAD 6";

    public static String KEYPAD_7_NAME = "KEYPAD 7";

    public static String KEYPAD_8_NAME = "KEYPAD 8";

    public static String KEYPAD_9_NAME = "KEYPAD 9";

    public static String KEYPAD_HYPHEN_NAME = "KEYPAD HYPHEN";

    public static String KEYPAD_COMMA_NAME = "KEYPAD COMMA";

    public static String KEYPAD_PERIOD_NAME = "KEYPAD PERIOD";

    public static String KEYPAD_RETURN_NAME = "KEYPAD RETURN";

    public static String UP_ARROW_NAME = "UP ARROW";

    public static String DOWN_ARROW_NAME = "DOWN ARROW";

    public static String LEFT_ARROW_NAME = "LEFT ARROW";

    public static String RIGHT_ARROW_NAME = "RIGHT ARROW";

    public static String HOME_NAME = "HOME";

    public static String NEWLINE_NAME = "NEWLINE";

    public static String REVERSE_NEWLINE_NAME = "REVERSE NEWLINE";

    public static String TAB_NAME = "TAB";

    public static String BACK_TAB_NAME = "BACK TAB";

    public static String DELETE_NAME = "DELETE";

    public static String INSERT_NAME = "INSERT";

    public static String ERASE_EOF_NAME = "ERASE EOF";

    public static String ERASE_INPUT_NAME = "ERASE INPUT";

    public static String DUPLICATE_NAME = "DUPLICATE";

    public static String BACKSPACE_NAME = "BACKSPACE";

    public static String RESET_NAME = "RESET";

    public static String ESCAPE_NAME = "ESC";

    private static Map ifa;

    private static Map a;

    static  {
        byte b = 0;
        ENTER_ID = b++;
        PF1_ID = b++;
        PF2_ID = b++;
        PF3_ID = b++;
        PF4_ID = b++;
        PF5_ID = b++;
        PF6_ID = b++;
        PF7_ID = b++;
        PF8_ID = b++;
        PF9_ID = b++;
        PF10_ID = b++;
        PF11_ID = b++;
        PF12_ID = b++;
        PF13_ID = b++;
        PF14_ID = b++;
        PF15_ID = b++;
        PF16_ID = b++;
        PF17_ID = b++;
        PF18_ID = b++;
        PF19_ID = b++;
        PF20_ID = b++;
        PF21_ID = b++;
        PF22_ID = b++;
        PF23_ID = b++;
        PF24_ID = b++;
        PA1_ID = b++;
        PA2_ID = b++;
        PA3_ID = b++;
        CLEAR_ID = b++;
        ATTN_ID = b++;
        SYSREQ_ID = b++;
        CURSOR_SELECT_ID = b++;
        KEYPAD_0_ID = b++;
        KEYPAD_1_ID = b++;
        KEYPAD_2_ID = b++;
        KEYPAD_3_ID = b++;
        KEYPAD_4_ID = b++;
        KEYPAD_5_ID = b++;
        KEYPAD_6_ID = b++;
        KEYPAD_7_ID = b++;
        KEYPAD_8_ID = b++;
        KEYPAD_9_ID = b++;
        KEYPAD_HYPHEN_ID = b++;
        KEYPAD_COMMA_ID = b++;
        KEYPAD_PERIOD_ID = b++;
        KEYPAD_RETURN_ID = b++;
        UP_ARROW_ID = b++;
        DOWN_ARROW_ID = b++;
        LEFT_ARROW_ID = b++;
        RIGHT_ARROW_ID = b++;
        HOME_ID = b++;
        NEWLINE_ID = b++;
        TAB_ID = b++;
        BACK_TAB_ID = b++;
        DELETE_ID = b++;
        INSERT_ID = b++;
        ERASE_EOF_ID = b++;
        ERASE_INPUT_ID = b++;
        DUPLICATE_ID = b++;
        BACKSPACE_ID = b++;
        RESET_ID = b++;
        ESCAPE_ID = b++;
        REVERSE_NEWLINE_ID = b++;
        MAX_ID = b;
        ENTER = new av(null);
        ENTER_WAIT = ENTER;
        PF1 = new ba(null);
        PF1_WAIT = PF1;
        PF2 = new a9(null);
        PF2_WAIT = PF2;
        PF3 = new a8(null);
        PF3_WAIT = PF3;
        PF4 = new a6(null);
        PF4_WAIT = PF4;
        PF5 = new a4(null);
        PF5_WAIT = PF5;
        PF6 = new a3(null);
        PF6_WAIT = PF6;
        PF7 = new a2(null);
        PF7_WAIT = PF7;
        PF8 = new a1(null);
        PF8_WAIT = PF8;
        PF9 = new a0(null);
        PF9_WAIT = PF9;
        PF10 = new ad(null);
        PF10_WAIT = PF10;
        PF11 = new ab(null);
        PF11_WAIT = PF11;
        PF12 = new aa(null);
        PF12_WAIT = PF12;
        PF13 = new z(null);
        PF13_WAIT = PF13;
        PF14 = new y(null);
        PF14_WAIT = PF14;
        PF15 = new x(null);
        PF15_WAIT = PF15;
        PF16 = new w(null);
        PF16_WAIT = PF16;
        PF17 = new v(null);
        PF17_WAIT = PF17;
        PF18 = new t(null);
        PF18_WAIT = PF18;
        PF19 = new r(null);
        PF19_WAIT = PF19;
        PF20 = new j(null);
        PF20_WAIT = PF20;
        PF21 = new i(null);
        PF21_WAIT = PF21;
        PF22 = new h(null);
        PF22_WAIT = PF22;
        PF23 = new g(null);
        PF23_WAIT = PF23;
        PF24 = new f(null);
        PF24_WAIT = PF24;
        PA1 = new ag(null);
        PA1_WAIT = PA1;
        PA2 = new af(null);
        PA2_WAIT = PA2;
        PA3 = new ae(null);
        PA3_WAIT = PA3;
        CLEAR = new s(null);
        CLEAR_WAIT = CLEAR;
        ATTN = new k(null);
        ATTN_WAIT = ATTN;
        SYSREQ = new n(null);
        SYSREQ_WAIT = SYSREQ;
        CURSOR_SELECT = new d(null);
        CURSOR_SELECT_WAIT = CURSOR_SELECT;
        KEYPAD_0 = new at(null);
        KEYPAD_1 = new as(null);
        KEYPAD_2 = new ar(null);
        KEYPAD_3 = new ap(null);
        KEYPAD_4 = new ao(null);
        KEYPAD_5 = new an(null);
        KEYPAD_6 = new am(null);
        KEYPAD_7 = new al(null);
        KEYPAD_8 = new aj(null);
        KEYPAD_9 = new ai(null);
        KEYPAD_HYPHEN = new q(null);
        KEYPAD_COMMA = new p(null);
        KEYPAD_PERIOD = new ah(null);
        KEYPAD_RETURN = new bb(null);
        UP_ARROW = new o(null);
        DOWN_ARROW = new a(null);
        LEFT_ARROW = new ay(null);
        RIGHT_ARROW = new m(null);
        HOME = new b(null);
        NEWLINE = new az(null);
        REVERSE_NEWLINE = new aw(null);
        TAB = new ak(null);
        BACK_TAB = new aq(null);
        DELETE = new e(null);
        INSERT = new au(null);
        ERASE_EOF = new u(null);
        ERASE_INPUT = new ax(null);
        DUPLICATE = new a5(null);
        BACKSPACE = new c(null);
        RESET = new ac(null);
        ESCAPE = new a7(null);
        ifa = new HashMap<Object, Object>();
        a = new HashMap<Object, Object>();
        a.put(new Integer(ENTER_ID), ENTER);
        a.put(new Integer(PF1_ID), PF1);
        a.put(new Integer(PF2_ID), PF2);
        a.put(new Integer(PF3_ID), PF3);
        a.put(new Integer(PF4_ID), PF4);
        a.put(new Integer(PF5_ID), PF5);
        a.put(new Integer(PF6_ID), PF6);
        a.put(new Integer(PF7_ID), PF7);
        a.put(new Integer(PF8_ID), PF8);
        a.put(new Integer(PF9_ID), PF9);
        a.put(new Integer(PF10_ID), PF10);
        a.put(new Integer(PF11_ID), PF11);
        a.put(new Integer(PF12_ID), PF12);
        a.put(new Integer(PF13_ID), PF13);
        a.put(new Integer(PF14_ID), PF14);
        a.put(new Integer(PF15_ID), PF15);
        a.put(new Integer(PF16_ID), PF16);
        a.put(new Integer(PF17_ID), PF17);
        a.put(new Integer(PF18_ID), PF18);
        a.put(new Integer(PF19_ID), PF19);
        a.put(new Integer(PF20_ID), PF20);
        a.put(new Integer(PF21_ID), PF21);
        a.put(new Integer(PF22_ID), PF22);
        a.put(new Integer(PF23_ID), PF23);
        a.put(new Integer(PF24_ID), PF24);
        a.put(new Integer(PA1_ID), PA1);
        a.put(new Integer(PA2_ID), PA2);
        a.put(new Integer(PA3_ID), PA3);
        a.put(new Integer(CLEAR_ID), CLEAR);
        a.put(new Integer(ATTN_ID), ATTN);
        a.put(new Integer(SYSREQ_ID), SYSREQ);
        a.put(new Integer(CURSOR_SELECT_ID), CURSOR_SELECT);
        a.put(new Integer(KEYPAD_0_ID), KEYPAD_0);
        a.put(new Integer(KEYPAD_1_ID), KEYPAD_1);
        a.put(new Integer(KEYPAD_2_ID), KEYPAD_2);
        a.put(new Integer(KEYPAD_3_ID), KEYPAD_3);
        a.put(new Integer(KEYPAD_4_ID), KEYPAD_4);
        a.put(new Integer(KEYPAD_5_ID), KEYPAD_5);
        a.put(new Integer(KEYPAD_6_ID), KEYPAD_6);
        a.put(new Integer(KEYPAD_7_ID), KEYPAD_7);
        a.put(new Integer(KEYPAD_8_ID), KEYPAD_8);
        a.put(new Integer(KEYPAD_9_ID), KEYPAD_9);
        a.put(new Integer(KEYPAD_HYPHEN_ID), KEYPAD_HYPHEN);
        a.put(new Integer(KEYPAD_COMMA_ID), KEYPAD_COMMA);
        a.put(new Integer(KEYPAD_PERIOD_ID), KEYPAD_PERIOD);
        a.put(new Integer(KEYPAD_RETURN_ID), KEYPAD_RETURN);
        a.put(new Integer(UP_ARROW_ID), UP_ARROW);
        a.put(new Integer(DOWN_ARROW_ID), DOWN_ARROW);
        a.put(new Integer(LEFT_ARROW_ID), LEFT_ARROW);
        a.put(new Integer(RIGHT_ARROW_ID), RIGHT_ARROW);
        a.put(new Integer(HOME_ID), HOME);
        a.put(new Integer(NEWLINE_ID), NEWLINE);
        a.put(new Integer(TAB_ID), TAB);
        a.put(new Integer(BACK_TAB_ID), BACK_TAB);
        a.put(new Integer(DELETE_ID), DELETE);
        a.put(new Integer(INSERT_ID), INSERT);
        a.put(new Integer(ERASE_EOF_ID), ERASE_EOF);
        a.put(new Integer(ERASE_INPUT_ID), ERASE_INPUT);
        a.put(new Integer(DUPLICATE_ID), DUPLICATE);
        a.put(new Integer(BACKSPACE_ID), BACKSPACE);
        a.put(new Integer(RESET_ID), RESET);
        a.put(new Integer(ESCAPE_ID), ESCAPE);
        a.put(new Integer(REVERSE_NEWLINE_ID), REVERSE_NEWLINE);
        ifa.put("ENTER", ENTER);
        ifa.put("PF1", PF1);
        ifa.put("PF2", PF2);
        ifa.put("PF3", PF3);
        ifa.put("PF4", PF4);
        ifa.put("PF5", PF5);
        ifa.put("PF6", PF6);
        ifa.put("PF7", PF7);
        ifa.put("PF8", PF8);
        ifa.put("PF9", PF9);
        ifa.put("PF10", PF10);
        ifa.put("PF11", PF11);
        ifa.put("PF12", PF12);
        ifa.put("PF13", PF13);
        ifa.put("PF14", PF14);
        ifa.put("PF15", PF15);
        ifa.put("PF16", PF16);
        ifa.put("PF17", PF17);
        ifa.put("PF18", PF18);
        ifa.put("PF19", PF19);
        ifa.put("PF20", PF20);
        ifa.put("PF21", PF21);
        ifa.put("PF22", PF22);
        ifa.put("PF23", PF23);
        ifa.put("PF24", PF24);
        ifa.put("PA1", PA1);
        ifa.put("PA2", PA2);
        ifa.put("PA3", PA3);
        ifa.put("CLEAR", CLEAR);
        ifa.put("ATTN", ATTN);
        ifa.put("SYSREQ", SYSREQ);
        ifa.put("CURSOR SELECT", CURSOR_SELECT);
        ifa.put("KEYPAD 0", KEYPAD_0);
        ifa.put("KEYPAD 1", KEYPAD_1);
        ifa.put("KEYPAD 2", KEYPAD_2);
        ifa.put("KEYPAD 3", KEYPAD_3);
        ifa.put("KEYPAD 4", KEYPAD_4);
        ifa.put("KEYPAD 5", KEYPAD_5);
        ifa.put("KEYPAD 6", KEYPAD_6);
        ifa.put("KEYPAD 7", KEYPAD_7);
        ifa.put("KEYPAD 8", KEYPAD_8);
        ifa.put("KEYPAD 9", KEYPAD_9);
        ifa.put("KEYPAD HYPHEN", KEYPAD_HYPHEN);
        ifa.put("KEYPAD COMMA", KEYPAD_COMMA);
        ifa.put("KEYPAD PERIOD", KEYPAD_PERIOD);
        ifa.put("KEYPAD RETURN", KEYPAD_RETURN);
        ifa.put("UP ARROW", UP_ARROW);
        ifa.put("DOWN ARROW", DOWN_ARROW);
        ifa.put("LEFT ARROW", LEFT_ARROW);
        ifa.put("RIGHT ARROW", RIGHT_ARROW);
        ifa.put("HOME", HOME);
        ifa.put("NEWLINE", NEWLINE);
        ifa.put("TAB", TAB);
        ifa.put("BACK TAB", BACK_TAB);
        ifa.put("DELETE", DELETE);
        ifa.put("INSERT", INSERT);
        ifa.put("ERASE EOF", ERASE_EOF);
        ifa.put("ERASE INPUT", ERASE_INPUT);
        ifa.put("DUPLICATE", DUPLICATE);
        ifa.put("BACKSPACE", BACKSPACE);
        ifa.put("RESET", RESET);
        ifa.put("REVERSE NEWLINE", REVERSE_NEWLINE);
    }

    private Key(Object o) {}

    public static Key find(String paramString) {
        paramString = paramString.toUpperCase();
        return (Key)ifa.get(paramString);
    }

    public static Key find(int paramInt) { return (Key)a.get(new Integer(paramInt)); }

    public abstract int getId();

    public abstract String toString();

    public boolean isWaitKey() { return false; }

    private static final class k extends l {
        private k(Object o) { super(null); }

        public int getId() { return ATTN_ID; }

        public String toString() { return "ATTN"; }
    }

    private static final class aq extends Key {
        private aq(Object o) { super(null); }

        public int getId() { return BACK_TAB_ID; }

        public String toString() { return "BACK TAB"; }
    }

    private static final class c extends Key {
        private c(Object o) { super(null); }

        public int getId() { return BACKSPACE_ID; }

        public String toString() { return "BACKSPACE"; }
    }

    private static final class s extends l {
        private s(Object o) { super(null); }

        public int getId() { return CLEAR_ID; }

        public String toString() { return "CLEAR"; }
    }

    private static final class d extends l {
        private d(Object o) { super(null); }

        public int getId() { return CURSOR_SELECT_ID; }

        public String toString() { return "CURSOR SELECT"; }
    }

    private static final class e extends Key {
        private e(Object o) { super(null); }

        public int getId() { return DELETE_ID; }

        public String toString() { return "DELETE"; }
    }

    private static final class a extends Key {
        private a(Object o) { super(null); }

        public int getId() { return DOWN_ARROW_ID; }

        public String toString() { return "DOWN ARROW"; }
    }

    private static final class a5 extends Key {
        private a5(Object o) { super(null); }

        public int getId() { return DUPLICATE_ID; }

        public String toString() { return "DUPLICATE"; }
    }

    private static final class av extends l {
        private av(Object o) { super(null); }

        public int getId() { return ENTER_ID; }

        public String toString() { return "ENTER"; }
    }

    private static final class u extends Key {
        private u(Object o) { super(null); }

        public int getId() { return ERASE_EOF_ID; }

        public String toString() { return "ERASE EOF"; }
    }

    private static final class ax extends Key {
        private ax(Object o) { super(null); }

        public int getId() { return ERASE_INPUT_ID; }

        public String toString() { return "ERASE INPUT"; }
    }

    private static final class a7 extends Key {
        private a7(Object o) { super(null); }

        public int getId() { return ESCAPE_ID; }

        public String toString() { return "ESC"; }
    }

    private static final class b extends Key {
        private b(Object o) { super(null); }

        public int getId() { return HOME_ID; }

        public String toString() { return "HOME"; }
    }

    private static final class au extends Key {
        private au(Object o) { super(null); }

        public int getId() { return INSERT_ID; }

        public String toString() { return "INSERT"; }
    }

    private static final class at extends l {
        private at(Object o) { super(null); }

        public int getId() { return KEYPAD_0_ID; }

        public String toString() { return "KEYPAD 0"; }
    }

    private static final class as extends l {
        private as(Object o) { super(null); }

        public int getId() { return KEYPAD_1_ID; }

        public String toString() { return "KEYPAD 1"; }
    }

    private static final class ar extends l {
        private ar(Object o) { super(null); }

        public int getId() { return KEYPAD_2_ID; }

        public String toString() { return "KEYPAD 2"; }
    }

    private static final class ap extends l {
        private ap(Object o) { super(null); }

        public int getId() { return KEYPAD_3_ID; }

        public String toString() { return "KEYPAD 3"; }
    }

    private static final class ao extends l {
        private ao(Object o) { super(null); }

        public int getId() { return KEYPAD_4_ID; }

        public String toString() { return "KEYPAD 4"; }
    }

    private static final class an extends l {
        private an(Object o) { super(null); }

        public int getId() { return KEYPAD_5_ID; }

        public String toString() { return "KEYPAD 5"; }
    }

    private static final class am extends l {
        private am(Object o) { super(null); }

        public int getId() { return KEYPAD_6_ID; }

        public String toString() { return "KEYPAD 6"; }
    }

    private static final class al extends l {
        private al(Object o) { super(null); }

        public int getId() { return KEYPAD_7_ID; }

        public String toString() { return "KEYPAD 7"; }
    }

    private static final class aj extends l {
        private aj(Object o) { super(null); }

        public int getId() { return KEYPAD_8_ID; }

        public String toString() { return "KEYPAD 8"; }
    }

    private static final class ai extends l {
        private ai(Object o) { super(null); }

        public int getId() { return KEYPAD_9_ID; }

        public String toString() { return "KEYPAD 9"; }
    }

    private static final class p extends l {
        private p(Object o) { super(null); }

        public int getId() { return KEYPAD_COMMA_ID; }

        public String toString() { return "KEYPAD COMMA"; }
    }

    private static final class q extends l {
        private q(Object o) { super(null); }

        public int getId() { return KEYPAD_HYPHEN_ID; }

        public String toString() { return "KEYPAD HYPHEN"; }
    }

    private static final class ah extends l {
        private ah(Object o) { super(null); }

        public int getId() { return KEYPAD_PERIOD_ID; }

        public String toString() { return "KEYPAD PERIOD"; }
    }

    private static final class bb extends l {
        private bb(Object o) { super(null); }

        public int getId() { return KEYPAD_RETURN_ID; }

        public String toString() { return "KEYPAD RETURN"; }
    }

    private static final class ay extends Key {
        private ay(Object o) { super(null); }

        public int getId() { return LEFT_ARROW_ID; }

        public String toString() { return "LEFT ARROW"; }
    }

    private static final class az extends Key {
        private az(Object o) { super(null); }

        public int getId() { return NEWLINE_ID; }

        public String toString() { return "NEWLINE"; }
    }

    private static final class ag extends l {
        private ag(Object o) { super(null); }

        public int getId() { return PA1_ID; }

        public String toString() { return "PA1"; }
    }

    private static final class af extends l {
        private af(Object o) { super(null); }

        public int getId() { return PA2_ID; }

        public String toString() { return "PA2"; }
    }

    private static final class ae extends l {
        private ae(Object o) { super(null); }

        public int getId() { return PA3_ID; }

        public String toString() { return "PA3"; }
    }

    private static final class ba extends l {
        private ba(Object o) { super(null); }

        public int getId() { return PF1_ID; }

        public String toString() { return "PF1"; }
    }

    private static final class ad extends l {
        private ad(Object o) { super(null); }

        public int getId() { return PF10_ID; }

        public String toString() { return "PF10"; }
    }

    private static final class ab extends l {
        private ab(Object o) { super(null); }

        public int getId() { return PF11_ID; }

        public String toString() { return "PF11"; }
    }

    private static final class aa extends l {
        private aa(Object o) { super(null); }

        public int getId() { return PF12_ID; }

        public String toString() { return "PF12"; }
    }

    private static final class z extends l {
        private z(Object o) { super(null); }

        public int getId() { return PF13_ID; }

        public String toString() { return "PF13"; }
    }

    private static final class y extends l {
        private y(Object o) { super(null); }

        public int getId() { return PF14_ID; }

        public String toString() { return "PF14"; }
    }

    private static final class x extends l {
        private x(Object o) { super(null); }

        public int getId() { return PF15_ID; }

        public String toString() { return "PF15"; }
    }

    private static final class w extends l {
        private w(Object o) { super(null); }

        public int getId() { return PF16_ID; }

        public String toString() { return "PF16"; }
    }

    private static final class v extends l {
        private v(Object o) { super(null); }

        public int getId() { return PF17_ID; }

        public String toString() { return "PF17"; }
    }

    private static final class t extends l {
        private t(Object o) { super(null); }

        public int getId() { return PF18_ID; }

        public String toString() { return "PF18"; }
    }

    private static final class r extends l {
        private r(Object o) { super(null); }

        public int getId() { return PF19_ID; }

        public String toString() { return "PF19"; }
    }

    private static final class a9 extends l {
        private a9(Object o) { super(null); }

        public int getId() { return PF2_ID; }

        public String toString() { return "PF2"; }
    }

    private static final class j extends l {
        private j(Object o) { super(null); }

        public int getId() { return PF20_ID; }

        public String toString() { return "PF20"; }
    }

    private static final class i extends l {
        private i(Object o) { super(null); }

        public int getId() { return PF21_ID; }

        public String toString() { return "PF21"; }
    }

    private static final class h extends l {
        private h(Object o) { super(null); }

        public int getId() { return PF22_ID; }

        public String toString() { return "PF22"; }
    }

    private static final class g extends l {
        private g(Object o) { super(null); }

        public int getId() { return PF23_ID; }

        public String toString() { return "PF23"; }
    }

    private static final class f extends l {
        private f(Object o) { super(null); }

        public int getId() { return PF24_ID; }

        public String toString() { return "PF24"; }
    }

    private static final class a8 extends l {
        private a8(Object o) { super(null); }

        public int getId() { return PF3_ID; }

        public String toString() { return "PF3"; }
    }

    private static final class a6 extends l {
        private a6(Object o) { super(null); }

        public int getId() { return PF4_ID; }

        public String toString() { return "PF4"; }
    }

    private static final class a4 extends l {
        private a4(Object o) { super(null); }

        public int getId() { return PF5_ID; }

        public String toString() { return "PF5"; }
    }

    private static final class a3 extends l {
        private a3(Object o) { super(null); }

        public int getId() { return PF6_ID; }

        public String toString() { return "PF6"; }
    }

    private static final class a2 extends l {
        private a2(Object o) { super(null); }

        public int getId() { return PF7_ID; }

        public String toString() { return "PF7"; }
    }

    private static final class a1 extends l {
        private a1(Object o) { super(null); }

        public int getId() { return PF8_ID; }

        public String toString() { return "PF8"; }
    }

    private static final class a0 extends l {
        private a0(Object o) { super(null); }

        public int getId() { return PF9_ID; }

        public String toString() { return "PF9"; }
    }

    private static final class ac extends Key {
        private ac(Object o) { super(null); }

        public int getId() { return RESET_ID; }

        public String toString() { return "RESET"; }
    }

    private static final class aw extends Key {
        private aw(Object o) { super(null); }

        public int getId() { return REVERSE_NEWLINE_ID; }

        public String toString() { return "REVERSE NEWLINE"; }
    }

    private static final class m extends Key {
        private m(Object o) { super(null); }

        public int getId() { return RIGHT_ARROW_ID; }

        public String toString() { return "RIGHT ARROW"; }
    }

    private static final class n extends l {
        private n(Object o) { super(null); }

        public int getId() { return SYSREQ_ID; }

        public String toString() { return "SYSREQ"; }
    }

    private static final class ak extends Key {
        private ak(Object o) { super(null); }

        public int getId() { return TAB_ID; }

        public String toString() { return "TAB"; }
    }

    private static final class o extends Key {
        private o(Object o) { super(null); }

        public int getId() { return UP_ARROW_ID; }

        public String toString() { return "UP ARROW"; }
    }

    private static abstract class l extends Key {
        private l(Object o) { super(null); }

        public boolean isWaitKey() { return true; }
    }
}
