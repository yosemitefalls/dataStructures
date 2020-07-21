package dnasplicing;

import static org.apache.commons.lang3.StringUtils.*;

public class LinkedDnaStrand implements DnaStrand {
	int nodeCount = 0;
	int appendCount;
	DnaSequenceNode cursor, top, tail;
	String dnaSequence;

	public LinkedDnaStrand(String dnaSequence) {

		DnaSequenceNode newNode = new DnaSequenceNode(dnaSequence);
		top = newNode;
		cursor = top;
		tail = top;
		top.previous = null;
		tail.previous = null;
		nodeCount++;
	}

	public LinkedDnaStrand() {

	}

	public String toString() {
		String dnaInStringFormat = "";
		DnaSequenceNode tempPosition = top;
		while (tempPosition != null) {
			dnaInStringFormat += tempPosition.dnaSequence;
			tempPosition = tempPosition.next;
		}
		return dnaInStringFormat;
	}

	@Override
	public long getNucleotideCount() {
		return toString().length();
	}

	@Override
	public void append(String dnaSequence) {

		if (tail == null) {
			if (dnaSequence != null && dnaSequence.length() > 0) {
				DnaSequenceNode newNode = new DnaSequenceNode(dnaSequence);
				top = newNode;
				tail = top;
				tail.next = newNode;
				tail.next.previous = tail;
				tail = tail.next;
				nodeCount++;
			}
		} else {
			if (dnaSequence != null && dnaSequence.length() > 0) {
				tail.next = new DnaSequenceNode(dnaSequence);
				tail.next.previous = tail;
				tail = tail.next;

				appendCount++;
				nodeCount++;
			}
		}
	}

	@Override
	public DnaStrand cutSplice(String enzyme, String splicee) {
		LinkedDnaStrand extraStrand = new LinkedDnaStrand();
		String uncutStrand = toString();
		String[] cutStrand = uncutStrand.split(enzyme, -1);
		if (uncutStrand.startsWith(enzyme)) {
			// index of
			for (int o = 1; o < cutStrand.length; o++) {
				extraStrand.append(splicee);
				extraStrand.append(cutStrand[o]);
			}
		} else {
			for (int p = 0; p < cutStrand.length; p++) {
				extraStrand.append(cutStrand[p]);
				if (p < cutStrand.length - 1) {
					extraStrand.append(splicee);
				}
			}
		}
		return extraStrand;

	}

	public DnaStrand createReversedDnaStrand() {

		String notReversedStrand = toString();
		String reversedStrand = reverse(notReversedStrand);
		LinkedDnaStrand toBeReversed = new LinkedDnaStrand(reversedStrand);
		// toBeReversed.append(reversedStrand);
		return toBeReversed;
		/**
		 * DnaSequenceNode tempPosition = cursor; LinkedDnaStrand NewReverseStrand = new
		 * LinkedDnaStrand(); while (tempPosition != null) {
		 * NewReverseStrand.append(reverse(cursor.dnaSequence)); cursor =
		 * cursor.previous; } return NewReverseStrand;
		 **/

	}

	@Override
	public int getAppendCount() {

		return appendCount;
	}

	@Override
	public DnaSequenceNode getFirstNode() {
		return top;
	}

	@Override
	public int getNodeCount() {
		return nodeCount;
	}

}
