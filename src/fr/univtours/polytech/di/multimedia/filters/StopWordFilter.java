package fr.univtours.polytech.di.multimedia.filters;



/**
 * Classe de filtrage impl�mentant un filtre de mots vides.
 * @author S�bastien Aupetit
 */
public class StopWordFilter implements Filter {

    String[] motsCommuns = {
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
    
    boolean caseFilterApplied;
    boolean accentFilterApplied;
  /**
   * Le constructeur.
   * @param caseFilterApplied indique si les signes ont �t� filtr�s en minuscule
   * @param accentFilterApplied indique si les signes ont �t� filtr�s sans
   *          accent et sans caract�res sp�ciaux
   */
  public StopWordFilter(final boolean caseFilterApplied,
      final boolean accentFilterApplied) {
    this.caseFilterApplied = caseFilterApplied;
    this.accentFilterApplied = accentFilterApplied;    
  }

  /**
   * {@inheritDoc}
   * @see fr.univtours.polytech.di.multimedia.filters.Filter#filter(java.lang.String)
   */
  @Override
  public String filter(final String sign) {
      String newSign = sign;
    if(caseFilterApplied){
	CaseFilter caseFilter = new CaseFilter();
	newSign = caseFilter.filter(sign);
    }
    if(accentFilterApplied) {
	AccentFilter accentFilter = new AccentFilter();
	newSign = accentFilter.filter(newSign);
    }
    for (String motCommun : motsCommuns) {
	if(motCommun.contains(newSign)){
	    newSign = null;
	    break;
	}
    }
    return newSign;
  }

}
