package com.andoid.notification.test;

import java.net.URI;
import java.util.Scanner;

import org.scribe.oauth.Token;

import winterwell.jtwitter.OAuthScribeClient;
import winterwell.jtwitter.OAuthSignpostClient;
import winterwell.jtwitter.Twitter;

public class OAuthTest {

	public static void main(String[] args) throws Exception {
//		// create a consumer object and configure it with the access
//        // token and token secret obtained from the service provider
//        OAuthConsumer consumer = new DefaultOAuthConsumer("V2P9iDzboewwWtMy1uhg",
//                                             "kU11AZcYxHTzWK8fuFVWyPtJemb7S9KGbe1toAB5c");
//        
////        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);
//
//        // create an HTTP request to a protected resource
//        URL url = new URL("https://api.twitter.com/oauth/request_token");
//        HttpURLConnection request = (HttpURLConnection) url.openConnection();
//
//        // sign the request
//        consumer.sign(request);
//
//        // send the request
//        request.connect();		
//		
//		OAuthProvider provider = new DefaultOAuthProvider(
//                "https://api.twitter.com/oauth/request_token", "https://api.twitter.com/oauth/access_token",
//                "https://api.twitter.com/oauth/authorize");
//
//		String u = provider.retrieveRequestToken(consumer, "oob");
//		
//		System.out.println(u);
//		
//		Scanner sc = new Scanner(System.in);
//	    
//		String key = sc.nextLine();
//	    
//		provider.retrieveAccessToken(consumer, key);


		// Make an oauth client (you'll want to change this bit)
		OAuthSignpostClient oauthClient = new OAuthSignpostClient("V2P9iDzboewwWtMy1uhg", "kU11AZcYxHTzWK8fuFVWyPtJemb7S9KGbe1toAB5c", "178972347-hGwakO60SB7ImU5aXQOWL31lm5bnjWTM0ux3vq5q");
	        // Open the authorisation page in the user's browser
		// On Android, you'd direct the user to URI url = client.authorizeUrl();
		// On a desktop, we can do that like this:
//	        oauthClient.authorizeDesktop();
//	        String v = oauthClient.askUser("Please enter the verification PIN from Twitter");
		
//			URI url = oauthClient.authorizeUrl();
//			
//			System.out.println(url);
//			
//			Scanner sc = new Scanner(System.in);
//			
//			String v = sc.nextLine();
//		
//	        oauthClient.setAuthorizationCode(v);
//		// Store the authorisation token details for future use
//	        Object accessToken = oauthClient.getAccessToken();
//	        
//	        System.out.println(accessToken);

		// Make a Twitter object
		Twitter twitter = new Twitter("my-name", oauthClient);
		// Print Daniel Winterstein's status
		System.out.println(twitter.getStatus("androidseries"));
		
		twitter.setStatus("in stock! buy now, http://migre.me/151E7");
		
		
	}
	
}
