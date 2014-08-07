package ru.dreamcloud.alexion.model.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.eclipse.persistence.oxm.record.JSONFormattedWriterRecord;
import org.zkoss.json.JSONArray;
import org.zkoss.json.JSONObject;
import org.zkoss.json.parser.JSONParser;

public class TestEntities {

	public static void main(String[] args) {
		JSONParser json = new JSONParser();
		try {
			InputStream is = new URL("http://basicdata.ru/api/json/fias/addrobj/").openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			JSONObject obj = (JSONObject)json.parse(rd);
			JSONArray regions = (JSONArray)obj.get("data");
			Object[] regionList = regions.toArray();
			for (Object object : regionList) {
				JSONObject jsonObj = (JSONObject)object;
				System.out.println(jsonObj.get("formalname") + " " + jsonObj.get("shortname"));				
			}			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


	}

}
