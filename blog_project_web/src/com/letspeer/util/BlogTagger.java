package com.letspeer.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class BlogTagger {

	static String[] stopWord = { "a", "about", "above", "above", "across", "after", "afterwards", "again", "against",
			"all", "almost", "alone", "along", "already", "also", "although", "always", "am", "among", "amongst",
			"amoungst", "amount", "an", "and", "another", "any", "anyhow", "anyone", "anything", "anyway", "anywhere",
			"are", "around", "as", "at", "back", "be", "became", "because", "become", "becomes", "becoming", "been",
			"before", "beforehand", "behind", "being", "below", "beside", "besides", "between", "beyond", "bill",
			"both", "bottom", "but", "by", "call", "can", "cannot", "cant", "co", "con", "could", "couldnt", "cry",
			"de", "describe", "detail", "do", "done", "down", "due", "during", "each", "eg", "eight", "either",
			"eleven", "else", "elsewhere", "empty", "enough", "etc", "even", "ever", "every", "everyone", "everything",
			"everywhere", "except", "few", "fifteen", "fify", "fill", "find", "fire", "first", "five", "for", "former",
			"formerly", "forty", "found", "four", "from", "front", "full", "further", "get", "give", "go", "had", "has",
			"hasnt", "have", "he", "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon", "hers",
			"herself", "him", "himself", "his", "how", "however", "hundred", "ie", "if", "in", "inc", "indeed",
			"interest", "into", "is", "it", "its", "itself", "keep", "last", "latter", "latterly", "least", "less",
			"ltd", "made", "many", "may", "me", "meanwhile", "might", "mill", "mine", "more", "moreover", "most",
			"mostly", "move", "much", "must", "my", "myself", "name", "namely", "neither", "never", "nevertheless",
			"next", "nine", "no", "nobody", "none", "noone", "nor", "not", "nothing", "now", "nowhere", "of", "off",
			"often", "on", "once", "one", "only", "onto", "or", "other", "others", "otherwise", "our", "ours",
			"ourselves", "out", "over", "own", "part", "per", "perhaps", "please", "put", "rather", "re", "same", "see",
			"seem", "seemed", "seeming", "seems", "serious", "several", "she", "should", "show", "side", "since",
			"sincere", "six", "sixty", "so", "some", "somehow", "someone", "something", "sometime", "sometimes",
			"somewhere", "still", "such", "system", "take", "ten", "than", "that", "the", "their", "them", "themselves",
			"then", "thence", "there", "thereafter", "thereby", "therefore", "therein", "thereupon", "these", "they",
			"thickv", "thin", "third", "this", "those", "though", "three", "through", "throughout", "thru", "thus",
			"to", "together", "too", "top", "toward", "towards", "twelve", "twenty", "two", "un", "under", "until",
			"up", "upon", "us", "very", "via", "was", "we", "well", "were", "what", "whatever", "when", "whence",
			"whenever", "where", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether",
			"which", "while", "whither", "who", "whoever", "whole", "whom", "whose", "why", "will", "with", "within",
			"without", "would", "yet", "you", "your", "yours", "yourself", "yourselves", "the" };

	static String sample = "Lionel Messi and FC Barcelona agreed to a new deal on Saturday morning that will keep the diminutive Argentine star with his longtime football club team through 2021.\n"
			+ "The buyout clause for the new deal was set at 700 million euros, according to a statement from FC Barcelona.\n"
			+ "The contract was first announced in August, just days after the record-breaking Messi married his childhood sweetheart, Antonella Roccuzzo.";

	public static boolean ASC = true;
	public static boolean DESC = false;

	public static String[] classifyDocument(String doc, int numberOfTag) {
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		ArrayList<String> stops = new ArrayList<String>(Arrays.asList(stopWord));
		String[] words = doc.toLowerCase().split("[^A-ZÃ…Ã„Ã–a-zÃ¥Ã¤Ã¶]+");
		for (String word : words) {
			if ("".equals(word)) {
				continue;
			}

			if (stops.contains(word)) {
				continue;
			}

			boolean exist  = countMap.containsKey(word);
			if (exist) {
				Integer i = countMap.get(word) ; 
				i = i + 1 ; 
				countMap.put(word, i);
			} else {
				countMap.put(word, 1);
			}

		}

		

		
		Map<String, Integer> sortedMapDesc = sortByComparator(countMap, DESC);
		
		String [] tags = new String[numberOfTag] ; 
		int j = 0 ; 
		for(String s : sortedMapDesc.keySet()) {
			if(j>numberOfTag-1)break ;
			tags[j++] = s; 			
		}
		
		return tags ; 

	}

	private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order) {

		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());

		// Sorting the list based on values
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if (order) {
					return o1.getValue().compareTo(o2.getValue());
				} else {
					return o2.getValue().compareTo(o1.getValue());

				}
			}
		});

		// Maintaining insertion order with the help of LinkedList
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

	public static void printMap(Map<String, Integer> map) {
		for (Entry<String, Integer> entry : map.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
		}
	}

	public static void main(String[] args) {
		String[] tags = classifyDocument(sample, 4);
		for (String tag : tags) {
			System.out.println(tag);
		}
		
	}

}
