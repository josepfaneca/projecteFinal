
/**
 *
 * @author Josep
 */
import java.util.Arrays;
public class GestorDades {

    static final int COLUMNS_PART = 3;
    static final int COLUMNS_TEMPS = 4;
    static final int DNI = 0;
    static final int NOM = 1;
    static final int MOVIL = 2;
    static final int CRONO = 3;
    static final int SURT_POBLE = 0;
    static final int ARRIBA_CIM = 1;
    static final int SURT_CIM = 2;
    static final int META = 3;
    static final int CINC_PRIMERS = 5;
    static final int COLUMNS_GUANY = 4;

    //crear variables tipus EstructuraDeDades i BaseDeDades
    EstructuraDeDades dades = new EstructuraDeDades();
    BaseDeDades baseDades = new BaseDeDades();

    //2.Definiu la funció per importar les dades i inicialitzar arrays
    public EstructuraDeDades importarDades() {
        //EstructuraDeDades dades = new EstructuraDeDades();
        //BaseDeDades baseDades = new BaseDeDades();
        dades.participants = baseDades.participants;
        dades.marcasDeTemps = new String[baseDades.obtenirNumeroDeParticipants()][COLUMNS_TEMPS];
        dades.tempsTotal = new int[baseDades.obtenirNumeroDeParticipants()];
        dades.cincPrimers = new String[CINC_PRIMERS][COLUMNS_GUANY];
        //dades.cincPrimers = 
        return dades;
    }

    //3.Definiu la funció guardar l'hora de sortida.recórrer la variable 
    //pertinent de la vostra estructura de dades per tal de guardar-hi, 
    //per a cada corredor, la hora de sortida indicada,
    public void guardarHoraSortida(EstructuraDeDades dades, String horaSortida) {
        //inicialitzar l'array amb la mida
        //dades.marcasDeTemps = new String[baseDades.obtenirNumeroDeParticipants()][EstructuraDeDades.COLUMNS_TEMPS];
        //recòrrer l'array i guardar l'hora de sortida a la columna 0
        for (int i = 0; i < dades.marcasDeTemps.length; i++) {
            dades.marcasDeTemps[i][SURT_POBLE] = horaSortida;
        }
    }

    //4.Definiu la funció guardar l'hora d'arribada i sortida del cim
    //no retornarà res
    //rebrà com a paràmetres la variable de tipus EstructuraDades del vostre 
    //projecte i la hora d’arribada al cim i de sortida al cim així com el dorsal 
    //o posició del corredor pel qual cal registrar aquesta informació.
    public void guardarArribaSortidaCim(EstructuraDeDades dades,
            String horaArribaCim, String horaSurtCim, int dorsal) {
        dades.marcasDeTemps[dorsal][ARRIBA_CIM] = horaArribaCim;
        dades.marcasDeTemps[dorsal][SURT_CIM] = horaSurtCim;
    }

    /* 4. Definiu la funció guardar l'hora d'arribada a meta.
    No retornarà res, però rebrà com a paràmetres la variable:
    1)Tipus EstructuraDades del vostre projecte
    2)hora de finalització d’un corredor/a
    3)com el dorsal o posició pel qual cal registrar aquesta informació
     */
    public void guardarHoraMeta(EstructuraDeDades dades, String horaMeta, int dorsal) {
        dades.marcasDeTemps[dorsal][META] = horaMeta;
    }

