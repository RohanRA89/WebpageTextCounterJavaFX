package application;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WebpageHandling {
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

		public void scrapeWebpage(String websiteLink) {
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
				String[] delimitedHeadingsText = allLowerCaseHeadingsText.split("[\\n|\\s|.|,]");
				List<String> convertedHeadingArrayList = Arrays.asList(delimitedHeadingsText);
				
				Elements boldedNames = doc.select("b");
				String allBoldedText = boldedNames.text();
				String allLowerCaseBoldText = allBoldedText.toLowerCase();
				String[] delimtedBoldedText = allLowerCaseBoldText.split("[\\n|\\s|.|,]");
				List<String> convertedBoldArrayList = Arrays.asList(delimtedBoldedText);
				
				Elements spokenLines = doc.select("a[name]");
				String allSpokenText = spokenLines.text();
				String allLowerCaseSpokenText = allSpokenText.toLowerCase();
				String[] delimitedSpokenText = allLowerCaseSpokenText.split("[\\n|\\s|.|,|!|'|;|?|-]");
				List<String> convertedSpokenArrayList = Arrays.asList(delimitedSpokenText);
				//end extract text
				
				//merge all lists
				List<String> mergedList = new ArrayList<>(convertedHeadingArrayList);
				mergedList.addAll(convertedBoldArrayList);
				mergedList.addAll(convertedSpokenArrayList);
				
				//use sort method.
				countRepeatedStrings(mergedList);
				
				//Left this test code as notes so I could reference how this worked. Have not worked with the Jsoup library before.
				//Could be important if a future developer was curious how the libaray wored.
				
				//Different test using Jsoup testing out how to single out each element.
				//Debated on making this program separate out by the tags after inspecting the HTML
				//elements on the MacBeth Webpage.
				
				//Testing out parsing by different html tags
				
				/*System.out.println("--Begin Act/Scene Parse--");
				//Elements gives us an arraylist of all <h3></h3>heading tags in this example
				//Elements headings = doc.select("h3");
				//Testing what values get printed out when using this Element search
				//Printing them out then separating them by value.
				//System.out.println(headings);
				//System.out.println("--End Act/Scene Parse--");*/
				
				/*System.out.println("--Begin Speaker Name Parse--");
				Elements boldedNames = doc.select("b");
				System.out.println(boldedNames);
				System.out.println("--End Speaker Name Parse--");*/
				
				
				/*System.out.println("--Begin Spoken Lines Parse--");
				Elements spokenLines = doc.select("a[name]");
				System.out.println(spokenLines);
				System.out.println("--End Spoken Lines Parse--");*/
				
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
		public static void countRepeatedStrings(List<String> list) {
			//Creating a HashMap to create a K,V of word, wordCount
			Map<String, Integer> hashMap = new HashMap<String,Integer>();
			
			for(String word : list) {
				Integer j = hashMap.get(word);
				hashMap.put(word, (j==null) ? 1 : j+1);
				
			}
			
			//Creating my own Comparator method from something I found online which compared two strings
			//but I was able to modify it to our String/Integer HashMap/Entry system
			Comparator<Entry<String, Integer>> valueComparator = new Comparator<Entry<String,Integer>>(){

				@Override
				public int compare(Entry<String, Integer> mapOne, Entry<String, Integer> mapTwo) {
					Integer valOne = mapOne.getValue();
					Integer valTwo = mapTwo.getValue();
					return valTwo.compareTo(valOne);
				}
				
			};
			//Create new list to store the sorted entry set.
			List<Entry<String,Integer>> listOfEntries = new ArrayList<Entry<String,Integer>>(hashMap.entrySet());
			//sort using created compare method.
			Collections.sort(listOfEntries, valueComparator);
			int counter = 1;
			for(Map.Entry<String, Integer> values : listOfEntries) {
					//Causing output error but I'm not sure why. It must be counting blank spaces left by the scrapped text and then adding that to my 
					//Output text.
					System.out.println(counter +") " + values.getKey() +" -> " + values.getValue());
					counter++;
				}
		}
}
