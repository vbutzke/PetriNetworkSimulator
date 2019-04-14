package controllers;

import java.io.*;

public class FileController {

    public boolean validateFile(BufferedReader br) {
        return (br.lines().count() % 4 == 0);
    }

    public String readFile(String fileName) throws IOException {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fileReader);
        String line, file = "";
        //validateFile(br);
        while(( line = br.readLine())!=null){
           // Integer.parseInt(line);
            file = file + line + "\n";
        }
        br.close();
        fileReader.close();
        return file;
    }

    public String[] getLines(String file){
        return file.split("\n");
    }

    public String[] formatContent(String [][] content) {
        String [] output = new String[content.length*content[0].length+2];
        output[0] = "";
        output[1] = "";
        for(int i=2; i<output.length; i++){
            for(int j=0; j<output.length; j++){
                output[i] = output[i] + content[j];
            }
        }
        return output;
    }

    public void writeToFile(String fileName, String[] content) throws IOException {
        FileWriter fileWriter = new FileWriter(new File(fileName));
        BufferedWriter bw = new BufferedWriter(fileWriter);
        for (String line: content) {
            bw.write(line+" \n ");
        }
        bw.close();
        fileWriter.close();
    }
}
