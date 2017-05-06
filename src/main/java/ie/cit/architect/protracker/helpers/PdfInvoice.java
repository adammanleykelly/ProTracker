package ie.cit.architect.protracker.helpers;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TabAlignment;
import com.itextpdf.layout.property.TextAlignment;
import ie.cit.architect.protracker.controller.Controller;
import ie.cit.architect.protracker.model.IProject;
import ie.cit.architect.protracker.model.Project;
import ie.cit.architect.protracker.model.ProjectList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by brian on 15/04/17.
 */
public class PdfInvoice {

    private static final String SEPARATOR = File.separator;
    private static final String PATH_TO_DESKTOP = System.getProperty("user.home") + SEPARATOR + "Desktop";
    private static final String FILE_NAME = "Invoice";
   //private static final String LOGO = "/home/brian/workspace/PdfInvoice/src/main/resources/companylogo.png";
    private static final String LOGO = "./src/main/resources/companylogo.png";
    private static Date mDate;



    private PdfInvoice() {
    }

    // static factory method
    public static PdfInvoice getInstance() {
        return new PdfInvoice();
    }


    public void editPdf(String pName, String cName, double fee) {
        try {
            createPdfDocument(pName, cName, fee);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void createPdfDocument(String projectName, String clientName, double fee) throws IOException {

        File file = new File(PATH_TO_DESKTOP);
        file.getParentFile().mkdirs();


        PdfWriter writer = new PdfWriter(
                PATH_TO_DESKTOP + SEPARATOR + projectName + SEPARATOR + FILE_NAME);

        PdfDocument pdf = new PdfDocument(writer);

        Document document = new Document(pdf);

        Image logo = new Image(ImageDataFactory.create(LOGO));

        logo.scaleToFit(208f, 115f);

        document.add(logo);


        Style title = new Style();
        PdfFont fontTitle = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        title.setFont(fontTitle).setFontSize(20);

        Style normal = new Style();
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        normal.setFont(font).setFontSize(14);

        Paragraph paragraphTitle = new Paragraph(new Text("INVOICE").addStyle(title));
        paragraphTitle.setTextAlignment(TextAlignment.CENTER);
        paragraphTitle.setUnderline();
        paragraphTitle.setFixedLeading(100);

        document.add(paragraphTitle);


        String date = FormatDate.formatDate(new Date());
        String vatNum = "000000000";


        IProject project = Controller.getInstance().createProject(projectName, clientName, fee);


        ArrayList<String> details =
                new ArrayList<>(Arrays.asList(
                        project.getClientName(),
                        date,
                        project.getName(),
                        String.valueOf(project.getFee()),
                        String.valueOf(project.getVat()),
                        String.valueOf(project.getTotal()),
                        vatNum));



        ProjectList projectList = new ProjectList();
        projectList.add((Project) project);




        Paragraph p1 = new Paragraph(new Text("TO: ").addStyle(normal));
        Paragraph p2 = new Paragraph(new Text("DATE:").addStyle(normal));
        Paragraph p3 = new Paragraph(new Text("RE:").addStyle(normal));
        Paragraph p4 = new Paragraph(new Text("ARCHITECTS FEE:").addStyle(normal));
        Paragraph p5 = new Paragraph(new Text("+ VAT @ 23%:").addStyle(normal));
        Paragraph p6 = new Paragraph(new Text("TOTAL:").addStyle(normal));
        Paragraph p7 = new Paragraph(new Text("VAT NO").addStyle(normal));

        ArrayList<Paragraph> paragraphList = new ArrayList<>( Arrays.asList(p1, p2, p3, p4, p5, p6, p7));


        for(int i = 0; i < paragraphList.size(); i++) {
            paragraphList.get(i).setFixedLeading(40);
            paragraphList.get(i).add(new Tab());
            paragraphList.get(i).addTabStops(new TabStop(220, TabAlignment.LEFT));
            paragraphList.get(i).add(details.get(i));

            document.add(paragraphList.get(i));
        }




        document.close();
    }






}