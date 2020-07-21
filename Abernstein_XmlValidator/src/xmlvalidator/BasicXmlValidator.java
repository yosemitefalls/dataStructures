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
	String peekZeroTag;
	String peekZeroLine;
	String counterAsString;
	String Dontequalerror;
	String reuseableemptyTagError0;
	String reuseableNotemptyError0;
	String popwhennotEmpty;

	@Override
	public String[] validate(String xmlDocument) {

		// String expr = "<[^<>]+>";
		String expr = "<(/?[\\w]+)(\\s[^<>]+)?>";
		// cuts off spaces and all that I dont want
		Pattern p = Pattern.compile(expr);
		Matcher m = p.matcher(xmlDocument);
		String[] seperatedTag;
		int count = m.regionEnd();
		while (m.find()) {
			int counter = StringUtils.countMatches(xmlDocument.substring(0, m.start()), "\n") + 1;
			this.x = counter;
			String choppedincarrots = m.group(1);
			String currentLine = lineDecipherandCount(xmlDocument, m.start(), counterv2);

			if ((xmlDocument.charAt(m.start() + 1) == '/')) {
				if (!openTags.isEmpty()) {

					String peek = openTags.peek(0);
					if (peek.equals(choppedincarrots.substring(1))) {
						openTags.pop();
						lineCounter.pop();

					} else {
						if (!peek.equals(choppedincarrots.substring(1))) {
							// for reusability
							String peekatzero = openTags.peek(0);
							this.peekZeroTag = peekatzero;
							String linecountzero = lineCounter.peek(0);
							this.peekZeroLine = linecountzero;
							String xxx = Integer.toString(counter);
							this.counterAsString = xxx;
							String firstError = "Tag mismatch";
							this.Dontequalerror = firstError;
							if (choppedincarrots.equals(xmlDocument.substring(m.start() + 1, m.end() - 1))) {
								System.out.println("Tag mismatch");
								errors = new String[5];
								errors[0] = Dontequalerror;
								errors[1] = peekZeroTag;
								errors[2] = peekZeroLine;
								errors[3] = xmlDocument.substring(m.start() + 2, m.end() - 1);
								errors[4] = counterAsString;
								return errors;
							}
						}
					}
				} else {
					if (openTags.isEmpty()) {
						String emptyTagError0 = "Orphan closing tag";
						this.reuseableemptyTagError0 = emptyTagError0;
						errors = new String[3];
						errors[0] = reuseableemptyTagError0;
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

		if (!openTags.isEmpty()) {
			String NotemptyError0 = "Unclosed tag at end";
			this.reuseableNotemptyError0 = NotemptyError0;

			String xx = openTags.pop();
			this.popwhennotEmpty = xx;
			// reuseablility
			errors = new String[3];
			errors[0] = reuseableNotemptyError0;
			errors[1] = popwhennotEmpty;
			errors[2] = Integer.toString(x);
			return errors;

		} else {
			return null;
		}

	}

	private String lineDecipherandCount(String xmlDocument, int correstPosition, BasicStringStack counterv22) {
		int correctLine = StringUtils.countMatches(xmlDocument.substring(0, correstPosition), "\n") + 1;
		this.correctLinezz = correctLine;
		return String.format("%s", correctLinezz);

	}

}
