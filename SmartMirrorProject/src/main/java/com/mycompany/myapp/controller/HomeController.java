package com.mycompany.myapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.dvdme.ForecastIOLib.FIOCurrently;
import com.github.dvdme.ForecastIOLib.FIODaily;
import com.github.dvdme.ForecastIOLib.FIODataPoint;
import com.github.dvdme.ForecastIOLib.ForecastIO;
import com.mycompany.myapp.util.Feed;
import com.mycompany.myapp.util.FeedMessage;
import com.mycompany.myapp.util.RSSFeedParser;

@Controller
public class HomeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	private List<FeedMessage> list;	

	@RequestMapping("/")
	public String home(){
		return "main";
	}

	@RequestMapping("/calander")
	public String calander() {
		return "calander";
	}
	
	@RequestMapping("/news")
	public void news(HttpServletResponse response) throws IOException {
		RSSFeedParser parser = new RSSFeedParser("https://news.google.com/news/rss/headlines?hl=ko&ned=kr");
		Feed feed = parser.readFeed();
		
		if(list != null) {
			list.clear();
		}
		list = feed.getMessages();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("titleList", list);
		String json = jsonObject.toString();
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.flush();
		pw.close();
	}
	
	@RequestMapping("/weather")
	public void weather(HttpServletResponse response) throws IOException {
		String apiKey = "c6c80c71939d52853536a9186fbca948";
		JSONObject jsonObject = new JSONObject();
		
		ForecastIO fio = new ForecastIO(apiKey);
		fio.setUnits(ForecastIO.UNITS_SI);
		fio.setLang(ForecastIO.LANG_ENGLISH);
		fio.getForecast("37.56621515784935", "126.98049545288086");

		FIOCurrently currently = new FIOCurrently(fio);

		FIODataPoint fdp = currently.get();
		double temp = fdp.temperature();
		String icon = fdp.icon();
		String summary = fdp.summary().substring(1, fdp.summary().length()-1);
		String iconR = icon.substring(1, icon.length() - 1);
		
		FIODaily daily = new FIODaily(fio);
		double[] tempMax = new double[8];
		double[] tempMin = new double[8];
		String[] iconW = new String[8];
		
		Calendar cal = Calendar.getInstance();
		String[] weekList = new String[7];
		int now = cal.get(Calendar.DAY_OF_WEEK);
		switch(now) {
		case Calendar.MONDAY:
			weekList[0] = "Mon";
			weekList[1] = "Tue";
			weekList[2] = "Wed";
			weekList[3] = "Thu";
			weekList[4] = "Fri";
			weekList[5] = "Sat";
			weekList[6] = "Sun";
			break;
		case Calendar.TUESDAY:
			weekList[0] = "Tue";
			weekList[1] = "Wed";
			weekList[2] = "Thu";
			weekList[3] = "Fri";
			weekList[4] = "Sat";
			weekList[5] = "Sun";
			weekList[6] = "Mon";
			break;
		case Calendar.WEDNESDAY:
			weekList[0] = "Wed";
			weekList[1] = "Thu";
			weekList[2] = "Fri";
			weekList[3] = "Sat";
			weekList[4] = "Sun";
			weekList[5] = "Mon";
			weekList[6] = "Tue";
			break;
		case Calendar.THURSDAY:
			weekList[0] = "Thu";
			weekList[1] = "Fri";
			weekList[2] = "Sat";
			weekList[3] = "Sun";
			weekList[4] = "Mon";
			weekList[5] = "Tue";
			weekList[6] = "Wed";
			break;
		case Calendar.FRIDAY:
			weekList[0] = "Fri";
			weekList[1] = "Sat";
			weekList[2] = "Sun";
			weekList[3] = "Mon";
			weekList[4] = "Tue";
			weekList[5] = "Wed";
			weekList[6] = "Thu";
			break;
		case Calendar.SATURDAY:
			weekList[0] = "Sat";
			weekList[1] = "Sun";
			weekList[2] = "Mon";
			weekList[3] = "Tue";
			weekList[4] = "Wed";
			weekList[5] = "Thu";
			weekList[6] = "Fri";
			break;
		case Calendar.SUNDAY:
			weekList[0] = "Sun";
			weekList[1] = "Mon";
			weekList[2] = "Tue";
			weekList[3] = "Wed";
			weekList[4] = "Thu";
			weekList[5] = "Fri";
			weekList[6] = "Sat";
			break;
		}
		
		if (daily.days() < 0) {
			jsonObject.put("icon", "No data available");
		}
		for (int i = 0; i < daily.days(); i++) {
			daily.getDay(i).setTimezone("Asia/Seoul");
			tempMax[i] = daily.getDay(i).temperatureMax();
			tempMin[i] = daily.getDay(i).temperatureMin();
			iconW[i] = daily.getDay(i).icon().substring(1, daily.getDay(i).icon().length()-1);
		}
		
		jsonObject.put("temp", temp);
		jsonObject.put("icon", iconR);
		jsonObject.put("summary", summary);

		for(int i = 1; i < 7; i++) {
			jsonObject.put("tempMax"+i, tempMax[i]);
			jsonObject.put("tempMin"+i, tempMin[i]);
			jsonObject.put("iconW"+i, iconW[i]);
			jsonObject.put("week"+i, weekList[i]);
		}
		
		String json = jsonObject.toString();
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.flush();
		pw.close();
	}
}	
