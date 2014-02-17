package fr.univtours.polytech.di.multimedia.filters;



/**
 * Classe de filtrage implémentant un filtre de mots vides.
 * @author Sébastien Aupetit
 */
public class StopWordFilter implements Filter {

	/// Liste des mots à filtrer (retirer)
    private String[] motsCommuns = {
	    "le",
	    "de",
	    "un",
	    "être",
	    "et",
	    "à",
	    "il",
	    "avoir",
	    "ne",
	    "je",
	    "son",
	    "que",
	    "se",
	    "qui",
	    "ce",
	    "dans",
	    "en",
	    "du",
	    "elle",
	    "au",
	    "de",
	    "ce",
	    "le",
	    "pour",
	    "pas",
	    "que",
	    "vous",
	    "par",
	    "sur",
	    "faire",
	    "plus",
	    "dire",
	    "me",
	    "on",
	    "mon",
	    "lui",
	    "nous",
	    "comme",
	    "mais",
	    "pouvoir",
	    "avec",
	    "tout",
	    "y",
	    "aller",
	    "voir",
	    "en",
	    "bien",
	    "où",
	    "sans",
	    "tu",
	    "ou",
	    "leur",
	    "homme",
	    "si",
	    "deux",
	    "mari",
	    "moi",
	    "vouloir",
	    "te",
	    "femme",
	    "venir",
	    "quand",
	    "grand",
	    "celui",
	    "si",
	    "notre",
	    "devoir",
	    "là",
	    "jour",
	    "prendre",
	    "même",
	    "votre",
	    "tout",
	    "rien",
	    "petit",
	    "encore",
	    "aussi",
	    "quelque",
	    "dont",
	    "tout",
	    "mer",
	    "trouver",
	    "donner",
	    "temps",
	    "ça",
	    "peu",
	    "même",
	    "falloir",
	    "sous",
	    "parler",
	    "alors",
	    "main",
	    "chose",
	    "ton",
	    "mettre",
	    "vie",
	    "savoir",
	    "yeux",
	    "passer",
	    "autre"
    };
    
    // indique si les mots reçu ont été filtré par CaseFilter
    private boolean caseFilterApplied;
    
    // indique si les mots reçu ont été filté par AccentFilter
    private boolean accentFilterApplied;
  
	/**
	 * Le constructeur.
	 * 
	 * @param caseFilterApplied
	 *            indique si les signes ont été filtrés en minuscule
	 * @param accentFilterApplied
	 *            indique si les signes ont été filtrés sans accent et sans
	 *            caractères spéciaux
	 */
	public StopWordFilter(final boolean caseFilterApplied,
			final boolean accentFilterApplied) {
		this.caseFilterApplied = caseFilterApplied;
		this.accentFilterApplied = accentFilterApplied;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see fr.univtours.polytech.di.multimedia.filters.Filter#filter(java.lang.String)
	 */
	@Override
	public String filter(final String sign) {
		for (String motCommun : motsCommuns) {
			// On applique les filtres
			if (caseFilterApplied) {
				CaseFilter caseFilter = new CaseFilter();
				motCommun = caseFilter.filter(motCommun);
			}
			if (accentFilterApplied) {
				AccentFilter accentFilter = new AccentFilter();
				motCommun = accentFilter.filter(motCommun);
			}
			// Si le mot est dans la liste, on quitte la fonction
			if (motCommun.equals(sign)) {
				return "";
			}
		}
		return sign;
	}

}
