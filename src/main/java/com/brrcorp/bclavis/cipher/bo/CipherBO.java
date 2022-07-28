package com.brrcorp.bclavis.cipher.bo;

import java.security.KeyPair;

import org.springframework.stereotype.Service;

import com.brrcorp.bclavis.cipher.entity.RSAKey;
import com.brrcorp.bclavis.cipher.model.DecryptRequest;
import com.brrcorp.bclavis.cipher.model.DecryptResponse;
import com.brrcorp.bclavis.cipher.model.EncryptResponse;
import com.brrcorp.bclavis.cipher.repository.RSAKeyRepository;
import com.brrcorp.bclavis.cipher.util.AESUtil;
import com.brrcorp.bclavis.cipher.util.HttpEncryptUtil;
import com.brrcorp.bclavis.cipher.util.RSAUtil;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CipherBO {
	@NonNull
	private HttpEncryptUtil httpEncryptUtil;
	@NonNull
	private AESUtil aesUtil;
	@NonNull
	private RSAUtil rsaUtil;
	@NonNull
	private RSAKeyRepository rsaKeyRepository;

	public DecryptResponse decrypt(DecryptRequest decryptRequest) throws Exception {
		RSAKey rsaKey = rsaKeyRepository.findByAesKey(decryptRequest.getAesKey());
		decryptRequest.setPrivateKeyStr(rsaKey.getPrivateKey());
		String content = httpEncryptUtil.appDecrypt(decryptRequest);

		return DecryptResponse.builder()
				.content(content)
				.build();
	}

	public EncryptResponse encrypt(String content) throws Exception {
		RSAKey rsaKey = createRSAKey();
		rsaKeyRepository.save(rsaKey);

		return httpEncryptUtil.serverEncrypt(rsaKey, content);
	}

	private RSAKey createRSAKey() throws Exception {
		KeyPair keyPair = rsaUtil.getKeyPair();
		String privateKey = rsaUtil.getPrivateKey(keyPair);
		String publicKey = rsaUtil.getPublicKey(keyPair);
		String aesKey = aesUtil.genKeyAES();

		return new RSAKey(aesKey, privateKey, publicKey);
	}
}
