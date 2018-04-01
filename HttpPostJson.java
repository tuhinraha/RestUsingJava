import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.*;

public class HttpPostJson {

	public static void main(String[] args) throws Exception{

		
		//
		String url="https://httpbin.org/post";
		URL object=new URL(url);

		HttpURLConnection con = (HttpURLConnection) object.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("POST");

		JSONObject parent = new JSONObject();
		parent.put("auth", "tuhin");
		/*JSONObject cred   = new JSONObject();
		JSONObject auth   = new JSONObject();
		

		cred.put("username","adm");
		cred.put("password", "pwd");

		auth.put("tenantName", "adm");
		auth.put("passwordCredentials", cred);

		parent.put("auth", auth);*/

		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		wr.write(parent.toString());
		wr.flush();

		//display what returns the POST request

		StringBuilder sb = new StringBuilder();  
		int HttpResult = con.getResponseCode(); 
		if (HttpResult == HttpURLConnection.HTTP_OK) {
		    BufferedReader br = new BufferedReader(
		            new InputStreamReader(con.getInputStream(), "utf-8"));
		    String line = null;  
		    while ((line = br.readLine()) != null) {  
		        sb.append(line + "\n");  
		    }
		    br.close();
		    System.out.println("" + sb.toString());  
		} else {
		    System.out.println(con.getResponseMessage());  
		} 
		
		 JSONObject objectjson = (JSONObject) new JSONTokener(sb.toString()).nextValue();
		 String query = objectjson.getString("origin");
		 System.out.println(query);
		 //JSONArray locations = objectjson.getJSONArray("locations");
		/*JSONParser jsonParser = new JSONParser();
		JSONObject json = (JSONObject) jsonParser.parse(sb.toString());
		System.out.println(json.get("origin"));
		//System.out.println(json.get("headers"));
		JSONObject characters = (JSONObject) json.get("headers");
		System.out.println(characters);*/

	
	}

}
