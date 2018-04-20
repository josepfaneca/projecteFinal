
/**
 * Aquí s'agrupen les funcions grans de l'aplicació: el menú principal, la
 * importació de dades al diccionari i la gestió del joc)
 *
 */
public class AppCursaFestaMajor {

    static final String SIMBOL = "*";
    static final String SIMBOL_CENTRAT = "**";
    static final int LLARG = 80;
    static final int LLARGERROR = 49;//sangrar missatge d'error, long máx linia
    static final String RED = "\033[0;31m";
    static final String BLUE = "\033[0;34m";
    static final String BLACK = "\u001B[30m";
    static final int SORTIR = -1;

    public static void main(String[] args) {
        AppCursaFestaMajor prg = new AppCursaFestaMajor();
        prg.inici();
    }

    /**
     * Inicalitza el menú principal i gestiona la opció escollida per l'usuari:
     * 1) inscripcions, 2) qualificacions o 3) acabar i sortir
     *
     * @param dades
     */
    void inici() {
        // inicialitza l'estructura de dades
        EstructuraDeDades dades;
        GestorDades gestor = new GestorDades();
        //importar les dades i inicialitzar arrays a la variable tipus compost dades
        dades = gestor.importarDades();
        char opcio;
        boolean opcioTriada;
        // mostra el menú i crida al controlPrincipal
        //si indica que no vol sortir, 
        do {
            opcio = mostrarMenu();
            opcioTriada = controlOpcioPrincipal(opcio, dades);
        } while (!opcioTriada);

    }

    /**
     * Mostra el menú principal i retorna la opció escollida per l'usuari
     *
     * @return opció escollida per l'usuari
     */
    char mostrarMenu() {
        // Mostra el menú principal i espera la selecció de l'usuari en format de char. 
        // Aquest valor serà el que es retornarà.
        BibliotecaInterficieUsuari biblio = new BibliotecaInterficieUsuari();
        char opcio;
        int salts = 1;
        String[] titols = {"Menú principal"};
        String[] opcions = {"1) Sortida inicial", "2) Etapa intermedia", "3) Arribada",
            "4) LLista de guanyadors", "0) Tancar aplicació"};
        char[] opcionsChar = {'1', '2', '3', '4', '0','5'};
        String missatge = "Escolliu una opció i premeu [ENTAR]: ";
        String textError = "Error, entri un valor vàlid";
        //1.Mostra titol principal
        biblio.mostrarTitol(titols, LLARG, SIMBOL, SIMBOL_CENTRAT);
        //salt de linia
        biblio.afegirLinies(salts);
        //2.Mostra opcions
        Utils.imprimirArrayString(opcions);
        //salt de linia
        biblio.afegirLinies(salts);
        //3. Mostra missatge i recull char vàlida
        opcio = biblio.controlChar(opcionsChar, missatge, textError);
        return opcio;
    }

    /**
     * Executa les funcions destinades a gestionar l'opció passadada per
     * paràmetre.
     *
     * @param opcio és la opció que cal processar
     * @param dades Son les dades del concurs necessàries per processar
     * qualsevol de els opcions.
     * @return retorna cert si l'usauri confirma la sortida o fals en cas
     * contrari.
     */
    boolean controlOpcioPrincipal(char opcio, EstructuraDeDades dades) {
        BibliotecaInterficieUsuari biblio = new BibliotecaInterficieUsuari();
        boolean ret = false;
        // Seleccionar la tasca a partir del valor d'opcio i fer la crida a la funció
        switch (opcio) {
            case '1':
                //System.out.println("Opció 1");
                tascaSortidaInicial(dades);
                break;
            case '2':
                //System.out.println("Opció 2");
                tascaEtapa(dades);
                break;
            case '3':
                //System.out.println("Opció 3");
                tascaArribada(dades);
                break;
            case '4':
                //System.out.println("Opció 4");
                tascaLlistaGuanyadors(dades);
                break;
            case '0':
                ret = biblio.confirmar(BLUE+"Ha triat sortir"+BLACK);
                break;
            case '5':
                //opció oculta: afegeix directament els temps a tots els corredors 
                //per tal de provar la funcionalitat del llistat sense haver de fer 
                //tot el procès d'inscripció dels 10 corredors.
                tascaLlistaGuanyadorsOculta(dades);
                break;
            default:
                break;
        }
        // En cas que la opció correspongui amb la demanda d'abandonar l'aplicació, 
        // es demanarà la confirmació de l'usuari. 
        // Es retornarà cert si la opció es corresponia amb la demanda d'abandonar l'aplicació i l'usuari ha confirmat
        // la sortida. En cas contrari es retornarà sempre fals.
        return ret;
    }

