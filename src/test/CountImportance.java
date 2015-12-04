package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountImportance {

	GetWebPage webPage;
	String expression;
	int importance=0;

	int mode = 0;

	public static void main(String args[]){
		CountImportance ci = new CountImportance();
		//ci.setExpression("title");
		ci.setExpression("a");
		ci.patternMatch();


	}


	public void setExpression(String expression){
		this.expression = expression;
	}

	public void setExpressionInput(){
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		try {
			System.out.println("取得したいWebページのタグを入力");
			expression = br.readLine();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public void patternMatch(){
		if(webPage == null){
			try {
				webPage = new GetWebPage(
						new URL("https://ja.wikipedia.org/wiki/Wikipedia:%E3%83%87%E3%83%BC%E3%82%BF%E3%83%99%E3%83%BC%E3%82%B9%E3%83%80%E3%82%A6%E3%83%B3%E3%83%AD%E3%83%BC%E3%83%89"));

				Pattern elementPattern;


			/*
					System.out.println("ゼロ");
					elementPattern = Pattern.compile(
						     "<" + expression + ".*?>(.+?)</" + expression + ">",
						     Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

*/


				elementPattern = Pattern.compile(
						"<"+expression + " href="+"\""+"/wiki/Wikipedia"+ ".*?>(.+?)</" + expression + ">",
						Pattern.CASE_INSENSITIVE | Pattern.DOTALL);




				Matcher matcher = elementPattern.matcher(webPage.getResponse().toString());
				/*String regex = ">" + "(.+?)" + "<";
				Pattern tag = Pattern.compile(regex);*/



				while(matcher.find()){
					String content = "";
					//if(mode == 0){
						content = matcher.group(1);
					/*}else{
						String contents = matcher.group();
						content = getTitle(tag,contents);
					}*/


					System.out.println(content);
					importance++;
				}
			} catch (MalformedURLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

		System.out.println("キーワード固有重要度は"+importance);


	}

	public String getTitle(Pattern tag, String contents){
		Matcher title;
		String content;

		title = tag.matcher(contents);
		if(title.find()){
			int start = title.start();
			int end = title.end();
			content = contents.substring(start+1,end-1);
			//content = "スタート:" + start + " エンド" + end;
		}else{
			content = "タイトルが取得できませんでした";
		}
		return content;

	}


}
