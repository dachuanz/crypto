package net.oschina.crypto;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author 张大川
 * @version 1.0
 */
public abstract class AES_ECB_Coder {
	/**
	 * 生成安全秘钥，避免弱秘钥问题
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] initKey() throws NoSuchAlgorithmException {
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		kg.init(256);// 可以为 128 192 256
		SecretKey secretKey = kg.generateKey();
		return secretKey.getEncoded();

	}

	/**
	 * 加密
	 * 
	 * @param data 需加密数据
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encypt(byte[] data, byte[] key) throws Exception {
		Key key2 = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key2);//加密模式
		return cipher.doFinal(data);

	}

	/**
	 * 包装key
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static Key toKey(byte[] key) throws Exception {
		SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
		return secretKey;
	}

	/**
	 * 解密
	 * 
	 * @param data 需解密数据
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
	 * 算法定义
	 */
	public static final String KEY_ALGORITHM = "AES";
/**
ECB算法优点：

简单、孤立，每个块单独运算。适合并行运算。传输错误一般只影响当前块。

ECB算法缺点：

同明文输出同密文，可能导致明文攻击。

*/
	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
}
