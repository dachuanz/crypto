package net.oschina.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
/**
 * 
 * @author 张大川
 * dachuanz@gmail.com
 *基于口令的加密
 */
public abstract class PBECoder {
	public static final String ALGORITHM = "PBEWITHMD5andDES";
	public static final int ITERATION_COUNT = 100;

	/**
	 * 生成密码
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	private static Key toKey(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());

		SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);

		SecretKey key = factory.generateSecret(keySpec);
		return key;
	}

	/**
	 * 生成盐
	 * 
	 * @return
	 */
	public static byte[] initSalt() {
		SecureRandom random = new SecureRandom();

		return random.generateSeed(8);
	}

	/**
	 * 加密
	 * @param data
	 * @param salt
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static byte[] encypt(byte[] data, byte[] salt, String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		Key key = toKey(password);
		PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, ITERATION_COUNT);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
		return cipher.doFinal(data);
	}

	/**
	 * 解密
	 * @param data
	 * @param salt
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static byte[] decypt(byte[] data, byte[] salt, String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		Key key = toKey(password);
		PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, ITERATION_COUNT);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);
		return cipher.doFinal(data);
	}
}
