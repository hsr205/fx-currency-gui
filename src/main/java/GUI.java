import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class GUI implements ActionListener {


    public GUI() {

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        JLabel fromCurrencyLabel = new JLabel("Convert From Currency: ");
        fromCurrencyLabel.setBounds(10, 20, 160, 25);
        panel.add(fromCurrencyLabel);

        JTextField fromCurrency = new JTextField();
        fromCurrency.setBounds(170, 20, 165, 25);
        panel.add(fromCurrency);

        JLabel toCurrencyLabel = new JLabel("Convert To Currency: ");
        toCurrencyLabel.setBounds(10, 50, 165, 25);
        panel.add(toCurrencyLabel);

        JTextField toCurrency = new JTextField();
        toCurrency.setBounds(170, 50, 165, 25);
        panel.add(toCurrency);

        JLabel fromHistoricCurrencyLabel = new JLabel("Convert From Historic Currency: ");
        fromHistoricCurrencyLabel.setBounds(10, 200, 220, 25);
        panel.add(fromHistoricCurrencyLabel);

        JTextField fromHistoric = new JTextField();
        fromHistoric.setBounds(220, 200, 165, 25);
        panel.add(fromHistoric);
//
//        JLabel toHistoricCurrencyLabel = new JLabel("Convert To Historic Currency: ");
//        toHistoricCurrencyLabel.setBounds(10, 230, 200, 25);
//        panel.add(toHistoricCurrencyLabel);

//        JTextField toHistoric = new JTextField();
//        toHistoric.setBounds(220, 200, 165, 25);
//        panel.add(toHistoric);

        JLabel fromDate = new JLabel("From Date (YYYY-MM-DD): ");
        fromDate.setBounds(10, 230, 200, 25);
        panel.add(fromDate);

        JTextField fromDateText = new JTextField();
        fromDateText.setBounds(220, 230, 165, 25);
        panel.add(fromDateText);

        JLabel toDate = new JLabel("To Date (YYYY-MM-DD): ");
        toDate.setBounds(10, 260, 200, 25);
        panel.add(toDate);

        JTextField toDateText = new JTextField();
        toDateText.setBounds(220, 260, 165, 25);
        panel.add(toDateText);

        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(10, 80, 100, 25);
        convertButton.addActionListener(this);
        panel.add(convertButton);

        JButton currentRatesButton = new JButton("Get Current Rates");
        currentRatesButton.setBounds(10, 120, 150, 25);
        currentRatesButton.addActionListener(this);
        panel.add(currentRatesButton);

        JButton historicRatesButton = new JButton("Get Historic Rates");
        historicRatesButton.setBounds(10, 290, 150, 25);
        historicRatesButton.addActionListener(this);
        panel.add(historicRatesButton);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fromCurrencyText = fromCurrency.getText();
                String toCurrencyText = toCurrency.getText();
                new GetExchangeRates().getSpecificRateConversion(fromCurrencyText.toUpperCase(Locale.ROOT), toCurrencyText.toUpperCase(Locale.ROOT));
            }
        });


        currentRatesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GetExchangeRates().getCurrentRates();
            }
        });


        historicRatesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fromHistoricString = fromHistoric.getText();
                String fromDateString = fromDateText.getText();
                String toDateString = toDateText.getText();
                System.out.println(fromHistoricString);
                System.out.println(fromDateString);
                System.out.println(toDateString);


                new GetExchangeRates().getHistoricExchangeRate(fromHistoricString.toUpperCase(Locale.ROOT),
                                                                fromDateString,
                                                                toDateString
                );
            }
        });


        frame.setTitle("Currency Converter");


        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //
    }
}
