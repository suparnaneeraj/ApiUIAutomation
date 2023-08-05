package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TestConfig {
	
	private BufferedReader reader;
	private final String propertyFilePath="configs//propertiesFile.properties";
	private String jsonFilePath="configs//requestjson//";
	Properties properties;

	public HashMap<String,String> getPropertiesAsMap()
	{
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			reader= new BufferedReader(new FileReader(propertyFilePath));
			properties=new Properties();
			properties.load(reader);
			reader.close();
			for (Entry<Object,Object> entry : properties.entrySet()){
				map.put((String) entry.getKey(), (String) entry.getValue());
			}
					
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return map;
	}
	
//	public JSONObject readJsonFromFile(String jsonFileName) throws Exception
//	{
//		JSONParser parser = new JSONParser();
//		Object obj=parser.parse(new FileReader(jsonFilePath+jsonFileName));
//		JSONObject jsonObject=(JSONObject)obj;
//		return jsonObject;
//		
//	}
	
}
