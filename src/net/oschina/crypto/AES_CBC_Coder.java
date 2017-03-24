package net.oschina.crypto;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author �Ŵ�
 * @version 1.0
 */
public abstract class AES_CBC_Coder {
	/**
	 * ���ɰ�ȫ��Կ����������Կ����
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] initKey() throws NoSuchAlgorithmException {
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		kg.init(256);// ����Ϊ 128 192 256
		SecretKey secretKey = kg.generateKey();
		return secretKey.getEncoded();

	}

	/**
	 * ����
	 * 
	 * @param data
	 *            ���������
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encypt(byte[] data, byte[] key, byte[] iv) throws Exception {
		Key key2 = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key2, new IvParameterSpec(iv));// ����ģʽ
		return cipher.doFinal(data);

	}

	public static byte[] initIV() {
		SecureRandom random = new SecureRandom();

		return random.generateSeed(16);
	}

	/**
	 * ��װkey
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
	 * ����
	 * 
	 * @param data
	 *            ���������
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decypt(byte[] data, byte[] key, byte[] iv) throws Exception {
		Key key2 = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key2, new IvParameterSpec(iv));
		return cipher.doFinal(data);
	}

	/**
	 * �㷨����
	 */
	public static final String KEY_ALGORITHM = "AES";

	public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";//CBC ģʽ
}
