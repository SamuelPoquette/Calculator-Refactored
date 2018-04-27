/**
 * @name        Simple Java Calculator
 * @package     ph.calculator
 * @file        UI.java
 * @author      SORIA Pierre-Henry
 * @email       pierrehs@hotmail.com
 * @link        http://github.com/pH-7
 * @copyright   Copyright Pierre-Henry SORIA, All Rights Reserved.
 * @license     Apache (http://www.apache.org/licenses/LICENSE-2.0)
 * @create      2012-03-30
 *
 * @modifiedby  Achintha Gunasekara
 * @modifiedby  Kydon Chantzaridis
 * @modweb      http://www.achinthagunasekara.com
 * @modemail    contact@achinthagunasekara.com
 * @modemail    kchantza@csd.auth.gr
 */

package simplejavacalculator;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.function.Supplier;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UI implements ActionListener {
    private final JFrame frame;
    private final JPanel panel;
    private final JTextArea text;
    private final HashMap<String, Supplier<Double>> monoOps;
    private final HashMap<String, Supplier<Double>> biOps;
    private final JButton but[], butEqual, butCancel;
    private final Calculator calc;

    public UI() {
    	calc = new Calculator();
    	
        frame = new JFrame("Calculator PH");
        frame.setResizable(false);
        panel = new JPanel(new FlowLayout());

        text = new JTextArea(2, 25);
        but = new JButton[10];
        for (int i = 0; i < 10; i++) {
            but[i] = new JButton(String.valueOf(i));
        }

        // declare mono-operation buttons
        monoOps = new HashMap<>();
        monoOps.put("\u221A", () -> Calculator.MonoOperatorModes.squareRoot(reader()));
        monoOps.put("x*x", () -> Calculator.MonoOperatorModes.square(reader()));
        monoOps.put("1/x", () -> Calculator.MonoOperatorModes.oneDevidedBy(reader()));
        monoOps.put("Cos", () -> Calculator.MonoOperatorModes.cos(reader()));
        monoOps.put("Sin", () -> Calculator.MonoOperatorModes.sin(reader()));
        monoOps.put("Tan", () -> Calculator.MonoOperatorModes.tan(reader()));
        monoOps.put("log10(x)", () -> Calculator.MonoOperatorModes.log(reader()));
        monoOps.put("x%", () -> Calculator.MonoOperatorModes.rate(reader()));
        
        // declare bi-operation buttons
        biOps = new HashMap<>();
        biOps.put("+", () -> calc.calculateBi(Calculator.BiOperatorModes.add, reader()));
        biOps.put("-", () -> calc.calculateBi(Calculator.BiOperatorModes.minus, reader()));
        biOps.put("*", () -> calc.calculateBi(Calculator.BiOperatorModes.multiply, reader()));
        biOps.put("/", () -> calc.calculateBi(Calculator.BiOperatorModes.divide, reader()));
        biOps.put("x^y", () -> calc.calculateBi(Calculator.BiOperatorModes.xpowerofy, reader()));
        
        // declare other buttons
        butEqual = new JButton("=");
        butCancel = new JButton("C");
    }

    public void init() {
        frame.setVisible(true);
        frame.setSize(330, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.add(text);
       
        for (int i = 0; i < 10; i++) {
            panel.add(but[i]);
            but[i].addActionListener(this);
        }

        // Add bi-operation buttons and set listener
        for (String key: biOps.keySet()) {
        	JButton nButton = new JButton(key);
        	nButton.addActionListener(this);
        	panel.add(nButton);
        }

        // Add mono-operation buttons and set listener
        for (String key : monoOps.keySet()) {
        	JButton nButton = new JButton(key);
        	nButton.addActionListener(this);
        	panel.add(nButton);
        }
        
        panel.add(butEqual);
        panel.add(butCancel);

        butEqual.addActionListener(this);
        butCancel.addActionListener(this);
    }

    //Refactored for mono operator modes
    @Override
    public void actionPerformed(ActionEvent e) {
        final Object source = e.getSource();
        final JButton srcButton = (JButton)source;
        
        // Numeric Values
        for (byte i = 0; i < 10; i++) {
            if (source == but[i]) {
                text.replaceSelection(String.valueOf(i));
                return;
            }
        }

        // Mono Operators
        if (monoOps.containsKey(srcButton.getText())) {
        	writer(monoOps.get(srcButton.getText()).get());
        }
        
        // Bi Operators
        if (biOps.containsKey(srcButton.getText())) {
        	writer(biOps.get(srcButton.getText()).get());
        }

        // Other Buttons
        if (source == butEqual) {
            writer(calc.calculateEqual(reader()));
        }

        if (source == butCancel) {
            writer(calc.reset());
        }

        text.selectAll();
    }

    //gets input from buttons
    public Double reader() {
        Double num;
        String str;
        str = text.getText();
        num = Double.valueOf(str);

        return num;
    }
    
    //writes output to calculator text box
    public void writer(final Double num) {
        if (Double.isNaN(num)) {
            text.setText("");
        } else {
            text.setText(Double.toString(num));
        }
    }
}
