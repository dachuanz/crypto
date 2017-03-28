package net.oschina.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * 
 * @author 张大川
 *
 */
public class PBECoder {

	private static final String ENCRYPTION_ALGORITHM = "PBEWithSHA1AndRC2_128";

	private static final int ITERATIONS = 1000;

	/**
	 * 
	 * @param encodedEncryptedSecret
	 * @param password
	 * @param salt
	 * @param iv
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static byte[] decodeAndDecryptSecret(String encodedEncryptedSecret, char[] password, byte[] salt, byte[] iv)
			throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		PBEKeySpec keySpec = new PBEKeySpec(password);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ENCRYPTION_ALGORITHM);
		SecretKey secretKey = keyFactory.generateSecret(keySpec);
		PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, ITERATIONS, new IvParameterSpec(iv));

		Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, secretKey, parameterSpec);

		return cipher.doFinal(Base64.getDecoder().decode(encodedEncryptedSecret));

	}

	public static byte[] initSalt() {
		SecureRandom random = new SecureRandom();

		return random.generateSeed(8);
	}

	public static byte[] initIV() {
		SecureRandom random = new SecureRandom();

		return random.generateSeed(16);
	}

	/**
	 * 
	 * @param secret
	 * @param password
	 * @param salt
	 * @param iv
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String encryptAndEncodeSecret(byte[] secret, char[] password, byte[] salt, byte[] iv)
			throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		PBEKeySpec keySpec = new PBEKeySpec(password, salt, ITERATIONS);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ENCRYPTION_ALGORITHM);
		SecretKey secretKey = keyFactory.generateSecret(keySpec);
		PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, ITERATIONS, new IvParameterSpec(iv));

		Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);

		byte[] encryptedSecret = cipher.doFinal(secret);
		return Base64.getEncoder().encodeToString(encryptedSecret);

	}
}
