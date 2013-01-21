/*
 * MSc(Biomedical Informatics) Project
 *
 * Development and Implementation of a Web-based Combined Data Repository of Genealogical, Clinical, Laboratory and Genetic Data
 * and
 * a Set of Related Tools
 */
package gov.sp.health.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.primefaces.component.menuitem.MenuItem;

/**
 *
 * @author Dr. M. H. B. Ariyaratne, MBBS, PGIM Trainee for MSc(Biomedical
 * Informatics)
 */
@ManagedBean
@RequestScoped
public class Menu implements Serializable {

    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;
    @ManagedProperty(value = "#{messageProvider}")
    private MessageProvider messageProvider;
    MenuModel model;
    MenuModel msModel;
    String temIx = "";

    public MenuModel getMsModel() {
        return msModel;
    }

    public void setMsModel(MenuModel msModel) {
        this.msModel = msModel;
    }

    public MessageProvider getMessageProvider() {
        return messageProvider;
    }

    public void setMessageProvider(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    public String getTemIx() {
        return temIx;
    }

    public void setTemIx(String temIx) {
        this.temIx = temIx;
    }

    public Menu() {
    }

    public Submenu metadataSubmenu() {
        Submenu submenu = new Submenu();
        submenu.setLabel("Edit");
        submenu.getChildren().add(demographySubmenu());
        submenu.getChildren().add(commonSubmenu());
        
        MenuItem item;
        
        Submenu medicinesSubmenu = new Submenu();
        medicinesSubmenu.setLabel(("medicines"));
        item = new MenuItem();
        item.setValue(("medicineGroups"));
        item.setUrl("ms_medicine_group.xhtml");
        medicinesSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("itemCategories"));
        item.setUrl("ms_item_category.xhtml");
        medicinesSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("itemSubCategories"));
        item.setUrl("ms_item_sub_category.xhtml");
        medicinesSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("genericNames"));
        item.setUrl("ms_vtm.xhtml");
        medicinesSubmenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Medicine Products");
        item.setUrl("ms_vmp.xhtml");
        medicinesSubmenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Items");
        item.setUrl("ms_amp.xhtml");
        medicinesSubmenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Medicine Packs");
        item.setUrl("ms_vmpp.xhtml");
        medicinesSubmenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Item Packs");
        item.setUrl("ms_ampp.xhtml");
        medicinesSubmenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Import from Excel");
        item.setUrl("ms_import_items.xhtml");
        medicinesSubmenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("importFromExcel");
        item.setUrl("ms_import_items.xhtml");
        medicinesSubmenu.getChildren().add(item);


        submenu.getChildren().add(medicinesSubmenu);


        
        
        return submenu;
    }

    public Submenu estimatesSubmenu() {
        Submenu submenu = new Submenu();
        submenu.setLabel("Estimates");
        
        MenuItem item = new MenuItem();
        item.setValue("Create Annual Estiamte");
        item.setUrl("create_estimate.xhtml");
        
        submenu.getChildren().add(item);
        
        return submenu;
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public void createMenu() {
        msModel = new DefaultMenuModel();
        model = new DefaultMenuModel();
        
        MenuItem item;
        
        item = new MenuItem();
        item.setValue("Home");
        item.setUrl("index.xhtml");
        model.addMenuItem(item);

        
        model.addSubmenu(estimatesSubmenu());
        model.addSubmenu(adminSubmenu());
        model.addSubmenu(userSubmenu());
        model.addSubmenu(metadataSubmenu());
    }

    private Submenu commonSubmenu() {
        Submenu submenu = new Submenu();
        submenu.setLabel("Institutions");

        MenuItem item;

        item = new MenuItem();
        item.setValue(("InstitutionTypes"));
        item.setUrl("institution_type.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("institutions"));
        item.setUrl("institutions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("Units"));
        item.setUrl("inventory_unit.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("unitsImages"));
        item.setUrl("unit_add_image.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("Locations"));
        item.setUrl("inventory_location.xhtml");
        submenu.getChildren().add(item);


        item = new MenuItem();
        item.setValue(("locationImages"));
        item.setUrl("location_add_image.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("Persons"));
        item.setUrl("person.xhtml");
        submenu.getChildren().add(item);


        item = new MenuItem();
        item.setValue(("Suppliers"));
        item.setUrl("inventory_supplier.xhtml");
        submenu.getChildren().add(item);


        item = new MenuItem();
        item.setValue(("Manufacturers"));
        item.setUrl("inventory_manufacturer.xhtml");
        submenu.getChildren().add(item);



        item = new MenuItem();
        item.setValue(("Countries"));
        item.setUrl("country.xhtml");
        submenu.getChildren().add(item);

        return submenu;

    }

    private Submenu userSubmenu() {
        Submenu submenu = new Submenu();
        submenu.setLabel(("user"));

        MenuItem item;

        item = new MenuItem();
        item.setValue(("changePassword"));
        item.setUrl("change_password.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("preferences"));
        item.setUrl("under_construction.xhtml");
        submenu.getChildren().add(item);

        return submenu;
    }

    private Submenu demographySubmenu() {
        Submenu submenu;

        MenuItem item;

        submenu = new Submenu();
        submenu.setLabel("Demography");


        item = new MenuItem();
        item.setValue(("edit"));
        item.setUrl("demography_edit.xhtml");
        submenu.getChildren().add(item);



        item = new MenuItem();
        item.setValue(("import"));
        item.setUrl("demography_import_excel.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("display"));
        item.setUrl("demography_display_areas.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("Designations"));
        item.setUrl("designation_level.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("Designations"));
        item.setUrl("designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("institutionDesignations"));
        item.setUrl("institution_designation.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("cadrePositions"));
        item.setUrl("cadre_positions.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("reports"));
        item.setUrl("reports.xhtml");
        submenu.getChildren().add(item);

        return submenu;
    }

   
    private Submenu stockReceiveSubmenu() {
        Submenu submenu = new Submenu();
        MenuItem item;

        item = new MenuItem();
        item.setValue(("msdReceive"));
        item.setUrl("ms_purchase.xhtml");
        submenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("otherGoodReceive"));
        item.setUrl("ms_good_receive_other.xhtml");
        submenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("adviceNoteReceive"));
        item.setUrl("ms_good_receive_advice_note.xhtml");
        submenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("laocalPurchase"));
        item.setUrl("ms_good_receive_local_purchase.xhtml");
        submenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("reprint"));
        item.setUrl("ms_good_receive_reprint.xhtml");
        submenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("cancel"));
        item.setUrl("ms_good_receive_cancel.xhtml");
        submenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("return"));
        item.setUrl("ms_good_receive_return.xhtml");
        submenu.getChildren().add(item);
       

        Submenu issueSubmenu = new Submenu();
        issueSubmenu.setLabel(("issue"));
        item = new MenuItem();
        item.setValue(("issue"));
        item.setUrl("ms_issue.xhtml");
        issueSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("adviceNoteIssue"));
        item.setUrl("ms_issue_advice_note.xhtml");
        issueSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("reprint"));
        item.setUrl("ms_issue_reprint");
        issueSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("Cancel"));
        item.setUrl("ms_issue_cancel.xhtml");
        issueSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("receiveReturn"));
        item.setUrl("ms_issue_return.xhtml");
        issueSubmenu.getChildren().add(item);
        submenu.getChildren().add(issueSubmenu);



        Submenu qualityfailureSubmenu = new Submenu();
        qualityfailureSubmenu.setLabel(("qualityfailure"));
        item = new MenuItem();
        item.setValue(("transfer"));
        item.setUrl("ms_qualityfailure_transfer");
        qualityfailureSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("receive"));
        item.setUrl("ms_qualityfailure_transfer");
        qualityfailureSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("consolidate"));
        item.setUrl("ms_qualityfailure_consolidate");
        qualityfailureSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("distroy"));
        item.setUrl("ms_qualityfailure_distroy");
        qualityfailureSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("circulars"));
        item.setUrl("ms_qualityfailure_circulars");
        qualityfailureSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("reprintTransfer"));
        item.setUrl("ms_qualityfailure_reprint");
        qualityfailureSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("reprintReceive"));
        item.setUrl("ms_qualityfailure_reprint");
        qualityfailureSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("cancelTransfer"));
        item.setUrl("ms_qualityfailure_cancel");
        qualityfailureSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("cancelReceive"));
        item.setUrl("ms_qualityfailure_cancel");
        qualityfailureSubmenu.getChildren().add(item);
        submenu.getChildren().add(qualityfailureSubmenu);



        Submenu reportSubmenu = new Submenu();
        reportSubmenu.setLabel(("report"));
        item = new MenuItem();
        item.setValue(("stockReports"));
        item.setUrl("ms_report_institution_stocks.xhtml");
        reportSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("issueReports"));
        item.setUrl("ms_report_transfer");
        reportSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("receiveReports"));
        item.setUrl("ms_report_consolidate");
        reportSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("qualityFailureReports"));
        item.setUrl("ms_report_distroy");
        reportSubmenu.getChildren().add(item);
        submenu.getChildren().add(reportSubmenu);

        Submenu estimateSubmenu = new Submenu();
        estimateSubmenu.setLabel(("estimate"));
        item = new MenuItem();
        item.setValue(("createEstimate"));
        item.setUrl("ms_estimate_transfer");
        estimateSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("forwardEstimate"));
        item.setUrl("ms_estimate_transfer");
        estimateSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("receiveEstimate"));
        item.setUrl("ms_estimate_consolidate");
        estimateSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("consolidateEstimate"));
        item.setUrl("ms_estimate_distroy");
        estimateSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue("Suplimentary Estimate Creation");
        item.setUrl("ms_estimate_distroy");
        estimateSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue("Supplimentary Estimate Autherization");
        item.setUrl("ms_estimate_distroy");
        estimateSubmenu.getChildren().add(item);

        submenu.getChildren().add(estimateSubmenu);


        Submenu editSubmenu = new Submenu();
        editSubmenu.setLabel(("edit"));

        Submenu insSubmenu = new Submenu();
        insSubmenu.setLabel("Institutions");

        item.setValue(("institutions"));
        item.setUrl("institutions.xhtml");
        insSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("InstitutionHirachi"));
        item.setUrl("inventory_unit.xhtml");
        insSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("Units"));
        item.setUrl("inventory_unit.xhtml");
        insSubmenu.getChildren().add(item);
        item = new MenuItem();
        item.setValue(("Suppliers"));
        item.setUrl("inventory_supplier.xhtml");
        insSubmenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("Manufacturers"));
        item.setUrl("inventory_manufacturer.xhtml");
        insSubmenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("Countries"));
        item.setUrl("country.xhtml");
        insSubmenu.getChildren().add(item);

        editSubmenu.getChildren().add(insSubmenu);


        submenu.getChildren().add(editSubmenu);


        return submenu;
    }

    private Submenu inventorySubmenu() {
        Submenu submenu;

        MenuItem item;

        submenu = new Submenu();
        submenu.setLabel(("inventory"));



        Submenu editMenu = new Submenu();
        editMenu.setLabel(("edit"));



        item = new MenuItem();
        item.setValue(("ItemCategories"));
        item.setUrl("inventory_item_category.xhtml");
        editMenu.getChildren().add(item);



        item = new MenuItem();
        item.setValue(("make"));
        item.setUrl("inventory_make.xhtml");
        editMenu.getChildren().add(item);


        item = new MenuItem();
        item.setValue(("model"));
        item.setUrl("inventory_modal.xhtml");
        editMenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("Items"));
        item.setUrl("inventory_item.xhtml");
        editMenu.getChildren().add(item);


        item = new MenuItem();
        item.setValue(("ItemsImportFromExcel"));
        item.setUrl("inventory_import_items.xhtml");
        editMenu.getChildren().add(item);

        submenu.getChildren().add(editMenu);

        item = new MenuItem();
        item.setValue(("purchase"));
        item.setUrl("inventory_purchase.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("issue"));
        item.setUrl("inventory_issue.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("receiveInventoryItems"));
        item.setUrl("inventory_institution_received_bills.xhtml");
        submenu.getChildren().add(item);


        item = new MenuItem();
        item.setValue(("requests"));
        item.setUrl("designation_category.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("estimates"));
        item.setUrl("designation_level.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("adjust"));
        item.setUrl("inventory_adjust.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("reports"));
        item.setUrl("inventory_reports.xhtml");
        submenu.getChildren().add(item);


        return submenu;
    }

    private Submenu storesSubmenu() {
        Submenu submenu;

        MenuItem item;

        submenu = new Submenu();
        submenu.setLabel(("stores"));



        Submenu editMenu = new Submenu();
        editMenu.setLabel(("edit"));



        item = new MenuItem();
        item.setValue(("ItemCategories"));
        item.setUrl("inventory_item_category.xhtml");
        editMenu.getChildren().add(item);



        item = new MenuItem();
        item.setValue(("make"));
        item.setUrl("inventory_make.xhtml");
        editMenu.getChildren().add(item);


        item = new MenuItem();
        item.setValue(("model"));
        item.setUrl("inventory_modal.xhtml");
        editMenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("Items"));
        item.setUrl("inventory_item.xhtml");
        editMenu.getChildren().add(item);


        item = new MenuItem();
        item.setValue(("ItemsImportFromExcel"));
        item.setUrl("inventory_import_items.xhtml");
        editMenu.getChildren().add(item);

        submenu.getChildren().add(editMenu);

        item = new MenuItem();
        item.setValue(("purchase"));
        item.setUrl("stores_purchase.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("issue"));
        item.setUrl("inventory_issue.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("receiveInventoryItems"));
        item.setUrl("inventory_institution_received_bills.xhtml");
        submenu.getChildren().add(item);


        item = new MenuItem();
        item.setValue(("requests"));
        item.setUrl("designation_category.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("estimates"));
        item.setUrl("designation_level.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue(("adjust"));
        item.setUrl("inventory_adjust.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("reports");
        item.setUrl("stores_reports.xhtml");
        submenu.getChildren().add(item);


        return submenu;
    }

    private Submenu adminSubmenu() {
        Submenu submenu;

        MenuItem item;

        submenu = new Submenu();
        submenu.setLabel("Admin");

        item = new MenuItem();
        item.setValue("Activate accounts");
        item.setUrl("admin_activate_users.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Add Persons As Users");
        item.setUrl("admin_person_to_user.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Manage Accounts");
        item.setUrl("admin_user_previlages.xhtml");
        submenu.getChildren().add(item);

        item = new MenuItem();
        item.setValue("Remove User Accounts");
        item.setUrl("admin_remove_users.xhtml");
        submenu.getChildren().add(item);

        return submenu;
    }

    
    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    @PostConstruct
    public void init() {
        try {
            createMenu();
        } catch (Exception e) {
            System.out.println("Error in init method. It is " + e.getMessage());
        }
    }
}
