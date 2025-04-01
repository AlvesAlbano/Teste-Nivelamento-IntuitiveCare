# Teste de Nivelamento

## Teste 1 - Web Scrapping
Este script em Python utiliza a biblioteca `BeautifulSoup` para extrair links de arquivos PDF do site da Agência Nacional de Saúde Suplementar [https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos] e baixa os arquivos em um diretório específico, compactando-os em um arquivo ZIP.

## Bibliotecas Utilizadas

- `BeautifulSoup` (para parsing do HTML)
- `requests` (para realizar requisições HTTP)
- `os` (para manipulação de diretórios e arquivos)
- `zipfile` (para compactação dos PDFs)

## Estrutura da Classe `WebScrapping`

### Atributos:

- `link_site`: URL da página onde os PDFs estão disponíveis.
- `pasta_download`: Caminho do diretório onde os arquivos serão salvos.

### Métodos:

#### `encontrar_pdf(self) -> list[str]`
1. Faz a requisição da página HTML.
2. Utiliza `BeautifulSoup` para buscar os links dentro de uma `<div>` específica.
3. Filtra os links que terminam com `.pdf` e os armazena em uma lista.
4. Retorna a lista de URLs dos PDFs.

#### `baixar_arquivo(self, urls_pdf: list[str]) -> None`
1. Cria um diretório de download, caso não exista.
2. Faz o download de cada PDF encontrado na lista `urls_pdf`.
3. Salva os arquivos localmente e os adiciona a um arquivo ZIP.
4. Remove os arquivos baixados após a compactação.


## Teste 2
Este script em Java utiliza as bibliotecas `Tabula` para extrair todas as tabelas do PDF para depois transformar em um arquivo CSV.

## Dependências

- **Apache PDFBox**: Biblioteca para manipulação de documentos PDF.
- **Tabula**: Biblioteca para extração e manupulação de tabelas.

## Estrutura da Classe `ArquivoPdf`

Esta classe em Java utiliza a biblioteca `Apache PDFBox` para carregar um arquivo PDF a partir de um caminho local. O objetivo é permitir a manipulação de arquivos PDF dentro de um sistema de transformação de dados.

## Estrutura da Classe `TabelasPdf`

Esta classe em Java utiliza a biblioteca `Tabula` para extrair tabelas de arquivos PDF. As tabelas extraídas são processadas e convertidas em um formato de texto, com a substituição de siglas por seus significados definidos.

### Atributos:

- `legenda`: Um HashMap que associa siglas a seus significados. Exemplo: "OD" para "Seg. Odontológica".

### Métodos:

#### `tabelaExiste(int tamanhoLinha)`
1. Verifica se a tabela possui mais de uma linha, indicando que há conteúdo relevante na tabela.

#### `substituiSiglas(String cell)`
1. Substitui siglas encontradas no texto por seus significados definidos no mapa legenda. O texto também é ajustado para remover quebras de linha e espaços extras.

#### `extrairTabelasDoPdf(PDDocument pdDocument)`
1. Utiliza ObjectExtractor para acessar o conteúdo de cada página do PDF.
2. Utiliza o SpreadsheetExtractionAlgorithm para extrair as tabelas da página.
3. As siglas nas tabelas são substituídas pelos significados definidos no mapa `legenda`.
4. O conteúdo extraído é formatado em um formato CSV (separado por ponto e vírgula) e armazenado em um `StringBuilder`.
5. Após a extração, o conteúdo é passado para o método `ArquivoZip.baixarZip()` para ser baixado como um arquivo compactado.

## Estrutura da Classe `ArquivoZip`

### Métodos:

#### `baixarZip(StringBuilder stringBuilder)`

1. O método cria um arquivo ZIP com o nome `"Teste_SamirAlves.zip"`.
2. O conteúdo presente no `StringBuilder` é escrito em um arquivo CSV dentro do ZIP.
3. A codificação UTF-8 é utilizada para garantir a correta representação de caracteres especiais.
4. A marca BOM é escrita no início do arquivo CSV para garantir que os caracteres especiais sejam corretamente interpretados por editores e sistemas que leem o arquivo CSV.
5. O arquivo ZIP é finalizado e salvo no diretório local.