    /**
     * Verifica si l'usuari vol sortir. En cas afirmatiu prepara la sortida. No
     * cal implementar, ja s'ha fet en la tasca anterior
     */
    boolean sortir() {
        BibliotecaInterficieUsuari biblio = new BibliotecaInterficieUsuari();
        boolean ret;
        ret = biblio.confirmarSortir();
        return ret;
    }

    void tascaSortidaInicial(EstructuraDeDades dades) {

        BibliotecaInterficieUsuari biblio = new BibliotecaInterficieUsuari();
        GestorDades gestor = new GestorDades();
        String[] títolTasca = {"Sortida de la cursa - entrada automàtica de la hora "};
        String[] informacio = {"Esteu a punt de donar la sortida. "
            + "Quan premeu la tecla [ENTRAR], s'emmagatzemarà com a hora de sortida,",
            "l'hora del sistema per a tots els participants.", "Doneu el tret de sortida i premeu [ENTRAR] simultàniament.",
            "", "prem [ENTRAR] per a continuar"};
        int salts = 1;
        String horaActual;
        boolean premEntrar;
        biblio.afegirLinies(salts);
        //Pantalla per mostrar títol principal
        biblio.mostrarTitol(títolTasca, LLARG, SIMBOL, SIMBOL_CENTRAT);
        //afegir un salt de línia
        biblio.afegirLinies(salts);
        //mostrar informació de la tasca
        biblio.presentacio(informacio);
        //obtenir hora de sortida (hora del sistema)
        //horaActual = Utils.obtenirHoraAra();
        //confirmar si ha pres [ENTER]
        premEntrar = biblio.premEntrar();
        //fer el tractament i informar si no ha premut [ENTRAR]
        if (premEntrar) {
            //guardar l'hora a l'array corresponent dades.marcasDeTemps
            horaActual = Utils.obtenirHoraAra();
            gestor.guardarHoraSortida(dades, horaActual);
        } else {
           //imprimir error; text 
           String missatge = "** ATENCIÓ: No ha premut [ENTRAR] i no s'ha emmagatzem l'hora de sortida **";
           missatge = biblio.sangrarString(missatge, LLARGERROR);
           biblio.error(missatge);
        }
        //Utils.imprimirArrayB(dades.marcasDeTemps);

    }

