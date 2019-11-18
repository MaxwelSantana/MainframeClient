package conduit;

import com.sun.org.apache.xml.internal.security.utils.I18n;
import config.Config;
import exception.JagacyException;
import ui.UserInterface;
import utils.Loggable;
import utils.Util;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;

public class X509TrustManagerB implements X509TrustManager {
    private UserInterface chara;

    private Loggable ifa;

    private SSLContext doa;

    private TrustManager[] casea;

    private X509Certificate[] fora;

    private Config bytea;

    private String newa;

    private String trya;

    private PrivateKey inta;

    private PublicKey a;

    public X509TrustManagerB(UserInterface paramUserInterface, Loggable paramLoggable, Config paramd) throws ClassNotFoundException, IllegalAccessException, InstantiationException, JagacyException {
        this.chara = paramUserInterface;
        this.ifa = paramLoggable;
        this.bytea = paramd;
    }

    private void a(String paramString) {
        if (paramString.indexOf("with") == -1) {
            int i = paramString.length();
            if (i > 3)
                paramString = String.valueOf(paramString.substring(0, i - 3).toUpperCase()) + "with" + paramString.substring(i - 3, i);
        }
        if (paramString.endsWith("RSA")) {
            this.trya = "RSA";
        } else {
            this.trya = "DSA";
        }
        this.newa = paramString;
    }

    public PrivateKey fora() { return this.inta; }

    public PublicKey ifa() { return this.a; }

    public String inta() { return this.bytea.getParam("jagacy.ssl.keyAlgorithm", this.trya); }

    public void doa() throws Exception {
        this.ifa.trace("Generating keys");
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(inta());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        this.inta = keyPair.getPrivate();
        this.a = keyPair.getPublic();
    }

    public void newa() throws Exception, JagacyException {
        InputStream inputStream = null;
        String str1 = this.bytea.getParam("jagacy.ssl.provider", null);
        if (str1 != null) {
            Class<?> clazz = Class.forName(str1);
            Security.addProvider((Provider)clazz.newInstance());
        }
        String str2 = this.bytea.getParam("jagacy.ssl.keyFile", "");
        String str3 = this.bytea.getParam("jagacy.ssl.keyPassword", "");
        if (!Util.isEmpty(str2) && Util.isEmpty(str3))
            throw new JagacyException(6, "Empty jagacy.keyPassword");
        if (!Util.isEmpty(str2)) {
            if (str2.equalsIgnoreCase(":java:"))
                str2 = String.valueOf(System.getProperty("user.home")) + File.separator + ".keystore";
            if ((new File(str2)).exists()) {
                inputStream = new FileInputStream(str2);
                this.ifa.trace("Using keystore: " + str2);
            } else {
                this.ifa.trace("Using empty keystore");
            }
        } else {
            inputStream = getClass().getResourceAsStream("cacerts");
            if (inputStream == null)
                inputStream = getClass().getClassLoader().getResourceAsStream("cacerts");
            this.ifa.trace("Using default keystore");
            str3 = "changeit";
        }
        String str4 = KeyStore.getDefaultType();
        str4 = this.bytea.getParam("jagacy.ssl.keystoreType", str4);
        str4 = str4.toUpperCase();
        this.ifa.trace("Using keystoreType: " + str4);
        char[] arrayOfChar = str3.toCharArray();
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(inputStream, arrayOfChar);
        if (inputStream != null)
            inputStream.close();
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, arrayOfChar);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        this.casea = trustManagerFactory.getTrustManagers();
            ArrayList arrayList = new ArrayList();
            for (byte b1 = 0; b1 < this.casea.length; b1++) {
            if (this.casea[b1] instanceof X509TrustManager)
                arrayList.addAll(Arrays.asList(((X509TrustManager)this.casea[b1]).getAcceptedIssuers()));
        }
        X509Certificate[] arrayOfX509Certificate = new X509Certificate[arrayList.size()];
        this.fora = arrayOfX509Certificate = (X509Certificate[])arrayList.toArray((Object[])arrayOfX509Certificate);
        String str5 = this.bytea.getParam("jagacy.ssl.context", "TLSv1.2");
        this.doa = SSLContext.getInstance(str5);
        this.doa.init(keyManagerFactory.getKeyManagers(), new TrustManager[] { this }, null);
    }

    public Socket a(InetAddress paramInetAddress, int paramInt1, int paramInt2) throws Exception {
        if (this.doa == null)
        throw new IllegalStateException("SSL context not initialized");
        SSLSocket sSLSocket = (SSLSocket)this.doa.getSocketFactory().createSocket(paramInetAddress, paramInt1);
        String[] arrayOfString = sSLSocket.getSupportedCipherSuites();
        sSLSocket.setEnabledCipherSuites(arrayOfString);
        sSLSocket.setNeedClientAuth(false);
        sSLSocket.startHandshake();
        return sSLSocket;
    }

    public Socket a(Socket paramSocket, InetAddress paramInetAddress, int paramInt1, int paramInt2) throws Exception {
        if (this.doa == null)
        throw new IllegalStateException("SSL context not initialized");
        SSLSocket sSLSocket = (SSLSocket)this.doa.getSocketFactory().createSocket(paramSocket, paramInetAddress.getHostAddress(), paramInt1, true);
        String[] arrayOfString = sSLSocket.getSupportedCipherSuites();
        sSLSocket.setEnabledCipherSuites(arrayOfString);
        sSLSocket.setNeedClientAuth(false);
        sSLSocket.startHandshake();
        return sSLSocket;
    }

    public X509Certificate[] getAcceptedIssuers() { return this.fora; }

    public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) throws CertificateException { throw new SecurityException("checkClientTrusted unsupported"); }

    public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) throws CertificateException {
        X509Certificate x509Certificate = null;
        try {
            x509Certificate = paramArrayOfX509Certificate[0];
            a(x509Certificate.getSigAlgName());
            for (byte b1 = 0; b1 < this.casea.length; b1++) {
                if (this.casea[b1] instanceof X509TrustManager)
                    ((X509TrustManager)this.casea[b1]).checkServerTrusted(paramArrayOfX509Certificate, paramString);
            }
        } catch (CertificateException certificateException) {
            String str = certificateException.getMessage();
            //String str = String.valueOf(I18n.getText("gui.cert.version")) + x509Certificate.getVersion() + "\n" + I18n.getText("gui.cert.serial") + x509Certificate.getSerialNumber() + "\n" + I18n.getText("gui.cert.signature") + x509Certificate.getSigAlgName() + "\n" + I18n.getText("gui.cert.issuer") + x509Certificate.getIssuerDN().getName() + "\n" + I18n.getText("gui.cert.valid_from") + x509Certificate.getNotBefore() + "\n" + I18n.getText("gui.cert.valid_to") + x509Certificate.getNotAfter() + "\n" + I18n.getText("gui.cert.subject_dn") + x509Certificate.getSubjectDN().getName() + "\n" + I18n.getText("gui.cert.public_key") + x509Certificate.getPublicKey().getFormat() + "\n";
            this.ifa.warn("Unknown Certificate\n----\n" + str + "----");
            if (this.chara != null && this.bytea.getParam().getBoolean("jagacy.emulator.showcert", true))
            this.chara.notify(2, "Unknown Certificate", str);
        }
    }
}