    /*6. Calcular el temps acumulat un cop hagin estat entrats tots els registres 
    de les hores. La funció calcularà el temps acumulat per a cada corredor.
    No retornarà res però rebrà com a paràmetre la variable del tipus EstructuraDades.
    El que farà és recórrer tots els temps o hores registrades de tots els corredors 
    i en calcularà el temps final emprat.
    Aquests temps final emprat es deixarà guardat també dins l'estructura,
    a la variable que servia per emmagatzemar els temps dels corredors definida 
    a la tasca 2 d’aquest període.
     */
    public void calcularTempsAcumulat(EstructuraDeDades dades) {

        for (int i = 0; i < dades.marcasDeTemps.length; i++) {
            int parcial1, parcial2;
            //COMPROBAR SI LA FILA CONTÉ CAMPS NULLS, TRUE: CALCULAR TEMPS
            if (Utils.comprobarParticipantTempsNulls(i, dades.marcasDeTemps)) {
                for (int j = 0; j < dades.marcasDeTemps[j].length; j++) {
                    //COMPROBAR CONGRUÈNCIA DELS TEMPS: el temps d'arribada al cim ha de ser més gran que el de la sortida. Si passa continua:
                    if (Utils.controlarTempsParcials(dades.marcasDeTemps[i][SURT_POBLE], dades.marcasDeTemps[i][ARRIBA_CIM])) {
                        //PRIMER PARCIAL: DES DE LA SORTIDA FINS AL CIM
                        parcial1 = Utils.obtenirDiferenciaComSegons(dades.marcasDeTemps[i][SURT_POBLE], dades.marcasDeTemps[i][ARRIBA_CIM]);
                        //COMPROBAR CONGRUÈNCIA DELS TEMPS: el temps d'arribada a meta ha de ser més gran que el de la sortida del cim. Si passa continua:
                        if (Utils.controlarTempsParcials(dades.marcasDeTemps[i][SURT_CIM], dades.marcasDeTemps[i][META])) {
                            //SEGON PARCILA: DES DE EL CIM FINS META
                            parcial2 = Utils.obtenirDiferenciaComSegons(dades.marcasDeTemps[i][2], dades.marcasDeTemps[i][3]);
                            //TEMPS TOTAL PER PARTICIPANT. GUARDAR TEMPS EN L'ARRAY ON ES GUARDEN ELS TEMPS 
                            dades.tempsTotal[i] = parcial1 + parcial2;
                            //no passa el control de temps 2, mostra un avís:    
                        } else {
                            System.out.println("Revisi el temps d'arribada a meta: " + dades.marcasDeTemps[i][META] + " del Dorsal "+ i);
                            //condició sortir del bucle
                            j = EstructuraDeDades.COLUMNS_TEMPS;
                        }
                        //no passa el control de temps, mostra un avís    
                    } else {
                        System.out.println("Revisi el temps d'arribada al cim: " + dades.marcasDeTemps[i][ARRIBA_CIM] + " del Dorsal "+ i);
                        j = EstructuraDeDades.COLUMNS_TEMPS;
                    }
                }
            } else {
                System.out.println("Participant amb el dorsal " + i + " amb temps buits");
            }
        }
    }
    /*7. Realitzar el càlcul de les posicions dels cinc primers classificats.
    com a paràmetre la variable interna que hem declarat per emmagatzemar-hi 
    les 5 posicions un cop les hàgim calculat:  String cincPrimers[][];
    */ 
    public void calcularCincPrimers (EstructuraDeDades dades) {
        //fer una còpia de l'array dels temps
        int copiaArrayTemps [] = Arrays.copyOf(dades.tempsTotal, dades.tempsTotal.length);//paràmetres l'array a copiar i la mida de l'array creat.
        //crear les variable auxiliars per a guardar els dorsals amb la mida dels corredors
        //int dorsals[] = new int[dades.tempsTotal.length];
        //String tempsAHores [] = new String [dades.tempsTotal.length];
        
        //ordenar l'array dels temps de menor a major
        Arrays.sort(dades.tempsTotal);
        //fer la crida al mètode que he creat a Utils per calcular els índexs desprès d'ordenar i guardar els indexs.
        //Com a paràmetres li passo la còpia de l'array i l'ordenat amb el mètode Arrays.sort
        int [] dorsals  = Utils.orderIntIndex(copiaArrayTemps, dades.tempsTotal);
        //convertir els temps amb segosns a temps en HH:MM:SS
        String [] tempsAHores = Utils.tempsSegonsAHores(dades.tempsTotal);
        //Omplir l'array String cincPrimers[][];  // Per enmagatzemar NOM, DNI, TELEFON MOVIL I TEMPS
        for(int i=0;i<dades.cincPrimers.length;i++) {
            dades.cincPrimers[i][NOM] = dades.participants[dorsals[i]][NOM];
            dades.cincPrimers[i][DNI] = dades.participants[dorsals[i]][DNI];
            dades.cincPrimers[i][MOVIL] = dades.participants[dorsals[i]][MOVIL];
            dades.cincPrimers[i][CRONO] = tempsAHores[i];
        }
    }
    
    //importar dades de marques de temps per fer joc de proves amb Tasca 4:Opció de la llista de guanyadors
    public EstructuraDeDades importarDadesLlistatGuanyadors() {
        //EstructuraDeDades dades = new EstructuraDeDades();
        //BaseDeDades baseDades = new BaseDeDades();
        dades.participants = baseDades.participants;
        dades.marcasDeTemps = baseDades.marquesDeTemps;
        dades.tempsTotal = new int[baseDades.obtenirNumeroDeParticipants()];
        dades.cincPrimers = new String[CINC_PRIMERS][COLUMNS_GUANY];
        return dades;
    }
    //calcular 5 primers amb dorsal
    public void calcularCincPrimersDorsal (EstructuraDeDades dades) {
        //fer una còpia de l'array dels temps
        int copiaArrayTemps [] = Arrays.copyOf(dades.tempsTotal, dades.tempsTotal.length);//paràmetres l'array a copiar i la mida de l'array creat.
        //crear les variable auxiliars per a guardar els dorsals amb la mida dels corredors
        //int dorsals[] = new int[dades.tempsTotal.length];
        //String tempsAHores [] = new String [dades.tempsTotal.length];
        
        //ordenar l'array dels temps de menor a major
        Arrays.sort(dades.tempsTotal);
        //fer la crida al mètode que he creat a Utils per calcular els índexs desprès d'ordenar i guardar els indexs.
        //Com a paràmetres li passo la còpia de l'array i l'ordenat amb el mètode Arrays.sort
        int [] dorsals  = Utils.orderIntIndex(copiaArrayTemps, dades.tempsTotal);
        //convertir els temps amb segosns a temps en HH:MM:SS
        String [] tempsAHores = Utils.tempsSegonsAHores(dades.tempsTotal);//
        String [] dorsalsOrdenats = Utils.deEnterToCadena(dorsals);
        String [] tempsEnCadenes = Utils.deEnterToCadena(dades.tempsTotal);
        
        //Omplir l'array String cincPrimers[][];  // Per enmagatzemar NOM, DNI, TELEFON MOVIL I TEMPS
        for(int i=0;i<dades.cincPrimers.length;i++) {
            dades.cincPrimers[i][MOVIL] = dades.participants[dorsals[i]][NOM];
            dades.cincPrimers[i][DNI] = dorsalsOrdenats[i];
            dades.cincPrimers[i][NOM] = dades.participants[dorsals[i]][DNI];
            dades.cincPrimers[i][CRONO] = tempsEnCadenes[i];
        }
    }
    

}
