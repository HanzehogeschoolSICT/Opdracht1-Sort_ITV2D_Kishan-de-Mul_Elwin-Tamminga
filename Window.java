import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.concurrent.Task;
import javafx.concurrent.Service;
import javafx.application.Platform;

public class Window extends Application {
	Sorter main;
	BarChart chart;
	XYChart.Series dataSeries;
	
	@Override
	public void start(Stage primaryStage) {
		main = Main.sorter;

		VBox vbox = new VBox();

		Scene scene = new Scene(vbox, 500, 400);

		primaryStage.setTitle("Sorting visualizer");
		primaryStage.setScene(scene);


		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setTickLabelsVisible(false);
		xAxis.setOpacity(0);

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Value");

	 	chart = new BarChart(xAxis, yAxis);
		chart.setAnimated(false);
		chart.setLegendVisible(false);
		chart.setCategoryGap(1);
		chart.setBarGap(1);

		dataSeries = new XYChart.Series();

		vbox.getChildren().add(chart);

		Service<Boolean> runService = new Service<Boolean>() {
			protected Task createTask() {
				return new Task<Boolean>() {
					@Override
					public Boolean call() {
						if (main.startCycle()) {
							return true;
						}

						while(main.runCycle()) {
							try {
								Thread.sleep(25);
							} catch (Exception e) {}

							Platform.runLater ( () -> updateDisplay() );
						}
						Platform.runLater( () -> updateDisplay() );
						return false;
					}
				};
			}
		};

		Button onceBtn = new Button();
		onceBtn.setText("Run cycle");
		onceBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!runService.isRunning()) {
					runService.restart();
				}
			}
		});

		Button autoBtn = new Button();
		autoBtn.setText("Run entire sort");
		autoBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (runService.isRunning()) {
					return;
				}
				runService.restart();
				runService.setOnSucceeded(e -> {
					Boolean v =runService.getValue();
					if (!v) {
						runService.restart();
					}
				});
			}
		});

		HBox btnBox = new HBox();

		btnBox.getChildren().add(onceBtn);
		btnBox.getChildren().add(autoBtn);

		vbox.getChildren().add(btnBox);

		updateDisplay();
		primaryStage.show();
	}

	public void updateDisplay() {
		int[] list = main.getList();

		dataSeries.getData().clear();

		int count = 0;
		for (int i : list) {
			count++;
			dataSeries.getData().add(new XYChart.Data(count+"", i));
		}
		chart.getData().clear();
		chart.getData().add(dataSeries);
	}
}
