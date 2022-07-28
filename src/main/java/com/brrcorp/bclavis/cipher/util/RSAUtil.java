package com.brrcorp.bclavis.cipher.util;

import static com.brrcorp.bclavis.cipher.constants.CipherConstants.*;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RSAUtil {
	private static KeyFactory keyFactory;

	@NonNull
	private CipherUtil cipherUtil;

	public KeyPair getKeyPair() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
		keyPairGenerator.initialize(RSA_KEY_SIZE);
		return keyPairGenerator.generateKeyPair();
	}

	public String getPublicKey(KeyPair keyPair) {
		return cipherUtil.byte2Base64(keyPair.getPublic().getEncoded());
	}

	public String getPrivateKey(KeyPair keyPair) {
		return cipherUtil.byte2Base64(keyPair.getPrivate().getEncoded());
	}

	public PublicKey string2PublicKey(String publicKeyStr) throws Exception {
		return (PublicKey)string2Key(publicKeyStr, PUBLIC);
	}

	public PrivateKey string2PrivateKey(String privateKeyStr) throws Exception {
		return (PrivateKey)string2Key(privateKeyStr, PRIVATE);
	}

	private Key string2Key(String key, String keySpec) throws Exception {
		byte[] keyBytes = cipherUtil.base642Byte(key);
		KeyFactory keyFactory = getKeyFactory();

		return StringUtils.equals(keySpec, PRIVATE) ?
				keyFactory.generatePrivate(new PKCS8EncodedKeySpec(keyBytes))
				: keyFactory.generatePublic(new X509EncodedKeySpec(keyBytes));
	}

	private KeyFactory getKeyFactory() throws Exception {
		return keyFactory == null ? KeyFactory.getInstance(RSA) : keyFactory;
	}
}
