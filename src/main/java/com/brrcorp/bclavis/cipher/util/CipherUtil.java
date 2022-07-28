package com.brrcorp.bclavis.cipher.util;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;

import org.springframework.stereotype.Component;

@Component
public class CipherUtil {

	public String byte2Base64(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}

	public byte[] base642Byte(String base64Key) {
		return Base64.getDecoder().decode(base64Key);
	}

	public String encodeEncryptKey(String content, Key key, String transformation) throws Exception {
		byte[] encryptKey = encryptKey(content.getBytes(), key, transformation);

		return byte2Base64(encryptKey).replaceAll("\r\n", "");
	}

	public String decryptKey(byte[] source, Key key, String transformation) throws Exception {
		return new String(cipherKey(Cipher.DECRYPT_MODE, source, key, transformation));
	}

	private byte[] encryptKey(byte[] content, Key key, String transformation) throws Exception {
		return cipherKey(Cipher.ENCRYPT_MODE, content, key, transformation);
	}

	private byte[] cipherKey(int cipherMode, byte[] input, Key key, String transformation) throws Exception {
		Cipher cipher = Cipher.getInstance(transformation);
		cipher.init(cipherMode, key);
		
		return cipher.doFinal(input);
	}
}
