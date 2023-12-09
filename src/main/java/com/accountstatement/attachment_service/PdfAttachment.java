package com.accountstatement.attachment_service;

import com.accountstatement.pojo.AccountStatement;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PdfAttachment implements AttachmentService<AccountStatement>{
    @Override
    public byte[] generate(List<AccountStatement> accountStatements) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             PDDocument document = new PDDocument()) {

            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(50, 700);

            for (AccountStatement statement : accountStatements) {
                contentStream.showText("User Email: " + statement.getUserEmail());
                contentStream.newLine();
                contentStream.showText("Transaction Date: " + statement.getTransactionDate().format(DateTimeFormatter.ISO_DATE));
                contentStream.newLine();
                contentStream.showText("Amount: " + statement.getAmount().format(DateTimeFormatter.ISO_DATE)); // Replace with proper formatting
                contentStream.newLine();
                contentStream.newLine(); // Space between entries
            }

            contentStream.endText();
            contentStream.close();
            document.save(outputStream);
            return outputStream.toByteArray();

        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }

        return new byte[0]; // Return empty byte array if an error occurs
    }
}
