package com.android.notification.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.util.NodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import winterwell.jtwitter.Twitter;

import com.android.notification.common.ApplicationProperties;
import com.android.notification.common.ParseValidateEnum;

/**
 * 
 * @author marciocamurati
 *
 */
@Service
public class ParserService {

	/*
	 * Log4j
	 */
	private static final Logger log = Logger.getLogger(ParserService.class.getName());
	
	private static List<String> androidUrls = null;
	
	@Autowired
	private TwitterService twitterService;
	
	static	{
		String[] urls = ApplicationProperties.get("android.urls").split(",");
		
		if (urls.length > 0)	{
			androidUrls = new ArrayList<String>(urls.length);
			
			for (int p=0;p<urls.length;p++)	{
				androidUrls.add(urls[p]);
			}
		}
	}
	
	public ParseValidateEnum validate() {
		boolean notify = false;
		
		for (String androidUrl : androidUrls) {
			try {
				notify = this.parse(androidUrl);
				
				log.log(Level.INFO, androidUrl + ": " + notify);
				
				if (notify == true)	{
					break;
				}
			} catch (Exception e) {
				log.log(Level.WARNING, e.getMessage());
				return ParseValidateEnum.ERROR;
			}
		}
		
		try {
			if (notify == true)	{
				Twitter twitter = twitterService.login(ApplicationProperties.get("twitter.login"));
				
				if (twitter != null)	{
					log.log(Level.INFO, "Twitter login succesful");
					
					twitterService.update(twitter, ApplicationProperties.get("status.message"));
				}
				
			}
		} catch (Exception e) {
			log.log(Level.WARNING, e.getMessage());
			return ParseValidateEnum.ERROR;
		}
		
		return ParseValidateEnum.COMPLETE;
	}	

	public boolean parse(String androidUrl) throws Exception {
		URL url = null;
		
		boolean notify = false;
			
		try {
			url = new URL(androidUrl);
			
			Parser parser = new Parser(url.openConnection());
			
			CssSelectorNodeFilter cssFilter = new CssSelectorNodeFilter("DIV.CurrentlySoldOut");
			
			NodeList nodes = parser.parse(cssFilter);
			
			if (nodes.size() > 0)	{
				notify = true;
			}
		}	catch (Exception e) {
			e.printStackTrace();
		} finally {
			url = null;
		}
		
		return notify;
	}
	
	public List<String> getAndroidUrls()	{
		return this.androidUrls;
	}
}
