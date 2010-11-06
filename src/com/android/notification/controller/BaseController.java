package com.android.notification.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.android.notification.service.CacheService;
import com.android.notification.service.TwitterService;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

@Controller
public class BaseController {

	private TwitterService twitterService = null;
	
	private CacheService cacheService = null;
	
	@Autowired
	public BaseController(TwitterService twitterService, CacheService cacheService)	{
		this.twitterService = twitterService;
		this.cacheService = cacheService;
	}
	
	@RequestMapping("/index")
	public ModelAndView hello() {
		ModelAndView model = new ModelAndView("/hello/index.jsp");
		
		return model;
	}
		
	@RequestMapping("/clean")
	public void cleanSessions(HttpServletResponse response)	{
		try {
			PrintWriter writer = response.getWriter();

			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Query query = new Query("_ah_SESSION");
			PreparedQuery results = datastore.prepare(query);

			int cleared = 0;
			
			writer.println("Encontrou: " + results.countEntities() + "<br />");
			
			try {
				for (Entity session : results.asIterable()) {				
					datastore.delete(session.getKey());
					cleared++;
				}
			} catch (Throwable e) {
				writer.println(e);
			}
			
			writer.println("Removeu: " + cleared + "<br />");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
