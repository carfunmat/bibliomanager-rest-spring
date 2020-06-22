package com.carlosfunalleras.rest.view.pdf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.carlosfunalleras.rest.dto.PrestamoDTO;
import com.carlosfunalleras.rest.models.services.IPrestamoService;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("prestamos/pdf")
public class PrestamosPdfView extends AbstractPdfView {
	
	@Autowired
	IPrestamoService prestamoService;

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<PrestamoDTO> prestamos = prestamoService.findAll();
		
		PdfPTable tabla = new PdfPTable(3);
		for (PrestamoDTO p : prestamos) {
			tabla.addCell("Título Libro");
			tabla.addCell(p.getLibro().getTitulo());
			tabla.addCell("FechaInicio");
			tabla.addCell(p.getFechaInicio().toString());
			tabla.addCell("FechaFinal");
			tabla.addCell(p.getFechaFinal().toString());
		}
		
		document.add(tabla);
		
	}

}
