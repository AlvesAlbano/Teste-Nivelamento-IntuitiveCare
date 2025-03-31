package org.example;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        ArquivoPdf arquivoPdf = new ArquivoPdf();
        TabelasPdf tabelasPdf = new TabelasPdf();

        tabelasPdf.extrairTabelasDoPdf(arquivoPdf.getArquivo());
    }
}