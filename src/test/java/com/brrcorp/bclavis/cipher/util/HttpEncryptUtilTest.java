package com.brrcorp.bclavis.cipher.util;

import static com.brrcorp.bclavis.cipher.constants.CipherConstants.*;

import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.brrcorp.bclavis.cipher.entity.RSAKey;
import com.brrcorp.bclavis.cipher.model.DecryptRequest;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class HttpEncryptUtilTest {
	@Autowired
	private RSAUtil rsaUtil;
	@Autowired
	private AESUtil aesUtil;
	@Autowired
	private CipherUtil cipherUtil;

	@Test
	public void appDecrypt() throws Exception {
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
		PublicKey appPublicKey = rsaUtil.string2PublicKey(decryptRequest.getPublicKeyStr());
		String encodedAesKey = cipherUtil.encodeEncryptKey(decryptRequest.getAesKey(), appPublicKey, RSA);
		byte[] encryptAesKey = cipherUtil.base642Byte(encodedAesKey);
		byte[] encryptContent = cipherUtil.base642Byte(decryptRequest.getEncryptedContent());

		PrivateKey appPrivateKey = rsaUtil.string2PrivateKey(decryptRequest.getPrivateKeyStr());
		String aesKeyBytes = cipherUtil.decryptKey(encryptAesKey, appPrivateKey, RSA);
		SecretKey aesKey = aesUtil.loadKeyAES(aesKeyBytes);

		String content = cipherUtil.decryptKey(encryptContent, aesKey, AES);

		// then
		Assertions.assertEquals("{\"retcode\":\"200\"}", content);
	}

	@Test
	public void serverEncrypt() throws Exception {
		// given
		RSAKey rsaKey = RSAKey.builder()
				.aesKey("jSUK49L6tDvubMREHvWq4A==")
				.privateKey(
						"MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCXGnBC+PgOkDVgsG/aAGIdy4WxY0Tjgp1YhxpviGM0X3Qk7/ihjbJz/vxYWvf52hfP4BG59wEyrRF26grNAy8bNpw1Wzr5zEEHgxfb5dvi5lXXUCtUfsdYb0eQgCyj77oD/LebLxoq917h3ShA5O12gkHo1GLFVFp9pYZIkVvLRyxtUk6Dkhqr22M9W3PK+MTISBChQymrtz6JMJuPT98k81ej1I4Cf1ZZ3kumNFC0lJllELSApyd7mIY4yCOZcXwY7zHTJY4dMmLOCF8W0w2Oh11KOXbteudraRNFVIHC2pha+lQJFar9U94EDzNUHjRs1n2BN9ylqKyhtmPiWvLDAgMBAAECggEBAIb92q8qZ8ny8Yzo83RKBV3i9hSMv/2VMebmBCsCKMagRE2/iVsjEPtqIJz64DD5s25GGvANiKdyUHjxDCctogbo2dMdJuoFnU8NweH1T2U9LBFIkP1SqdsKx1+D8t2ejIuFWa6s/O7Hwp98Cbs4CiFmq2VLYNYzzwgTOY1ZNy25jOViHNjGa2oCiviLSq88coqQpLuDun3rgrjHgyBPPKFMHwIsLgee0sYQ9Fmc59gpRMOFZsl6A7TaGP6yvmrs4GHK+9V+9UavcyhgFAW/1PlLfoSHTdBrtCEjlyfOOCISPhzyG4jKm+Uu5gsv8+OWGzy3WOKMkHfuMcB/Llt2lqkCgYEA314M5EcNjV/rXCWId98IG1hxNwurZHo+NiJfrXUkQ+yDCNDN+xXJ8vCN0gtkdQeBvrcxS2t/NZtOWac3TBpR0x8SUNJRJEbMDlfs9MTavAZEdbJpoWNuBWUWvzGhwpCPb3Sl8fWIQaphUEQ19i960tyb9a24GHLmTReRKbN2sf0CgYEArS21GkvTDIXk37BFdQAABwhUmnjrxL+xq4+hq05GU4zOLGLOmzK3PZGV3QAKcAbyrZHrT5H3nIge+/CKOu5QCzQdYd1cl89/NIoH9xGWQn65G+480/ZHOdPh+4eVNQgSbm3MhYhcT4g3p3sLX10TxkqRvE8BJVRA3SohzegN878CgYEAouGEaRpfCY6+pC1CfJcI5Ff0Bv3dd6Gkp/kc5/IaBeX8D3ukGMpkv2EnKdng4W4d8KLJsYOr+b1MvxeuvTIcJOXthz42qUtvFa6MPlkmYCH53hHB8Uv+OLTkh3t7x9N+pPnc4Ln5KFlEKTH0X5dz6zuwBtEtA7cVtXXQBya1EUkCgYBsOh1tMqfRsrfHZ8TwirB5tKYS57EIg3hIrHmvsjpXKq8KpDFUYoyLyLkspULgoib1fDTfxGxHgfZDIx9wFtElP0uBhrLAGSf5MK4rpO0P31xV7TxMhyQaKqWW6TPBMas7Ihln9kEiXxHQ9B+2WFfsW1vaoFEqAA6NaTA5G2XkxwKBgEEhRhPFclIWXli0NmBIRZdVCMuXIzqhcvtRjiwNz+jSrwBNw1oqVmUWRWVW9EWj8ofHOoNqmrfzZtOxm0YXAhCFur/lAY4v4nwQGLS8OxNEv1GPSyTEYaFAHlDRVOmZxkU6TmlavY/Nbu/DCIIWAMnz+/r1FQ+oVG5jbHp7Kw7w")
				.publicKey(
						"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlxpwQvj4DpA1YLBv2gBiHcuFsWNE44KdWIcab4hjNF90JO/4oY2yc/78WFr3+doXz+ARufcBMq0RduoKzQMvGzacNVs6+cxBB4MX2+Xb4uZV11ArVH7HWG9HkIAso++6A/y3my8aKvde4d0oQOTtdoJB6NRixVRafaWGSJFby0csbVJOg5Iaq9tjPVtzyvjEyEgQoUMpq7c+iTCbj0/fJPNXo9SOAn9WWd5LpjRQtJSZZRC0gKcne5iGOMgjmXF8GO8x0yWOHTJizghfFtMNjoddSjl27Xrna2kTRVSBwtqYWvpUCRWq/VPeBA8zVB40bNZ9gTfcpaisobZj4lrywwIDAQAB")
				.build();
		String content = "test plain content";

		// when
		SecretKey aesKey = aesUtil.loadKeyAES(rsaKey.getAesKey());
		String encodedContent = cipherUtil.encodeEncryptKey(content, aesKey, AES);

		// then
		Assertions.assertNotNull(encodedContent);
	}
}
