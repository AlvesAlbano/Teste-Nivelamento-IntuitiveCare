package org.example;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

public class ArquivoPdf {

    private final PDDocument arquivo;

    public ArquivoPdf() throws IOException {
        this.arquivo = Loader.loadPDF(new File(pegarCaminhoPdf()));
    }

    public PDDocument getArquivo() {
        return arquivo;
    }

    private String pegarCaminhoPdf(){

        StringBuilder stringBuilder = new StringBuilder();

        final String caminhoAtual = System.getProperty("user.dir");
        final String caminhoPdf = "\\Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf";

        return String.valueOf(stringBuilder.append(caminhoAtual).append(caminhoPdf));
    }
}