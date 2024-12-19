

package com.mycompany.trainticketingsystem;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class TrainTicketingSystem {

    private static HashMap<String, String> users = new HashMap<>(); // Email-password store
    private static ArrayList<String> tickets = new ArrayList<>();   // List of booked tickets
    private static List<Train> trainSchedule = new ArrayList<>();   // Train schedule

    public static void main(String[] args) {
        initializeTrainSchedule();
        SwingUtilities.invokeLater(TrainTicketingSystem::showLoginScreen);
    }

    // Initialize some example trains
    private static void initializeTrainSchedule() {
        trainSchedule.add(new Train("Train A", "Lahore", "Karachi", "10:00 AM", "3:00 PM", 1500));
        trainSchedule.add(new Train("Train B", "Islamabad", "Lahore", "11:00 AM", "3:00 PM", 2000));
        trainSchedule.add(new Train("Train C", "islamabad", "jehlum", "1:00 PM", "4:00 PM", 1200));
    }

    // Creates a styled JButton with hover effect
    private static JButton createStyledButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setBorder(new LineBorder(Color.BLACK, 2, true)); // Rounded border
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(150, 40));

        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.darker());
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    // Creates a styled JLabel
    private static JLabel createStyledLabel(String text, int fontSize, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, fontSize));
        label.setForeground(color);
        return label;
    }

    // Login Screen
    private static void showLoginScreen() {
        JFrame frame = new JFrame("Login");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = createStyledLabel("Train Ticketing System", 22, new Color(50, 50, 150));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(title, gbc);

        JLabel emailLabel = createStyledLabel("Email:", 16, Color.BLACK);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(emailLabel, gbc);

        JTextField emailField = new JTextField();
        gbc.gridx = 1;
        mainPanel.add(emailField, gbc);

        JLabel passwordLabel = createStyledLabel("Password:", 16, Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField();
        gbc.gridx = 1;
        mainPanel.add(passwordField, gbc);

        JButton loginButton = createStyledButton("Login", new Color(192, 192, 192), Color.WHITE);
        JButton registerButton = createStyledButton("Register", new Color(192, 192, 192), Color.WHITE);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(buttonPanel, gbc);

        frame.add(mainPanel, BorderLayout.CENTER);

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (users.containsKey(email) && users.get(email).equals(password)) {
                JOptionPane.showMessageDialog(frame, "Login Successful!");
                frame.dispose();
                showMainMenu();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials.");
            }
        });

        registerButton.addActionListener(e -> {
            frame.dispose();
            showRegistrationScreen();
        });

        frame.setVisible(true);
    }

    // Registration Screen
    private static void showRegistrationScreen() {
        JFrame frame = new JFrame("Register");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = createStyledLabel("Register Account", 22, new Color(50, 50, 150));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(title, gbc);

        JLabel emailLabel = createStyledLabel("Email:", 16, Color.BLACK);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(emailLabel, gbc);

        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(200, 30)); // Set preferred size for the email field
        gbc.gridx = 1;
        mainPanel.add(emailField, gbc);

        JLabel passwordLabel = createStyledLabel("Password:", 16, Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 30)); // Set preferred size for the password field
        gbc.gridx = 1;
        mainPanel.add(passwordField, gbc);

        // Create a panel to hold the button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center alignment
        buttonPanel.setOpaque(false); // Make the panel transparent
        JButton registerButton = createStyledButton("Register", new Color(192, 192, 192), Color.WHITE);
        buttonPanel.add(registerButton); // Add the button to the panel

        gbc.gridwidth = 2; // Make the button span both columns
        gbc.gridx = 0; // Reset grid x position
        gbc.gridy = 3; // Set grid y position for the button
        mainPanel.add(buttonPanel, gbc); // Add the button panel to the main panel

        frame.add(mainPanel, BorderLayout.CENTER);

        registerButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (!email.isEmpty() && !password.isEmpty()) {
                users.put(email, password);
                JOptionPane.showMessageDialog(frame, "Registration Successful!");
                frame.dispose();
                showLoginScreen();
            } else {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
            }
        });

        frame.setVisible(true);
    }

    // Main Menu
    private static void showMainMenu() {
        JFrame frame = new JFrame("Main Menu");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(220, 220, 220)); // Light grey background

        JLabel title = createStyledLabel("Main Menu", 30, new Color(47, 79, 79));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(title);

        JButton searchTrainButton = createStyledButton("Search Trains", new Color(192, 192, 192), Color.black);
        JButton myTicketsButton = createStyledButton("My Tickets", new Color(192, 192, 192), Color.BLACK);
        JButton logoutButton = createStyledButton("Logout", new Color(192, 192, 192), Color.black);

