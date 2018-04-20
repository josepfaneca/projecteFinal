/**
 *
 * @author Josep
 */
public class BaseDeDades {

    String[][] participants = {
        {"64702078L", "Monserrat Arevalo De la corte", "649312120"},
        {"31422674M", "Iris Oliveira Vilamadrid", "630474563"},
        {"13289227B", "Ester Aloy Ojeda", "588456286"},
        {"49344915W", "Estel Carrio Tantiña", "312355979"},
        {"51093509K", "Anasti Villarejo Andujar", "909022599"},
        {"95038192F", "Olegario Malik Bes", "489209226"},
        {"82393239V", "Antonio Saldaña Arboix", "591549363"},
        {"95851561G", "Carlota Garces Barrero", "940771284"},
        {"82550835V", "Simeó Ontiveros Manchon", "216518746"},
        {"31417888A", "Vicente Boix Campuzano", "315522748"}
    };

    int obtenirNumeroDeParticipants() {
        return participants.length;
    }

    String[] obtenirParticipant(int i) {
        return participants[i];
    }
    
    String[][] marquesDeTemps = {
        {"12:00:00", "12:35:00", "13:00:15", "13:45:00"},
        {"12:00:00", "12:38:00", "13:02:20", "13:42:50"},
        {"12:00:00", "12:41:00", "13:04:25", "14:01:00"},
        {"12:00:00", "12:44:00", "13:06:30", "13:43:00"},
        {"12:00:00", "12:47:00", "13:08:35", "15:00:02"},
        {"12:00:00", "12:50:00", "13:10:40", "14:00:00"},
        {"12:00:00", "12:53:00", "13:12:45", "13:55:37"},
        {"12:00:00", "12:56:00", "13:14:50", "13:58:00"},
        {"12:00:00", "12:59:00", "13:16:55", "14:00:23"},
        {"12:00:00", "12:59:00", "13:16:55", "13:17:23"},
    };

}
