package oumarly.controllers;

import java.security.KeyPair;
import java.util.ArrayList;

import javax.crypto.SecretKey;

import oumarly.app.crypto.MyKey;
import oumarly.app.dao.KeyPairDao;
import oumarly.app.dao.SecretKeyDao;
import oumarly.app.model.KeyPairModel;
import oumarly.app.model.SecretKeyModel;

public class KeysController {

	private SecretKeyDao secretKeyDao;
	private KeyPairDao keyPairDao;
	
	public KeysController() {
		secretKeyDao = new SecretKeyDao();
		keyPairDao = new KeyPairDao();
	}

	
	public void createSecretKey(String nom, String algo, int taille) throws Exception {
		SecretKey secretKey = MyKey.getSecretKey(algo, taille);
		SecretKeyModel secretKeyModel = new SecretKeyModel(nom, secretKey);
		secretKeyDao.store(secretKeyModel);
	}
	
	public ArrayList<SecretKeyModel> fetchSecretKeys() throws Exception {
		return secretKeyDao.all();
	}
	
	public void deleteSecretKey(SecretKeyModel secretKeyModel) throws Exception {
		secretKeyDao.delete(secretKeyModel);
	}


	public void createKeyPair(String nom, String algo, int taille) throws Exception {
		KeyPair keyPair = MyKey.getKeyPair(algo, taille);
		KeyPairModel keyPairModel = new KeyPairModel(nom, algo, keyPair);
		keyPairDao.store(keyPairModel);
	}
	
	public ArrayList<KeyPairModel> fetchKeyPairs() throws Exception {
		return keyPairDao.all();
	}
	
	public void deleteKeyPair(KeyPairModel keyPairModel) throws Exception {
		keyPairDao.delete(keyPairModel);
	}
}
