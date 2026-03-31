import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class GeneratePasswordButton {
    public static void main(String[] args){
        DatabaseManager.init();

        JFrame frame = new JFrame("Password Manager");
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        JTextField siteField = new JTextField(10);
        JTextField userField = new JTextField(10);
        JButton button = new JButton("Generate & Save");

        inputPanel.add(new JLabel("Site:"));
        inputPanel.add(siteField);
        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(userField);
        inputPanel.add(button);

        String[] columns = {"Site", "Username", "Password"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        JPanel tablePanel = new JPanel(new BorderLayout());

        JButton deleteButton = new JButton("Remove Selected");
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                String site = (String) model.getValueAt(row, 0);
                String user = (String) model.getValueAt(row, 1);
                DatabaseManager.deletePassword(site, user);
                model.removeRow(row);
            }
        });
        inputPanel.add(deleteButton);

        tablePanel.add(new JScrollPane(table));

        try {
            ResultSet rs = DatabaseManager.getAllPasswords();
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("site"), rs.getString("username"), rs.getString("password")});
            }
        } catch (SQLException e){ e.printStackTrace(); }

        button.addActionListener(e -> {
            String site = siteField.getText();
            String user = userField.getText();
            String password = PasswordGenerator.generatePassword(12);
            DatabaseManager.savePassword(site, user, password);
            model.addRow(new Object[]{site, user, password});
            siteField.setText("");
            userField.setText("");
        });

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(tablePanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}