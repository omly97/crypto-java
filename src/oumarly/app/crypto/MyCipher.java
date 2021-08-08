package oumarly.app.crypto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;

public class MyCipher {

	/**
	 * @author Oumar Ly
	 * @apiNote crypt and decrypt byte array
	 * @param algo algorithm or transformation
	 * @param key is an SecretKey instance
	 * @param data the bytes to be crypted or decrypted
	 * @param isEncryptMode: ENCRYPT_MODE if true and DECRYPT_MODE else
	 * @return bytes of crypted or decrypted data
	 * @throws Exception
	 */
	private static byte[] cryptoByte(String algo, SecretKey key, byte[] data, boolean isCryptMode) throws Exception {
		Cipher cipher = Cipher.getInstance(algo);
		if (isCryptMode) cipher.init(Cipher.ENCRYPT_MODE, key);
		else cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(data);
	}

	/**
	 * @author Oumar Ly
	 * @apiNote crypt byte array
	 * @param algo algorithm or transformation
	 * @param key an SecretKey instance
	 * @param data the byte array to be crypted
	 * @return byte array representation of crypted data
	 * @throws Exception
	 */
	public static byte[] cryptByteArray(String algo, SecretKey key, byte[] data) throws Exception {
		return cryptoByte(algo, key, data, true);
	}

	/**
	 * @author Oumar Ly
	 * @apiNote decrypt byte array
	 * @param algo algorithm or transformation
	 * @param key an SecretKey instance
	 * @param data the byte array to be decrypted
	 * @return byte array representation of decrypted data
	 * @throws Exception
	 */
	public static byte[] decryptByteArray(String algo, SecretKey key, byte[] data) throws Exception {
		return cryptoByte(algo, key, data, false);
	}

	/**
	 * @author Oumar Ly
	 * @apiNote crypt clear text
	 * @param algo algorithm or transformation
	 * @param key an SecretKey instance
	 * @param text the string to be crypted
	 * @return hex representation of crypted data
	 * @throws Exception
	 */
	public static String cryptString(String algo, SecretKey key, String text) throws Exception {
		byte[] data = cryptoByte(algo, key, text.getBytes(), true);
		return Converter.toHex(data);
	}

	/**
	 * @author Oumar Ly
	 * @apiNote decrypt hex text (crypted text)
	 * @param algo algorithm or transformation
	 * @param key an SecretKey instance
	 * @param hexStrng the hex string to be decrypted
	 * @return clear text of decrypted data
	 * @throws Exception
	 */
	public static String decryptString(String algo, SecretKey key, String hexStrng) throws Exception {
		byte[] data = cryptoByte(algo, key, Converter.toBytes(hexStrng), false);
		return new String(data);
	}

	/**
	 * @author Oumar Ly
	 * @apiNote crypt and decrypt file
	 * @param algo: algorithm or transformation
	 * @param key: SecretKey or PublicKey or PrivateKey
	 * @param inputfilepath: file to be crypted or decrypted
	 * @param outputfilepath: file to save crypted or decrypted data
	 * @param isEncryptMode: ENCRYPT_MODE if true and DECRYPT_MODE else
	 * @return void
	 */
	@SuppressWarnings("resource")
	private static void cryptoFile(String algo, Key key, String inputfilepath, String outputfilepath,
			boolean isEncryptMode) throws Exception 
	{
		Cipher cipher = Cipher.getInstance(algo);
		if (isEncryptMode) cipher.init(Cipher.ENCRYPT_MODE, key);
		else cipher.init(Cipher.DECRYPT_MODE, key);

		FileInputStream fis = new FileInputStream(inputfilepath);
		CipherInputStream cis = new CipherInputStream(fis, cipher);
		FileOutputStream fos = new FileOutputStream(outputfilepath);
		byte[] buf = new byte[8];
		int i = cis.read(buf);
		while (i != -1) {
			fos.write(buf, 0, i);
			i = cis.read(buf);
		}
		fis.close();
		fos.close();
	}


	/**
	 * @author Oumar Ly
	 * @apiNote crypt file
	 * @param algo: algorithm or transformation
	 * @param key: SecretKey or PublicKey or PrivateKey
	 * @param inputfilepath: file to be crypted
	 * @param outputfilepath: file to save crypted data
	 * @return void
	 */
	public static void cryptFile(String algo, Key key, String inputfilepath, String outputfilepath) throws Exception {
		cryptoFile(algo, key, inputfilepath, outputfilepath, true);
	}


	/**
	 * @author Oumar Ly
	 * @apiNote decrypt file
	 * @param algo: algorithm or transformation
	 * @param key: SecretKey or PublicKey or PrivateKey
	 * @param inputfilepath: file to be decrypted
	 * @param outputfilepath: file to save decrypted data
	 * @return void
	 */
	public static void decryptFile(String algo, Key key, String inputfilepath, String outputfilepath) throws Exception {
		cryptoFile(algo, key, inputfilepath, outputfilepath, false);
	}
}
