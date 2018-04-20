
import java.util.Calendar;
import java.util.Date;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalTime;

public class Utils {
    static final String RED = "\033[0;31m";


    //public class Tasca1S1 {
    static final int VALOR_BUID = -1;

    static int[] afegirEnter(int valor, int[] valors) {
        int[] ret = valors;
        if (esPotAfegir(valor, valors)) {
            ret = new int[valors.length + 1];
            for (int i = 0; i < valors.length; i++) {
                ret[i] = valors[i];
            }
            ret[valors.length] = valor;
        }
        return ret;
    }


    //public class Tasca2 {
    static String cadenaAMajuscula(String cad) {
        char lletra;
        String ret = "";
        for (int i = 0; i < cad.length(); i++) {
            lletra = cad.charAt(i);
            if (lletra >= 'a') {
                lletra -= 'a' - 'A';
            }
            ret += lletra;
        }
        return ret;
    }
    //}
    static boolean cadenaComencaPerNumero(String num, int limit) {
        boolean ret = num.length() >= limit;
        for (int i = 0; ret && i < limit; i++) {
            ret = '0' <= num.charAt(i) && num.charAt(i) <= '9';
        }
        return ret;
    }
    static int compararHores(String horaInici, String horaFinal) {//si hi ha algun null casca!!!
        int dif;
        LocalTime hora1 = LocalTime.parse(horaInici);
        LocalTime hora2 = LocalTime.parse(horaFinal);
        dif = hora1.compareTo(hora2);
        return dif;
    }
    static boolean comprobarNulls(String[][] array) {
        boolean hiHaNull = false;
        for (int i =0;i< array.length; i++) {
            for (int j=0;j<array[i].length;j++)
                if (array[i][j] == null) {
                    hiHaNull = true;
                }
        }
        return hiHaNull;
    }
    static boolean comprobarParticipantTempsNulls(int fila, String[][] array) {
        boolean hiHaNull = true;
        for (int j = 0; j < array[j].length; j++) {
            if (array[fila][j] == null) {
                hiHaNull = false;
            }
        }
        return hiHaNull;
    }
    static char controlChar(char[] opcions, String missatge, String textError) {
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
                System.out.println(RED+textError);
            }
        } while (!esCorrecte);
        //convertir String a char
        return cadena.charAt(0);
    }
    static boolean controlarTempsParcials(String horaInicial, String horaFinal) {
        boolean totCorrecte = false;
        int diferencia = obtenirHoraComSegons(horaFinal) - obtenirHoraComSegons(horaInicial);
        if (diferencia > 0) {
            totCorrecte = true;
        }
        return totCorrecte;
    }
    //FUNCIONS PER CREAR EL LLISTAT DELS CINC PRIMERS
    //copia de l'array
    static int[] copiaArray(int[] array) {
        int[] arrayCopia = Arrays.copyOf(array, array.length);//
        return arrayCopia;
    }
    static String [] deEnterToCadena (int [] arrayEnters) {
        String [] cadenes = new String [arrayEnters.length];
        for (int i=0;i<cadenes.length;i++) {
            cadenes[i] = Integer.toString(arrayEnters[i]);
        }
        return cadenes;
    }
    static boolean esPotAfegir(int valor, int[] col) {
        boolean ret = true;
        
        for (int i = 0; ret && i < col.length; i++) {
            if (valor == col[i]) {
                ret = false;
            }
        }
        return ret;
    }
    //}

    static boolean esPotAfegir(String dni, String[][] participants) {
        boolean ret = false;
        for (int i = 0; i < participants.length && !ret; i++) {
            ret = participants[i][0].equals(dni);
        }
        return !ret;
    }
    static boolean hiHaValor(int valor, int[] valors) {
        boolean hiHaValor = false;
        for (int i = 0; i < valors.length; i++) {
            if (valors[i] == valor) {
                i = valors.length;
                hiHaValor = true;
            }
        }
        return hiHaValor;
    }


    static void imprimirArray(int[] valors) {
        //int limitFor = valors.length - 1;
        //System.out.print("{ ");
        for (int i = 0; i < valors.length; i++) {
            System.out.print(valors[i]);
            System.out.println();
        }
        //if (limitFor >= 0) {
        //System.out.print(valors[limitFor]);
        //}
        //System.out.println(" }");
    }
    //}
    static void imprimirArrayB(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                System.out.println();
            }
            for (int j = 0; j < array[j].length; j++) {
                if (j == 0) {
                    System.out.print("{");
                }
                System.out.print(array[i][j]);
                if (j == array[j].length - 1) {
                    System.out.print("}");
                }
                if (j < array[j].length - 1) {
                    System.out.print(" , ");
                }
            }
        }
        System.out.println();
    }

    static void imprimirArrayString(String[] valors) {
        //int limitFor = valors.length - 1;
        //System.out.print("{ ");
        for (int i = 0; i < valors.length; i++) {
            System.out.print(valors[i]);
            System.out.println();
        }
        //if (limitFor >= 0) {
        //System.out.print(valors[limitFor]);
        //}
        //System.out.println(" }");
    }
    static int obtenirDiferenciaComSegons(String horaInicial, String horaFinal) {
        //horaInicial = "17:07:29";
        //horaFinal = "18:47:29";
        //declarar variables
        int horaI, minutsI, segonsI, horaF, minutsF, segonsF;
        //obtenir els segons de l'hora, minuts i segons hora INICIAL
        horaI = (Integer.parseInt(horaInicial.substring(0, 2))) * 3600;
        minutsI = (Integer.parseInt(horaInicial.substring(3, 5))) * 60;
        segonsI = (Integer.parseInt(horaInicial.substring(6, 8)));
        //obtenir els segons de l'hora, minuts i segons hora FINAL
        horaF = (Integer.parseInt(horaFinal.substring(0, 2))) * 3600;
        minutsF = (Integer.parseInt(horaFinal.substring(3, 5))) * 60;
        segonsF = (Integer.parseInt(horaFinal.substring(6, 8)));
        
        //System.out.println((horaF + minutsF + segonsF) - (horaI + minutsI + segonsI));
        //fer la resta
        int dif = (horaF + minutsF + segonsF) - (horaI + minutsI + segonsI);
        return dif;
        
    }

    static int obtenirDiferenciaComSegons2(String horaInicial, String horaFinal) {
        return obtenirHoraComSegons(horaFinal) - obtenirHoraComSegons(horaInicial);
    }
    //Funció static
    static String obtenirHoraAra() {
        Date horaAra = new Date();
        String obtenirHoraAra;
        obtenirHoraAra = String.format("%tT", horaAra);
        return obtenirHoraAra;
    }
    //-----> Solucio Tasca Codificació de Biblioteques de funcions estàtiques
    //Funció modificada per guardar hora + x, és molt últil pel joc de proves del
    //programa principal.
    static String obtenirHoraAraMesXHora(int hora) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, hora);
        return String.format("%tH:%tM:%tS", calendar, calendar, calendar);
    }

    static int obtenirHoraComSegons(String hora) {
        int hores;
        int minuts;
        int segons;
        segons = Integer.parseInt(hora.substring(6, 8));
        minuts = Integer.parseInt(hora.substring(3, 5));
        segons += minuts * 60;
        hores = Integer.parseInt(hora.substring(0, 2));
        segons += hores * 60 * 60;
        return segons;
    }


    static int[] orderIntIndex(int[] disorderArray, int[] orderArray) {
        int lon = disorderArray.length;

        int[] index = new int[lon];
        Arrays.fill(index, 0);

        boolean[] esta = new boolean[lon];
        Arrays.fill(esta, false);

        for (int i = 0; i < orderArray.length; i++) {
            int in = 0;
            boolean stay = false;
            while (in < orderArray.length & !stay) {
                if ((disorderArray[in] == orderArray[i]) & !esta[in]) {
                    esta[in] = true;
                    index[i] = in;
                    stay = true;
                } else {
                    in++;
                }
            }
        }
        return index;
    }

    static String[] tempsSegonsAHores(int[] timeInSeconds) {
        String[] formatSeconds = new String[timeInSeconds.length];
        for (int i = 0; i < timeInSeconds.length; i++) {
            int hours = timeInSeconds[i] / 3600;
            int secondsLeft = timeInSeconds[i] - hours * 3600;
            int minutes = secondsLeft / 60;
            int seconds = secondsLeft - minutes * 60;

            String formattedTime = "";
            if (hours < 10) {
                formattedTime += "0";
            }
            formattedTime += hours + ":";

            if (minutes < 10) {
                formattedTime += "0";
            }
            formattedTime += minutes + ":";

            if (seconds < 10) {
                formattedTime += "0";
            }
            formattedTime += seconds;

            formatSeconds[i] = formattedTime;
        }
        return formatSeconds;
    }
    //public class Tasca3S1 {
    static boolean verificarDni(String dni, String[][] participants) {
        boolean ret = false;
        ret = dni.length() == 9;
        if (ret) {
            ret = cadenaComencaPerNumero(dni, 8);
        }
        if (ret) {
            ret = dni.charAt(8) >= 'A' && dni.charAt(8) <= 'Z';
        }
        if (ret) {
            ret = esPotAfegir(dni, participants);
        }
        return ret;
    }
    

    //Funció no static
    void funcioNoStatic() {
        //implementació
    }
}
