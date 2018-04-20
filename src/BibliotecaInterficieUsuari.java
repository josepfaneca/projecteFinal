/*
En aquesta tasca un demanarem que creeu una classe anomenada 
BibliotecaInterficieUsuari on hi copiareu totes les funcions reutilitzables, 
relacionades amb la creació d'interfícies d'usuari, que hàgiu fet fins ara. 
A més us demanarem també que complementeu la biblioteca amb noves funcions que 
us ajudin a crear interfícies de consola de forma ràpida i fàcil.
 */

/**
 * @author Josep
 */
import java.util.Scanner;

public class BibliotecaInterficieUsuari {

    static final String BLACK = "\u001B[30m";
    static final String BLUE = "\033[0;34m";
    static final int DNI = 0;  //Columna on s'emmagatzema el DNI a la matriu de corredors
    // per tal de fer quadrar les posicions de la resta de dades.
    static final char FARCIMENT = ' ';
    static final int MAXIM_CORREDORS = 30;//no s'inscriuran mai més de 30 corredors
    static final int MIDA_MAXIMA_NOM = 45; // Mida màxima que donarem al nom d'un corredor en el llistat
    static final int MOBIL = 2;   //Columna on s'emmagatzema el número de mòbil a la matriu de corredors
    static final int NOM = 1;  //Columna on s'emmagatzema el nom a la matriu de corredors
    static final String RED = "\033[0;31m";

    boolean afegirDni(String valor, String[][] valors) {
//afegir enters a una array quan troni el valor buid -1
        boolean elements = false;
        for (int i = 0; (i < valors.length); i++) {
            if (valors[i][DNI] == null) {
                valors[i][DNI] = valor;
                elements = true;
                System.out.println("Inserció realitzada");
                break;
            }
        }
//controlar que si el valor està repetit, torni a escriure un valor buid
//FUNCIONA
        for (int i = 0; i < valors.length; i++) {
            for (int j = i + 1; j < valors.length; j++) {
                if ((valors[i] == valors[j]) && (i != j) && (valors[j] != null)) {
                    valors[j] = null;
                    elements = false;
                    break;
                }
            }
        }

        return elements;
    }

    /*
    Una funció afegirLinies, que afegeixi un nombre determinat de línies buides,
    a fi de simular un salt a una nova pantalla.
     */
    void afegirLinies(int numeroSalts) {
        for (int i = 0; i < numeroSalts; i++) {
            System.out.println();
        }
    }

    void afegirNom(String valor, String[][] valors) {
//afegir enters a una array quan troni el valor buid -1
        boolean elements;
        for (int i = 0; (i < valors.length); i++) {
            if (valors[i][NOM] == null) {
                valors[i][NOM] = valor;
                elements = true;
                System.out.println("Inserció realitzada");
                break;
            }
        }
//return elements;
    }

    void afegirTf(String valor, String[][] valors) {
//afegir enters a una array quan troni el valor buid -1
        boolean elements;
        for (int i = 0; (i < valors.length); i++) {
            if (valors[i][MOBIL] == null) {
                valors[i][MOBIL] = valor;
                elements = true;
                System.out.println("Inserció realitzada");
                break;
            }
        }
//return elements;
    }

    boolean buscarDniLlista(String dni, String[][] participants) {
        //funció per buscar DNI repetits a la llista. Buscar a la columna 0 amb equals.
        boolean dniOk = true;
        for (int numFila = DNI; numFila < participants.length; numFila++) {
            if (dni.equals(participants[numFila][DNI])) {
                dniOk = false;
                break;
            }
        }
        return dniOk;
    }

    String cadenaAMajuscula(String cad) {
        char minuscula, majuscula;
        //recòrrer la cadena
        for (int i = 0; i < cad.length(); i++) {
            //El mètode .codePointAt() retorna el codi ASCII d'un carácter en concret
            //a partir del valor 97 i fins el valor 122 trobarem la codificació de les lletres minúscules
            if ((cad.codePointAt(i) >= 97) && (cad.codePointAt(i) <= 122)) {
                minuscula = cad.charAt(i);//guardo la minúscula
                majuscula = (char) (minuscula - 32);//si li resto 32, retorna la majúscula
                cad = cad.replace(minuscula, majuscula);//mètode replace(char oldChar, char newChar)
            }
        }
        return cad;
    }

