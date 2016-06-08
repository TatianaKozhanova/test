package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.testng.annotations.DataProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXmlFile {

	private String fileName;
	public static Object[][] data;

	public ReadXmlFile(String fileName, int numberOfDrafts) {
		this.fileName = fileName;
		data = new Object[numberOfDrafts][3];
		parseXml();
	}

	@DataProvider(name = "letterInfo")
	public static Object[][] getLetterInfo() {
		return data;
	}

	public void parseXml() {
		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = documentBuilder.parse(fileName);
			Node root = document.getDocumentElement();
			NodeList letters = root.getChildNodes();
			List<Node> trueLetters = new ArrayList<Node>();
			for (int i = 0; i < letters.getLength(); i++) {
				if (letters.item(i).getNodeType() == Node.ELEMENT_NODE) {
					trueLetters.add(letters.item(i));
				}
			}
			for (int i = 0; i < trueLetters.size(); i++) {
				Node letter = trueLetters.get(i);
				NodeList letterAttributes = letter.getChildNodes();
				List<Node> trueLetterAttributes = new ArrayList<Node>();
				for (int j = 0; j < letterAttributes.getLength(); j++) {
					if (letterAttributes.item(j).getNodeType() == Node.ELEMENT_NODE) {
						trueLetterAttributes.add(letterAttributes.item(j));
					}
				}
				for (int j = 0; j < trueLetterAttributes.size(); j++) {
					data[i][j] = trueLetterAttributes.get(j).getTextContent();
				}
			}
		} catch (ParserConfigurationException ex) {
			ex.printStackTrace(System.out);
		} catch (SAXException ex) {
			ex.printStackTrace(System.out);
		} catch (IOException ex) {
			ex.printStackTrace(System.out);
		}
	}
}
