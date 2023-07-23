/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.movie.moviestore.managedbean;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import com.movie.moviestore.entity.Movie;
import com.movie.moviestore.sessionbean.MovieSessionBean;
import com.movie.moviestore.utils.AppConstants;
import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.component.export.ExcelOptions;
import org.primefaces.component.export.PDFOptions;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kioko
 */
@Named(value = "movieManagedBean")
@ViewScoped
public class MovieManagedBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<Movie> movielist;
    
    @EJB
    private MovieSessionBean msb;

    private Integer year;

    private ExcelOptions excelOpt;

    private PDFOptions pdfOpt;

    private String description;
    private String name;
    private String production;
    private String status;
    private String type;
    private String movieId;

    private String selectedOption;

    private String filterColumn;
    private Boolean detail;

    private static final Logger LOG = Logger.getLogger(MovieManagedBean.class.getName());

    /**
     * Creates a new instance of MovieManagedBean
     */
    public MovieManagedBean() {
    }

    public void onItemSelect(SelectEvent event) {
        switch (selectedOption) {
            case "selectColumn":
                detail = false;
            case "name":
                detail = true;
                break;
            case "status":
            case "production":
            case "type":
            case "movieId":
                detail = true;
                break;
            default:
                detail = false;
                break;
        }
    }

    public void filter() throws IOException {
        if (getSelectedOption().equalsIgnoreCase("name")) {
            getMovielist().clear();
            getMovielist().add(msb.getByName(getFilterColumn()));
        } else if (getSelectedOption().equalsIgnoreCase("production")) {
            getMovielist().clear();
            getMovielist().addAll(msb.getByProduction(getFilterColumn()));
        } else if (getSelectedOption().equalsIgnoreCase("status")) {
            getMovielist().clear();
            getMovielist().addAll(msb.getByStatus(getFilterColumn()));
        } else if (getSelectedOption().equalsIgnoreCase("movieid")) {
            getMovielist().clear();
            getMovielist().add(msb.getByMovieId(getFilterColumn()));
        }else if(getSelectedOption().equalsIgnoreCase("type")) {
            getMovielist().clear();
            getMovielist().addAll(msb.getByType(getFilterColumn()));
        }
    }

    public void refresh() {
        detail = false;
        setFilterColumn(null);
        setSelectedOption(null);
        setMovieId(null);
        setStatus(null);
        setProduction(null);
        setName(null);
        getMovielist().clear();
        setMovielist(msb.getAllMovies());

    }

    public void createMovie() {
        try {
            Movie mvie = new Movie();
            String MovieId = AppConstants.TAG + RandomStringUtils.randomAlphanumeric(10);
            
            mvie.setDescription(description);
            mvie.setName(name);
            mvie.setProduction(production);
            mvie.setStatus(status);
            mvie.setType(type);
            mvie.setMovieId(MovieId);
            mvie.setDateCreated(Date.from(Instant.now()));

            LOG.info(name);
            LOG.info(description);
            LOG.info(production);
            LOG.info(status);
            LOG.info(type);
            LOG.info(MovieId);

            msb.addMovie(mvie);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "New Movie added!"));

        } catch (Exception e) {
            System.out.println("Error message");
        }
    }

    @PostConstruct
    public void init() {
        Calendar cal = Calendar.getInstance();
        setMovielist(msb.getAllMovies());
        setYear(cal.get(Calendar.YEAR));
        customizationOptions();
    }

    public List<Movie> getMovielist() {
        return movielist;
    }

    public void setMovielist(List<Movie> movielist) {
        this.movielist = movielist;
    }

    public void customizationOptions() {
        excelOpt = new ExcelOptions();
        excelOpt.setFacetBgColor("#F88017");
        excelOpt.setFacetFontSize("10");
        excelOpt.setFacetFontColor("#0000ff");
        excelOpt.setFacetFontStyle("BOLD");
        excelOpt.setCellFontColor("#00ff00");
        excelOpt.setCellFontSize("8");

        pdfOpt = new PDFOptions();
        pdfOpt.setFacetBgColor("#F88017");
        pdfOpt.setFacetFontColor("#0000ff");
        pdfOpt.setFacetFontStyle("BOLD");
        pdfOpt.setCellFontSize("12");

    }

    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);

        HSSFCellStyle cellStyle = wb.createCellStyle();

        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = header.getCell(i);

            cell.setCellStyle(cellStyle);
        }
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public ExcelOptions getExcelOpt() {
        return excelOpt;
    }

    public void setExcelOpt(ExcelOptions excelOpt) {
        this.excelOpt = excelOpt;
    }

    public PDFOptions getPdfOpt() {
        return pdfOpt;
    }

    public void setPdfOpt(PDFOptions pdfOpt) {
        this.pdfOpt = pdfOpt;
    }

    public MovieSessionBean getMsb() {
        return msb;
    }

    public void setMsb(MovieSessionBean msb) {
        this.msb = msb;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public String getFilterColumn() {
        return filterColumn;
    }

    public void setFilterColumn(String filterColumn) {
        this.filterColumn = filterColumn;
    }

    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

}
