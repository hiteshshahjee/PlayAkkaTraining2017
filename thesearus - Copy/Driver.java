package org.training.nirmalya.thesearus;



import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.training.nirmalya.thesearus.ThesaurusMessageProtocol;

import com.typesafe.config.ConfigFactory;
import com.typesafe.config.Config;

import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;


public class Driver {

	public static void main(String[] args) {
		
		ActorSystem system = ActorSystem.create("Thesaurus");
		
		ActorRef thesaurusActor = system.actorOf(ThesaurusActor.props(),"ThesaurusActor");
		
		final Inbox inbox = Inbox.create(system);
		
		inbox.send(thesaurusActor, new ThesaurusMessageProtocol.SynonymRequest("Strong", "ALL"));
        
        try {
			System.out.println((ThesaurusMessageProtocol.MatchedWords)inbox.receive(Duration.create(2, TimeUnit.SECONDS)));
		} catch (TimeoutException e1) {
			
			e1.printStackTrace();
		}       
        
        System.out.println("terminating actor system!");
        system.terminate();

	}

}
