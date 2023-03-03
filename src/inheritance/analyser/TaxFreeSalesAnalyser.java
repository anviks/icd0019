package inheritance.analyser;

import java.util.List;

public final class TaxFreeSalesAnalyser extends AbstractAnalyser {

    public TaxFreeSalesAnalyser(List<SalesRecord> records) {
        super(records);
        vat = 0.0;
    }

    @Override
    public boolean differentiated() {
        return false;
    }

}
