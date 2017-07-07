package extr;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.apache.pdfbox.contentstream.PDFGraphicsStreamEngine;
import org.apache.pdfbox.contentstream.PDFStreamEngine;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.graphics.image.PDImage;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

public class ImageExtractor extends PDFStreamEngine {

	File resource;
	public final File RESULT_FOLDER = new File("C:\\Users\\nw\\Documents\\tmp\\results");



	public void extractImages(Path path, List<PageBookmark> bookmarkList) throws InvalidPasswordException, IOException{
		File resource = path.toFile();
		String pdfFileName = path.getFileName().toString().replace(".pdf", "");
		PDDocument document = PDDocument.load(resource);
		int page = 1;
		for (final PDPage pdPage : document.getPages())
		{
			final int currentPage = page;
			PDFGraphicsStreamEngine pdfGraphicsStreamEngine = new PDFGraphicsStreamEngine(pdPage)
			{
				int index = 0;

				@Override
				public void drawImage(PDImage pdImage) throws IOException
				{
					if (pdImage instanceof PDImageXObject)
					{
						PDImageXObject image = (PDImageXObject)pdImage;
						System.out.println("P:" + currentPage + "\t  " + "Index=" + index + "\t" + pdfFileName);
						String bookmarkName = bookmarkList.get(currentPage - 1).getBookmark();
						String imageFileName = bookmarkName + "_p" + currentPage + "_" + pdfFileName + ".jpg";
						File file = new File(RESULT_FOLDER, String.format(imageFileName, currentPage, index, image.getSuffix()));
						ImageIOUtil.writeImage(image.getImage(), image.getSuffix(), new FileOutputStream(file));
						index++;
					}
				}

				@Override
				public void appendRectangle(Point2D p0, Point2D p1, Point2D p2, Point2D p3) throws IOException { }

				@Override
				public void clip(int windingRule) throws IOException { }

				@Override
				public void moveTo(float x, float y) throws IOException {  }

				@Override
				public void lineTo(float x, float y) throws IOException { }

				@Override
				public void curveTo(float x1, float y1, float x2, float y2, float x3, float y3) throws IOException {  }

				@Override
				public Point2D getCurrentPoint() throws IOException { return null; }

				@Override
				public void closePath() throws IOException { }

				@Override
				public void endPath() throws IOException { }

				@Override
				public void strokePath() throws IOException { }

				@Override
				public void fillPath(int windingRule) throws IOException { }

				@Override
				public void fillAndStrokePath(int windingRule) throws IOException { }

				@Override
				public void shadingFill(COSName shadingName) throws IOException { }
			};
			pdfGraphicsStreamEngine.processPage(pdPage);
			page++;
		}
	}
}
//    protected int documentImageCount = 0;
//
//    public int getDocumentImageCount() {
//        return documentImageCount;
//    }
//
//    public ImageExtractor() {
//        addOperator(new OperatorProcessor() {
//            @Override
//            public void process(Operator operator, List<COSBase> arguments) throws IOException {
//                if (arguments.size() < 1) {
//                    throw new MissingOperandException(operator, arguments);
//                }
//                if (isImage(arguments.get(0))) {
//                    documentImageCount++;
//                }
//            }
//
//            protected Boolean isImage(COSBase base) {
//                return (base instanceof COSName) &&
//                        context.getResources().isImageXObject((COSName)base);
//            }
//
//            @Override
//            public String getName() {
//                return "Do";
//            }
//        });
//    }
//    
////    Invoke it for each page:
////    protected int countImagesWithProcessor(PDDocument pdf) throws IOException {
////        StopWatch stopWatch = new StopWatch();
////        stopWatch.start();
////
////        PdfImageCounter counter = new PdfImageCounter();
////        for (PDPage pdPage : pdf.getPages()) {
////            counter.processPage(pdPage);
////        }
////
////        stopWatch.stop();
////        int imageCount = counter.getDocumentImageCount();
////        log.info("Images counted: time={}s,imageCount={}", stopWatch.getTime() / 1000, imageCount);
////        return imageCount;
////    }
//}
