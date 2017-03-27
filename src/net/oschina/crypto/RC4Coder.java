package net.oschina.crypto;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * RC4 �㷨�Ѿ���ʱ�ˣ���Ӧ��ʹ��
 * 
 * @author �Ŵ�
 *
 */
public abstract class RC4Coder {
	/*
	 * 
	 * @return
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] initKey() throws NoSuchAlgorithmException {
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);

		SecretKey secretKey = kg.generateKey();
		return secretKey.getEncoded();

	}

	/**
	 * ����
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encypt(byte[] data, byte[] key) throws Exception {
		Key key2 = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key2);
		return cipher.doFinal(data);

	}

	/**
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static Key toKey(byte[] key) throws Exception {
		SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
		return secretKey;
	}

	/**
	 * 
	 * ����
	 * 
	 * @param data
	 *            ����
	 * @param key
	 *            ��Կ
	 * @return
	 * @throws Exception
	 */
	public static byte[] decypt(byte[] data, byte[] key) throws Exception {
		Key key2 = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key2);
		return cipher.doFinal(data);
	}

	/**
	 * 
	 */
	public static final String KEY_ALGORITHM = "RC4";

	public static final String CIPHER_ALGORITHM = "RC4/ECB/NOPadding";
}
