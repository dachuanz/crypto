package net.oschina.crypto;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * ECB模式 优点:
 * 
 * 1.简单；
 * 
 * 2.有利于并行计算；
 * 
 * 3.误差不会被传送；
 * 
 * 缺点:
 * 
 * 1.不能隐藏明文的模式；
 * 
 * 2.可能对明文进行主动攻击；
 */
public abstract class AES_ECB_Coder {
	public static final String KEY_ALGORITHM = "AES";

	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

	public static byte[] initKey(int i) throws NoSuchAlgorithmException {
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		kg.init(i);// 128 192 256
		SecretKey secretKey = kg.generateKey();
		return secretKey.getEncoded();

	}

	public static byte[] encypt(byte[] data, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Key key2 = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key2);
		return cipher.doFinal(data);

	}

	private static Key toKey(byte[] key) {
		SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
		return secretKey;
	}

	public static byte[] decypt(byte[] data, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Key key2 = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key2);
		return cipher.doFinal(data);
	}

}
