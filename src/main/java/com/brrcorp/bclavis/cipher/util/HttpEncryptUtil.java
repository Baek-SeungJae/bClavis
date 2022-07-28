package com.brrcorp.bclavis.cipher.util;

import static com.brrcorp.bclavis.cipher.constants.CipherConstants.*;

import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.brrcorp.bclavis.cipher.entity.RSAKey;
import com.brrcorp.bclavis.cipher.model.DecryptRequest;
import com.brrcorp.bclavis.cipher.model.EncryptResponse;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class HttpEncryptUtil {
	private RSAUtil rsaUtil;
	private AESUtil aesUtil;
	private CipherUtil cipherUtil;

	public String appDecrypt(DecryptRequest decryptRequest) throws Exception {
		PublicKey appPublicKey = rsaUtil.string2PublicKey(decryptRequest.getPublicKeyStr());
		String encodedAesKey = cipherUtil.encodeEncryptKey(decryptRequest.getAesKey(), appPublicKey, RSA);
		byte[] encryptAesKey = cipherUtil.base642Byte(encodedAesKey);
		byte[] encryptContent = cipherUtil.base642Byte(decryptRequest.getEncryptedContent());

		PrivateKey appPrivateKey = rsaUtil.string2PrivateKey(decryptRequest.getPrivateKeyStr());
		String aesKeyBytes = cipherUtil.decryptKey(encryptAesKey, appPrivateKey, RSA);
		SecretKey aesKey = aesUtil.loadKeyAES(aesKeyBytes);

		return cipherUtil.decryptKey(encryptContent, aesKey, AES);
	}

	public EncryptResponse serverEncrypt(RSAKey rsaKey, String content) throws Exception {
		SecretKey aesKey = aesUtil.loadKeyAES(rsaKey.getAesKey());
		String encodedContent = cipherUtil.encodeEncryptKey(content, aesKey, AES);

		return new EncryptResponse(rsaKey.getAesKey(), rsaKey.getPublicKey(), encodedContent);
	}
}
