package inheritance.analyser;

import java.util.List;

public final class FlatTaxSalesAnalyser extends AbstractAnalyser {

    public FlatTaxSalesAnalyser(List<SalesRecord> records) {
        super(records);
    }

    @Override
    public boolean differentiated() {
        return false;
    }


}
