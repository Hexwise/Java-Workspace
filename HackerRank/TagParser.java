import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class TagParser{

	private static String extractBetweenTags(String line) {
			String regex = "<([\\s\\S]+)>([\\s\\S]*)</\\1>";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(line);
			if(matcher.find())
				return extractBetweenTags(matcher.group(2));
			else
				return line;
	}

	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);
		int testCases = Integer.parseInt(in.nextLine());
		while(testCases>0){
			String line = in.nextLine();
            //Write your code here
			String regex = "<([\\s\\S]+)>([\\s\\S]+)</\\1>";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(line);
			boolean none = true;
			while(matcher.find()) {
				String innerText = extractBetweenTags(matcher.group(2));
				if(!innerText.isEmpty()) {
					regex = "<[\\s\\S]+>";
					pattern = Pattern.compile(regex);
					Matcher matcher2 = pattern.matcher(innerText);
					if(!matcher2.find()) {
					System.out.println(innerText);
					none = false;
					}
				}
			}
			if(none)
				System.out.println("None");
			testCases--;
		}
		in.close();
	}
}