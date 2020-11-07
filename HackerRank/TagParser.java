import java.util.*;
import java.util.regex.*;

public class TagParser{

	public static void main(String[] args){
		
		Pattern pattern = Pattern.compile("<\\s\\S>([^<>]+)</\\1>");
		Scanner in = new Scanner(System.in);
		int testCases = Integer.parseInt(in.nextLine());
		
		while(testCases-- > 0){
			String line = in.nextLine();
			Matcher matcher = pattern.matcher(line);
			boolean none = true;
			while(matcher.find()) {
				System.out.println(matcher.group(2));
				none = false;
			}
			if(none)
				System.out.println("None");
		}
		in.close();
	}
}