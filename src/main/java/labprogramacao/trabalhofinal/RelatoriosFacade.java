/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labprogramacao.trabalhofinal;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.File; 
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

/**Classe na qual são estruturados os métodos responsáveis por montar a estrutura
 * e gerar o relatorio de uma venda seja ele em pdf ou txt
 * @author carol
 */
public class RelatoriosFacade {
    
    /**
    *Método que recebe a instancia da venda atual, monta um pdf e gera o arquivo 
    * com o mesmo
    * @param tarefas - ArrayList<Tarefa> 
     * @throws java.io.IOException 
    */
    public void geraPDF(ArrayList<Tarefa> tarefas, String locale) throws IOException{
        
        try (PDDocument documento = new PDDocument()) {

            PDPage relatorioPDF = new PDPage();
            documento.addPage(relatorioPDF);

            try (PDPageContentStream cont = new PDPageContentStream(documento, relatorioPDF)) {

                cont.beginText();

                cont.setFont(PDType1Font.COURIER, 12);
                cont.setLeading(14.5f);

                cont.newLineAtOffset(10, 700);
                
                String linha1 = "Lista de Tarefas";
                
                if (locale.equals("inglês")) {
                    linha1 = "Task List";
                } 
                    
                cont.showText(linha1);
                cont.newLine();
                cont.newLine();
                
                for(Tarefa tarefa: tarefas){
                    String linha2 = tarefa.getId() + " - " + tarefa.getDescricao();
                    
                    cont.showText(linha2);
                    cont.newLine();
                }
                
                cont.endText();
            }

            documento.save("src/main/resources/tarefas.pdf");
            
            JFrame frame = new JFrame("");
            JOptionPane.showMessageDialog(frame,
                "PDF gerado com sucesso",
                "Aviso", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
    *Método que recebe a instancia da venda atual, monta um txt e gera o arquivo 
    * com o mesmo
    * @param venda - Venda 
    */
    public void geraTXT(ArrayList<Tarefa> tarefas, String locale) {
        
        File myObj = new File("tarefas.txt");
        
        try {
            FileWriter myWriter = new FileWriter("tarefas.txt");
            
            
            String linha1 = "Lista de Tarefas";
                
                if (locale.equals("inglês")) {
                    linha1 = "Task List";
                } 
                
            myWriter.write(linha1 + "\n" + "\n");
            
            for(Tarefa tarefa: tarefas){
                myWriter.write(tarefa.getId() + " - " + tarefa.getDescricao() + "\n");
            }
            
            myWriter.close();
            
            JFrame frame = new JFrame("");
            JOptionPane.showMessageDialog(frame,
            "Txt gerado com sucesso",
            "Aviso", 
            JOptionPane.INFORMATION_MESSAGE);
            
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
