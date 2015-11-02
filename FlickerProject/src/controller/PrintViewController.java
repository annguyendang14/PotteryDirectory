package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;
import data.Order;
import data.OrderTable;
import data.TempOrder;


public class PrintViewController implements Initializable{
	@FXML private TextArea fromAddress;
	@FXML private TextArea toAddress;
	@FXML private Button printButton;
	@FXML private AnchorPane shippingLabelPane;
	@FXML private TextArea packingListBox;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Order order = TempOrder.getTempOrder();
		packingListBox.setText(order.getDescription());
		toAddress.setText(order.getShippingAddress());
		
	}
	public void print(ActionEvent event){
		
		printButton.setVisible(false);
		Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
       double scaleX = pageLayout.getPrintableWidth() / shippingLabelPane.getBoundsInParent().getWidth();
       double scaleY = pageLayout.getPrintableHeight() / shippingLabelPane.getBoundsInParent().getHeight();
       shippingLabelPane.getTransforms().add(new Scale(scaleX, scaleY));
 
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean success = job.printPage(shippingLabelPane);
            if (success) {
                job.endJob();
            }
        }
        printButton.setVisible(true);
	}
}
