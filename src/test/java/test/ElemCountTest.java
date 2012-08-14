package test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import org.easymock.IAnswer;
import org.junit.Test;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import test.ElemCount.MyErrorHandler;

public class ElemCountTest {
	@Test
	public void testExternalXSD() throws Exception {
		ElemCount elemCount = new TestElemCount("name", "item");
		ElemCount.parse("target/test-classes/PurchaseOrder/po.xml", false,
				true, "target/test-classes/PurchaseOrder/po.xsd", elemCount,
				new MyErrorHandler(System.err));
		assertNotNull(elemCount.tags);
		assertTrue(!elemCount.tags.isEmpty());
		assertEquals(2, elemCount.tags.size());
		assertEquals(new Integer(2), elemCount.tags.get("name"));
		assertEquals(new Integer(2), elemCount.tags.get("item"));
	}

	@Test
	public void testXSDNamespace() throws Exception {
		ElemCount elemCount = new TestElemCount("Book");
		ElemCount.parse(
				"target/test-classes/PublicationCatalogue/Catalogue.xml",
				false, true, null, elemCount, new MyErrorHandler(System.err));
		assertNotNull(elemCount.tags);
		assertTrue(!elemCount.tags.isEmpty());
		assertEquals(new Integer(2), elemCount.tags.get("c:Book"));
	}

	@Test
	public void testXSDNamespace2() throws Exception {
		ElemCount elemCount = new TestElemCount("c:Book");
		ElemCount.parse(
				"target/test-classes/PublicationCatalogue/Catalogue.xml",
				false, true, null, elemCount, new MyErrorHandler(System.err));
		assertNotNull(elemCount.tags);
		assertTrue(!elemCount.tags.isEmpty());
		assertEquals(new Integer(2), elemCount.tags.get("c:Book"));
	}

	@Test
	public void testDTD() throws Exception {
		ElemCount elemCount = new TestElemCount("state");
		ElemCount.parse("target/test-classes/Invoice/Invoice.xml", true, false,
				null, elemCount, new MyErrorHandler(System.err));
		assertNotNull(elemCount.tags);
		assertTrue(!elemCount.tags.isEmpty());
		assertEquals(new Integer(1), elemCount.tags.get("state"));
	}

	@Test
	public void testXSD() throws Exception {
		ElemCount elemCount = new TestElemCount("FirstName");
		ElemCount.parse(
				"target/test-classes/GolfCountryClub/GolfCountryClub.xml",
				false, true, null, elemCount, new MyErrorHandler(System.err));
		assertNotNull(elemCount.tags);
		assertTrue(!elemCount.tags.isEmpty());
		assertEquals(new Integer(6), elemCount.tags.get("FirstName"));
	}

	@Test
	public void testFailureElementNotFound() throws Exception {
		ElemCount elemCount = new TestElemCount("NonExistingElement");
		ElemCount.parse(
				"target/test-classes/GolfCountryClub/GolfCountryClub.xml",
				false, true, null, elemCount, new MyErrorHandler(System.err));
		assertNotNull(elemCount.tags);
		assertTrue(elemCount.tags.isEmpty());
	}

	@Test(expected = FileNotFoundException.class)
	public void testFailureInvalidArgs() throws Exception {
		ElemCount.parse(
				"target/test-classes/GolfCountryClub/NonExistingFile.xml",
				false, true, null, new TestElemCount("SomeElement"),
				new MyErrorHandler(System.err));
	}

	@Test
	public void testFailureParsingException() throws Exception {
		ErrorHandler errorHandlerMock = createMock(ErrorHandler.class);
		reset(errorHandlerMock);
		errorHandlerMock.error(isA(SAXParseException.class));
		expectLastCall().andAnswer(new IAnswer<Object>() {
			@Override
			public Object answer() throws Throwable {
				SAXParseException e = (SAXParseException) getCurrentArguments()[0];
				assertTrue(e.getMessage().contains(
						"Document is invalid: no grammar found."));
				return null;
			}
		});
		errorHandlerMock.error(isA(SAXParseException.class));
		expectLastCall().andAnswer(new IAnswer<Object>() {
			@Override
			public Object answer() throws Throwable {
				SAXParseException e = (SAXParseException) getCurrentArguments()[0];
				assertTrue(e
						.getMessage()
						.contains(
								"Document root element \"Invoice\", must match DOCTYPE root \"null\"."));
				return null;
			}
		});
		replay(errorHandlerMock);
		ElemCount.parse("target/test-classes/Invoice/Invoice_nodoctype.xml",
				true, false, null, new TestElemCount("SomeElement"),
				errorHandlerMock);
		verify(errorHandlerMock);
	}

	class TestElemCount extends ElemCount {
		TestElemCount(String... elements) {
			this(Arrays.asList(elements));
		}

		TestElemCount(List<String> elements) {
			super(elements);
		}

		@Override
		public void endDocument() throws SAXException {
			// do not output data
		}
	}
}
