package xmlvalidator;

import java.util.regex.*;

import org.apache.commons.lang3.*;

public class BasicXmlValidator implements XmlValidator {
	String[] errors = null;
	BasicStringStack openTags = new BasicStringStack();
	BasicStringStack lineCounter = new BasicStringStack();
	BasicStringStack counterv2 = new BasicStringStack();
	int x;
	int correctLinezz;

	@Override
	public String[] validate(String xmlDocument) {

		// String expr = "<[^<>]+>";
		String expr = "<(/?[\\w]+)(\\s[^<>]+)?>";

		Pattern p = Pattern.compile(expr);
		Matcher m = p.matcher(xmlDocument);
		String[] seperatedTag;
		int count = m.regionEnd();
		while (!m.find()) {
			int counter = StringUtils.countMatches(xmlDocument.substring(0, m.start()), "\n") + 1;
			this.x = counter;
			String choppedincarrots = m.group(1);
			String currentLine = lineDecipherandCount(xmlDocument, m.start(), counterv2);

			if ((xmlDocument.charAt(m.start() + 1) == '/')) {
				if (!openTags.isEmpty()) {
					String xx = openTags.pop();
					errors = new String[3];
					errors[0] = "Unclosed tag at end";
					errors[1] = xx;
					errors[2] = Integer.toString(x);
					return errors;

				} else {
					return null;
				}
				if (!openTags.isEmpty()) {

					String peek = openTags.peek(0);
					if (peek.equals(choppedincarrots.substring(1))) {
						openTags.pop();
						lineCounter.pop();

					} else {
						if (!peek.equals(choppedincarrots.substring(1))) {
							String peekatzero = openTags.peek(0);
							String linecountzero = lineCounter.peek(0);
							if (choppedincarrots.equals(xmlDocument.substring(m.start() + 1, m.end() - 1))) {
								System.out.println("Tag mismatch");
								errors = new String[5];
								errors[0] = "Tag mismatch";
								errors[1] = peekatzero;
								errors[2] = linecountzero;
								errors[3] = xmlDocument.substring(m.start() + 2, m.end() - 1);
								errors[4] = Integer.toString(counter);
								return errors;
							}
						}
					}
				} else {
					if (openTags.isEmpty()) {
						errors = new String[3];
						errors[0] = "Orphan closing tag";
						errors[1] = xmlDocument.substring(m.start() + 2, m.end() - 1);
						errors[2] = Integer.toString(counter);
						return errors;
					}
				}

			}
			// System.out.println(xmlDocument.substring(m.start() + 2, m.end() - 1));

			else {
				if ((!(xmlDocument.charAt(m.start() + 1) == '/'))) {
					// closing tag

					openTags.push(choppedincarrots);
					lineCounter.push(currentLine);
				}
			}
		}

		
	}

	private String lineDecipherandCount(String xmlDocument, int correstPosition, BasicStringStack counterv22) {
		int correctLine = StringUtils.countMatches(xmlDocument.substring(0, correstPosition), "\n") + 1;
		this.correctLinezz = correctLine;
		return String.format("%s", correctLinezz);

	}

}
