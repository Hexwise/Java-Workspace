import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class TagParser{
	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);
		int testCases = Integer.parseInt(in.nextLine());
		while(testCases>0){
			String line = in.nextLine();
			
            //Write your code here
			String regex = "\\b<\\S+>[^]</\\1>\\b";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(line);
			if(line.matches(regex))
				System.out.println(matcher.group(0));
			else
				System.out.println("None");

			testCases--;
		}
	}
}