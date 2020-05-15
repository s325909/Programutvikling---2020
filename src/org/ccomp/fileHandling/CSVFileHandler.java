package org.ccomp.fileHandling;

import org.ccomp.model.CompOrder;
import org.ccomp.model.CustomerOrder;

import java.util.List;

public interface CSVFileHandler {

    List<CompOrder> readCompOrder(List<CompOrder> compOrderList);

    List<CustomerOrder> readCustomerOrder(List<CustomerOrder> customerOrderList);

    void writeCompOrder(List<CompOrder> compOrderList);

    void writeCustomerOrder(CustomerOrder customerOrder);
}
