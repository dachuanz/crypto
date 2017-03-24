package net.oschina.crypto;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author �Ŵ�
 * @version 1.0
 */
public abstract class AES_ECB_Coder {
	/**
	 * ���ɰ�ȫ��Կ����������Կ����
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
	 * @param data ���������
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encypt(byte[] data, byte[] key) throws Exception {
		Key key2 = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key2);//����ģʽ
		return cipher.doFinal(data);

	}

	/**
	 * ��װkey
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
	 * @param data ���������
	 * @param key
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
	 * �㷨����
	 */
	public static final String KEY_ALGORITHM = "AES";
/**
ECB�㷨�ŵ㣺

�򵥡�������ÿ���鵥�����㡣�ʺϲ������㡣�������һ��ֻӰ�쵱ǰ�顣

ECB�㷨ȱ�㣺

ͬ�������ͬ���ģ����ܵ������Ĺ�����

*/
	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
}