package net.oschina.crypto;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * RSA 算法
 * 
 * @author 张大川
 */

public abstract class RSACoder {
	public static final String KEY_ALGORITHM = "RSA";

	public static final String PUBLIC_KEY = "RSAPublicKey";
	public static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
	public static final String PRIVATE_KEY = "RSAPrivateKey";
	public static final int KEY_SIZE = 512;

	/**
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static Map<String, Object> initKey() throws NoSuchAlgorithmException {
		// 实例化密钥生成器
		KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		generator.initialize(KEY_SIZE);// 1024 512
		KeyPair keyPair = generator.generateKeyPair();
		RSAPublicKey key = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey key2 = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put(PUBLIC_KEY, key);
		map.put(PRIVATE_KEY, key2);
		return map;
	}

	/**
	 * 生成密钥对
	 * 
	 * @param i
	 *            可选 512 1024 2048
	 * @return
	 * @throws NoSuchAlgorithmException
	 */

	public static Map<String, Object> initKey(int i) throws NoSuchAlgorithmException {
		// 实例化密钥生成器
		KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		generator.initialize(i);// 1024 512
		KeyPair keyPair = generator.generateKeyPair();
		RSAPublicKey key = (RSAPublicKey) keyPair.getPublic();
		// System.out.println(key.getEncoded());

		RSAPrivateKey key2 = (RSAPrivateKey) keyPair.getPrivate();
		// System.out.println(key2.getEncoded());
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put(PUBLIC_KEY, key);
		map.put(PRIVATE_KEY, key2);
		return map;
	}

	/**
	 * 私钥解密
	 * 
	 * @param data
	 * @param privateKey
	 * @return
	 * @throws GeneralSecurityException
	 * @throws NoSuchAlgorithmException
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] data, RSAPrivateKey privateKey)
			throws NoSuchAlgorithmException, GeneralSecurityException {
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/**
	 * 公钥加密
	 * 
	 * @param data
	 * @param publicKey
	 * @return
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, RSAPublicKey publicKey) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	/**
	 * 
	 * 私钥加密
	 *  数字签名
	 * @param data
	 * @param key
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static byte[] encryptByPrivateKey(byte[] data, byte[] key)
			throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		/**
		 * 私钥规范 PKCS8，描述私有密钥信息格式
		 * 
		 */
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey key2 = factory.generatePrivate(keySpec);
		//System.out.println(factory.getAlgorithm());
		Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, key2);
		return cipher.doFinal(data);

	}

	public static void main(String[] args) throws Exception {
		Map<String, Object> map = RSACoder.initKey(1024);
		// System.out.println(map.get(PUBLIC_KEY));

		String string = "12345678909";
		byte[] bs = string.getBytes();
		System.err.println("明文:" + string);
		// byte[] bs2 = AESCoder.initKey();
		/**
		 * 使用java 8自带base64算法
		 */
		PrivateKey key = (PrivateKey) map.get(PRIVATE_KEY);
		// System.err.println("密码" + Base64.getEncoder().encodeToString(bs2));
		bs = RSACoder.encryptByPrivateKey(bs, key.getEncoded());
		System.err.println("加密后:" + Base64.getEncoder().encodeToString(bs));
		// byte[] bs3 = RSACoder.decryptByPrivateKey(bs, (RSAPrivateKey)
		// map.get(PRIVATE_KEY));
		// String string2 = new String(bs3);
		// System.err.println("解密后:" + string2);

	}

}
