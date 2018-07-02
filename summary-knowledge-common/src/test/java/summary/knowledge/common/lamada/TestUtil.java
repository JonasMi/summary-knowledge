package summary.knowledge.common.lamada;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

public class TestUtil {
	public static final String KEY_ALGORITHM = "RSA";
    public static final int MAX_ENCRYPT_BLOCK = 117;
    public static final int MAX_DECRYPT_BLOCK = 128;

    public static KeyPair generateKeyPair() {
        // 实例化密钥生成器
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            // 初始化密钥生成器
            keyPairGenerator.initialize(1024);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
           throw new RuntimeException("",e);
        }
    }

    // 从证书文件中加载公钥
    public static PublicKey getPublicKeyFromCert(InputStream in) throws Exception {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        X509Certificate x509certificate = (X509Certificate) certificateFactory.generateCertificate(in);
        return x509certificate.getPublicKey();
    }

    // 从pfx文件中加载私钥
    public static PrivateKey getPrivateKeyFromPfx(InputStream in, String keyStorePwd, String privateKeyPwd, String entry) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("pkcs12");
        keyStore.load(in, keyStorePwd.toCharArray());
        return (PrivateKey) keyStore.getKey(entry, privateKeyPwd.toCharArray());
    }

    // 从pfx文件中加载公钥
    public static PublicKey getPublicKeyFromPfx(InputStream in, String keyStorePwd, String entry) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("pkcs12");
        keyStore.load(in, keyStorePwd.toCharArray());
        X509Certificate certificate = (X509Certificate) keyStore.getCertificate(entry);
        return certificate.getPublicKey();
    }

    // 公钥加密
    public static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int keySize = 1024;
        if (publicKey instanceof RSAPublicKey) {
            keySize = ((RSAPublicKey) publicKey).getModulus().bitLength();
        }
        int maxEncryptBlock = keySize/8 - 11;
        return segmentEncryptOrDecrypt(content, cipher, maxEncryptBlock);
    }

    // 私钥加密
    public static byte[] encrypt(byte[] content, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        int keySize = 1024;
        if (privateKey instanceof RSAPrivateKey) {
            keySize = ((RSAPrivateKey) privateKey).getModulus().bitLength();
        }
        int maxEncryptBlock = keySize/8 - 11;
        return segmentEncryptOrDecrypt(content, cipher, maxEncryptBlock);
    }

    // 私钥解密
    public static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        int keySize = 1024;
        if (privateKey instanceof RSAPrivateKey) {
            keySize = ((RSAPrivateKey) privateKey).getModulus().bitLength();
        }
        int maxDecryptBlock = keySize/8;
        return segmentEncryptOrDecrypt(content, cipher, maxDecryptBlock);
    }

    // 公钥解密
    public static byte[] decrypt(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        int keySize = 1024;
        if (publicKey instanceof RSAPublicKey) {
            keySize = ((RSAPublicKey) publicKey).getModulus().bitLength();
        }
        int maxDecryptBlock = keySize/8;
        return segmentEncryptOrDecrypt(content, cipher, maxDecryptBlock);
    }

    // 公钥解密
    public static byte[] decryptBase64String(String content, PublicKey publicKey) throws Exception {
        byte[] b = Base64.getDecoder().decode(content);
        return decrypt(b, publicKey);
    }

    private static byte[] segmentEncryptOrDecrypt(byte[] content, Cipher cipher, int block) throws IllegalBlockSizeException, BadPaddingException {
        int inputLen = content.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > block) {
                cache = cipher.doFinal(content, offSet, block);
            } else {
                cache = cipher.doFinal(content, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * block;
        }

        return out.toByteArray();
    }
    
    public static String buildKey(String tenantId,String orgKey){
    	Long time = (new Date()).getTime();
    	return tenantId+"&"+time.toString()+"&"+orgKey;
    }

    public static void main(String[] args) throws Exception {
        String key = buildKey("yanghao","bdeb6419702654ff4bfd36774473804ea3ea76559b4186c1a35e438eeba92f2b");
        /*InputStream in = TestUtil.class.getClassLoader().getResourceAsStream("download.crt");*///cdr界面下载的公匙
        InputStream in = new FileInputStream("D:\\我的文档\\Downloads\\download (5).crt");
        PublicKey tenantPublicKey = getPublicKeyFromCert(in);
        
        byte[] b = encrypt(key.getBytes(StandardCharsets.UTF_8), tenantPublicKey);
        
        InputStream clientCertStream = TestUtil.class.getClassLoader().getResourceAsStream("RootCA.pfx");
        
        PrivateKey privateKey = getPrivateKeyFromPfx(clientCertStream, "n3stv1s1on", "n3stv1s1on", "RootCA");
        System.out.println(new String(b));
        b = encrypt(b, privateKey);
       
        String tenantKey = new String(Base64.getEncoder().encode(b));
        System.out.println(tenantKey);
    	
    	InputStream input = TestUtil.class.getClassLoader().getResourceAsStream("RootCA.crt");
    	
    	PublicKey pk = getPublicKeyFromCert(input);
    	
    	b = decrypt(b, pk);
    	System.out.println(new String(b));
    	
    }
}
