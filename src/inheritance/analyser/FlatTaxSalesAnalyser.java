package inheritance.analyser;

import java.util.List;

public final class FlatTaxSalesAnalyser extends AbstractAnalyser {

    public FlatTaxSalesAnalyser(List<SalesRecord> records) {
        super(records);
    }

    @Override
    protected boolean differentiated() {
        return false;
    }


}