    //buscar la cadena més llarga per a cada columna i guardar en un array. 
    //desprès utilitzarem aquest valor per farcir les cadenes que no arribin
    //a aquest valor.
    int[] cadenaMesLlarga(String[][] cadena) {
        int[] longer = new int[cadena[0].length];
        for (int i = 0; i < cadena.length; i++) {
            for (int j = 0; j < cadena[j].length; j++) {
                if (cadena[i][j].length() > longer[j]) {
                    longer[j] = cadena[i][j].length();
                }
            }
        }
        return longer;
    }

    boolean comprobarTf(String tf) {
        boolean tfOk = false;
        if (tf.length() == 9) {
            if (tf.charAt(0) == '6') {
                for (int i = 0; i < tf.length(); i++) {
                    char aux = tf.charAt(i);
                    if ((aux >= '0') && (aux <= '9')) {
                        tfOk = true;
                    } else {
                        tfOk = false;
                        break;
                    }
                }
            } else {
                System.out.println(RED + "El mòbil ha de començar amb 6");
                tfOk = false;
            }
        } else {
            System.out.println(RED + "El mòbil ha de contenir 9 digits");
            tfOk = false;
        }
        return tfOk;
    }

    /*
    Una funció anomenada confirmar, que mostri un missatge per pantalla i
    s'esperi la confirmació de l'usuari amb un caràcter S/s o N/n. Si l'usuari
    confirma amb S/s es retorna cert, en cas contrari fals.
     */
    boolean confirmar(String valorDefecte) {
        Scanner scanner = new Scanner(System.in);
        String resposta = "";
        System.out.println("Estas a punt de sortir de l'aplicació. Si marxes perdràs totes les dades introduides fins ara. Vols sortir?");
        System.out.println("Indica S (si) o N (no) i prem [ENTRAR]. Si nomes prems [ENTRAR] es considerarà una resposta afirmativa.");
        resposta = scanner.nextLine();
        if (resposta.isEmpty()) {
            System.out.println(valorDefecte);
        }
        return resposta.isEmpty() || resposta.charAt(0) == 'S' || resposta.charAt(0) == 's';
    }

    boolean confirmarSortir() {
        Scanner scanner = new Scanner(System.in);
        String resposta = "";
        System.out.println("Estas a punt de sortir de l'aplicació. Si marxes perdràs totes les dades introduides fins ara. Vols sortir?");
        System.out.println("Indica S (si) o N (no) i prem [ENTRAR]. Si nomes prems [ENTRAR] es considerarà una resposta afirmativa.");
        resposta = scanner.nextLine();
        return resposta.isEmpty() || resposta.charAt(0) == 'S' || resposta.charAt(0) == 's';
    }

    /* ------------------------------------------------------------------------
    ---------------------------------------------------------------------------
    Fi Tasca 3 Implementació d'una biblioteca per crear interfícies d'usuari
    ---------------------------------------------------------------------------
    ---------------------------------------------------------------------------
     */
    char controlChar(char[] opcions, String missatge, String textError) {
        Scanner lector = new Scanner(System.in);
        boolean esCorrecte = false;
        String cadena = "";
        do {
            System.out.print(missatge);
            cadena = lector.next();
            for (int i = 0; i < opcions.length; i++) {
                //controlar que no entri més d'un caràcter
                if (cadena.equals(String.valueOf(opcions[i]))) {
                    esCorrecte = true;
                    i = opcions.length;
                }
            }
            if (!esCorrecte) {
                System.out.println(RED + textError);
            }
        } while (!esCorrecte);
        //convertir String a char
        return cadena.charAt(0);
    }

    String demanarCadena(String missatge) {
        String cadena;
        System.out.print(missatge);
        Scanner lector = new Scanner(System.in);
        cadena = lector.next();
        return cadena;
    }

    String demanarNom(String missatge) {
        String cadena;
        Scanner lector = new Scanner(System.in);
        do {
            System.out.print(missatge);
            cadena = lector.nextLine();
        } while (cadena.trim().length() == 0);//evitar que premi sense voler o espais
        return cadena;
    }

    String demanarTf(String missatge) {
        String cadena;
        Scanner lector = new Scanner(System.in);
        do {
            System.out.print(missatge);
            cadena = lector.nextLine();
        } while (cadena.trim().length() == 0);//evitar que premi sense voler o espais
        return cadena;
    }

