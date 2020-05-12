package org.ccomp.fileHandling;

import org.ccomp.model.CompOrder;
import org.ccomp.model.CustomerOrder;
import org.ccomp.model.component.CarComponent;
import org.ccomp.user.Customer;

import java.util.List;

public interface CSVFileHandler {

    List<CompOrder> readCompOrder(List<CompOrder> compOrderList, String filePath);

    List<CarComponent> readComponent(List<CarComponent> compList, String filePath);

    List<CustomerOrder> readCustomerOrder(List<CustomerOrder> customerOrderList, String filePath);


    void writeCompOrder(List<CompOrder> compOrderList, String filePath);

    void writeComponent(List<CarComponent> compList, String filePath);

    void writeCustomerOrder(CustomerOrder customerOrder, String filePath);
}
