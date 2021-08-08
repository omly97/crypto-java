package oumarly;

import java.util.ArrayList;

import oumarly.app.model.KeyPairModel;
import oumarly.app.model.SecretKeyModel;
import oumarly.controllers.CryptoController;
import oumarly.controllers.KeysController;

public class Test {

	public static void main(String[] args) {
		try {
			KeysController keysController = new KeysController();
			keysController.createSecretKey("CLE AES 01", "AES", 128);
			keysController.createKeyPair("RSA 001", "RSA", 2048);
			ArrayList<SecretKeyModel> secretKeyModels = keysController.fetchSecretKeys();
			ArrayList<KeyPairModel> keyPairModels = keysController.fetchKeyPairs();
			
			
			CryptoController cryptoController = new CryptoController();
			
			for (SecretKeyModel secretKeyModel : secretKeyModels) {
				cryptoController.symmetricCryptFile(secretKeyModel, "src/mtdsi/aesClair", "src/mtdsi/aesChiffre");
				cryptoController.symmetricDecryptFile(secretKeyModel, "src/mtdsi/aesChiffre", "src/mtdsi/aesDechiffre");
			}
			System.out.println("AES ok");
			
			for (KeyPairModel keyPairModel : keyPairModels) {
				cryptoController.rsaCryptFile(keyPairModel, "src/mtdsi/rsaClair", "src/mtdsi/rsaChiffre");
				cryptoController.rsaDecryptFile(keyPairModel, "src/mtdsi/rsaChiffre", "src/mtdsi/rsaDechiffre");
			}
			System.out.println("RSA ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
