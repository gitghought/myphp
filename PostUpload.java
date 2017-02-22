package testupload;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class PostUpload {

	public void uploadedAPKFile(String url, String path)
			throws ClientProtocolException, IOException {
		File file = new File(path);
		if (file.exists() == false) {
			System.out.println("failed");
		}

		HttpClient client = new DefaultHttpClient();
		HttpPost postOBJ = new HttpPost(url);

		FileBody fBody = new FileBody(file,
				"application/vnd.android.package-archive");
		MultipartEntity entity = new MultipartEntity();
		entity.addPart("application", fBody);
		postOBJ.setEntity(entity);

		try {
			HttpResponse response = client.execute(postOBJ);

			int statusCode = response.getStatusLine().getStatusCode();
			String result = EntityUtils.toString(response.getEntity(), "utf-8");

			System.out.println("statuscode = " + statusCode);
			System.out.println("result = " + result);

			if (statusCode == 201) {
				System.out.println("successed");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return;
	}

	/**
	 * 
	 * @param url
	 * @param path
	 *            :file path
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public void uploadedImgFile(String url, String path)
			throws ClientProtocolException, IOException {
		File file = new File(path);
		if (file.exists() == false) {
			System.out.println("failed");
		}

		HttpClient client = new DefaultHttpClient();
		HttpPost postOBJ = new HttpPost(url);

		FileBody fBody = new FileBody(file);
		MultipartEntity entity = new MultipartEntity();
		entity.addPart("file", fBody);
		postOBJ.setEntity(entity);

		try {
			HttpResponse response = client.execute(postOBJ);

			int statusCode = response.getStatusLine().getStatusCode();
			String result = EntityUtils.toString(response.getEntity(), "utf-8");

			System.out.println("statuscode = " + statusCode);
			System.out.println("result = " + result);

			if (statusCode == 201) {
				System.out.println("successed");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return;
	}

	public static void main(String[] args) {
		try {
			// new
			// PostUpload().uploadedImgFile("http://192.168.1.109/upload_img.php",
			// "./a.jpg");
			// new
			// PostUpload().uploadedImgFile("http://192.168.1.109/upload_img.php",
			// "./MainActivity-debug.apk");
			new PostUpload().uploadedImgFile(
					"http://192.168.1.109/upload.php",
					"./MainActivity-debug.apk");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
