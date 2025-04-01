package org.transformacao_dados;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ArquivoZip {
    public static void baixarZip(StringBuilder stringBuilder) {
        final String nomeZip = "Teste_SamirAlves.zip";
        try(FileOutputStream fileOutputStream = new FileOutputStream(nomeZip);
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(zipOutputStream, StandardCharsets.UTF_8);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)){

            zipOutputStream.putNextEntry(new ZipEntry("Tabela.csv"));
            System.out.println("Criando CSV da tabela...");

            bufferedWriter.write("\uFEFF");
            bufferedWriter.write(stringBuilder.toString());
            System.out.println("Compactando para ZIP...");
            bufferedWriter.flush();
            zipOutputStream.closeEntry();

            final String caminhoZip = System.getProperty("user.dir") + "\\" + nomeZip;
            System.out.printf("Arquivo ZIP criado no caminho %s\n",caminhoZip);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}