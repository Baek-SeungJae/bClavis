package com.brrcorp.bclavis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.brrcorp.bclavis.cipher.bo.CipherBO;
import com.brrcorp.bclavis.cipher.model.DecryptRequest;
import com.brrcorp.bclavis.cipher.model.DecryptResponse;
import com.brrcorp.bclavis.cipher.model.EncryptResponse;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BClavisApplicationTests {
	@Autowired
	CipherBO cipherBO;

	@Test
	public void fullTest() throws Exception {
		// given
		String content = "{\"retcode\":\"200\"}";

		// when
		EncryptResponse encryptResponse = cipherBO.encrypt(content);

		// given
		DecryptRequest decryptRequest = DecryptRequest.builder()
				.aesKey(encryptResponse.getAesKey())
				.publicKeyStr(encryptResponse.getPublicKey())
				.encryptedContent(encryptResponse.getEncryptedContent())
				.build();

		// when
		DecryptResponse result = cipherBO.decrypt(decryptRequest);

		System.out.println(result.getContent());
		assert content.equals(result.getContent());


		/* TODO : 설계부터 다시해야 함
		 member와 aesKey의 관계를 어떻게 가져갈건지
		 publicKey는 어떻게 보관할건지
		 privateKey도
		*/
	}
}
