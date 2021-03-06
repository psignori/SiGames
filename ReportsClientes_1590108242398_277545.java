/*
 * Generated by JasperReports - 21/05/20 21:44
 */
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.fill.*;

import java.util.*;
import java.math.*;
import java.text.*;
import java.io.*;
import java.net.*;



/**
 *
 */
public class ReportsClientes_1590108242398_277545 extends JREvaluator
{


    /**
     *
     */
    private JRFillParameter parameter_IS_IGNORE_PAGINATION = null;
    private JRFillParameter parameter_REPORT_CONNECTION = null;
    private JRFillParameter parameter_situacao = null;
    private JRFillParameter parameter_FILTER = null;
    private JRFillParameter parameter_JASPER_REPORT = null;
    private JRFillParameter parameter_REPORT_LOCALE = null;
    private JRFillParameter parameter_REPORT_TIME_ZONE = null;
    private JRFillParameter parameter_REPORT_TEMPLATES = null;
    private JRFillParameter parameter_REPORT_MAX_COUNT = null;
    private JRFillParameter parameter_idpessoa = null;
    private JRFillParameter parameter_REPORT_SCRIPTLET = null;
    private JRFillParameter parameter_JASPER_REPORTS_CONTEXT = null;
    private JRFillParameter parameter_REPORT_FORMAT_FACTORY = null;
    private JRFillParameter parameter_REPORT_PARAMETERS_MAP = null;
    private JRFillParameter parameter_REPORT_RESOURCE_BUNDLE = null;
    private JRFillParameter parameter_REPORT_DATA_SOURCE = null;
    private JRFillParameter parameter_REPORT_CONTEXT = null;
    private JRFillParameter parameter_REPORT_CLASS_LOADER = null;
    private JRFillParameter parameter_REPORT_VIRTUALIZER = null;
    private JRFillParameter parameter_SORT_FIELDS = null;
    private JRFillField field_id_pessoa = null;
    private JRFillField field_situacao = null;
    private JRFillField field_COLUMN_10 = null;
    private JRFillField field_bairro = null;
    private JRFillField field_numero_casa = null;
    private JRFillField field_cpf = null;
    private JRFillField field_nome = null;
    private JRFillField field_data_nasc = null;
    private JRFillField field_nome_user = null;
    private JRFillField field_email = null;
    private JRFillField field_rua = null;
    private JRFillVariable variable_PAGE_NUMBER = null;
    private JRFillVariable variable_MASTER_CURRENT_PAGE = null;
    private JRFillVariable variable_MASTER_TOTAL_PAGES = null;
    private JRFillVariable variable_COLUMN_NUMBER = null;
    private JRFillVariable variable_REPORT_COUNT = null;
    private JRFillVariable variable_PAGE_COUNT = null;
    private JRFillVariable variable_COLUMN_COUNT = null;


    /**
     *
     */
    public void customizedInit(
        Map pm,
        Map fm,
        Map vm
        )
    {
        initParams(pm);
        initFields(fm);
        initVars(vm);
    }


    /**
     *
     */
    private void initParams(Map pm)
    {
        parameter_IS_IGNORE_PAGINATION = (JRFillParameter)pm.get("IS_IGNORE_PAGINATION");
        parameter_REPORT_CONNECTION = (JRFillParameter)pm.get("REPORT_CONNECTION");
        parameter_situacao = (JRFillParameter)pm.get("situacao");
        parameter_FILTER = (JRFillParameter)pm.get("FILTER");
        parameter_JASPER_REPORT = (JRFillParameter)pm.get("JASPER_REPORT");
        parameter_REPORT_LOCALE = (JRFillParameter)pm.get("REPORT_LOCALE");
        parameter_REPORT_TIME_ZONE = (JRFillParameter)pm.get("REPORT_TIME_ZONE");
        parameter_REPORT_TEMPLATES = (JRFillParameter)pm.get("REPORT_TEMPLATES");
        parameter_REPORT_MAX_COUNT = (JRFillParameter)pm.get("REPORT_MAX_COUNT");
        parameter_idpessoa = (JRFillParameter)pm.get("idpessoa");
        parameter_REPORT_SCRIPTLET = (JRFillParameter)pm.get("REPORT_SCRIPTLET");
        parameter_JASPER_REPORTS_CONTEXT = (JRFillParameter)pm.get("JASPER_REPORTS_CONTEXT");
        parameter_REPORT_FORMAT_FACTORY = (JRFillParameter)pm.get("REPORT_FORMAT_FACTORY");
        parameter_REPORT_PARAMETERS_MAP = (JRFillParameter)pm.get("REPORT_PARAMETERS_MAP");
        parameter_REPORT_RESOURCE_BUNDLE = (JRFillParameter)pm.get("REPORT_RESOURCE_BUNDLE");
        parameter_REPORT_DATA_SOURCE = (JRFillParameter)pm.get("REPORT_DATA_SOURCE");
        parameter_REPORT_CONTEXT = (JRFillParameter)pm.get("REPORT_CONTEXT");
        parameter_REPORT_CLASS_LOADER = (JRFillParameter)pm.get("REPORT_CLASS_LOADER");
        parameter_REPORT_VIRTUALIZER = (JRFillParameter)pm.get("REPORT_VIRTUALIZER");
        parameter_SORT_FIELDS = (JRFillParameter)pm.get("SORT_FIELDS");
    }


