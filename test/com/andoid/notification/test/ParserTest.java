package com.andoid.notification.test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.android.notification.common.ParseValidateEnum;
import com.android.notification.service.ParserService;

/**
 * ParserTeste unit tests
 * @author marciocamurati
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@PrepareForTest(ParserTest.class)
@ContextConfiguration(locations = {"file:war/WEB-INF/applicationContext.xml"})
public class ParserTest {
	
	@Autowired
	ParserService parserService;
	
	@Before
	public void before()	{
	}
	
	@Test
	public void testLoadUrlsList() throws Exception {
		assertTrue("Error on urls load", ((parserService.getAndroidUrls() != null && parserService.getAndroidUrls().size() > 0) ? true : false));
	}

//	@Test
//    public void testParse() throws Exception {
//		boolean notify = parserService.parse(parserService.getAndroidUrls().get(0));
//		
//		assertTrue("Parser Error", notify);
//	}
	
	@Test
	public void testValidate() throws Exception {
		ParseValidateEnum validate = parserService.validate();
		
		assertTrue("Validate error", validate.equals(ParseValidateEnum.COMPLETE));
	}
	
	@After
	public void after()	{
		parserService = null;
	}
}
