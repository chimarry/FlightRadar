package pro.artse.admin.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import pro.artse.dal.dto.AppVisitDTO;
import pro.artse.dal.services.IAppVisitService;
import pro.artse.dal.services.ServiceFactory;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean(name = "appVisitsBean")
public class AppVisitsBean implements Serializable {

	private static final long serialVersionUID = 6317403104897109023L;

	private final IAppVisitService visitService = ServiceFactory.getAppVisitService();
	private LineChartModel lineModel;

	@PostConstruct
	public void init() {
		lineModel = new LineChartModel();
		lineModel.setTitle("Visits per day for User Application");
		lineModel.setZoom(true);

		LineChartSeries series = new LineChartSeries();
		List<AppVisitDTO> visits = visitService.getInLast(30);
		series.setLabel("Visits count");
		for (AppVisitDTO appVisitDTO : visits)
			series.set(appVisitDTO.getVisitedOn(), appVisitDTO.getCount());

		lineModel.addSeries(series);

		lineModel.getAxis(AxisType.Y).setLabel("Total number of visits");
		DateAxis axis = new DateAxis("Dates");
		axis.setTickAngle(-50);
		axis.setMax(LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")));
		axis.setTickFormat("%b %#d, %y");
		lineModel.getAxes().put(AxisType.X, axis);
	}

	public LineChartModel getLineModel() {
		return lineModel;
	}
}
