package oumarly.controllers;

import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;

import oumarly.app.crypto.MyCipher;
import oumarly.app.model.KeyPairModel;
import oumarly.app.model.SecretKeyModel;

public class CryptoController {

	public void symmetricCryptFile(SecretKeyModel KeyModel, String inputfile, String outputfile) throws Exception {
		MyCipher.cryptFile(KeyModel.getAlgorithme(), KeyModel.getSecretKey(), inputfile, outputfile);
	}

	public void symmetricDecryptFile(SecretKeyModel KeyModel, String inputfile, String outputfile) throws Exception {
		MyCipher.decryptFile(KeyModel.getAlgorithme(), KeyModel.getSecretKey(), inputfile, outputfile);
	}


	public void rsaCryptFile(KeyPairModel pairModel, String inputfile, String outputfile) throws Exception {
		MyCipher.cryptFile(pairModel.getAlgorithme(), (RSAPublicKey) pairModel.getPublicKey(), inputfile, outputfile);
	}
	
	public void rsaDecryptFile(KeyPairModel pairModel, String inputfile, String outputfile) throws Exception {
		MyCipher.decryptFile(pairModel.getAlgorithme(), (RSAPrivateKey) pairModel.getPrivateKey(), inputfile, outputfile);
	}


	public void dsaCryptFile(KeyPairModel pairModel, String inputfile, String outputfile) throws Exception {
		MyCipher.cryptFile(pairModel.getAlgorithme(), (DSAPublicKey) pairModel.getPublicKey(), inputfile, outputfile);
	}

	public void dsaDecryptFile(KeyPairModel pairModel, String inputfile, String outputfile) throws Exception {
		MyCipher.decryptFile(pairModel.getAlgorithme(), (DSAPrivateKey) pairModel.getPrivateKey(), inputfile, outputfile);
	}


	public void dhCryptFile(KeyPairModel pairModel, String inputfile, String outputfile) throws Exception {
		MyCipher.cryptFile(pairModel.getAlgorithme(), (DHPublicKey) pairModel.getPublicKey(), inputfile, outputfile);
	}
	
	public void dhDecryptFile(KeyPairModel pairModel, String inputfile, String outputfile) throws Exception {
		MyCipher.decryptFile(pairModel.getAlgorithme(), (DHPrivateKey) pairModel.getPrivateKey(), inputfile, outputfile);
	}
}
