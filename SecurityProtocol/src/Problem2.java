import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem2 {

    private static final int BUFFER_SIZE = 128;
    private static final String CURRENT_PATH = Paths.get(".").toAbsolutePath().normalize().toString();
    private static final String SERVER_LOG = "ServerLogEnc.dat";
    private static final String CLIENT_LOG = "ClientLogEnc.dat";
    private static final String OUTPUT_FILE = "Problem2.txt";
    private static byte []  encryptClient, encryptServer;
    private static List<String> clientMessage, serverMessage;

    public static void main(String args[]){
        try {
            decryptAndGetListMessage();
            String output = getAllLoginTrial();
            writeResultToFile(output, OUTPUT_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getAllLoginTrial() throws Exception{
        String output = "";
        for( int i = 0 ; i < clientMessage.size(); i++){
            String currentMessage = clientMessage.get(i).trim().replaceAll("( )+", " ");

            if(currentMessage.contains("LOGIN")){
                String [] array = currentMessage.split(" ");
                String userName = array[1];
                String password = array[2];

                String currentServerMessage = serverMessage.get(i);
                String result = currentServerMessage.contains("WELCOME") ? "[CORRECT]" : "[WRONG]";

                while (result.length() < 15){
                    result += " ";
                }
                while (userName.length() < 15){
                    userName += " ";
                }
                output += result + "  " +  userName + "  " +  password + "\n";

            }
        }
        return output;
    }

    private static void writeResultToFile(String output, String outputFile) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        writer.write(output);
        writer.close();
    }

    private static void decryptAndGetListMessage() throws Exception{
        byte[] cipher = getAllCipherStream();
        byte[] plainTextServer = decrypt(cipher, encryptServer);
        byte[] plainTextClient = decrypt(cipher, encryptClient);

        clientMessage = getMessageFromByteArray(plainTextClient, BUFFER_SIZE);
        serverMessage = getMessageFromByteArray(plainTextServer, BUFFER_SIZE);

//        testMessage(clientMessage);
//        testMessage(serverMessage);

    }

    private static void testMessage(List<String> messages) {
        for( String s: messages){
            System.out.println(s);
        }
    }

    private static List<String> getMessageFromByteArray(byte[] plainText, int bufferSize) {
        List<String> output = new ArrayList<>();
        for( int i = 0 ; i < plainText.length; i += bufferSize){
            byte [] currentByte = Arrays.copyOfRange(plainText, i, i + bufferSize);
            output.add(new String(currentByte));
        }
        return output;
    }

    private static byte[] decrypt(byte[] stream, byte[] encrypted) {
        byte [] decode = new byte[stream.length];
        for( int i = 0; i < stream.length; i++){
            decode[i] = (byte)  (stream[i] ^ encrypted[i]);
        }
        return decode;
    }

    private static byte[] getAllCipherStream() throws Exception {
        encryptClient = getByteArrayFromFile(CLIENT_LOG);
        encryptServer = getByteArrayFromFile(SERVER_LOG);

        int len = encryptClient.length;
        byte[] cipher = new byte[len];

        for( int i = 0 ; i < len; i+= BUFFER_SIZE){
            byte [] nextCipher = getSingleCipherStreamOfOneMessage(Arrays.copyOfRange(encryptClient, i, i+ BUFFER_SIZE),
                    Arrays.copyOfRange(encryptServer, i, i+ BUFFER_SIZE));
            updateCipher(cipher, nextCipher, i);
        }
        return cipher;
    }

    private static void updateCipher(byte[] cipher, byte[] nextCipher, int index) {
        for( int i = 0; i < BUFFER_SIZE; i++){
            cipher[index + i] = nextCipher[i];
        }
    }

    private static byte[] getSingleCipherStreamOfOneMessage(byte[] encryptClient, byte[] encryptServer) throws Exception{
        List<Byte> cipherList = new ArrayList<>();


        if( (encryptClient[0] ^ (byte) 'M') == (encryptServer[0] ^ (byte) 'R')){
            /*
            Case of message reply:
              MESSAGE USER1 USER2 CONTENT
              REPLY MESSAGE USER1 USER2 CONTENT
              server message is shifted by client message by 6 bytes to the right
             */

            String MESSAGE_REPLY_PREFIX = "REPLY MESSAGE ";
            //get cipher for "REPLY MESSAGE ", since we already know the message content and the cipher text
            for( int i = 0 ; i < MESSAGE_REPLY_PREFIX.length(); i++){
                cipherList.add((byte)(encryptServer[i] ^ (MESSAGE_REPLY_PREFIX.charAt(i))));
            }

            /*
            next we need to find client message from "USER1 ...." using part of server message already that we already know
            counter is the length of "MESSAGE " in client message
             */
            int shift = 6;
            int counter = 8;

            byte[] serverChar = new byte[shift];
            byte[] clientChar ;

            //serverChar is "SSAGE ", with length of 6, corresponding to 6 char of "USER1..." in client message
            for( int i = 0 ; i < serverChar.length; i++){
                serverChar[i] = (byte) "SSAGE ".charAt(i);
            }

            while (true){
                clientChar = new byte[shift];

                //find client char using corresponding server char that we already know
                for( int i = 0; i < shift; i++){
                    clientChar[i] = (byte)(serverChar[i] ^ encryptServer[counter + i] ^ encryptClient[counter +i] );
                }

                counter += shift;

                //update server char since next 6 char of client is also next 6 char of server
                for(int i = 0 ; i < shift; i++){
                    serverChar[i] = clientChar[i];
                }

                //update cipher
                for(int i = 0 ; i < shift; i++){
                    cipherList.add((byte)(serverChar[i] ^ encryptServer[counter + i]));
                }

                //if reach two space => end of message, all other char is padded space
                if(clientChar[0] == (byte) ' ' && clientChar[1] == (byte) ' ') break;
            }

            //get cipher that encode remaining padded space
            String remainPadding = "";
            int currentIndex  = cipherList.size();
            while (remainPadding.length() < BUFFER_SIZE - currentIndex){
                remainPadding += " ";
            }
            for( int i = 0 ; i < remainPadding.length(); i++){
                cipherList.add((byte)(remainPadding.charAt(i) ^ encryptServer[currentIndex + i]));
            }

        }else {
            /*
            Case of login:
             */
            if( (encryptClient[0] ^ (int)'L') == (encryptServer[0] ^ (int) 'W') ){
                /*
                case of login success
                 */

                //first we calculate the stream with server cipher text and message of "WELCOME "
                cipherList.clear();
                String WELCOME_PREFIX = "WELCOME ";
                for( int i = 0 ; i < WELCOME_PREFIX.length(); i++){
                    cipherList.add (((byte) ( WELCOME_PREFIX.charAt(i) ^ encryptServer[i]) ));
                }

                /*
                  LOGIN USER PASS
                  WELCOME USER
                  counter is the length of "WELCOM"
                  serverChar1 is "E", serverChar2 is " ", which correspond to 2 chars of user name in client message
                 */
                int counter = 6;
                byte serverChar1 = (byte)  'E';
                byte serverChar2 =  (byte) ' ';
                byte clientChar1, clientChar2;

                while (true){
                    //get the two char correspond to serverChar1 and serverChar2
                    clientChar1 = (byte) (serverChar1 ^ encryptServer[counter] ^ encryptClient[counter]);
                    counter++;
                    clientChar2 =  (byte) (serverChar2 ^ encryptServer[counter] ^ encryptClient[counter]);
                    counter++;

                    //server char is also client char, since they have the same user name
                    serverChar1 = clientChar1;
                    serverChar2 = clientChar2;

                    //if reach space => end of server messsage
                    cipherList.add( (byte) (serverChar1 ^ encryptServer[counter]));
                    if(serverChar1 == (byte) ' ') break;

                    //if reach space => end of server messsage
                    cipherList.add( (byte)( serverChar2 ^ encryptServer[counter + 1]));
                    if(serverChar2 == (byte) ' ') break;

                }

                //get cipher that encode remaining padded space
                String remainPadding = "";
                int currentIndex  = cipherList.size();
                while (remainPadding.length() < BUFFER_SIZE - currentIndex){
                    remainPadding += " ";
                }
                for( int i = 0 ; i < remainPadding.length(); i++){
                    cipherList.add((byte)(remainPadding.charAt(i) ^ encryptServer[currentIndex + i]));
                }


            }else if( (encryptClient[0] ^ (int)'L') == (encryptServer[0] ^ (int) 'I') ){ //incorrect user name
                /*
                Case of login with incorrect user name
                since we already know the server message, calculate the stream is straight forward
                 */
                cipherList.clear();
                String serverText = "INCORRECT USERNAME";
                while (serverText.length() < BUFFER_SIZE){
                    serverText += " ";
                }
                for( int i = 0 ; i < BUFFER_SIZE; i++){
                    cipherList.add (((byte) ( serverText.charAt(i) ^ encryptServer[i]) ));
                }

            }else if( (encryptClient[0] ^ (int)'L') == (encryptServer[0] ^ (int) 'P') ){ //password mismatch
                /*
                Case of login with password mismatch
                since we already know the server message, calculate the stream is straight forward
                 */
                cipherList.clear();
                String serverText = "PASSWORD MISMATCH";
                while (serverText.length() < BUFFER_SIZE){
                    serverText += " ";
                }
                for( int i = 0 ; i < BUFFER_SIZE; i++){
                    cipherList.add (((byte) ( serverText.charAt(i) ^ encryptServer[i]) ));
                }
            }
        }

        //convertToLong to byte array
        byte[] cipher = new byte[BUFFER_SIZE];
        for( int i = 0 ; i < cipherList.size(); i++){
            cipher[i] = cipherList.get(i);
        }
        return cipher;
    }

    private static byte[] getByteArrayFromFile(String fileName) throws Exception {
        String path = CURRENT_PATH + File.separator +  fileName;
        File file = new File(path);
        FileInputStream in = new FileInputStream(file);

        int avail = in.available();
        byte[] output = new byte[avail];
        int result = in.read(output, 0, avail); //  read all from input stream,

        return output;
    }

}
