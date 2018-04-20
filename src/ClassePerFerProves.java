
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Josep
 */
public class ClassePerFerProves {

    static final String RED = "\033[0;31m";
    static final String BLUE = "\033[0;34m";
    static final String BLACK = "\u001B[30m";

    public static void main(String[] args) {
        ClassePerFerProves programa = new ClassePerFerProves();
        programa.inici();

    }

    void inici() {
        //BibliotecaInterficieUsuari biblio = new BibliotecaInterficieUsuari();

        String simbol = "*************************************************";
        String missatge = "** ATENCIÓ: No ha premut [ENTRAR] i no s'ha emmagatzem "
                + "l'hora de sortida **ATENCIÓ: No ha premut [ENTRAR] i no s'ha emmagatzem l'hora de sortida **";
        String error = "*************************************************///////";
        System.out.println(simbol.length() + "  " + missatge.length() + "  " + error.length());
        
        String tallat;
        int saltLinia = simbol.length();
        tallat = wrapString(missatge,saltLinia);
        System.out.println(tallat);
        error(tallat);

    }

    void error(String text) {
        String simbols = RED + "*************************************************";
        int begin = 0;
        int llargSimbol = simbols.length();
        int llargText = text.length();
        System.out.println();
        for (int i = 0; i < 1; i++) {
            System.out.println(simbols);
        }
        System.out.println(RED + "******************ERROR**************************");
        for (int i = 0; i < 1; i++) {
            System.out.println(simbols);
        }
        System.out.println(text);
        //imprimir les 2 linies del marc per tancar missatge d'ERROR
        for (int i = 0; i < 1; i++) {
            System.out.println(RED + simbols + BLACK);
        }
        System.out.println();
    }
    String wrapString(String string, int charWrap) {
    int lastBreak = 0;
    int nextBreak = charWrap;
    if (string.length() > charWrap) {
        String setString = "";
        do {
            while (string.charAt(nextBreak) != ' ' && nextBreak > lastBreak) {
                nextBreak--;
            }
            if (nextBreak == lastBreak) {
                nextBreak = lastBreak + charWrap;
            }
            setString += string.substring(lastBreak, nextBreak).trim() + "\n";
            lastBreak = nextBreak;
            nextBreak += charWrap;

        } while (nextBreak < string.length());
        setString += string.substring(lastBreak).trim();
        return setString;
    } else {
        return string;
    }
}

}