    void tascaEtapa(EstructuraDeDades dades) {
        BibliotecaInterficieUsuari biblio = new BibliotecaInterficieUsuari();
        BaseDeDades bbdd = new BaseDeDades();
        GestorDades gestor = new GestorDades();
        int salts = 1;
        int dorsal;
        int participants;
        int diferencia;
        String horaArribaCim;
        String horaSurtCim = "";
        boolean premEntrar;
        boolean sortirOk = false;
        boolean hiHaNulls;
        boolean confirmar;
        //obtenir número de participants
        participants = bbdd.obtenirNumeroDeParticipants();
        String missatgeCrono = "Escriu l'hora d'arribada al punt de control del corredor:";
        String[] títolTasca = {"Introducció de temps de l'etapa intermitja"};
        String[] informacio = {"Quan el corredor vagi a línia de sortida de "
            + "l'etapa intermèdia haurà de dur un paper amb l'hora que ha",
            "arribat al punt de control. Demaneu-li el paper i el dorsal i seguiu les indicacions de la pantalla.",
            ""};

        String missatge = "Quin és el dorsal de l'usuari?";
        String missatge2 = "Prem -1 o el número del dorsal:";
        String missatgeErrorNulls = "** ATENCIÓ: S'ha detectat que no ha  "
                + "emmagatzemat l'hora de sortida dels participants "
                + "Si us plau, enregistri abans l'hora de sortida dels corredors.";
        hiHaNulls = dades.marcasDeTemps[0][0] == null;//comprobar que s'ha donat el tret de sortida
        if (!hiHaNulls) { //si s'ha donat la sortida, emmagatzemar les altres dades
            //afegir salt linia
            biblio.afegirLinies(salts);
            //mostrar títol de la tasca
            biblio.mostrarTitol(títolTasca, LLARG, SIMBOL, SIMBOL_CENTRAT);
            //mostrar informació de la tasca
            biblio.presentacio(informacio);
            //afegir salt linia
            biblio.afegirLinies(salts);
            //demanar i guardar dorsal
            dorsal = biblio.entrarEnter(missatge, participants, 0);
            biblio.afegirLinies(salts);
            //demanar i guardar crono
            //es repetirà aquesta part del flux fins ens premi -1

            do {
                do {//es repetirà si les hores no són correctes
                    do {//es repetirà si les hores no són correctes
                        horaArribaCim = biblio.entrarCadena(missatgeCrono);
                        diferencia = Utils.compararHores(horaArribaCim, dades.marcasDeTemps[0][0]);
                        if (diferencia < 0) {
                            String missatgeError = "** ATENCIÓ: l'hora d'arribada al cim és "
                                    + "més petita que la sortida de meta. Si us plau, \nasseguris que ha introduit"
                                    + "bé l'hora d'arribada al cim ";
                            missatgeError = biblio.sangrarString(missatgeError, LLARGERROR);
                            biblio.error(missatgeError);
                        }
                    } while (diferencia < 0);

                    //mostrar informació
                    System.out.println("\nEsteu a punt de donar la sortida al corredor amb "
                            + "el dorsal " + dorsal + ". Quan premeu la tecla [ENTRAR] s'emmagatzemarà\n"
                            + "com a hora de sortida la hora del sistema.");
                    biblio.afegirLinies(salts);
                    System.out.println("Doneu el tret de sortida i premeu [ENTRAR] simultàniament.");
                    premEntrar = biblio.premEntrar();
                    if (premEntrar) {
                        horaSurtCim = Utils.obtenirHoraAra();
                        //comparar hores i veure si és correcte. Valor -1 si horaSurtCim>horaArribaCim
                        diferencia = Utils.compararHores(horaSurtCim, horaArribaCim);
                        if (diferencia < 0) {
                            String missatgeError2 = "** ATENCIÓ: l'hora d'arribada al cim és "
                                    + "més gran que la sortida de cim. Si us plau, \nasseguris que ha introduit "
                                    + "bé l'hora d'arribada al cim\n ";
                            missatgeError2 = biblio.sangrarString(missatgeError2, LLARGERROR);
                            biblio.error(missatgeError2);
                            System.out.println(RED + "Torni a repetir el procès de nou, si us plau\n");
                        }

                    } else {
                        System.out.println(RED + "** ATENCIÓ: No ha premut [ENTRAR] i no s'han emmagatzemat"
                                + " les hores d'arriba i sortida. **" + BLACK);
                    }
                } while (diferencia < 0);
                gestor.guardarArribaSortidaCim(dades, horaArribaCim, horaSurtCim, dorsal);
                System.out.println(BLUE + "Si tens un altre corredor esperant la sortida, demana-li el paper amb l'hora d'arribada i\n"
                        + "escriu el seu número de dorsal." + BLUE + " Si no tens més corredors, prem -1.\n" + BLACK);
                dorsal = biblio.entrarEnter(missatge2, participants, SORTIR);
                if (dorsal == SORTIR) {
                    sortirOk = true;
                }
            } while (!sortirOk);
            Utils.imprimirArrayB(dades.marcasDeTemps);
            biblio.afegirLinies(salts);
        } else {
            missatgeErrorNulls = biblio.sangrarString(missatgeErrorNulls, LLARGERROR);
            biblio.error(missatgeErrorNulls);
            do {
                confirmar = biblio.premEntrarPerContinuar();
            } while (!confirmar);
        }
    }

