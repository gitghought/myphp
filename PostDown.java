package testdown;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.text.html.parser.Entity;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class PostDown {
	public void sendPost(String url, String param) throws ClientProtocolException, IOException {
		HttpPost objPost = new HttpPost(url);

		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse objEXE = client.execute(objPost);
		
		long len = objEXE.getEntity().getContentLength();
		
		System.out.println("len = " + len);
		System.out.println("\r\n");

		
		InputStream is = objEXE.getEntity().getContent();
		
		File localFile = new File("./MainActivity-debug.apk");
		FileOutputStream fos = new FileOutputStream(localFile);
		byte[] buf = new byte[1024];
		while (true) {
			int cont = is.read(buf);
			if (cont == -1) {
				break;	
			}
			
			System.out.println("........");
			fos.write(buf, 0, cont);
		}
		
		fos.close();
		is.close();

		return; 

	}

	public static void main(String[] args) {
		PostDown pd = new PostDown();
		String url = "http://192.168.1.109/down.php";

		String res = null;
		try {
			pd.sendPost(url, null);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(res);
	}
}
