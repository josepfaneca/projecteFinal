/*
Implementeu en un projecte nou de Netbeans la classe EstructuraDades.java 
que proposeu. Aquest serà el projecte sobre el que realitzarem tota la 
programació de la cursa de corredors. Feu per donar-li un nom adequat.
@author Josep
 */
public class EstructuraDeDades {
    
    static final int COLUMNS_PART = 3;
    static final int COLUMNS_TEMPS = 4;
    static final int DNI = 0;
    static final int NOM = 1;
    static final int MOVIL = 2;
    static final int SURT_POBLE = 0;
    static final int ARRIBA_CIM = 1;
    static final int SURT_CIM = 2;
    static final int META = 3;

    String participants[][]; // Per enmagatzemar el NOM, DNI I TELEFON MOVIL 
    //(3 COLUMNES TIPUS STRING)
    String marcasDeTemps[][];// Per enmagatzemar sortida poble, arribada cim, 
    //sortida cim i arribada meta (4 COLUMNES TIPUS STRING)
    String cincPrimers[][];  // Per enmagatzemar NOM, DNI, TELEFON MOVIL I TEMPS
    // (4 COLUMNES TIPUS STRING (DORSAL ES L'INDEX) ----es pot fer unidimensional----
    int tempsTotal[]; // Per enmagatzemar el temps total de cada corredor en segons  

}
