package testing;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JunitTesting {
	public int countHeadingTagTotal(String websiteLink) {
		Document doc = null;
		
		try {
			int count = 0;
			//Decided to try and use the Jsoup library/jar to try and run this program.
			doc = Jsoup.connect(websiteLink).get();
			
			
			//begin extract text
			Elements headings = doc.select("h3");
			String allHeadingsText = headings.text();
			String allLowerCaseHeadingsText = allHeadingsText.toLowerCase();
			String[] trimmedHeadingList = allLowerCaseHeadingsText.trim().split("[\\n|\\s+|.|,|:]");
			count = trimmedHeadingList.length;
			return count;
			
		}
		catch(IOException e){
			e.printStackTrace();
			return -1;
		}
	}
	
	public int countBoldTagTotal(String websiteLink) {
		Document doc = null;
		
		try {
			int count = 0;
			//Decided to try and use the Jsoup library/jar to try and run this program.
			doc = Jsoup.connect(websiteLink).get();
	
			Elements headings = doc.select("b");
			String allHeadingsText = headings.text();
			String allLowerCaseHeadingsText = allHeadingsText.toLowerCase();
			String[] trimmedHeadingList = allLowerCaseHeadingsText.trim().split("[\\n|\\s+|.|,|:]");
			count = trimmedHeadingList.length;
			return count;
			
		}
		catch(IOException e){
			e.printStackTrace();
			return -2;
		}
	}
}
