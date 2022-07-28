package com.brrcorp.bclavis.cipher.util;

import static com.brrcorp.bclavis.cipher.constants.CipherConstants.*;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AESUtil {
	private CipherUtil cipherUtil;

	public String genKeyAES() throws Exception {
		KeyGenerator keyGen = KeyGenerator.getInstance(AES);
		keyGen.init(AES_KEY_SIZE);
		SecretKey key = keyGen.generateKey();

		return cipherUtil.byte2Base64(key.getEncoded());
	}

	public SecretKey loadKeyAES(String base64Key) {
		byte[] bytes = cipherUtil.base642Byte(base64Key);
		
		return new SecretKeySpec(bytes, AES);
	}
}
