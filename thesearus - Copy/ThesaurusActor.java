package org.training.nirmalya.thesearus;


/*import org.training.nirmalya.thesearus.ThesaurusMessageProtocol.PingMessage;
import org.training.nirmalya.thesearus.ThesaurusMessageProtocol.PongMessage;*/
import org.training.nirmalya.thesearus.ThesaurusMessageProtocol.SynonymRequest;

import java.util.Arrays;

import org.training.nirmalya.thesearus.ThesaurusMessageProtocol.GetEnglishSynonms;
import org.training.nirmalya.thesearus.ThesaurusMessageProtocol.GetHindiSynonms;
import org.training.nirmalya.thesearus.ThesaurusMessageProtocol.MatchedEnglishWords;
import org.training.nirmalya.thesearus.ThesaurusMessageProtocol.MatchedHindiWords;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;

public class ThesaurusActor extends UntypedActor {
	
	// TODO [NS]: To be given as exercise
	
	private ActorRef englishActor = null;
	private ActorRef hindiActor =  null;

	@Override
	public void onReceive(Object arg0) throws Throwable {
		
		// TODO: Boss doesn't do anything by herself. She asks Kalia and Sambha to do the needful.
		// TODO: Make Sambha and Kalia identify themselves, 
		// when they respond to Pong messages
		if (arg0 instanceof SynonymRequest) {
			SynonymRequest synon = (SynonymRequest) arg0;
			System.out.println("Thesaurus Actor Message [" + arg0 + "]" + " " + synon.getWord() + " " + synon.getLang());
			
			if(synon.getLang() == "ENGLISH"||  synon.getLang() == "ALL")
			{
				englishActor = getContext().actorOf(EnglishActor.props());
				englishActor.tell(new GetEnglishSynonms(synon.getWord()), getSelf());
			}
			if(synon.getLang() == "HINDI" || synon.getLang() == "ALL")
			{
				hindiActor = getContext().actorOf(HindiActor.props());
				hindiActor.tell(new GetHindiSynonms(synon.getWord()), getSelf());
			}			
			
			getSender().tell(new ThesaurusMessageProtocol.MatchedWords(), getSelf());

		}	
		else if (arg0 instanceof MatchedEnglishWords) {
			MatchedEnglishWords synon = (MatchedEnglishWords) arg0;
			System.out.println("Thesaurus Actor Message: MatchedEnglishWords [" + arg0 + "] " + Arrays.toString(synon.getMatchedWords()));
		}
		else if (arg0 instanceof MatchedHindiWords) {
			MatchedHindiWords synon = (MatchedHindiWords) arg0;
			System.out.println("Thesaurus Actor Message: MatchedHindiWords [" + arg0 + "] " + Arrays.toString(synon.getMatchedWords()));
		}
		else {
			System.out.println("Unknown Message:Thesaurus [" + arg0 + "]");
			unhandled(arg0);
		}

	}
	
	public static Props props() {
	    return Props.create(new Creator<ThesaurusActor>() {
	      private static final long serialVersionUID = 1L;
	 
	      public ThesaurusActor create() throws Exception {
	        return new ThesaurusActor();
	      }
	    });
	} 

	public ThesaurusActor() {
	
	}

}
