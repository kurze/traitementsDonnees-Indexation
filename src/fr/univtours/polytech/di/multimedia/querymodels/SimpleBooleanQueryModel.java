package fr.univtours.polytech.di.multimedia.querymodels;

import java.util.ArrayList;
import java.util.List;

import fr.univtours.polytech.di.multimedia.database.Database;
import fr.univtours.polytech.di.multimedia.database.Document;
import fr.univtours.polytech.di.multimedia.database.InvertedIndex;
import fr.univtours.polytech.di.multimedia.database.ValuedObject;
import fr.univtours.polytech.di.multimedia.signextractors.SignExtractor;

/**
 * Classe implémentant le modèle d'interrogation booléen simple.
 * @author Sébastien Aupetit
 */
public class SimpleBooleanQueryModel extends QueryModel {

    boolean questionMethodAND = true; //Je ne sais pas si chaque mot de la question sont liés par ET ou OU... 
    
  /**
   * Le constructeur.
   * @param database la base de données
   */
  public SimpleBooleanQueryModel(final Database database) {
    super(database);
  }

  /**
   * {@inheritDoc}
   * @see fr.univtours.polytech.di.multimedia.querymodels.QueryModel#getAnswers(java.lang.String)
   */
  @Override
  public List < ValuedObject > getAnswers(final String question) {
    final List < ValuedObject > results = new ArrayList < ValuedObject >();

    String filteredQuestion = getDatabase().filterSign(question);
    //extractions des mots de la question
    ArrayList<String> questionWords = new ArrayList<String>();
    SignExtractor questionWordExtractor = getDatabase().getSignExtractor();
    questionWordExtractor.setContent(filteredQuestion);
    String word;
    while((word = questionWordExtractor.nextToken()) != null){
	questionWords.add(word);
    }

    InvertedIndex invertedIndex = getDatabase().getInvertedIndex();
    List<Document> allDocs = new ArrayList<Document>();
    for (String questionWord : questionWords) {
	List<Document> docs = invertedIndex.getAllDocuments(questionWord);
	if(questionMethodAND) {
	    if(allDocs.size() == 0) {
		allDocs.addAll(docs);
	    }
	    else {
		List<Document> toDelete = new ArrayList<Document>();
		for (Document document : allDocs) {
		    if(!docs.contains(document)) {
			toDelete.add(document);
		    }
		}
		for (Document document : toDelete) {
		    allDocs.remove(document);
		}
	    }
	}
	else {
	    
	    for (Document document : docs) {
		if(!allDocs.contains(document)){
		    results.add(new ValuedObject(document, 1.0));
		    allDocs.add(document);
		}
	    }
	}
    }
    if(questionMethodAND){
	for (Document document : allDocs) {
	    results.add(new ValuedObject(document, 1));
	}
    }
    
    return results;
  }

}
