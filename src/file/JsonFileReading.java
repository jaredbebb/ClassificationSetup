package file;

import java.io.*;
import javax.json.Json;
import javax.json.stream.JsonParser;

public class JsonFileReading {
	
	String reviewerID;
	String asin;
	String reviewerName;
	String helpful = "";
	int helpfulCount = 0;
	String reviewText;
	Double overall;
	String summary;
	String unixReviewTime;
	String reviewTime;
		
	public void GetValues(JsonParser parser, String whichid){		
		switch(whichid){
		case "reviewerID":
			reviewerID =parser.getString();
			break;
		case "asin":
			asin = parser.getString();
			break;			
		case "reviewerName":			
			reviewerName = parser.getString();
			break;
		case "helpful":
			if(helpfulCount==0){
				helpful = parser.getString();
				helpfulCount++;
				break;
			}
			else if(helpfulCount==1){
			helpful = helpful+"," +parser.getString();
			helpfulCount=0;
			break;
			}
		case "reviewText":
			reviewText = parser.getString();
			break;
		case "overall":
			overall = Double.parseDouble(parser.getString());
			break;
		case "summary":
			summary = parser.getString();
			break;
		case "unixReviewTime":	
			unixReviewTime = Long.toString(parser.getLong());
			break;
		case "reviewTime":
			reviewTime = parser.getString();
			break;
		}			
	}
	
	/*See 'Reading JSON Data Using a Parser' section
	 * http://docs.oracle.com/middleware/1221/wls/WLPRG/java-api-for-json-proc.htm#WLPRG1062
	 * 
	 */
	public void getJson(String jsonData){
//		System.out.println("-----------------------------------------");
		JsonParser parser = Json.createParser(new StringReader(jsonData));
		String whichid = "whichid";
		while(parser.hasNext()){
			JsonParser.Event event = parser.next();
			switch(event){		
			case START_ARRAY:
			case END_ARRAY:
			case START_OBJECT:
			case END_OBJECT:
			case VALUE_FALSE:
			case VALUE_NULL:
			case VALUE_TRUE:
//				System.out.println(event.toString());
				break;
			case KEY_NAME:
//				System.out.println(event.toString()+" "+
//									parser.getString() + "-");
				whichid = parser.getString();
				break;
			case VALUE_STRING:
			case VALUE_NUMBER:
//				System.out.println(event.toString()+ " "+
//									parser.getString());
//				System.out.println(whichid+ ": "+parser.getString());
				GetValues(parser, whichid);
				break;
			default:
				break;				
			}
		}		
	}
	public static void main(String[] args) throws IOException {
		String workingDirectory = System.getProperty("user.dir");
		String in =workingDirectory+"\\src\\data\\stopwords.txt";
		String out = workingDirectory+"\\src\\data\\stopwords.txt";

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(workingDirectory+"\\src\\data\\short.json"));
			br = new BufferedReader(new FileReader(workingDirectory+"\\src\\data\\Home_and_Kitchen_5.json"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line;
		int row_count=0;
		System.out.println("reviewerID,reviewText,normalized_overall");

		//iterate over every line of the input file. Only first 50 lines are inserted
		while((line = br.readLine()) != null && row_count<1000) {
			if (line.isEmpty()) continue;
			row_count++;
			JsonFileReading jfr = new JsonFileReading();
			jfr.getJson(line);
			String reviewerID = (jfr.reviewerID == null) ? "" : jfr.reviewerID;
			String reviewText = (jfr.reviewText == null) ? "" : jfr.reviewText;
			Double overall = (Double) ((jfr.overall == null) ? "" : jfr.overall);
			String normalized_overall = "";
			Reading r = new Reading(workingDirectory+"\\src\\data\\short.csv");
			reviewText = r.format(reviewText);
			if(overall == 5.0){
				normalized_overall = "PERFECT";
			}
			if(overall != 5.0){
				normalized_overall = "NOTPERFECT";
			}
			System.out.println(reviewerID + "," + reviewText + ","+normalized_overall);
		}
	}
}

