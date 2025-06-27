package com.bnb.airbnb.service;

import com.bnb.airbnb.entity.Booking;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.stream.Stream;

@Service
public class PdfService {
    private EmailService emailService;

    public PdfService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void generatePdf(Booking booking) {
        try {
            // Implement PDF generation logic using a library like iText or Apache FOP
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("C://AIR BNB//"+booking.getId()+"booking_confirmation.pdf"));

            document.open();

            PdfPTable table = new PdfPTable(3);
            addTableHeader(table);//set the header row and add to table
            addRows(table,booking);
            //addCustomRows(table);

            document.add(table);
            document.close();
//            emailService.sendEmail(booking.getEmail(),
//                    "Your Airbnb Booking Confirmation is done your booking id is "+booking.getId() ,
//                    "Please find your booking confirmation attached.",
//              new File("C://AIR BNB//"+booking.getId()+"booking_confirmation.pdf")
//            );
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    private void addTableHeader(PdfPTable table) {
        Stream.of("Guest Name", "Hotel Name", "City")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }
    private void addRows(PdfPTable table, Booking booking) {
        table.addCell(booking.getGuestName());
        table.addCell(booking.getProperty().getName());
        table.addCell(booking.getProperty().getCity().getName());
    }
//    private void addCustomRows(PdfPTable table)
//            throws URISyntaxException, BadElementException, IOException {
//        Path path = Paths.get(ClassLoader.getSystemResource("Java_logo.png").toURI());
//        Image img = Image.getInstance(path.toAbsolutePath().toString());
//        img.scalePercent(10);
//
//        PdfPCell imageCell = new PdfPCell(img);
//        table.addCell(imageCell);
//
//        PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
//        horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(horizontalAlignCell);
//
//        PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
//        verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
//        table.addCell(verticalAlignCell);
//    }
}
