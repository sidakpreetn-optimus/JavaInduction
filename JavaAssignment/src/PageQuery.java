import java.util.*;
import java.util.Map.Entry;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class PageQuery
{
	public static void main(String[] args)
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String temp="";
		int pages=0,queries=0;
		List<List<String>> pagesList = new ArrayList<List<String>>();
		List<List<String>> queriesList = new ArrayList<List<String>>();
		try {
			/*Loop for reading input line by line and storing whole input
			 *in a List of Lists*/
			while(!((temp=br.readLine()).equals("E")))
			{
				if(temp.charAt(0)=='P')
				{
					String a[]=temp.split("\\s+");
					pagesList.add(Arrays.asList(a));
					pages++;
				}
				if(temp.charAt(0)=='Q')
				{
					String a[]=temp.split("\\s+");
					queriesList.add(Arrays.asList(a));
					queries++;			
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Query Pages");
		/*Loop for printing the pages list*/
		for(int i=0;i<queriesList.size();i++)
		{
			System.out.println("Q"+(i+1)+":"+returnListOfPages(queriesList.get(i), pagesList));
		}
	}
	/*Function for getting the list of all relevant pages for a specific query*/
	static String returnListOfPages(List<String> singleQuery, List<List<String>> allPages)
	{
		String order="";
		HashMap<String, Integer> pageQueryStrength = new HashMap<String, Integer>();
		for(int i=0;i<allPages.size();i++)
		{
			pageQueryStrength.put((i+1)+"", calculateStrength(singleQuery, allPages.get(i), 8));
		}
		List<Entry<String,Integer>> sortedDescEntries=entriesSortedByValues(pageQueryStrength);
		for (int i=0;i<sortedDescEntries.size();i++)
		{
			if(sortedDescEntries.get(i).getValue()==0||i>5)
				break;
			order=order+" P"+sortedDescEntries.get(i).getKey();
		}
			return order;
	}
	/*Function for calculating Strength between a query and a page*/
	static int calculateStrength(List<String> l1,List<String> l2, int maxN)
	{
		int strength=0;
		int l1Size=l1.size()-1;
		int l2Size=l2.size()-1;
		for(int i=1;i<=l1Size;i++)
		{
			for(int j=1;j<=l2Size;j++)
			{
				if(l1.get(i).equals(l2.get(j)))
				{
					strength+=(9-i)*(9-j);
				}
			}
		}
		return strength;
	}
	/*Function for sorting a Hashmap by Values in descending order*/
	static <K,V extends Comparable<? super V>> List<Entry<K, V>> entriesSortedByValues(Map<K,V> map)
	{
		List<Entry<K,V>> sortedEntries = new ArrayList<Entry<K,V>>(map.entrySet());

		Collections.sort(sortedEntries, new Comparator<Entry<K,V>>()
		{
        	public int compare(Entry<K,V> e1, Entry<K,V> e2)
        	{
            	return e2.getValue().compareTo(e1.getValue());
        	}
		}
		);
		return sortedEntries;
	}
}
