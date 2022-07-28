package com.brrcorp.bclavis.cipher.bo;

import java.security.KeyPair;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.brrcorp.bclavis.cipher.entity.RSAKey;
import com.brrcorp.bclavis.cipher.model.DecryptRequest;
import com.brrcorp.bclavis.cipher.model.EncryptResponse;
import com.brrcorp.bclavis.cipher.util.AESUtil;
import com.brrcorp.bclavis.cipher.util.HttpEncryptUtil;
import com.brrcorp.bclavis.cipher.util.RSAUtil;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CipherBOTest {
	@Autowired // spy bean
	private HttpEncryptUtil httpEncryptUtil;

	@Autowired
	private RSAUtil rsaUtil;

	@Autowired
	private AESUtil aesUtil;

	@Test
	@Transactional
	public void decrypt() throws Exception {
		// given
		DecryptRequest decryptRequest = DecryptRequest.builder()
				.aesKey("c283OgYePudU97GBRRBIkw==")
				.publicKeyStr(
						"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjOQNfvrcCORH2rJ6Bb/7f1O8sgNWx0P7UpQgh++2vOW/qfX4N1Q18tESJGp6qI/Iom18hMUsEG1pvFv2iGJwU9Uo963r1JWoKmxYXe/8s/fuUsIVwX/pilS0gZVNEAItjD3k242Mw3sWgbdyVH9xlrsoq11hrO3wmcv505kSKqyenQNm177u9qm+VDEXS2j++6GENL6aR0xURGcchXDq1wcKAq9hE2mvx78Cv4T+HGW4SiVEDUyRmJQzeRM0hAbjCYPfiEGxnROWQ5k+l7JybdScR7hHn4cTt9JTx7aBFKUvRAYBej61zfdP9nyfEexVnQeUf82ZCyFBj7UaU3kGLwIDAQAB")
				.encryptedContent("iIE87aS37Um8plrD5QOftBW541hvuCiLJ/qJuU9bP3U=")
				.privateKeyStr(
						"MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCM5A1++twI5EfasnoFv/t/U7yyA1bHQ/tSlCCH77a85b+p9fg3VDXy0RIkanqoj8iibXyExSwQbWm8W/aIYnBT1Sj3revUlagqbFhd7/yz9+5SwhXBf+mKVLSBlU0QAi2MPeTbjYzDexaBt3JUf3GWuyirXWGs7fCZy/nTmRIqrJ6dA2bXvu72qb5UMRdLaP77oYQ0vppHTFREZxyFcOrXBwoCr2ETaa/HvwK/hP4cZbhKJUQNTJGYlDN5EzSEBuMJg9+IQbGdE5ZDmT6XsnJt1JxHuEefhxO30lPHtoEUpS9EBgF6PrXN90/2fJ8R7FWdB5R/zZkLIUGPtRpTeQYvAgMBAAECggEAaxTBNStQpOlru0o+97PMYB+yoNFs2RJQJ5kxiF+m0bcEjxbFSrgUMLK6STcsdoShhuIT6404Slv+KwzyOQIoQW8XLfff77i3EVc7aloGBHqBhiDBDEo9o8dxUbO52FrvZ1F7aWsR+1jH6FsW6CXcnSUuaeAVZquJXDrta+dBKXDYmOC4xqNcelZp1nij4jLMmiGY9l7gu0nVGixBleZTeOXDGc8QZ07F+mi2h/jzs66jXl9WK9PBETMU6SLjgyNIeYgdiju44nuHRuxgU6+OvLMin1rEi3TD2a1kYXPqEL8C1nvq/A8OiR/YDlaRMvQ7zJLGWJb3v5svk2sBdPbMMQKBgQDf5tlqZRSzEERbGKp9e/3zsonCFTV5MlhMnm2p9lR5y593/SQSNoqO4qaxdP3Enerw6yy8rRLaf92gRLQ8JhcUEOHQEO+G/t5ail//2dx51CQqAyZWhpyUBNkSbAtWrR9fxH1weYEDG/0YHjy2eAyiMqmiNVMjjKP4ij9QE7SrnQKBgQChFrf+PWCr2cyzmZZGSd577dBQLhIx4IGvAG71fi6Tuuck0bIcRG/20Q67jXKTXFIO201+I6lfPy05w+3q8T3ryh4FN1Ep5bzgg93mIB1Q3YV7vhziZam76V0yxItau66e3Dnkqg6260K7GNxRMWZ/Hyw2LVc7Gyh009xUVpCNOwKBgDb5PCqGVPW+KbpgLQQQXILoNDCl+nIebVWqlGxlocoht67DRYb/Qzff6GOrwN70kwnCBELzD6z8NhMyqzoVdBcmKftnHRh9HWUHkpiECkETWCnEijY2aN1i71U5l/MZIJnn6ZCjFI0J4zN3wRpgEWIFpGbCRv50epH8h50GqZZ1AoGAbiUFm65GCls671bF/LuHh79c32YRBM+YfJtUQ0GLAXIbl/5VxaQ51k6tvyoBPKc0aHok62ng5oJhHw1MAYgAKdgiXLitI3O1iOJBeJde1CsTmT+tqlOPEjCHjC39hwWraW31m9d3hjUohJuEEXQwvB0JcuXfrkdsiy1NitFBt38CgYEAidT2GuJqil2Ln75gffW7z7jdKkzAn5sr3GIxLlvU9oB74UxiCIskY4nPtNyaEvyX+odA4iblwKcFzRfakCw3FjcDndrAbqnUvjivyIWVzM81HzyiS+wFwTMCrdToXHXIk8AvPvyI/zHuLqwWil8Qgi5RN/uPXbwq4IbFwoNHT34=")
				.build();

		// when
		String content = httpEncryptUtil.appDecrypt(decryptRequest);

		// then
		Assertions.assertNotNull(content);
	}

	@Test
	public void encrypt() throws Exception {
		// given
		RSAKey rsaKey = createRSAKey();
		String content = "test content";

		// when
		EncryptResponse encryptResponse = httpEncryptUtil.serverEncrypt(rsaKey, content);

		// then
		Assertions.assertNotNull(encryptResponse.getEncryptedContent());
		Assertions.assertNotNull(encryptResponse.getAesKey());
		Assertions.assertNotNull(encryptResponse.getPublicKey());
	}

	private RSAKey createRSAKey() throws Exception {
		KeyPair keyPair = rsaUtil.getKeyPair();
		String privateKey = rsaUtil.getPrivateKey(keyPair);
		String publicKey = rsaUtil.getPublicKey(keyPair);
		String aesKey = aesUtil.genKeyAES();

		return new RSAKey(aesKey, privateKey, publicKey);
	}
}
