package net.oschina.crypto;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * 消息摘要算法，比传统的摘要算法更加安全
 * 
 * @author zdc52
 *
 */
public abstract class MacCoder {
	/**
	 * 初始化 MD5 key
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] initHmacMD5Key() throws NoSuchAlgorithmException {
		KeyGenerator generator = KeyGenerator.getInstance("HmacMD5");
		SecretKey key = generator.generateKey();
		return key.getEncoded();
	}

	/**
	 * 
	 * 生成 摘要值
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static byte[] encodeHmacMD5(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
		SecretKey key2 = new SecretKeySpec(key, "HmacMD5");
		Mac mac = Mac.getInstance(key2.getAlgorithm());
		mac.init(key2);
		return mac.doFinal(data);

	}

}
