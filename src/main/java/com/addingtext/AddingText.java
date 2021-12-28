package com.addingtext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class AddingText {

    public static void main(String[] args) throws Exception {

        String inputFilePath = "D:\\Belajar Java\\Task\\pdfaddingtext\\assets\\uploadpdf\\SPD-2021-0004899-12-10302000.pdf";
        String outputFilePath = "D:\\Belajar Java\\Task\\pdfaddingtext\\assets\\uploadpdf\\SPD-2021-0004899-12-10302000(Update).pdf";

        OutputStream fos = new FileOutputStream(new File(outputFilePath));

        PdfReader pdfReader = new PdfReader(inputFilePath);
        PdfStamper pdfStamper = new PdfStamper(pdfReader, fos);
        // Logo BSrE
        Image image = Image.getInstance("D:\\Belajar Java\\Task\\pdfaddingtext\\assets\\uploadpdf\\img\\BSE.png");
        image.scaleAbsolute(50, 33);
        image.setAbsolutePosition(510f, 20f);

        for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {

            // Posisi Content getOverContent / getBottomContent
            PdfContentByte pdfContentByte = pdfStamper.getOverContent(i);
            // Add Image (LOGO)
            pdfContentByte.addImage(image);
            // Add text in existing PDF
            pdfContentByte.beginText();
            // set font and size
            pdfContentByte.setFontAndSize(BaseFont.createFont(
                    BaseFont.TIMES_ROMAN,
                    BaseFont.CP1257,
                    BaseFont.EMBEDDED), 7);
            // set x and y koordinat
            pdfContentByte.setTextMatrix(135, 30);
            pdfContentByte.showText(
                    "Dokumen ini telah di tanda tangani secara elektronik menggunakan sertifikat elektronik yang di terbitkan oleh BSrE");
            System.out.println("Text added in " + outputFilePath);
            pdfContentByte.endText();

        }
        pdfStamper.close(); // close pdfStamper

        System.out.println("PDF Modified = " + outputFilePath);

    }
}
