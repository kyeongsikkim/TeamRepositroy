package com.mycompany.myapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.myapp.util.Feed;
import com.mycompany.myapp.util.FeedMessage;
import com.mycompany.myapp.util.RSSFeedParser;

@Controller
public class HomeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	private List<FeedMessage> list;	

	@RequestMapping("/")
	public String home() {
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
}	
