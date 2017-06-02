package org.training.nirmalya.thesearus;

import org.training.nirmalya.thesearus.ThesaurusMessageProtocol.GetEnglishSynonms;
import org.training.nirmalya.thesearus.ThesaurusMessageProtocol.MatchedEnglishWords;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import java.util.*;

public class EnglishActor extends UntypedActor {
	
	static Map<String,String[]> englishMap = new HashMap<String,String[]>(){{
		put("Strong", new String [] {"powerful","forceful","tough"});  
	}};
	//englishMap.);
	@Override
	public void onReceive(Object arg0) throws Throwable {
		
		if (arg0 instanceof GetEnglishSynonms) {
			GetEnglishSynonms synon = (GetEnglishSynonms) arg0;
			System.out.println("EnglishActor Message [" + arg0 + "] " + synon.getWord());
			String[] temp = englishMap.get(synon.getWord());
			getSender().tell(new MatchedEnglishWords(temp), getSelf());
		}
		else {
			System.out.println("EnglishActor Unknown Message [" + arg0 + "]");
			unhandled(arg0);
		}
	}
	
	public static Props props() {
	    return Props.create(new Creator<EnglishActor>() {
	      private static final long serialVersionUID = 1L;
	 
	      public EnglishActor create() throws Exception {
	        return new EnglishActor();
	      }
	    });
	} 
	
	public EnglishActor() {
		
	}
}

