/**
 * Descreption of AutoClicker
 *
 * @author Nirushaan Sureshkumar
 * @version 0.1
 * @since 02.08.2020
 */
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;


/**
 * The type Auto clicker.
 */
public class AutoClicker extends JFrame implements ActionListener {


    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JTextField textFieldNr;
    private JTextField textFieldInterval;
    private JProgressBar progressBar;
    private JButton start;
    private JLabel label;


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AutoClicker frame = new AutoClicker();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo( null );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * Instantiates a new Auto clicker.
     */
    public AutoClicker() {
        setUndecorated(true);
        setTitle("AutoClicker");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 294, 139);
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Settings", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        panel.setBounds(10, 11, 168, 86);
        this.panel.add(panel);
        panel.setLayout(null);

        JLabel lblNumberOfClicks = new JLabel("Number of Click(s)");
        lblNumberOfClicks.setBounds(21, 21, 94, 20);
        panel.add(lblNumberOfClicks);

        JLabel lblInterval = new JLabel("Interval");
        lblInterval.setBounds(21, 52, 94, 19);
        panel.add(lblInterval);

        textFieldNr = new JTextField();
        textFieldNr.setText("70");
        textFieldNr.setBounds(119, 21, 33, 20);
        panel.add(textFieldNr);
        textFieldNr.setColumns(10);

        textFieldInterval = new JTextField();
        textFieldInterval.setText("100");
        textFieldInterval.setBounds(119, 51, 33, 20);
        panel.add(textFieldInterval);

        start = new JButton("Start");
        start.addActionListener( this );
        start.setActionCommand( "Start" );
        start.setBounds(188, 16, 89, 23);
        this.panel.add(start);

        progressBar = new JProgressBar();
        progressBar.setString("Please wait...");
        progressBar.setStringPainted(true);
        progressBar.setBounds(187, 77, 90, 19);
        setProgressState( true );
        this.panel.add(progressBar);

        label = new JLabel("-Tabs5894");
        label.setForeground(Color.GRAY);
        label.setFont(new Font("Tahoma", Font.PLAIN, 8));
        label.setHorizontalAlignment(SwingConstants.TRAILING);
        label.setBounds(188, 83, 89, 14);
        this.panel.add(label);
    }


    /**
     * Sets progress state.
     *
     * @param done the done
     */
    void setProgressState( final boolean done ) {
        SwingUtilities.invokeLater( new Runnable() {

            @Override
            public void run() {
                progressBar.setVisible( !done );
                progressBar.setIndeterminate( !done );

                start.setEnabled( done );
                textFieldInterval.setEditable( done );
                textFieldNr.setEditable( done );
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if( evt.getActionCommand().equals( "Start" ) ) {
            try {
                StartClick click = new StartClick( AutoClicker.this,
                        Integer.parseInt( "0" + textFieldNr.getText().trim()),
                        Integer.parseInt( "0" + textFieldInterval.getText().trim() ));

                JOptionPane.showMessageDialog( null, "Auto Click will start in 3sec.", "Warning", JOptionPane.WARNING_MESSAGE );
                ExecutorService executor = Executors.newCachedThreadPool();
                executor.execute( click );
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog( null, "Invalid Input!", "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
        else {
            System.out.println( "Clicked!" );
        }
    }
}