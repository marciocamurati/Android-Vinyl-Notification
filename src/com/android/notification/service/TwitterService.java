package com.android.notification.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.android.notification.common.ApplicationProperties;

import winterwell.jtwitter.OAuthSignpostClient;
import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import winterwell.jtwitter.Twitter.Status;

/**
 * Twitter business basic class
 * 
 * @author marciocamurati
 * 
 */
@Service
public class TwitterService {

	/*
	 * Log4j
	 */
	private static final Logger log = Logger.getLogger(TwitterService.class.getName());

	/**
	 * Login on twitter with basic authentication, do this for not need the
	 * authorization pop call
	 * 
	 * @param <code>String</code> userName
	 * @param <code>String</code> password
	 * @return <code>Twitter</code>
	 * @throws {@link TwitterException}
	 */
	@Deprecated
	public Twitter login(String userName, String password)
			throws TwitterException {
		return new Twitter(userName, password);
	}
	
	/**
	 * Login on twitter with OAuth authentication, do this for not need the
	 * authorization pop call
	 * 
	 * @return <code>Twitter</code>
	 * @throws {@link TwitterException}
	 */	
	public Twitter login(String userName)	{
		OAuthSignpostClient oauthClient = new OAuthSignpostClient(ApplicationProperties.get("twitter.api.key"), 
				ApplicationProperties.get("twitter.consumer.secret"), 
				ApplicationProperties.get("twitter.access.token.secret"));
	
		return new Twitter(userName, oauthClient);
	}

	/**
	 * Update the status of the logged user
	 * 
	 * @param <code>String</code> twitter
	 * @param <code>String</code> statusText, short messages with less or equal
	 *        than 140 characters
	 * @throws {@link TwitterException}
	 */
	public void update(Twitter twitter, String statusText)
			throws TwitterException {
		if (twitter != null)
			twitter.updateStatus(statusText);
		else
			throw new TwitterException("Not connected");

	}

	/**
	 * Make a search at Twitter
	 * @param <code>String</code> searchTerm, text or #hash
	 * @param <code>Long</code> sinceId, last id
	 * @return <code>List<Status></code>
	 * @throws {@link TwitterException}
	 */
	public List<Status> search(String searchTerm, Long sinceId) throws TwitterException		{
		if (sinceId == null)
			throw new TwitterException("Since id null.");
		
		Twitter twitter = new Twitter();
		
		twitter.setSinceId(sinceId);
		
		return twitter.search(searchTerm);
	}
}
