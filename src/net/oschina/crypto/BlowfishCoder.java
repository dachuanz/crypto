package net.oschina.crypto;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

 
public class BlowfishCoder {
	
	public static byte[] initKey() throws NoSuchAlgorithmException {
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		kg.init(56*8);
		SecretKey secretKey = kg.generateKey();
		return secretKey.getEncoded();

	}

	
	public static byte[] encypt(byte[] data, byte[] key) throws Exception {
		Key key2 = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key2);
		return cipher.doFinal(data);

	}


	private static Key toKey(byte[] key) throws Exception {
		SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
		return secretKey;
	}


	public static byte[] decypt(byte[] data, byte[] key) throws Exception {
		Key key2 = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key2);
		return cipher.doFinal(data);
	}


	public static final String KEY_ALGORITHM = "Blowfish";

	public static final String CIPHER_ALGORITHM = "Blowfish/ECB/PKCS5Padding";
}
