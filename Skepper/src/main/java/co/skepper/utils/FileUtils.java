package co.skepper.utils;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
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

    public static String readDocFile(MultipartFile book) {
        String content = "";
        String filename = book.getOriginalFilename();

        try {
            if (filename.endsWith(".doc")) { //Judge the file format
                InputStream fis = book.getInputStream();
                WordExtractor wordExtractor = new WordExtractor(fis);//Use the WordExtractor class in the HWPF component to extract text or paragraphs from a Word document
                for (String words : wordExtractor.getParagraphText()) {//Get paragraph content
                    content += words + "\n";
                }
                fis.close();
            }
            if (filename.endsWith(".docx")) {
                File uFile = new File("tempFile.docx");//Create a temporary file
                if (!uFile.exists()) {
                    uFile.createNewFile();
                }
                FileCopyUtils.copy(book.getBytes(), uFile);//Copy file content
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

    public static String readPdfFile(MultipartFile file) {
        String content = "";

        try {
            PdfReader reader = new PdfReader(file.getOriginalFilename());
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                content += PdfTextExtractor.getTextFromPage(reader, i);
            }
            reader.close();

        } catch (IOException e) {
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

    private static File createFile(byte[] bytes) {
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
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return file;
    }

    private static File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
