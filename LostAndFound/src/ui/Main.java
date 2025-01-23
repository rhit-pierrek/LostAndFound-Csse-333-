import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Page");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordText = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(userLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.add(userText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(passwordText, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        frame.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame reportFrame = new JFrame("Report Page");
                reportFrame.setSize(1000, 600);
                reportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                reportFrame.setLayout(new BorderLayout());

                // Panel for the "File a Report" button and search bar
                JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JButton reportButton = new JButton("File a Report");
                JTextField searchBar = new JTextField(20);
                JButton searchButton = new JButton("Search");
                topPanel.add(reportButton);
                topPanel.add(searchBar);
                topPanel.add(searchButton);

                reportFrame.add(topPanel, BorderLayout.NORTH);

                reportButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame formFrame = new JFrame("File a Report");
                        formFrame.setSize(1000, 600);
                        formFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                        JPanel formPanel = new JPanel(new GridBagLayout());
                        JScrollPane scrollPane = new JScrollPane(formPanel);
                        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                        JLabel nameLabel = new JLabel("Name of Item:");
                        JTextField nameText = new JTextField(50);
                        JLabel brandLabel = new JLabel("Brand Name:");
                        JTextField brandText = new JTextField(50);
                        JLabel sizeLabel = new JLabel("Size:");
                        JTextField sizeText = new JTextField(50);
                        JLabel typeLabel = new JLabel("Type:");
                        String[] typeOptions = {"", "Clothes", "Electronics", "School Supplies", "Misc"};
                        JComboBox<String> typeComboBox = new JComboBox<>(typeOptions);
                        JLabel miscTypeLabel = new JLabel("Specify Type:");
                        JTextField miscTypeText = new JTextField(50);
                        miscTypeLabel.setVisible(false);
                        miscTypeText.setVisible(false);
                        JLabel imageLabel = new JLabel("Upload Image:");
                        JButton uploadButton = new JButton("Upload");
                        JLabel imagePathLabel = new JLabel();
                        JLabel itemDescLabel = new JLabel("Item Description:");
                        JTextArea itemDescText = new JTextArea(5, 50);
                        itemDescText.setLineWrap(true);
                        itemDescText.setWrapStyleWord(true);
                        JScrollPane itemDescScrollPane = new JScrollPane(itemDescText);
                        JLabel itemDescCharCount = new JLabel("0/255");
                        JLabel locationDescLabel = new JLabel("Location Description:");
                        JTextArea locationDescText = new JTextArea(5, 50);
                        locationDescText.setLineWrap(true);
                        locationDescText.setWrapStyleWord(true);
                        JScrollPane locationDescScrollPane = new JScrollPane(locationDescText);
                        JLabel locationDescCharCount = new JLabel("0/255");
                        JLabel dateLabel = new JLabel("Date (dd/mm/yyyy):");
                        JTextField dateText = new JTextField(20);
                        JLabel statusLabel = new JLabel("Status:");
                        String[] statusOptions = {"", "Lost", "Found"};
                        JComboBox<String> statusComboBox = new JComboBox<>(statusOptions);
                        JLabel buildingLabel = new JLabel("Building:");
                        String[] buildingOptions = {
                            "", "Moench Hall", "Branam Innovation Center", "Crapo Hall", "John A. Logan Library",
                            "John T. Myers Hall", "Kremer Innovation Center", "Olin Hall", "Olin Advanced Learning Center",
                            "William A. Cook Laboratory for Bioscience Research", "Fowler Academic Building", "Hadley Hall",
                            "Hatfield Hall", "Mussallem Union", "Facilities Operations", "Public Safety Office", "White Chapel",
                            "Sports and Recreation Center", "Other"
                        };
                        JComboBox<String> buildingComboBox = new JComboBox<>(buildingOptions);
                        JLabel otherBuildingLabel = new JLabel("Specify Building:");
                        JTextField otherBuildingText = new JTextField(50);
                        otherBuildingLabel.setVisible(false);
                        otherBuildingText.setVisible(false);
                        JButton submitButton = new JButton("Submit");

                        typeComboBox.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (typeComboBox.getSelectedItem().equals("Misc")) {
                                    miscTypeLabel.setVisible(true);
                                    miscTypeText.setVisible(true);
                                } else {
                                    miscTypeLabel.setVisible(false);
                                    miscTypeText.setVisible(false);
                                }
                            }
                        });

                        buildingComboBox.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (buildingComboBox.getSelectedItem().equals("Other")) {
                                    otherBuildingLabel.setVisible(true);
                                    otherBuildingText.setVisible(true);
                                } else {
                                    otherBuildingLabel.setVisible(false);
                                    otherBuildingText.setVisible(false);
                                }
                            }
                        });

                        uploadButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFileChooser fileChooser = new JFileChooser();
                                int returnValue = fileChooser.showOpenDialog(null);
                                if (returnValue == JFileChooser.APPROVE_OPTION) {
                                    File selectedFile = fileChooser.getSelectedFile();
                                    imagePathLabel.setText(selectedFile.getAbsolutePath());
                                }
                            }
                        });

                        itemDescText.getDocument().addDocumentListener(new DocumentListener() {
                            @Override
                            public void insertUpdate(DocumentEvent e) {
                                updateCharCount();
                            }

                            @Override
                            public void removeUpdate(DocumentEvent e) {
                                updateCharCount();
                            }

                            @Override
                            public void changedUpdate(DocumentEvent e) {
                                updateCharCount();
                            }

                            private void updateCharCount() {
                                itemDescCharCount.setText(itemDescText.getText().length() + "/255");
                            }
                        });

                        locationDescText.getDocument().addDocumentListener(new DocumentListener() {
                            @Override
                            public void insertUpdate(DocumentEvent e) {
                                updateCharCount();
                            }

                            @Override
                            public void removeUpdate(DocumentEvent e) {
                                updateCharCount();
                            }

                            @Override
                            public void changedUpdate(DocumentEvent e) {
                                updateCharCount();
                            }

                            private void updateCharCount() {
                                locationDescCharCount.setText(locationDescText.getText().length() + "/255");
                            }
                        });

                        GridBagConstraints gbc = new GridBagConstraints();
                        gbc.insets = new Insets(10, 10, 10, 10);

                        gbc.gridx = 0;
                        gbc.gridy = 0;
                        formPanel.add(nameLabel, gbc);

                        gbc.gridx = 1;
                        gbc.gridy = 0;
                        formPanel.add(nameText, gbc);

                        gbc.gridx = 0;
                        gbc.gridy = 1;
                        formPanel.add(brandLabel, gbc);

                        gbc.gridx = 1;
                        gbc.gridy = 1;
                        formPanel.add(brandText, gbc);

                        gbc.gridx = 0;
                        gbc.gridy = 2;
                        formPanel.add(sizeLabel, gbc);

                        gbc.gridx = 1;
                        gbc.gridy = 2;
                        formPanel.add(sizeText, gbc);

                        gbc.gridx = 0;
                        gbc.gridy = 3;
                        formPanel.add(typeLabel, gbc);

                        gbc.gridx = 1;
                        gbc.gridy = 3;
                        formPanel.add(typeComboBox, gbc);

                        gbc.gridx = 0;
                        gbc.gridy = 4;
                        formPanel.add(miscTypeLabel, gbc);

                        gbc.gridx = 1;
                        gbc.gridy = 4;
                        formPanel.add(miscTypeText, gbc);

                        gbc.gridx = 0;
                        gbc.gridy = 5;
                        formPanel.add(imageLabel, gbc);

                        gbc.gridx = 1;
                        gbc.gridy = 5;
                        formPanel.add(uploadButton, gbc);

                        gbc.gridx = 2;
                        gbc.gridy = 5;
                        formPanel.add(imagePathLabel, gbc);

                        gbc.gridx = 0;
                        gbc.gridy = 6;
                        formPanel.add(itemDescLabel, gbc);

                        gbc.gridx = 1;
                        gbc.gridy = 6;
                        formPanel.add(itemDescScrollPane, gbc);

                        gbc.gridx = 2;
                        gbc.gridy = 6;
                        formPanel.add(itemDescCharCount, gbc);

                        gbc.gridx = 0;
                        gbc.gridy = 7;
                        formPanel.add(buildingLabel, gbc);

                        gbc.gridx = 1;
                        gbc.gridy = 7;
                        formPanel.add(buildingComboBox, gbc);

                        gbc.gridx = 0;
                        gbc.gridy = 8;
                        formPanel.add(otherBuildingLabel, gbc);

                        gbc.gridx = 1;
                        gbc.gridy = 8;
                        formPanel.add(otherBuildingText, gbc);

                        gbc.gridx = 0;
                        gbc.gridy = 9;
                        formPanel.add(locationDescLabel, gbc);

                        gbc.gridx = 1;
                        gbc.gridy = 9;
                        formPanel.add(locationDescScrollPane, gbc);

                        gbc.gridx = 2;
                        gbc.gridy = 9;
                        formPanel.add(locationDescCharCount, gbc);

                        gbc.gridx = 0;
                        gbc.gridy = 10;
                        formPanel.add(dateLabel, gbc);

                        gbc.gridx = 1;
                        gbc.gridy = 10;
                        formPanel.add(dateText, gbc);

                        gbc.gridx = 0;
                        gbc.gridy = 11;
                        formPanel.add(statusLabel, gbc);

                        gbc.gridx = 1;
                        gbc.gridy = 11;
                        formPanel.add(statusComboBox, gbc);

                        gbc.gridx = 1;
                        gbc.gridy = 12;
                        formPanel.add(submitButton, gbc);

                        submitButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                formFrame.setVisible(false);
                                reportFrame.setVisible(true);
                            }
                        });

                        formFrame.add(scrollPane);
                        reportFrame.setVisible(false);
                        formFrame.setVisible(true);
                    }
                });

                frame.setVisible(false);
                reportFrame.setVisible(true);
            }
        });

        frame.setVisible(true);
    }
}