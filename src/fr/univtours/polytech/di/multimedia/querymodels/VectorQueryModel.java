package fr.univtours.polytech.di.multimedia.querymodels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.univtours.polytech.di.multimedia.database.Database;
import fr.univtours.polytech.di.multimedia.database.DecreasingValuedObjectComparator;
import fr.univtours.polytech.di.multimedia.database.Document;
import fr.univtours.polytech.di.multimedia.database.InvertedIndex;
import fr.univtours.polytech.di.multimedia.database.ValuedObject;
import fr.univtours.polytech.di.multimedia.signextractors.SignExtractor;

/**
 * Classe impl�mentant le mod�le d'interrogation vectoriel.
 * @author S�bastien Aupetit
 */
public class VectorQueryModel extends QueryModel {
  /** Indique s'il faut utiliser une ponderation TF-IDF. */
  private final boolean useTFIDF;

  /**
   * Le constructeur.
   * @param database la base de donn�es
   * @param useTFIDF indique s'il faut utiliser une ponderation TF-IDF
   */
  public VectorQueryModel(final Database database, final boolean useTFIDF) {
    super(database);
    this.useTFIDF = useTFIDF;
  }

	/**
	 * {@inheritDoc}
	 * 
	 * @see fr.univtours.polytech.di.multimedia.querymodels.QueryModel#getAnswers(java.lang.String)
	 */
	@Override
	public List<ValuedObject> getAnswers(final String question) {
		final List<ValuedObject> results = new ArrayList<ValuedObject>();

		int nbTotalDocs = getDatabase().getDocuments().size();
 
		// extractions des mots de la question
		ArrayList<String> questionWords = new ArrayList<String>();
		
		// application de l'extracteur sur la question
		SignExtractor extractor = getDatabase().getSignExtractor();
		extractor.setContent(question);
		String word;
		while ((word = extractor.nextToken()) != null) {
			//application des filtres sur chaque signe extrait 
			word = getDatabase().filterSign(word);
			questionWords.add(word);
		}

		// r�cup�ration de la listes des documents index�s
		InvertedIndex invertedIndex = getDatabase().getInvertedIndex();
		List<Document> docs = new ArrayList<Document>();
		for (String questionWord : questionWords) {
			docs.addAll(invertedIndex.getAllDocuments(questionWord));
		}

		// parcours de chaque document
		for (Document document : docs) {
			double somme = 0;
			double sommeCarre = 0;
			double sommeWord = 0;
			// parcours de chaque signe de la question
			for (String questionWord : questionWords) {
				double occurence = invertedIndex.getWordOccurrences(
						questionWord, document);
				sommeWord++;
				if(occurence != 0){
					if (useTFIDF) {
						int nbDocs = invertedIndex.getAllDocuments(questionWord)
								.size();
						occurence = occurence * (nbTotalDocs / nbDocs);
					}
					somme += occurence;
					sommeCarre += (occurence * occurence);
				}
				
				
			}
			if (somme != 0) {
				double normes = Math.sqrt(sommeCarre) * Math.sqrt(sommeWord);
				double cos = (normes == 0 ? 0 : somme / normes);
				results.add(new ValuedObject(document, cos));
			}
		}

		Collections.sort(results, new DecreasingValuedObjectComparator());

		return results;
	}
}
