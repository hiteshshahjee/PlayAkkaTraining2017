package org.training.nirmalya.thesearus;

import org.training.nirmalya.thesearus.ThesaurusMessageProtocol.GetHindiSynonms;
import org.training.nirmalya.thesearus.ThesaurusMessageProtocol.MatchedHindiWords;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import java.util.*;

public class HindiActor extends UntypedActor {
	
	static Map<String,String[]> hindiMap = new HashMap<String,String[]>(){{
		put("Strong", new String [] {"मजबूत","कड़ा","गहरी"});  
	}};
	
	@Override
	public void onReceive(Object arg0) throws Throwable {
		
		if (arg0 instanceof GetHindiSynonms) {
			GetHindiSynonms synon = (GetHindiSynonms) arg0;
			System.out.println("HindiActor Message [" + arg0 + "] " + synon.getWord());
			String[] temp = hindiMap.get(synon.getWord());
			getSender().tell(new MatchedHindiWords(temp), getSelf());
		}
		else {
			System.out.println("HindiActor Unknown Message [" + arg0 + "]");
			unhandled(arg0);
		}
	}
	
	public static Props props() {
	    return Props.create(new Creator<HindiActor>() {
	      private static final long serialVersionUID = 1L;
	 
	      public HindiActor create() throws Exception {
	        return new HindiActor();
	      }
	    });
	} 
	
	public HindiActor() {
		
	}
}

