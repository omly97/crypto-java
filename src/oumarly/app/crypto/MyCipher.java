package oumarly.app.crypto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;

public class MyCipher {

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
	private static void cryptoFile(String algo, String provider, Key key,
			String inputfilepath, String outputfilepath, boolean isEncryptMode) throws Exception 
	{
		Cipher cipher;
		if (provider == null) 
			cipher = Cipher.getInstance(algo);
		else
			cipher = Cipher.getInstance(algo, provider);

		if (isEncryptMode)
			cipher.init(Cipher.ENCRYPT_MODE, key, new SecureRandom());
		else
			cipher.init(Cipher.DECRYPT_MODE, key, new SecureRandom());

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
		cryptoFile(algo, null, key, inputfilepath, outputfilepath, true);
	}


	/**
	 * @author Oumar Ly
	 * @apiNote crypt file with provider
	 * @param algo: algorithm or transformation
	 * @param key: SecretKey or PublicKey or PrivateKey
	 * @param inputfilepath: file to be crypted
	 * @param outputfilepath: file to save crypted data
	 * @return void
	 */
	public static void cryptFile(String algo, String provider, Key key, String inputfilepath, String outputfilepath) throws Exception {
		cryptoFile(algo, provider, key, inputfilepath, outputfilepath, true);
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
		cryptoFile(algo, null, key, inputfilepath, outputfilepath, false);
	}


	/**
	 * @author Oumar Ly
	 * @apiNote decrypt file with provider
	 * @param algo: algorithm or transformation
	 * @param key: SecretKey or PublicKey or PrivateKey
	 * @param inputfilepath: file to be decrypted
	 * @param outputfilepath: file to save decrypted data
	 * @return void
	 */
	public static void decryptFile(String algo, String provider, Key key, String inputfilepath, String outputfilepath) throws Exception {
		cryptoFile(algo, provider, key, inputfilepath, outputfilepath, false);
	}
}