// Set preferred size for buttons to make them less wide
// Change the size as needed
        Dimension buttonSize = new Dimension(100, 30); 
        searchTrainButton.setPreferredSize(buttonSize);
        myTicketsButton.setPreferredSize(buttonSize);
        logoutButton.setPreferredSize(buttonSize);

        mainPanel.add(searchTrainButton);
        mainPanel.add(myTicketsButton);
        mainPanel.add(logoutButton);

        frame.add(mainPanel, BorderLayout.CENTER);

        searchTrainButton.addActionListener(e -> {
            frame.dispose();
            showSearchTrainScreen();
        });

        myTicketsButton.addActionListener(e -> {
            frame.dispose();
            showMyTicketsScreen();
        });

        logoutButton.addActionListener(e -> {
            frame.dispose();
            showLoginScreen();
        });

        frame.setVisible(true);
    }

    // Search Train Screen
    private static void showSearchTrainScreen() {
        JFrame frame = new JFrame("Search Trains");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240));

        JLabel title = createStyledLabel("Available Trains", 22, new Color(50, 50, 150));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultListModel<String> trainListModel = new DefaultListModel<>();
        for (Train train : trainSchedule) {
            trainListModel.addElement(train.toString());
        }

        JList<String> trainList = new JList<>(trainListModel);
        trainList.setBorder(new LineBorder(Color.BLACK, 1));
        trainList.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(trainList);

        JButton bookButton = createStyledButton("Book Ticket", new Color(192, 192, 192), Color.WHITE);
        JButton backButton = createStyledButton("Back", new Color(255, 80, 80), Color.WHITE);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.add(bookButton);
        buttonPanel.add(backButton);

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel, BorderLayout.CENTER);

        bookButton.addActionListener(e -> {
            String selectedTrain = trainList.getSelectedValue();
            if (selectedTrain != null) {
                tickets.add(selectedTrain);
                JOptionPane.showMessageDialog(frame, "Ticket Booked Successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a train.");
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            showMainMenu();
        });

        frame.setVisible(true);
    }

    // My Tickets Screen
    private static void showMyTicketsScreen() {
        JFrame frame = new JFrame("My Tickets");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240));
mainPanel.setPreferredSize(new Dimension(400, 200));
        JLabel title = createStyledLabel("My Tickets", 22, new Color(50, 50, 150));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultListModel<String> ticketListModel = new DefaultListModel<>();
        for (String ticket : tickets) {
            ticketListModel.addElement(ticket);
        }

        JList<String> ticketList = new JList<>(ticketListModel);
        ticketList.setBorder(new LineBorder(Color.BLACK, 1));
        ticketList.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(ticketList);

        JButton backButton = createStyledButton("Back", new Color(255, 80, 80), Color.WHITE);

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);

        frame.add(mainPanel, BorderLayout.CENTER);

        backButton.addActionListener(e -> {
            frame.dispose();
            showMainMenu();
        });

        frame.setVisible(true);
    }

    // Train class
    static class Train {

        private String name;
        private String origin;
        private String destination;
        private String departureTime;
        private String arrivalTime;
        private int price;

        public Train(String name, String origin, String destination, String departureTime, String arrivalTime, int price) {
            this.name = name;
            this.origin = origin;
            this.destination = destination;
            this.departureTime = departureTime;
            this.arrivalTime = arrivalTime;
            this.price = price;
        }

        @Override
        public String toString() {
            return String.format("%s: %s -> %s, Departure: %s, Arrival: %s, Price: rs.%d",
                    name, origin, destination, departureTime, arrivalTime, price);
        }
    }
}
