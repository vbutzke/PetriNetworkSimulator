package controllers;

import java.io.*;

public class FileController {

    public String readFile(String fileName) throws IOException {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fileReader);
        String line, file = "";
        while(( line = br.readLine())!=null){
            file = file + line + "\n";
        }
        br.close();
        fileReader.close();
        return file;
    }

    public String[] getLines(String file){
        return file.split("\n");
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
