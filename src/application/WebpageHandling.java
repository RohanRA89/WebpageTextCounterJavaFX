package application;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WebpageHandling {
	//private static boolean ASCENDING = true;
		private static boolean DESCENDING = false;
		Map<String, Long> sortedMapAsc;
		private String webAddress;

		public String getWebAddress() {
			return webAddress;
		}

		public void setWebAddress(String webAddress) {
			this.webAddress = webAddress;
		}
		
		
		
		public WebpageHandling(String webAddress) {
			super();
			this.webAddress = webAddress;
		}

		public String scrapeWebpage(String websiteLink) {
			Document doc = null;
			
			try {
				//Decided to try and use the Jsoup library/jar to try and run this program.
				doc = Jsoup.connect(websiteLink).get();
				
				/* Removing the following String manipulation from the sections below will separate the list in to upper and lowercase if needed.
				String allLowerCaseHeadingsText = allHeadingsText.toLowerCase();
				String allLowerCaseBoldText = allBoldedText.toLowerCase();
				String allLowerCaseSpokenText = allSpokenText.toLowerCase();
				*/
				
				//begin extract text
				Elements headings = doc.select("h3");
				String allHeadingsText = headings.text();
				String allLowerCaseHeadingsText = allHeadingsText.toLowerCase();
				String[] delimitedHeadingsText = allLowerCaseHeadingsText.trim().split("[\\n|\\s+|.|,|:]");
				List<String> convertedHeadingArrayList = Arrays.asList(delimitedHeadingsText);
				
				Elements boldedNames = doc.select("b");
				String allBoldedText = boldedNames.text();
				String allLowerCaseBoldText = allBoldedText.toLowerCase();
				String[] delimtedBoldedText = allLowerCaseBoldText.trim().split("[\\n|\\s+|.|,|:]");
				List<String> convertedBoldArrayList = Arrays.asList(delimtedBoldedText);
				
				Elements spokenLines = doc.select("a[name]");
				String allSpokenText = spokenLines.text();
				String allLowerCaseSpokenText = allSpokenText.toLowerCase();
				String[] delimitedSpokenText = allLowerCaseSpokenText.trim().split("[\\n|\\s+|.|,|!|'|;|?|-|,|:]");
				List<String> convertedSpokenArrayList = Arrays.asList(delimitedSpokenText);
				//end extract text
				
				//merge all lists
				List<String> mergedList = new ArrayList<>(convertedHeadingArrayList);
				mergedList.addAll(convertedBoldArrayList);
				mergedList.addAll(convertedSpokenArrayList);
				
				
				Map<String, Long> wordCount = mergedList.stream()
											.collect(Collectors.groupingBy(element -> element,
											Collectors.counting()));
				
				  //System.out.println("Word Frequencies");
			        sortedMapAsc = sortByValue(wordCount, DESCENDING);
			        //printMap(sortedMapAsc);
			        
			        //Map as String
			        //System.out.println("Map as String Method");
			        
			        
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return convertWithIteration(sortedMapAsc);

		}
		
		//Method used to sort our Map by values.
		//Using Java 8 stream as opposed to the different methods I had set up earlier. These methods were not
		//good algorithms increasing the computing time.
		private static Map<String, Long> sortByValue(Map<String, Long> unsortMap, final boolean order)
	    {
	        List<Entry<String, Long>> list = new LinkedList<>(unsortMap.entrySet());

	        // Sorting the list based on values
	        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
	                ? o1.getKey().compareTo(o2.getKey())
	                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
	                ? o2.getKey().compareTo(o1.getKey())
	                : o2.getValue().compareTo(o1.getValue()));
	        return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));

	    }
		
		//Print to console method
	    /*private static void printMap(Map<String, Long> map)
	    {
	        map.forEach((key, value) -> System.out.println(key + " = " + value));
	    }
		*/
	    public static String convertWithIteration(Map<String, ?> map) {
	        StringBuilder mapAsString = new StringBuilder("");
	        for (String key : map.keySet()) {
	            mapAsString.append(key + " = " + map.get(key) + "\n");
	        }
	        mapAsString.delete(mapAsString.length()-2, mapAsString.length()).append("");
	        return mapAsString.toString();
	    }
}
