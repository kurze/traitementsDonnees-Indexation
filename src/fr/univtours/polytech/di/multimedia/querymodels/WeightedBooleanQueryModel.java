package fr.univtours.polytech.di.multimedia.querymodels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.antlr.runtime.tree.Tree;

import fr.univtours.polytech.di.multimedia.database.Database;
import fr.univtours.polytech.di.multimedia.database.DecreasingValuedObjectComparator;
import fr.univtours.polytech.di.multimedia.database.Document;
import fr.univtours.polytech.di.multimedia.database.InvertedIndex;
import fr.univtours.polytech.di.multimedia.database.ValuedObject;
import fr.univtours.polytech.di.multimedia.parser.BooleanExpressionParser;
import fr.univtours.polytech.di.multimedia.parser.ExpressionParser;

/**
 * Classe implémentant le modèle d'interrogation booléen pondéré.
 * 
 * @author Sébastien Aupetit
 */
public class WeightedBooleanQueryModel extends QueryModel {

	/** Indique s'il faut utiliser une pondération TF-IDF. */
	private final boolean useTFIDF;
	int nbTotalDocs;

	/**
	 * Le constructeur.
	 * 
	 * @param database
	 *            la base de données
	 * @param useTFIDF
	 *            indique s'il faut utiliser une pondération TF-IDF
	 */
	public WeightedBooleanQueryModel(final Database database,
			final boolean useTFIDF) {
		super(database);
		this.useTFIDF = useTFIDF;
		nbTotalDocs = database.getDocuments().size();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see fr.univtours.polytech.di.multimedia.querymodels.QueryModel#getAnswers(java.lang.String)
	 */
	@Override
	public List<ValuedObject> getAnswers(final String question) {
		final List<ValuedObject> results = new ArrayList<ValuedObject>();

		final Tree tree = ExpressionParser.parseQuery(question);
		List<Document> docs = getDatabase().getDocuments();
		for (Document document : docs) {
			double score = value(tree, document);
			if (score != 0) {
				results.add(new ValuedObject(document, score));
			}
		}
		Collections.sort(results, new DecreasingValuedObjectComparator());
		return results;
	}

	private double value(Tree tree, Document doc) {
		double resultat = 0;
		switch (tree.getType()) {
		case BooleanExpressionParser.AND:
			resultat = value(tree.getChild(0), doc)
					* value(tree.getChild(1), doc);
			break;
		case BooleanExpressionParser.OR:
			resultat = value(tree.getChild(0), doc)
					+ value(tree.getChild(1), doc);
			break;
		case BooleanExpressionParser.SIGN:
			// This is where magic happens
			String sign = tree.getText();
			InvertedIndex invertedIndex = getDatabase().getInvertedIndex();
			resultat = invertedIndex.getWordOccurrences(sign, doc);
			if (useTFIDF) {
				int nbDocs = invertedIndex.getAllDocuments(sign).size();
				resultat = resultat * (nbTotalDocs / nbDocs);
			}
			break;
		case BooleanExpressionParser.NOT:
			resultat = -value(tree.getChild(0), doc);
			break;
		default:
			break;
		}
		return resultat;
	}

}
