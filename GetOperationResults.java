package ComputerVisionOCR.ComputerVisionOCR;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class GetOperationResults {

	public void getOcrOutput(String subscriptionKey, String operationId, String imageToAnalyze) {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
			String url = "https://eastus.api.cognitive.microsoft.com/vision/v2.0/textOperations/" + operationId;
			System.out.println(url);
			URIBuilder uriBuilder = new URIBuilder(url);

			// uriBuilder.setParameter("language", "unk");
			// uriBuilder.setParameter("detectOrientation", "true");
			uriBuilder.setParameter("operationId", operationId);

			// Request parameters.
			URI uri = uriBuilder.build();
			HttpGet request = new HttpGet(uri);

			// Request headers.
			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

			// Request body.
			// StringEntity requestEntity = new StringEntity("{\"url\":\"" +
			// imageToAnalyze + "\"}");
			// request.setEntity(requestEntity);

			// Call the REST API method and get the response entity.
			HttpResponse response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();
			System.out.println(entity);
			// HttpEntity entity = response.getEntity();
			if (entity != null) {
				String jsonString = EntityUtils.toString(entity);
				JSONObject json = new JSONObject(jsonString);
				System.out.println("REST Response:");
				System.out.println(json);
			}
		} catch (Exception e) {
			// Display error message.
			e.printStackTrace();
		}
	}
}
