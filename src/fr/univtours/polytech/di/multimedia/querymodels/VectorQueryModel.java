package fr.univtours.polytech.di.multimedia.querymodels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.univtours.polytech.di.multimedia.database.Database;
import fr.univtours.polytech.di.multimedia.database.DecreasingValuedObjectComparator;
import fr.univtours.polytech.di.multimedia.database.Document;
import fr.univtours.polytech.di.multimedia.database.InvertedIndex;
import fr.univtours.polytech.di.multimedia.database.ValuedObject;
import fr.univtours.polytech.di.multimedia.signextractors.WordExtractor;

/**
 * Classe implémentant le modèle d'interrogation vectoriel.
 * @author Sébastien Aupetit
 */
public class VectorQueryModel extends QueryModel {
  /** Indique s'il faut utiliser une ponderation TF-IDF. */
  private final boolean useTFIDF;

  /**
   * Le constructeur.
   * @param database la base de données
   * @param useTFIDF indique s'il faut utiliser une ponderation TF-IDF
   */
  public VectorQueryModel(final Database database, final boolean useTFIDF) {
    super(database);
    this.useTFIDF = useTFIDF;
  }

  /**
   * {@inheritDoc}
   * @see fr.univtours.polytech.di.multimedia.querymodels.QueryModel#getAnswers(java.lang.String)
   */
  @Override
  public List < ValuedObject > getAnswers(final String question) {
    final List < ValuedObject > results = new ArrayList < ValuedObject >();
       
    int nbTotalDocs = getDatabase().getDocuments().size();
    
    String filteredQuestion = getDatabase().filterSign(question);
    //extractions des mots de la question
    ArrayList<String> questionWords = new ArrayList<String>();
    WordExtractor questionWordExtractor = new WordExtractor();
    questionWordExtractor.setContent(filteredQuestion);
    String word;
    while((word = questionWordExtractor.nextToken()) != null){
      questionWords.add(word);
    }
    
    InvertedIndex invertedIndex = getDatabase().getInvertedIndex();
    List<Document> docs = new ArrayList<Document>();
    for (String questionWord : questionWords) {
	docs.addAll(invertedIndex.getAllDocuments(questionWord));
    }
    
    for (Document document : docs) {
	double somme = 0;
	double sommeCarre = 0;
	double sommeWord = 0;
	for (String questionWord : questionWords) {
	    double occurence = invertedIndex.getWordOccurrences(questionWord, document);
	    if(useTFIDF) {
		int nbDocs = invertedIndex.getAllDocuments(questionWord).size();
		occurence = occurence * (nbTotalDocs / nbDocs); //TODO maybe we can use log() or log(1+) or even ln
	    }
	    somme += occurence;
	    sommeCarre += (occurence * occurence);
	    sommeWord++;
	}
	if(somme != 0) {
	    double normes = Math.sqrt(sommeCarre) * Math.sqrt(sommeWord);
	    double cos = (normes==0?0:somme/normes);
	    results.add(new ValuedObject(document, cos));
	}
    }
    
    Collections.sort(results, new DecreasingValuedObjectComparator());
    
    return results;
  }
}