    void tascaArribada(EstructuraDeDades dades) {
        BibliotecaInterficieUsuari biblio = new BibliotecaInterficieUsuari();
        GestorDades gestor = new GestorDades();
        BaseDeDades bbdd = new BaseDeDades();
        int salts = 1;
        int dorsal;
        int participants;
        String horaMeta = "";
        int diferencia = 0;
        boolean sortirOk = false;
        String[] títolTasca = {"Introducció de l'hora d'arribada a la meta final"};
        String[] informacio = {"Quan el corredor vagi al control de la meta final  "
            + "haurà de dur un paper amb l'hora a la que ha",
            "arribat a la meta. Demaneu-li el paper i el dorsal i seguiu les indicacions de la pantalla.",
            ""};
        String missatgeCrono = "Escriu l'hora d'arribada al punt de control del corredor:";
        String missatge = "Quin és el dorsal de l'usuari?";
        String missatge2 = "Prem -1 o el número del dorsal:";

            //obtenir número de participants
            participants = bbdd.obtenirNumeroDeParticipants();
            //afegir salt de linia
            biblio.afegirLinies(salts);
            //imprimir títol principal
            biblio.mostrarTitol(títolTasca, LLARG, SIMBOL, SIMBOL_CENTRAT);
            biblio.afegirLinies(salts);
            //imprimir informació
            biblio.presentacio(informacio);
            biblio.afegirLinies(salts);
            //demanar i guardar dorsal
            dorsal = biblio.entrarEnter(missatge, participants, 0);
            biblio.afegirLinies(salts);
            do {
                do {//es repetirà si les hores no són correctes
                    if (dades.marcasDeTemps[dorsal][2] != null) {
                        horaMeta = biblio.entrarCadena(missatgeCrono);
                        diferencia = Utils.compararHores(horaMeta, dades.marcasDeTemps[dorsal][2]);
                        if (diferencia < 0) {
                            String missatgeError = "** ATENCIÓ: l'hora d'arribada a meta és "
                                    + "més petita que la sortida del cim. Si us plau, \nasseguris que ha introduit"
                                    + "bé l'hora d'arribada a meta ";
                            missatgeError = biblio.sangrarString(missatgeError, LLARGERROR);
                            biblio.error(missatgeError);
                        }
                    } else {
                        String missatgeError2 = "** ATENCIÓ: el corredor amb el dorsal " + dorsal
                                + " no té hora registrada de sortida de l'etapa intermitja, \nPot sortir "
                                + "prement -1 o introduïr un altre dorsal";
                        missatgeError2 = biblio.sangrarString(missatgeError2, LLARGERROR);
                        biblio.error(missatgeError2);
                        biblio.afegirLinies(salts);
                    }
                } while (diferencia < 0);
                //si tot és correcte, guardo l'arribada a meta
                gestor.guardarHoraMeta(dades, horaMeta, dorsal);
                System.out.println(BLUE + "Si tens un altre corredor esperant la sortida, demana-li el paper amb l'hora d'arribada i\n"
                        + "escriu el seu número de dorsal." + BLUE + " Si no tens més corredors, prem -1.\n" + BLACK);
                dorsal = biblio.entrarEnter(missatge2, participants, SORTIR);
                if (dorsal == SORTIR) {
                    sortirOk = true;
                }
            } while (!sortirOk);
            Utils.imprimirArrayB(dades.marcasDeTemps);
            biblio.afegirLinies(salts);
    }

