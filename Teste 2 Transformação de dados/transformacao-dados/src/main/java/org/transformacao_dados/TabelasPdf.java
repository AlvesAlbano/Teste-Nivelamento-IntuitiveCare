package org.transformacao_dados;

import org.apache.pdfbox.pdmodel.PDDocument;
import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabelasPdf {

    private final Map<String,String> legenda = new HashMap<>() {{
        put("OD","Seg. Odontológica");
        put("AMB", "Seg. Ambulatorial");
//        put("HCO", "Seg. Hospitalar Com Obstetrícia");
//        put("HSO", "Seg. Hospitalar Sem Obstetrícia");
//        put("REF", "Plano Referência");
//        put("PAC", "Procedimento de Alta Complexidade");
//        put("DUT", "Diretriz de Utilização");
    }};


    private boolean tabelaExiste(int tamanhoLinha){
        return tamanhoLinha > 1;
    }

    private String substituiSiglas(String cell){
        for(String key: legenda.keySet()){
            if (cell.replace("\r"," ").trim().equals(key)){
                cell = legenda.get(key);
            }
        }
        return cell.replace("\r"," ").trim();
    }

    public void extrairTabelasDoPdf(PDDocument pdDocument) {

        ObjectExtractor objectExtractor = new ObjectExtractor(pdDocument);
        SpreadsheetExtractionAlgorithm spreadsheetExtractionAlgorithm = new SpreadsheetExtractionAlgorithm();

        StringBuilder stringBuilder = new StringBuilder();
        final int totalPaginas = pdDocument.getNumberOfPages();

        boolean cabecalhoImpresso = false;

        System.out.println("Extraindo Tabelas...");
        for(int i = 1;i < totalPaginas+1;i++){
            Page pagina = objectExtractor.extract(i);

            List<Table> tabelas = spreadsheetExtractionAlgorithm.extract(pagina);
//            System.out.println("pg:"+i);

            for(Table tabela: tabelas) {
//                System.out.println(tabela.getRows().size());

                if (tabelaExiste(tabela.getRows().size())) {
                    if (!cabecalhoImpresso) {
                        List<RectangularTextContainer> cabecalho = tabela.getRows().getFirst();

                        for (RectangularTextContainer cell : cabecalho) {

//                            System.out.println(cell.getText());
                            stringBuilder.append(substituiSiglas(cell.getText())).append(";");
                        }
                        stringBuilder.deleteCharAt(stringBuilder.length()-1);
                        stringBuilder.append("\n");
                    }
                    cabecalhoImpresso = true;
                }

                for (List<RectangularTextContainer> linhas : tabela.getRows().subList(1, tabela.getRows().size())) {
                    for (RectangularTextContainer cell : linhas) {
                        stringBuilder.append(substituiSiglas(cell.getText())).append(";");
                    }
                    stringBuilder.deleteCharAt(stringBuilder.length()-1);
                    stringBuilder.append("\n");
                }
            }
        }
        ArquivoZip.baixarZip(stringBuilder);
    }
}