package utils;

import java.util.LinkedHashMap;
import java.util.Map;

//com.jagacy.framework.c
public abstract class TerminalC {
    public static int maxWidth = 80;
    public static int maxHeight = 24;

    public static int elsea = 2;

    public static final TerminalC s = new e();
    private static Map q = (Map)new LinkedHashMap();
    private static Map doa = (Map)new LinkedHashMap();

    public static TerminalC getTerminal(String paramString) {
        TerminalC c1 = getTerminal2(paramString);
        if (c1 == null)
            c1 = ifa(paramString);
        return c1;
    }

    public static final TerminalC getTerminal3 = new e();

    public static TerminalC getTerminal2(String paramString) { return (TerminalC)q.get(paramString); }
    public static TerminalC ifa(String paramString) { return (TerminalC)doa.get(paramString); }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public static final TerminalC d = new i(null);

    private TerminalC(Object o) {}

    public int getTerminalFlags() { return 0; };

    static  {
        q.put("IBM-3278-2", s);
        q.put("IBM-3277-2", q);
    }

    private static final class e extends TerminalC {
        private e() { super(null); }

        public String toString() { return "IBM-3278-2"; }
    }

    public int trya() { return maxHeight; }

    /*
    public static int goto = 80;

    public static int void = 24;

    public static int for = 1;

    public static int else = 2;

    public static final c d = new i(null);

    public static final c s = new e(null);

    public static final c char = new g(null);

    public static final c p = new d(null);

    public static final c try = new q(null);

    public static final c o = new c(null);

    public static final c if = new h(null);

    public static final c m = new b(null);

    public static final c y = new a(null);

    public static final c h = new o(null);

    public static final c A = new r(null);

    public static final c f = new n(null);

    public static final c x = new j(null);

    public static final c e = new m(null);

    public static final c r = new f(null);

    public static final c b = new l(null);

    public static final c g = new p(null);

    public static final c w = new k(null);

    private static Map q = (Map)new LinkedHashMap();

    private static Map do = (Map)new LinkedHashMap();

    static  {
        q.put("IBM-3278-2", s);
        q.put("IBM-3278-2-E", char);
        q.put("IBM-3278-3", p);
        q.put("IBM-3278-3-E", try);
        q.put("IBM-3278-4", o);
        q.put("IBM-3278-4-E", if);
        q.put("IBM-3278-5", m);
        q.put("IBM-3278-5-E", y);
        q.put("IBM-3279-2", h);
        q.put("IBM-3279-2-E", A);
        q.put("IBM-3279-3", f);
        q.put("IBM-3279-3-E", x);
        q.put("IBM-3279-4", e);
        q.put("IBM-3279-4-E", r);
        q.put("IBM-3279-5", b);
        q.put("IBM-3279-5-E", g);
        q.put("IBM-3277-2", w);
    }

    private c() {}

    public static c do(String paramString) { return (c)q.get(paramString); }

    public static c if(String paramString) { return (c)do.get(paramString); }

    public static c a(String paramString) {
        c c1 = do(paramString);
        if (c1 == null)
            c1 = if(paramString);
        return c1;
    }

    public abstract String toString();

    public int new() { return goto; }

    public int do() { return new(); }

    public int try() { return void; }

    public int for() { return try(); }

    public int if() { return 0; }

    private static final class k extends c {
        private k() { super(null); }

        public String toString() { return "IBM-3277-2"; }
    }

    private static final class e extends c {
        private e() { super(null); }

        public String toString() { return "IBM-3278-2"; }
    }

    private static final class g extends c {
        private g() { super(null); }

        public int if() { return for; }

        public String toString() { return "IBM-3278-2-E"; }
    }

    private static final class d extends c {
        private d() { super(null); }

        public int for() { return 32; }

        public String toString() { return "IBM-3278-3"; }
    }

    private static final class q extends c {
        private q() { super(null); }

        public int for() { return 32; }

        public int if() { return for; }

        public String toString() { return "IBM-3278-3-E"; }
    }

    private static final class c extends c {
        private c() { super(null); }

        public int for() { return 43; }

        public String toString() { return "IBM-3278-4"; }
    }

    private static final class h extends c {
        private h() { super(null); }

        public int for() { return 43; }

        public int if() { return for; }

        public String toString() { return "IBM-3278-4-E"; }
    }

    private static final class b extends c {
        private b() { super(null); }

        public int for() { return 27; }

        public int do() { return 132; }

        public String toString() { return "IBM-3278-5"; }
    }

    private static final class a extends c {
        private a() { super(null); }

        public int for() { return 27; }

        public int do() { return 132; }

        public int if() { return for; }

        public String toString() { return "IBM-3278-5-E"; }
    }

    private static final class o extends c {
        private o() { super(null); }

        public int if() { return else; }

        public String toString() { return "IBM-3279-2"; }
    }

    private static final class r extends c {
        private r() { super(null); }

        public int if() { return else | for; }

        public String toString() { return "IBM-3279-2-E"; }
    }

    private static final class n extends c {
        private n() { super(null); }

        public int if() { return else; }

        public int for() { return 32; }

        public String toString() { return "IBM-3279-3"; }
    }

    private static final class j extends c {
        private j() { super(null); }

        public int for() { return 32; }

        public int if() { return else | for; }

        public String toString() { return "IBM-3279-3-E"; }
    }

    private static final class m extends c {
        private m() { super(null); }

        public int if() { return else; }

        public int for() { return 43; }

        public String toString() { return "IBM-3279-4"; }
    }

    private static final class f extends c {
        private f() { super(null); }

        public int for() { return 43; }

        public int if() { return else | for; }

        public String toString() { return "IBM-3279-4-E"; }
    }

    private static final class l extends c {
        private l() { super(null); }

        public int if() { return else; }

        public int for() { return 27; }

        public int do() { return 132; }

        public String toString() { return "IBM-3279-5"; }
    }

    private static final class p extends c {
        private p() { super(null); }

        public int for() { return 27; }

        public int do() { return 132; }

        public int if() { return else | for; }

        public String toString() { return "IBM-3279-5-E"; }
    }

    private static final class i extends c {
        private i() { super(null); }

        public String toString() { return "Unknown"; }
    }
     */
    private static final class i extends TerminalC {
        private i(Object o) { super(null); }

        public String toString() { return "Unknown"; }
    }
}
