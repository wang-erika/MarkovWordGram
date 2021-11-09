import java.util.Objects;

/**
 * A WordGram represents a sequence of strings
 * just as a String represents a sequence of characters
 * 
 * @author Erika Wang
 *
 */
public class WordGram {
	
	private String[] myWords;   
	private String myToString;  // cached string
	private int myHash;         // cached hash value

	/**
	 * Create WordGram by creating instance variable myWords and copying
	 * size strings from source starting at index start
	 * @param source is array of strings from which copying occurs
	 * @param start starting index in source for strings to be copied
	 * @param size the number of strings copied
	 */
	public WordGram(String[] source, int start, int size) {
		myWords = new String[size];
		for(int k = 0; k < size; k ++) {
			myWords[k] = source[k+start];
		}

		myToString = ""; // Leave this alone, you'll change myToString in toString
		myHash = 0; //Leave this alone, you'll change myHash in hashCode
	}

	/**
	 * Return string at specific index in this WordGram
	 * @param index in range [0..length() ) for string 
	 * @return string at index
	 */
	public String wordAt(int index) {
		if (index < 0 || index >= myWords.length) {
			throw new IndexOutOfBoundsException("bad index in wordAt "+index);
		}
		return myWords[index];
	}

	@Override
	public String toString(){
		if(myToString.equals("")) {
			myToString = String.join(" ", myWords);
		}
		return myToString;
	}

	@Override
	public int hashCode(){
		if(myHash == 0){
			String x = this.toString();
			myHash = x.hashCode();
		}
		return myHash;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (! (o instanceof WordGram) || o == null){    // we have the cover on a dog and we say, we know there's a dog underneath
			return false;
		}
		WordGram wg = (WordGram) o; // what is this? Can't you have things be the same without the same hashcode? we don't need this, but it's essentially taking the cover off the dog and being like hey, o is a dog.
		if (hashCode()!=wg.hashCode()) {  // we need this to cast wg onto o, so essentially o points to wg, but o does not have the attributes of a wordgram so we can't say o.myWords.length, but it doesn't have the attributes, only points to them
			return false;
		}
		if(myWords.length != wg.myWords.length) {
			return false;
		}

		for(int k = 0; k<myWords.length; k++) {
			if(!myWords[k].equals(wg.myWords[k])) {
				return false;
			}
		}

		return true;
	}

	public int length(){
		return myWords.length;
	}

	public WordGram shiftAdd(String last) {

		WordGram wg = new WordGram(myWords,1,myWords.length-1);
		String[] tempwords = new String[myWords.length];
		for(int k = 0; k < wg.myWords.length; k ++) {
			tempwords[k] = wg.myWords[k];
		}
		tempwords[tempwords.length-1] = last;

		return new WordGram(tempwords, 0, tempwords.length);
	}
}
