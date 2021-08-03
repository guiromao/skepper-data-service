package co.skepper.utils;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

public class FileUtils {

    public static String readDocFile(Blob textBlob, String type) {
        File file = null;
        String content = "";

        try {
            file = createFile(textBlob.getBytes(1, (int) textBlob.length()));
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        try {
            if (type.equals("doc")) { //Judge the file format
                InputStream fis = new FileInputStream(file);
                WordExtractor wordExtractor = new WordExtractor(fis);//Use the WordExtractor class in the HWPF component to extract text or paragraphs from a Word document
                for (String words : wordExtractor.getParagraphText()) {//Get paragraph content
                    content += words + "\n";
                }
                fis.close();
            }
            if (type.equals("docx")) {
                File uFile = new File("tempFile.docx");//Create a temporary file
                if (!uFile.exists()) {
                    uFile.createNewFile();
                }
                FileCopyUtils.copy(textBlob.getBytes(1, (int)textBlob.length()), uFile);//Copy file content
                OPCPackage opcPackage = POIXMLDocument.openPackage("tempFile.docx");//Contain all common functions of POI OOXML document class, open a file package.
                XWPFDocument document = new XWPFDocument(opcPackage);//Use XWPF component XWPFDocument class to get the document content
                List<XWPFParagraph> paras = document.getParagraphs();
                for (XWPFParagraph paragraph : paras) {
                    String words = paragraph.getText();
                    content += words + "\n";
                }
                uFile.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return content;
    }

    public static String readTextFile(MultipartFile file) {
        String result = "";

        try {
            result = new String(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static File createFile(byte [] bytes){
        File file = new File("book");

        try {
            // Initialize a pointer
            // in file using OutputStream
            OutputStream
                    os
                    = new FileOutputStream(file);

            // Starts writing the bytes in it
            os.write(bytes);

            // Close the file
            os.close();
        }

        catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return file;
    }

}
