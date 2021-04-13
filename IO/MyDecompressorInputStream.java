package IO;
import algorithms.mazeGenerators.Maze;

import java.io.*;
import java.math.BigInteger;

public class MyDecompressorInputStream extends InputStream {

    private InputStream in;


    public MyDecompressorInputStream(InputStream in){
        super();
        this.in=in;
    }


    @Override
    public int read() throws IOException {
        return 0;
    }

    //reads the compressed byte array and inserts the uncompressed array to array b
    @Override
    public int read(byte[] b) throws IOException {
        in.read(b); //this command reads the input stream and store it to b
        //now start decompressing
        int size_to_add=Integer.toBinaryString(b[b.length-1]).length()+b[12];
        byte[] to_return=new byte[13+((b.length-13)*7)+size_to_add];
        int index_to_insert=13;

        for(int i=13; i<b.length; i++){
            int temp=(int)b[i]; // for example temp is 31
            String binary_temp_rep=Integer.toBinaryString(temp);// now temp is 111111

            // building the correct string represenation for the the decimal number, 31 will be 0111111 and not 111111
            while(binary_temp_rep.length() != 7 && i != b.length-1)
                binary_temp_rep="0"+binary_temp_rep; // now will become to 0111111
            if(i==b.length-1){//deal with last one, add 0 in the left end if necessary
                int num_of_zeros_to_add=b[12];
                while(num_of_zeros_to_add != 0){
                    binary_temp_rep="0"+binary_temp_rep; // adding zeros
                    num_of_zeros_to_add--;
                }
            }
            for(int j=0; j<binary_temp_rep.length(); j++){
                String temp_char_as_string=Character.toString(binary_temp_rep.charAt(j));
                byte temp_byte=(byte)Integer.parseInt(temp_char_as_string);
                to_return[index_to_insert]=temp_byte;
                index_to_insert++;
            }
        }

        b=to_return;
        return 1;
    }
}
