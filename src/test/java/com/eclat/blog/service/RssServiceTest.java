package com.eclat.blog.service;

import static org.junit.Assert.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.eclat.blog.entity.Item;
import com.eclat.blog.exception.RssException;

public class RssServiceTest {

	private RssService rssService;

	@Before
	public void setUp() throws Exception {
		rssService = new RssService();
	}

	@Test
	public void testGetItemsFile() throws RssException {
		List<Item> items = rssService
				.getItems(new File("test-rss/javavids.xml"));
		assertEquals(10, items.size());
		Item firstItem = items.get(0);
		assertEquals(
				"How to solve Source not found error during debug in Eclipse",
				firstItem.getTitle());
		assertEquals("23 06 2014 02:05:49", new SimpleDateFormat(
				"dd MM yyyy HH:mm:ss").format(firstItem.getPublishedDate()));

	}

}
