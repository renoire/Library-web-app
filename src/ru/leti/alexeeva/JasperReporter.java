package ru.leti.alexeeva;

import net.sf.jasperreports.engine.*;

import java.util.HashMap;

public class JasperReporter {
    public void doReport(String name) throws JRException {
        System.out.println("starting report");
        JasperReport jasperReport = JasperCompileManager
                .compileReport("/home/renoire/sdfs/Library/resources/authorReport.jrxml");

        final HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("name", name);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                parameters, new JREmptyDataSource());

        JasperExportManager.exportReportToPdfFile(jasperPrint, "/home/renoire/sdfs/Library/resources/simple_report.pdf");

    }
}