package org.example;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ArquivoZip {
    public static void baixarZip(StringBuilder stringBuilder) {
        try(FileOutputStream fileOutputStream = new FileOutputStream("Teste_SamirAlves.zip");
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(zipOutputStream, StandardCharsets.UTF_8);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)){

            zipOutputStream.putNextEntry(new ZipEntry("Tabela.csv"));

            bufferedWriter.write("\uFEFF");
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.flush();
            zipOutputStream.closeEntry();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}