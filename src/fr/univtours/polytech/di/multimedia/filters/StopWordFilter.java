package fr.univtours.polytech.di.multimedia.filters;



/**
 * Classe de filtrage impl�mentant un filtre de mots vides.
 * @author S�bastien Aupetit
 */
public class StopWordFilter implements Filter {

	/// Liste des mots � filtrer (retirer)
    private String[] motsCommuns = {
	    "le",
	    "de",
	    "un",
	    "�tre",
	    "et",
	    "�",
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
	    "o�",
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
	    "l�",
	    "jour",
	    "prendre",
	    "m�me",
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
	    "�a",
	    "peu",
	    "m�me",
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
    
    // indique si les mots re�u ont �t� filtr� par CaseFilter
    private boolean caseFilterApplied;
    
    // indique si les mots re�u ont �t� filt� par AccentFilter
    private boolean accentFilterApplied;
  
	/**
	 * Le constructeur.
	 * 
	 * @param caseFilterApplied
	 *            indique si les signes ont �t� filtr�s en minuscule
	 * @param accentFilterApplied
	 *            indique si les signes ont �t� filtr�s sans accent et sans
	 *            caract�res sp�ciaux
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