    boolean digitsCorrectes(String dni) {
        //funció auxiliar per comprobar que els 8 primers caràcters de la cadena són digits
        boolean dniOk = false;
        if (dni.length() >= 8) {
            for (int i = 0; i < dni.length() - 1; i++) {
                char aux = dni.charAt(i);
                if ((aux >= '0') && (aux <= '9')) {
                    dniOk = true;
                } else {
                    dniOk = false;
                    break;
                }
            }
        } else {
            dniOk = false;
        }
        return dniOk;
    }

    /*--------------------------------------------------------------------------
    ----------------------------------------------------------------------------
    FI FUNCIONS implementades a la tasca 4 de l'activitat A404
     */
 /* ------------------------------------------------------------------------
    ---------------------------------------------------------------------------
    Tasca 3 Implementació d'una biblioteca per crear interfícies d'usuari
    ---------------------------------------------------------------------------
    ---------------------------------------------------------------------------
     */

 /*Una funció entrarCadena. igual que entrarEnter,
    però retorna l'entrada com a tipus text.
     */
    String entrarCadena(String missatge) {
        Scanner lector = new Scanner(System.in);
        String cadena;
        boolean correcte;
        do {
            System.out.println(missatge);
            cadena = lector.nextLine();
            correcte = true;
            //si la cadena està buida: mostrar missatge ERROR i
            //tornar a repetir el procès.
            if (cadena.trim().isEmpty() || cadena.isEmpty()) {
                correcte = false;
                System.out.println(RED + "ERROR: si es plau, escrigui quelcom.");
            }
        } while (!correcte);

        return cadena;
    }

    //1.Afegiu-hi també la funció entrarEnter
    int entrarEnter(String missatge) {
        Scanner scanner = new Scanner(System.in);
        int enter = 0;
        boolean correcte = false;
        do {
            System.out.print(missatge);
            correcte = scanner.hasNextInt();
            if (correcte) {
                enter = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
                System.out.println("Cal que introduïu un valor vàlid");
            }
        } while (!correcte);
        return enter;
    }

    int entrarEnter(String missatge, int limit, int limitInf) {
        int entrada = 0;
        Scanner lector = new Scanner(System.in);
        boolean tipusCorrecte;
        boolean numOk = false;
        while (!numOk) {
            System.out.println(missatge);
            tipusCorrecte = lector.hasNextInt();
            if (tipusCorrecte) {
                entrada = lector.nextInt();
                if (entrada >= limitInf && entrada <= limit) {
                    numOk = true;
                } else {
                    System.out.println(RED + "Si us plau, entri un dorsal entre 0 i " + limit + BLACK);
                    lector.nextLine();
                }
            } else {
                System.out.println(RED + "Si us plau, entri un número enter" + BLACK);
                lector.nextLine();
            }
        }
        return entrada;
    }

    /*
    Una funció entrarPerContinuar, que mostri un text per pantalla indicant a
    l'usuari que premi ENTRAR per continuar i un cop premut salti a una nova
    pantalla. Aquesta acció podria executar-se després de mostrar un text llarg
    o un a llista llarga de dades de manera que permeti mantenir visible la
    pantalla, mentre l'usuari no premi la tecla ENTRAR en senyal de confirmació
    que ja ha acabat de llegir i vol passar a la següent pantalla.
     */
    void entrarPerContinuar() {
        System.out.println("Estas a punt de sortir de l'aplicació. Si marxes perdràs totes les dades introduides fins ara. Vols sortir?");
        System.out.println("Si prems [ENTRAR] es considerarà una resposta afirmativa.");
    }