    /**
     *
     */
    private void initFields(Map fm)
    {
        field_id_pessoa = (JRFillField)fm.get("id_pessoa");
        field_situacao = (JRFillField)fm.get("situacao");
        field_COLUMN_10 = (JRFillField)fm.get("COLUMN_10");
        field_bairro = (JRFillField)fm.get("bairro");
        field_numero_casa = (JRFillField)fm.get("numero_casa");
        field_cpf = (JRFillField)fm.get("cpf");
        field_nome = (JRFillField)fm.get("nome");
        field_data_nasc = (JRFillField)fm.get("data_nasc");
        field_nome_user = (JRFillField)fm.get("nome_user");
        field_email = (JRFillField)fm.get("email");
        field_rua = (JRFillField)fm.get("rua");
    }


    /**
     *
     */
    private void initVars(Map vm)
    {
        variable_PAGE_NUMBER = (JRFillVariable)vm.get("PAGE_NUMBER");
        variable_MASTER_CURRENT_PAGE = (JRFillVariable)vm.get("MASTER_CURRENT_PAGE");
        variable_MASTER_TOTAL_PAGES = (JRFillVariable)vm.get("MASTER_TOTAL_PAGES");
        variable_COLUMN_NUMBER = (JRFillVariable)vm.get("COLUMN_NUMBER");
        variable_REPORT_COUNT = (JRFillVariable)vm.get("REPORT_COUNT");
        variable_PAGE_COUNT = (JRFillVariable)vm.get("PAGE_COUNT");
        variable_COLUMN_COUNT = (JRFillVariable)vm.get("COLUMN_COUNT");
    }


    /**
     *
     */
    public Object evaluate(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = ((java.lang.Integer)field_id_pessoa.getValue()); //$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = ((java.lang.String)field_nome.getValue()); //$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = ((java.sql.Date)field_data_nasc.getValue()); //$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = ((java.lang.String)field_cpf.getValue()); //$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = ((java.lang.String)field_email.getValue()); //$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = ((java.lang.String)field_COLUMN_10.getValue()); //$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = ((java.lang.String)field_situacao.getValue()); //$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = "Page "+((java.lang.Integer)variable_PAGE_NUMBER.getValue())+" of"; //$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = " " + ((java.lang.Integer)variable_PAGE_NUMBER.getValue()); //$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = new java.util.Date(); //$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = "Arroio do Meio "; //$JR_EXPR_ID=18$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


    /**
     *
     */
    public Object evaluateOld(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = ((java.lang.Integer)field_id_pessoa.getOldValue()); //$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = ((java.lang.String)field_nome.getOldValue()); //$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = ((java.sql.Date)field_data_nasc.getOldValue()); //$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = ((java.lang.String)field_cpf.getOldValue()); //$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = ((java.lang.String)field_email.getOldValue()); //$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = ((java.lang.String)field_COLUMN_10.getOldValue()); //$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = ((java.lang.String)field_situacao.getOldValue()); //$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = "Page "+((java.lang.Integer)variable_PAGE_NUMBER.getOldValue())+" of"; //$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = " " + ((java.lang.Integer)variable_PAGE_NUMBER.getOldValue()); //$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = new java.util.Date(); //$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = "Arroio do Meio "; //$JR_EXPR_ID=18$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


    /**
     *
     */
    public Object evaluateEstimated(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = new java.lang.Integer(1); //$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = new java.lang.Integer(0); //$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = ((java.lang.Integer)field_id_pessoa.getValue()); //$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = ((java.lang.String)field_nome.getValue()); //$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = ((java.sql.Date)field_data_nasc.getValue()); //$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = ((java.lang.String)field_cpf.getValue()); //$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = ((java.lang.String)field_email.getValue()); //$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = ((java.lang.String)field_COLUMN_10.getValue()); //$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = ((java.lang.String)field_situacao.getValue()); //$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = "Page "+((java.lang.Integer)variable_PAGE_NUMBER.getEstimatedValue())+" of"; //$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = " " + ((java.lang.Integer)variable_PAGE_NUMBER.getEstimatedValue()); //$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = new java.util.Date(); //$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = "Arroio do Meio "; //$JR_EXPR_ID=18$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


}
