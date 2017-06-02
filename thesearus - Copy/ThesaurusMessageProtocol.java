package org.training.nirmalya.thesearus;

import java.io.Serializable;

public class ThesaurusMessageProtocol {
	
	public static class SynonymRequest implements Serializable {
		private static final long serialVersionUID = 1L;		
		private String word;
		private String lang;
		
		public String getWord() {
			return word;
		}

		public String getLang() {
			return lang;
		}

		public SynonymRequest(String word, String lang) {
			super();
			this.word = word;
			this.lang = lang;
		}

		public String toString() {
			return ("Thesaurus Request Accepted");
		}
	}
	
	public static class MatchedWords implements Serializable {
		private static final long serialVersionUID = 1L;
		public String toString() {
			return ("Matched Words");
		}
	}
	
	public static class GetEnglishSynonms implements Serializable {
		private static final long serialVersionUID = 1L;
		private String word;
		
		public String getWord() {
			return word;
		}

		public GetEnglishSynonms(String word) {
			super();
			this.word = word;
		}

		public String toString() {
			return ("GetEnglishSynonms Request Accepted");
		}
	}
	
	public static class GetHindiSynonms implements Serializable {
		private static final long serialVersionUID = 1L;
		private String word;
		
		public String getWord() {
			return word;
		}

		public GetHindiSynonms(String word) {
			super();
			this.word = word;
		}

		public String toString() {
			return ("GetHindiSynonms Request Accepted");
		}
	}
	
	public static class MatchedEnglishWords implements Serializable {
		private static final long serialVersionUID = 1L;
		String[] matchedWords;
		
		public String[] getMatchedWords() {
			return matchedWords;
		}

		public MatchedEnglishWords(String[] matchedWords) {
			super();
			this.matchedWords = matchedWords;
		}

		public String toString() {
			return ("MatchedEnglishWords Request Processed");
		}
	}
	
	public static class MatchedHindiWords implements Serializable {
		private static final long serialVersionUID = 1L;
		String[] matchedWords;
		
		public String[] getMatchedWords() {
			return matchedWords;
		}

		public MatchedHindiWords(String[] matchedWords) {
			super();
			this.matchedWords = matchedWords;
		}

		public String toString() {
			return ("MatchedHindiWords Request Processed");
		}
	}

}