    void tascaLlistaGuanyadors(EstructuraDeDades dades) {
        BibliotecaInterficieUsuari biblio = new BibliotecaInterficieUsuari();
        boolean hiHaNulls;
        boolean confirmar;
        String missatgeError = "** ATENCIÓ: Aquesta opció només serà valida "
                + "quan s'hagi acabat la cursa i tots els corredors hagin enregistrar el seu temps "
                + "Si us plau, enregistri els temps dels corredors.";
        //abans que res, comprobar que tots els corredors hagin enregistrar el seu temps
        hiHaNulls = Utils.comprobarNulls(dades.marcasDeTemps);
        //Si tots els temps estan afegits, endavant-->
        if (!hiHaNulls) {
            GestorDades gestor = new GestorDades();
            int salts = 1;
            String[] títolTasca = {" Llistat dels 5 primers classificats"};
            String[] titolLlistat = {"DORSAL", "DNI", "NOM", "TEMPS"};
            biblio.afegirLinies(salts);
            biblio.mostrarTitol(títolTasca, LLARG, SIMBOL, SIMBOL_CENTRAT);
            biblio.afegirLinies(salts);
            gestor.calcularTempsAcumulat(dades);//calcular temps
            gestor.calcularCincPrimersDorsal(dades);//calcular 5 primers
            //operacions per farcir llistat i veure ben tabulat
            int[] longer = biblio.cadenaMesLlarga(dades.cincPrimers);
            char farcit = ' ';
            titolLlistat = biblio.farciment3(titolLlistat, farcit, longer);//farcir títol columnes
            biblio.presentacioColumn(titolLlistat);//imprimir farcit
            biblio.imprimirFarcit(dades.cincPrimers, farcit, longer);//imprimir bé
            System.out.println("--------------------------------------------------------------------------------");
            do {
                confirmar = biblio.premEntrarPerContinuar();
            } while (!confirmar);
        } else {
            missatgeError = biblio.sangrarString(missatgeError, LLARGERROR);
            biblio.error(missatgeError);
            do {
                confirmar = biblio.premEntrarPerContinuar();
            } while (!confirmar);
        }
    }
    /*
    funcionalitat (oculta) que afegeix directament els temps a tots els corredors 
    per tal de provar la funcionalitat del llistat sense haber de fer tot el 
    procès d'inscripció dels 10 corredors.
    */
    void tascaLlistaGuanyadorsOculta(EstructuraDeDades dades) {
        BibliotecaInterficieUsuari biblio = new BibliotecaInterficieUsuari();
        GestorDades gestor = new GestorDades();
        EstructuraDeDades dadesLlistat;
        dadesLlistat = gestor.importarDadesLlistatGuanyadors();//afegeix els temps
        dades = dadesLlistat;
        int salts = 1;
        boolean confirmar;
        String[] títolTasca = {" Llistat dels 5 primers classificats"};
        String [] titolLlistat = {"DORSAL","DNI","NOM","TEMPS"};
        biblio.afegirLinies(salts);
        biblio.mostrarTitol(títolTasca, LLARG, SIMBOL, SIMBOL_CENTRAT);
        biblio.afegirLinies(salts);
        gestor.calcularTempsAcumulat(dades);
        gestor.calcularCincPrimersDorsal(dades);
        int [] longer = biblio.cadenaMesLlarga(dades.cincPrimers);
        char farcit = ' ';
        titolLlistat = biblio.farciment3(titolLlistat, farcit, longer);
        biblio.presentacioColumn(titolLlistat);//imprimir farcit el títol columna
        biblio.imprimirFarcit(dades.cincPrimers, farcit, longer);//imprimir farcit llistat
        System.out.println("--------------------------------------------------------------------------------");
        do {
            confirmar = biblio.premEntrarPerContinuar();
        } while (!confirmar);
    }
}
