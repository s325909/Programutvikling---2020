package org.ccomp.fileHandling;

import org.ccomp.model.CompOrder;
import org.ccomp.model.CustomerOrder;
import org.ccomp.model.component.CarComponent;
import org.ccomp.user.Customer;

import java.util.List;

public interface CSVFileHandler {

    List<CompOrder> readCompOrder(List<CompOrder> compOrderList);

    List<CustomerOrder> readCustomerOrder(List<CustomerOrder> customerOrderList);


    void writeCompOrder(List<CompOrder> compOrderList);

    void writeCustomerOrder(CustomerOrder customerOrder);
}
