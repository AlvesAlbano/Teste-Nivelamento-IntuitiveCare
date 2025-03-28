from bs4 import BeautifulSoup
import requests
import os
import zipfile

class WebScrapping:
    
    link_site:str = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos"
    pasta_download:str = "Teste 1 Webscrapping/PDF Downloads/"
    
    def encontrar_pdf(self) -> list[str]:
        html_site:str = requests.get(self.link_site).content
        soup:str = BeautifulSoup(html_site,"html.parser")
        
        div_conteudo:str = soup.find("div",class_="cover-richtext-tile tile-content")
        ol_conteudo:str = div_conteudo.find("ol")
        urls_pdf:list[str] = []
        
        for li in ol_conteudo.find_all("li"):
            a_conteudo = li.find_all("a",href=True)
            
            for link in a_conteudo:
                link = str(link["href"])
                
                if link.endswith(".pdf"):
                    print(f"link encontrado: {link}")
                    urls_pdf.append(link)
        
        print(f"{len(urls_pdf)} links encontrados")
        return urls_pdf
    
    def baixar_arquivo(self, urls_pdf:list[str]) -> None:
        nome_arquivo:str
        caminho_download:str
        arquivo_zip:str = os.path.join(self.pasta_download,"PDFs.zip")
        
        if not os.path.exists(self.pasta_download):
            os.makedirs(self.pasta_download)
        
        with zipfile.ZipFile(arquivo_zip,"w",zipfile.ZIP_DEFLATED) as zipf:
            for i,url in enumerate(urls_pdf):
                print(f"baixando pdf ({i+1}/{len(urls_pdf)}) da url: {url}")

                pdf = requests.get(url,allow_redirects=True)
                nome_arquivo = os.path.basename(url)
                caminho_download = os.path.join(self.pasta_download,nome_arquivo)

                with open(caminho_download,"wb") as arquivo:
                    arquivo.write(pdf.content)
                    
                zipf.write(caminho_download,arcname=nome_arquivo)
                os.remove(caminho_download)
            
if __name__ == '__main__':
    web_scrapping:WebScrapping = WebScrapping()
    
    arquivos_pdf = web_scrapping.encontrar_pdf()
    
    web_scrapping.baixar_arquivo(arquivos_pdf)