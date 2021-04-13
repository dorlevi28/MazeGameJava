package IO;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;

public class MyCompressorOutputStream extends OutputStream {

    private OutputStream out;

    public MyCompressorOutputStream(OutputStream o){
        this.out=o; 
    }

    @Override
    public void write(int b) throws IOException {}


    @Override
    public void write(byte[] b) throws IOException {
        byte[]to_return;
        int size_of_new_arr;
        if((b.length-13)%7==0)
            size_of_new_arr=((b.length-13)/7)+13;
        else
            size_of_new_arr=((b.length-13)/7)+1+13;
        to_return=new byte[size_of_new_arr];
        byte[] begin_of_new_arr= Arrays.copyOfRange(b,0,13);
        byte[]final_result=new byte[size_of_new_arr];
        String s1="";
        int j=0;
        int index=0;
        //go through the body of the maze
        for(int i=13; i<b.length; i++){
            //reached end of chunk
            if(j==7){
                byte temp=(byte)Integer.parseInt(s1,2);
                to_return[index]=temp;
                index++;
                s1=Integer.toString(b[i]);
                j=1;
            }
            else if(j != 7 && i==b.length-1){ //remaining in case doesn't divide in 7 completely
                s1+=Integer.toString(b[i]);
                byte temp=(byte)Integer.parseInt(s1,2); //turns from binary string to byte(0-127)
                to_return[index]=temp;
            }
            else{
                s1+=Integer.toString(b[i]);
                j++;
            }

        }
        //merge two arrays into one
        System.arraycopy(begin_of_new_arr,0,final_result,0,begin_of_new_arr.length);
        System.arraycopy(to_return,0,final_result,begin_of_new_arr.length,to_return.length);
        out.write(final_result);


    }
}