    /*
    Una funció anomenada error que mostri un missatge d'error formatant el text
    passat per paràmetre de forma específica a fi que alerti a l'usuari que s'ha
    produït un error i informi de quin.
     */
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
        //imprimir missatge 
        System.out.println(BLUE + text + BLUE);
        //imprimir les 2 linies del marc per tancar missatge d'ERROR
        for (int i = 0; i < 1; i++) {
            System.out.println(RED + simbols + BLACK);
        }
        System.out.println();
    }

    //funció per farcir les cadenes que no arribin a la mida amb el caràcter
    //de farciment.
    String farciment(String cadena, char farciment, int mida) {
        int midaActual = cadena.length();
        while (midaActual < mida) {
            cadena += farciment;
            midaActual++;
        }
        return cadena;
    }

    String farciment2(String cadenaAmpliar, char farcir, int mida) {
        if (cadenaAmpliar.length() < mida) {
            int i = cadenaAmpliar.length();
            while (i < mida) {
                cadenaAmpliar += farcir;
                i++;
            }
        }
        return cadenaAmpliar;
    }
    String[] farciment3(String[] cadenaAmpliar, char farcir, int[] mida) {
        for (int i = 0; i < cadenaAmpliar.length; i++) {
            if (cadenaAmpliar[i].length() < mida[i]) {
                int j = cadenaAmpliar[i].length();
                while (j < mida[i]) {
                    cadenaAmpliar[i] += farcir;
                    j++;
                }
            }
        }
        return cadenaAmpliar;
    }

    void imprimirArrayBidi(String[][] participants, int comptador) {

        System.out.println("DNI       NOM                                            MÒBIL      \n"
                + "--------------------------------------------------------------------");
        for (int i = 0; i < comptador; i++) {
            for (int j = 0; j < participants[i].length; j++) {
                if (participants[i][j] != null) {
                    System.out.print(participants[i][j] + " ");
                }
            }
            System.out.println();

        }
    }

    //imprimir array farcit. És la mateixa funció standard per imprimir un array
    //bidimensional, però amb l'afegir que abans d'imprimir la cadena, li passem
    //la funció farciment.
    void imprimirFarcit(String[][] arrayB, char farcit, int[] mida) {
        for (int i = 0; i < arrayB.length; i++) {
            for (int j = 0; j < arrayB[j].length; j++) {
                //aquí imprimeixo per pantalla la cadena passant la funció de
                //farciment
                System.out.print(farciment(arrayB[i][j], farcit, mida[j]));
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    void menu(String titol, String[] opcions) {
        char opcio = 0;
        System.out.println("---------------------------------------");
        System.out.print("\t");
        System.out.println(titol);
        System.out.println("---------------------------------------");
        for (int i = 0; i < opcions.length; i++) {
            System.out.print("\t");
            System.out.println(opcions[i]);
        }
    }

    void mostrarResultat(String resultat, int opcio) {
        //System.out.println();
        System.out.print("---------------------------------------------------");
        System.out.println(BLUE + resultat + BLUE + opcio);
    }


    /*Una funció mostrarTítol, per mostrar un títol (cadena de text) en un 
    format específic que l'identifiqui com a títol o capçalera d'una pantalla. 
    Serà útil per estandarditzar els títols de les diferents pantalles 
    d'una aplicació. Semblant a la presentació
     */
    void mostrarTitol(String[] titols, int llarg, String simbol, String simbolCentrat) {
        //imprimir el simbol per dalt
        for (int i = 0; i < llarg; i++) {
            System.out.print(BLUE + simbol);
        }
        System.out.println();
        //imprimir els titols
        for (int i = 0; i < titols.length; i++) {
            System.out.print(BLUE + simbolCentrat);
            //afegir espais a la esq dels titols
            for (int j = 0; j < (llarg - titols[i].length()) / 2; j++) {
                System.out.print(" ");
            }
            //imprimir titols
            System.out.print(titols[i]);
            //afegir espais a la dreta del titol
            for (int j = 0; j < (llarg - titols[i].length()) / 2 - (simbolCentrat.length() * 2); j++) {
                System.out.print(" ");
            }
            //imprimir simbol final de la linia
            System.out.print(BLUE + simbolCentrat);
            System.out.println();
        }
        //imprimir simbols per baix
        for (int i = 0; i < llarg; i++) {
            System.out.print(BLUE + simbol + BLACK);
        }
        System.out.println(BLACK);
    }

    boolean noveCaracter(String dni) {
        //funció per comprobar que el novè caràcter de la cadena sigui una caràcter alfabètic entre l'"A" i la "Z"
        boolean dniOk = false;
        if (dni.length() == 9) {
            if ((dni.charAt(8) >= 'A') && (dni.charAt(8) <= 'Z')) {
                dniOk = true;
            } else {
                dniOk = false;
            }
        }
        return dniOk;
    }

    boolean premEntrar() {
        Scanner scanner = new Scanner(System.in);
        String resposta = "";
        resposta = scanner.nextLine();
        return resposta.isEmpty();
    }
    boolean premEntrarPerContinuar() {
        Scanner scanner = new Scanner(System.in);
        String resposta = "";
        System.out.println("\nprem [ENTRAR] per a continuar");
        resposta = scanner.nextLine();
        return resposta.isEmpty();
    }

    /*--------------------------------------------------------------------------
    ----------------------------------------------------------------------------
    Funcions implementades a la tasca 4 de l'activitat A404
     */
    void presentacio(String[] linies) {
        //System.out.println("======================================================================");
        for (int i = 0; i < linies.length; i++) {
            System.out.println(linies[i]);
        }
        //System.out.println();
        //System.out.println("--------------------------------------------------------------------------------");
    }
    void presentacioColumn(String[] linies) {
        //System.out.println("======================================================================");
        for (int i = 0; i < linies.length; i++) {
            System.out.print(linies[i]+"\t");
        }
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------");
    }

    String sangrarString(String string, int longSangrat) {
        int lastBreak = 0;
        int nextBreak = longSangrat;
        if (string.length() > longSangrat) {
            String setString = "";
            do {
                while (string.charAt(nextBreak) != ' ' && nextBreak > lastBreak) {
                    nextBreak--;
                }
                if (nextBreak == lastBreak) {
                    nextBreak = lastBreak + longSangrat;
                }
                setString += string.substring(lastBreak, nextBreak).trim() + "\n";
                lastBreak = nextBreak;
                nextBreak += longSangrat;

            } while (nextBreak < string.length());
            setString += string.substring(lastBreak).trim();
            return setString;
        } else {
            return string;
        }
    }

    boolean sortirPrograma(String missatge) {
        Scanner lector = new Scanner(System.in);
        boolean sortirBucle = false;
        boolean sortirPrograma = false;
        String resposta;
        do {
            System.out.println(missatge);
            resposta = lector.nextLine();
            switch (resposta) {
                case "":
                    sortirBucle = true;
                    sortirPrograma = true;
                    System.out.println("Ha triat sortir");
                    break;
                case "S":
                    sortirBucle = true;
                    sortirPrograma = true;
                    System.out.println("Ha triat sortir");
                    break;
                case "N":
                    sortirBucle = true;
                    System.out.println("Ha triat No sortir");
                    break;
                default:
                    sortirBucle = false;
                    System.out.println(RED + "Introdueixi un valor vàlid ENTER, S o N");
                    break;
            }
        } while (!sortirBucle);
        return sortirPrograma;
    }
//funció extreta de la solució de l'exercici 404 del professor Tasca4S2. Path:
//D:\DAM\PROGRAMACIO2\UF 2.3 Disseny modular _P III. Projecte\M3_uf2_projecte_2017_18_2_A404.zip

    void switchOpcioTriada(int opcio) {
        switch (opcio) {
            case 1:
                System.out.println(BLUE + "Ha triat: Inscriure un nou participant" + BLACK);
                break;
            case 2:
                System.out.println(BLUE + "Ha triat: llistar tots els participants" + BLACK);
                break;
            default:
                System.out.println(BLUE + "Ha triat: sortir de l'aplicació" + BLACK);
                break;
        }
    }

    boolean verificarDni(String dni, String[][] participants) {
        //Codifiqueu aquí la funció de verificació
        //ajudeu-vos creant d'altres funcions auxiliars.
        boolean verificarDni;
        verificarDni = digitsCorrectes(dni);
        //1 pas: comprobar que els 8 primers digits són números. Si passa, continuo
        if (verificarDni) {
            verificarDni = noveCaracter(dni);
            //2 pas: comprobar si el nové caràcter és lletra majúscula. Si passa continuo
            if (verificarDni) {
                verificarDni = buscarDniLlista(dni, participants);
                if (verificarDni) {
                    //System.out.println("DNI insertat correctament");
                } else {
                    System.out.println("El DNI és erroni o està repetit. "
                            + "Verifiqueu que sigui correcte i torneu a inscriure el participant");
                }
                //3 pas. comprobar si el DNI ja està a la llista. Última comprobació.
            } else {
                System.out.println("El DNI és erroni o està repetit. Verifiqueu "
                        + "que sigui correcte i torneu a inscriure el participant");
            }
        } else {
            System.out.println("El DNI és erroni o està repetit. "
                    + "Verifiqueu que sigui correcte i torneu a inscriure el participant");
        }
        return verificarDni;
    }

}
