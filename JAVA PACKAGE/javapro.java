
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.plot.RingPlot3D;
import org.jfree.chart.plot.RingPlot3DModel;
import org.jfree.chart.plot.RingPlotModel;
import org.jfree.chart.plot.ThermometerPlot;
import org.jfree.chart.plot.ThermometerPlotModel;
import org.jfree.chart.plot.ThermometerPlotModel.DEFAULT_VALUE;
import org.jfree.chart.plot.ThermometerType;
import org.jfree.data.category.DefaultCategoryDataset;

@WebServlet("/barchart")
public class BarChartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Create a dataset (you should replace this with your data)
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1.0, "Category 1", "Item 1");
        dataset.addValue(4.0, "Category 1", "Item 2");
        dataset.addValue(3.0, "Category 1", "Item 3");

        // Create the bar chart
        JFreeChart barChart = ChartFactory.createBarChart(
            "Sample Bar Chart",
            "Category",
            "Value",
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false);

        // Generate the chart image
        response.setContentType("image/png");
        ChartUtilities.writeChartAsPNG(response.getOutputStream(), barChart, 400, 300);
    }
}
