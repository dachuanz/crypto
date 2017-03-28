package net.oschina.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author
 * @version 1.0
 */
public abstract class AES_CBC_Coder {
	/**
	 * 创建密钥
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] initKey(int i) throws NoSuchAlgorithmException {
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		kg.init(i);// 128 192 256
		SecretKey secretKey = kg.generateKey();
		return secretKey.getEncoded();

	}

	/**
	 * 
	 * @param data
	 *            数据
	 * @param key
	 *            密钥
	 * @param iv
	 *            向量
	 * @return
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 */
	public static byte[] encypt(byte[] data, byte[] key, byte[] iv)
			throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException {
		Key key2 = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key2, new IvParameterSpec(iv));
		return cipher.doFinal(data);

	}

	public static byte[] initIV() {
		SecureRandom random = new SecureRandom();

		return random.generateSeed(16);
	}

	private static Key toKey(byte[] key) {
		SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
		return secretKey;
	}

	/**
	 * 
	 * @param data
	 * @param key
	 * @param iv
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */

	public static byte[] decypt(byte[] data, byte[] key, byte[] iv)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Key key2 = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key2, new IvParameterSpec(iv));
		return cipher.doFinal(data);
	}

	/**
	 * 
	 */
	public static final String KEY_ALGORITHM = "AES";

	public static final String CIPHER_ALGORITHM = "AES/PCBC/PKCS5Padding";

}
