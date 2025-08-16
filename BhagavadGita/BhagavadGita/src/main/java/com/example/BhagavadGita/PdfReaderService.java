package com.example.BhagavadGita;



import java.util.List;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.stereotype.Service;

@Service
public class PdfReaderService {

    public List<Document> getDocsFromPdf() {
        PagePdfDocumentReader pdfReader = new PagePdfDocumentReader(
            "classpath:/gita.pdf",
            PdfDocumentReaderConfig.builder()
                .withPageTopMargin(0)
                .withPageExtractedTextFormatter(
                    ExtractedTextFormatter.builder()
                        .withNumberOfTopTextLinesToDelete(0)
                        .build()
                )
                .withPagesPerDocument(1)
                .build()
        );

        return pdfReader.read();
    }
}

