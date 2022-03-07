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
        fromHistoricCurrencyLabel.setBounds(10, 125, 220, 25);
        panel.add(fromHistoricCurrencyLabel);

        JTextField fromHistoric = new JTextField();
        fromHistoric.setBounds(220, 125, 165, 25);
        panel.add(fromHistoric);

        JLabel fromDate = new JLabel("From Date (YYYY-MM-DD): ");
        fromDate.setBounds(10, 160, 200, 25);
        panel.add(fromDate);

        JTextField fromDateText = new JTextField();
        fromDateText.setBounds(220, 160, 165, 25);
        panel.add(fromDateText);

        JLabel toDate = new JLabel("To Date (YYYY-MM-DD): ");
        toDate.setBounds(10, 195, 200, 25);
        panel.add(toDate);

        JTextField toDateText = new JTextField();
        toDateText.setBounds(220, 195, 165, 25);
        panel.add(toDateText);

        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(10, 80, 100, 25);
        convertButton.addActionListener(this);
        panel.add(convertButton);

        JButton currentRatesButton = new JButton("Get Current Rates");
        currentRatesButton.setBounds(10, 340, 150, 25);
        currentRatesButton.addActionListener(this);
        panel.add(currentRatesButton);

        JButton historicRatesButton = new JButton("Get Historic Rates");
        historicRatesButton.setBounds(10, 230, 150, 25);
        historicRatesButton.addActionListener(this);
        panel.add(historicRatesButton);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fromCurrencyText = fromCurrency.getText();
                String toCurrencyText = toCurrency.getText();

                JLabel resultString = new JLabel(new GetExchangeRates().getSpecificRateConversion(fromCurrencyText.toUpperCase(Locale.ROOT), toCurrencyText.toUpperCase(Locale.ROOT)));
                resultString.setBounds(175, 80, 300, 25);
                panel.add(resultString);
            }
        });


        currentRatesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JLabel resultString = new JLabel("Output Printed!");
                resultString.setBounds(175, 340, 300, 25);
                panel.add(resultString);
                new GetExchangeRates().getCurrentRates("/Users/HenryRothenberg/Desktop/output.txt");
            }
        });


        historicRatesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fromHistoricString = fromHistoric.getText();
                String fromDateString = fromDateText.getText();
                String toDateString = toDateText.getText();

                JLabel resultString = new JLabel("Output Printed!");
                resultString.setBounds(225, 225, 200, 25);
                panel.add(resultString);

                new GetExchangeRates().getHistoricExchangeRate(fromHistoricString.toUpperCase(Locale.ROOT),
                        fromDateString,
                        toDateString,
                        "/Users/HenryRothenberg/Desktop/histOutput.csv"
                );
            }
        });

        frame.setTitle("Currency Converter");

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //
    }
}
