package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GetWebPage {
	public static void main(String args[]){
		GetWebPage gwp = new GetWebPage();
		gwp.setURLInput();
		gwp.getWebPage();
		gwp.displayWebPage();



	}

	URL url;
	static StringBuffer response;

	GetWebPage(){

	}

	GetWebPage(URL url){
		this.url = url;
		getWebPage();
	}

	public StringBuffer getResponse(){
		return response;
	}

	public void setURLInput(){
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		try {
			System.out.println("取得したいWebページのURLを入力");
			url = new URL(br.readLine());
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public void setURL(String newUrl){
		try {
			url = new URL(newUrl);
		} catch (MalformedURLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public void getWebPage(){
		URLConnection conn = null;
		try {
			conn = url.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:42.0) Gecko/20100101 Firefox/42.0");
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		/*String charset = Arrays.asList(conn.getContentType().split(";") ).get(1);
	    String encoding = Arrays.asList(charset.split("=") ).get(1);
	    */
	    BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		response = new StringBuffer();
		String inputLine;

		try {
			while((inputLine = in.readLine()) != null)
				response.append(inputLine+"\n");

			in.close();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	void displayWebPage(){

		System.out.println("webページのソースを出力します");
		System.out.print(response.toString());




	}
}
