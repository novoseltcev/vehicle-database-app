package controller;

import javafx.collections.FXCollections;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import model.AutoTest;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AutotestController extends Controller {

    public LineChart<Integer, Double> chartMeanAdd;
    public LineChart<Integer, Double> chartMeanRm;
    public LineChart<Integer, Long> chartTotalAdd;
    public LineChart<Integer, Long> chartTotalRm;

    List<AutoTest> arrayListTests;
    List<AutoTest> linkedListTests;

    @Override
    protected void setLang() {
    }

    @Override
    protected void initialize() {
        LineChart[] charts = {
                chartMeanAdd,
                chartMeanRm,
                chartTotalAdd,
                chartTotalRm
        };

        for (LineChart chart: charts) {
            chart.getXAxis().setLabel("Size");
            chart.getYAxis().setLabel("Elapsed Nanos");
            chart.getXAxis().setAutoRanging(true);
            chart.getYAxis().setAutoRanging(true);
        }

        arrayListTests = generateTests(ArrayList.class.arrayType());
        linkedListTests = generateTests(LinkedList.class.arrayType());
        showData(arrayListTests);
        showData(linkedListTests);
    }

    private List<AutoTest> generateTests(Class<?> listClass) {
        List<AutoTest> result = new LinkedList<>();
        for (int i = 1; i <= 5; i++) {
            result.add(new AutoTest(listClass, (int) Math.pow(10, i)));
        }
        return result;
    }

    private void showData(List<AutoTest> autoTests) {

        XYChart.Series<Integer, Double> meanAddSeries = new XYChart.Series<>(
                autoTests.get(0).getListClass(),
                FXCollections.observableArrayList(
                        autoTests.stream()
                                .map(autoTest -> new XYChart.Data<>(autoTest.getSize(), autoTest.getAddMeanNanos()))
                                .toList()
                )
        );

        XYChart.Series<Integer, Double> meanRmSeries = new XYChart.Series<>(
                autoTests.get(0).getListClass(),
                FXCollections.observableArrayList(
                        autoTests.stream()
                                .map(autoTest ->
                                        new XYChart.Data<>(autoTest.getSize(), autoTest.getRmMeanNanos()))
                                .toList()
                )
        );

        XYChart.Series<Integer, Long> totalAddSeries = new XYChart.Series<>(
                autoTests.get(0).getListClass(),
                FXCollections.observableArrayList(
                        autoTests.stream()
                                .map(autoTest -> new XYChart.Data<>(autoTest.getSize(), autoTest.getAddTotalNanos()))
                                .toList()
                )
        );

        XYChart.Series<Integer, Long> totalRmSeries = new XYChart.Series<>(
                autoTests.get(0).getListClass(),
                FXCollections.observableArrayList(
                        autoTests.stream()
                                .map(autoTest ->
                                        new XYChart.Data<>(autoTest.getSize(), autoTest.getRmTotalNanos()))
                                .toList()
                )
        );

        chartMeanAdd.getData().add(meanAddSeries);
        chartMeanRm.getData().add(meanRmSeries);
        chartTotalRm.getData().add(totalRmSeries);
        chartTotalAdd.getData().add(totalAddSeries);
    }
}