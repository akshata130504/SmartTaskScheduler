import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.util.Map;

public class ExpenseChart {

    public static void showChart(){

        ExpenseDAO dao = new ExpenseDAO();
        Map<String, Double> data = dao.getCategorySummary();

        DefaultPieDataset dataset = new DefaultPieDataset();

        for(String category : data.keySet()){
            dataset.setValue(category, data.get(category));
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Expense Distribution",
                dataset,
                true,
                true,
                false
        );

        ChartFrame frame = new ChartFrame("Expense Chart", chart);
        frame.setSize(600,400);
        frame.setVisible(true);
    }
}