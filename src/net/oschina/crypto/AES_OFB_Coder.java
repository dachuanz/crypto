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

public class AES_OFB_Coder {
	/**
	 * 
	 * @author
	 * @version 1.0
	 */

	/**
	 * 
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
	 * 
	 * @param data
	 * 
	 * @param key
	 * @return
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws Exception
	 */
	public static byte[] encypt(byte[] data, byte[] key, byte[] iv)
			throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException {
		Key key2 = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key2, new IvParameterSpec(iv));//
		return cipher.doFinal(data);

	}

	/**
	 * 
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static Key toKey(byte[] key) {
		SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
		return secretKey;
	}

	public static byte[] initIV() {
		SecureRandom random = new SecureRandom();

		return random.generateSeed(16);
	}

	/**
	 * 
	 * 
	 * @param data
	 * 
	 * @param key
	 * @return
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws Exception
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

	public static final String CIPHER_ALGORITHM = "AES/OFB/NoPadding";
}
