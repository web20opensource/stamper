package part2.chapter06;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;




public class Stamptext {
  public static final String myPdfSheets
    = "myPdfSheets.pdf";
	
	public static final String myPdfSheets_stamped
	= "myPdfSheets_stamped.pdf";
	
	public static final String leyend
	= "leyend.pdf";
	
	public static void main(String[] args)
	        throws DocumentException, IOException {
		
		PdfReader reader = new PdfReader(myPdfSheets);
		manipulateWithStamper(reader);

	}
	
	private static void manipulateWithStamper(PdfReader reader)
			throws IOException, DocumentException {
			
		
			// Leyenda de Cursando el servicio social
			PdfReader s_reader = new PdfReader(leyend);
			
			// stamper
			PdfStamper stamper =
				new PdfStamper(reader, new FileOutputStream(myPdfSheets_stamped));
			
			PdfImportedPage pageLeyend = stamper.getImportedPage(s_reader, 1);
			
			stamper.setRotateContents(false);
			
			// Canvas to write text and then add to the page
			PdfContentByte background;
			
			for (int i = 1; i <= reader.getNumberOfPages(); i++) {
				background = stamper.getUnderContent(i);
				background.addTemplate(pageLeyend, 0, 0);
			}	
			stamper.close();
			}
	
}
