package com.sports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.PreferenceChangeEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class Dashboard extends JFrame {
    public Dashboard() {
        setTitle("ArenaX | College Sports Management");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // ArenaX header
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(Color.WHITE);
        header.setBorder(
                BorderFactory.createEmptyBorder(12, 10, 12, 10));

        JLabel titlee = new JLabel("ArenaX ⚡");
        titlee.setFont(new Font("SansSerif", Font.BOLD, 30));
        titlee.setForeground(new Color(20, 35, 65));
        titlee.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel tagline = new JLabel(
                "Your Campus. Your Teams. Your Game.");
        tagline.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tagline.setForeground(new Color(100, 116, 139));
        tagline.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(titlee);
        header.add(Box.createVerticalStrut(4));
        header.add(tagline);

        add(header, BorderLayout.NORTH);

        JPanel panel = new JPanel(); // to arrange the GUI without making it complicated
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(20, 35, 65));
        panel.setPreferredSize(new Dimension(165, 0));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 10, 20, 10));
        JButton playerButton = new JButton("Players");
        JButton teamButton = new JButton("Teams");
        JButton coachButton = new JButton("Coaches");
        JButton tournamentButton = new JButton("Tournaments");
        JButton venueButton = new JButton("Venues");
        JButton matchButton = new JButton("Matches");
        JButton scoreButton = new JButton("Scores");
        JButton overViewButton = new JButton("Overview");
        JButton scheduleButton = new JButton("Match Schedule");
        JButton resultsButton = new JButton("Match Results");

        styleSidebarButton(playerButton);
        styleSidebarButton(teamButton);
        styleSidebarButton(coachButton);
        styleSidebarButton(tournamentButton);
        styleSidebarButton(venueButton);
        styleSidebarButton(matchButton);
        styleSidebarButton(scoreButton);
        styleSidebarButton(overViewButton);
        styleSidebarButton(scheduleButton);
        styleSidebarButton(resultsButton);
        setActiveButton(
                overViewButton,
                playerButton, teamButton, coachButton, tournamentButton,
                venueButton, matchButton, scoreButton, overViewButton,
                scheduleButton, resultsButton);

        // We want out buttons to be placed vertically, since JPanel uses FlowLayout it
        // placed the buttons horizontally.
        // We don't indent to use the object again so we created it in single line
        Dimension buttonsize = new Dimension(180, 45);

        playerButton.setPreferredSize(buttonsize);
        teamButton.setPreferredSize(buttonsize);
        coachButton.setPreferredSize(buttonsize);
        tournamentButton.setPreferredSize(buttonsize);
        venueButton.setPreferredSize(buttonsize);
        matchButton.setPreferredSize(buttonsize);
        scoreButton.setPreferredSize(buttonsize);
        overViewButton.setPreferredSize(buttonsize);
        scheduleButton.setPreferredSize(buttonsize);
        resultsButton.setPreferredSize(buttonsize);

        JButton[] buttons = {
                playerButton, teamButton, coachButton,
                tournamentButton, venueButton, matchButton,
                scoreButton, overViewButton, scheduleButton,
                resultsButton
        };

        for (JButton button : buttons) {
            button.setMaximumSize(new Dimension(165, 42));
            button.setPreferredSize(new Dimension(165, 42));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setBackground(new Color(30, 35, 65));
            button.setForeground(Color.WHITE);
            button.setFont(new Font("SansSerif", Font.BOLD, 13));
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setOpaque(true);
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        panel.add(playerButton);
        panel.add(Box.createVerticalStrut(8));
        panel.add(teamButton);
        panel.add(Box.createVerticalStrut(8));
        panel.add(coachButton);
        panel.add(Box.createVerticalStrut(8));
        panel.add(tournamentButton);
        panel.add(Box.createVerticalStrut(8));
        panel.add(venueButton);
        panel.add(Box.createVerticalStrut(8));
        panel.add(matchButton);
        panel.add(Box.createVerticalStrut(8));
        panel.add(scoreButton);
        panel.add(Box.createVerticalStrut(8));
        panel.add(overViewButton);
        panel.add(Box.createVerticalStrut(8));
        panel.add(scheduleButton);
        panel.add(Box.createVerticalStrut(8));
        panel.add(resultsButton);

        JPanel content = new JPanel(new BorderLayout());

        showOverview(content);

        add(content, BorderLayout.CENTER);

        playerButton.addActionListener(e -> {
            setActiveButton(playerButton,
                    playerButton, teamButton, coachButton, tournamentButton,
                    venueButton, matchButton, scoreButton, overViewButton,
                    scheduleButton, resultsButton);

            showMessage(content, "Players");
        });
        teamButton.addActionListener(e -> {
            setActiveButton(teamButton, playerButton, teamButton, coachButton, tournamentButton,
                    venueButton, matchButton, scoreButton, overViewButton, scheduleButton, resultsButton);
            showMessage(content, "Teams");
        });
        coachButton.addActionListener(e -> {
            setActiveButton(coachButton, playerButton, teamButton, coachButton, tournamentButton,
                    venueButton, matchButton, scoreButton, overViewButton, scheduleButton, resultsButton);
            showMessage(content, "Coaches");
        });
        tournamentButton.addActionListener(e -> {
            setActiveButton(tournamentButton, playerButton, teamButton, coachButton, tournamentButton,
                    venueButton, matchButton, scoreButton, overViewButton, scheduleButton, resultsButton);
            showMessage(content, "Tournaments");
        });
        venueButton.addActionListener(e -> {
            setActiveButton(venueButton, playerButton, teamButton, coachButton, tournamentButton,
                    venueButton, matchButton, scoreButton, overViewButton, scheduleButton, resultsButton);
            showMessage(content, "Venues");
        });
        matchButton.addActionListener(e -> {
            setActiveButton(matchButton, playerButton, teamButton, coachButton, tournamentButton,
                    venueButton, matchButton, scoreButton, overViewButton, scheduleButton, resultsButton);
            showMessage(content, "Matches");
        });
        scoreButton.addActionListener(e -> {
            setActiveButton(scoreButton, playerButton, teamButton, coachButton, tournamentButton,
                    venueButton, matchButton, scoreButton, overViewButton, scheduleButton, resultsButton);
            showMessage(content, "Scores");
        });
        overViewButton.addActionListener(e -> {
            setActiveButton(
                    overViewButton,
                    playerButton, teamButton, coachButton, tournamentButton,
                    venueButton, matchButton, scoreButton, overViewButton,
                    scheduleButton, resultsButton);

            showOverview(content);
        });
        scheduleButton.addActionListener(e -> {
            setActiveButton(
                    scheduleButton,
                    playerButton, teamButton, coachButton, tournamentButton,
                    venueButton, matchButton, scoreButton, overViewButton,
                    scheduleButton, resultsButton);

            showMatchSchedule(content);
        });

        resultsButton.addActionListener(e -> {
            setActiveButton(
                    resultsButton,
                    playerButton, teamButton, coachButton, tournamentButton,
                    venueButton, matchButton, scoreButton, overViewButton,
                    scheduleButton, resultsButton);

            showMatchResults(content);
        });
        add(panel, BorderLayout.WEST);
        setVisible(true);
    }

    private void showMessage(JPanel panel, String section) {
        // Remove the previous content
        panel.removeAll();

        // Get the teams information
        if (section.equals("Teams")) {
            String[] columns = { "TEAM_ID", "Team_Name", "Department", "Email" };
            DefaultTableModel model = new DefaultTableModel(columns, 0);

            String query = "SELECT * FROM team";
            try (Connection connection = DBConnection.getConnection();
                    Statement statement = connection.createStatement();
                    ResultSet result = statement.executeQuery(query)) {

                while (result.next()) {
                    model.addRow(new Object[] {
                            result.getInt("TEAM_ID"),
                            result.getString("Team_Name"),
                            result.getString("Department"),
                            result.getString("Email")
                    });
                }

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                        this,
                        e.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            JTable table = new JTable(model);

            JButton addButton = new JButton("Add Team");
            JButton editButton = new JButton("Edit Team");
            JButton deleteButton = new JButton("Delete Team");

            JTextField searchField = new JTextField(15);
            JButton searchButton = new JButton("Search");
            JButton resetButton = new JButton("Show all");

            JLabel searchLabel = new JLabel("Search Team:");

            styleTable(table);

            // Style CRUD buttons
            styleActionButton(addButton);
            styleActionButton(editButton);
            styleActionButton(deleteButton);

            // Style search components
            styleSearchField(searchField);
            styleSearchButton(searchButton);
            styleSearchButton(resetButton);
            searchLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));

            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            topPanel.add(addButton);
            topPanel.add(editButton);
            topPanel.add(deleteButton);

            JPanel northPanel = new JPanel(new BorderLayout());
            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            searchPanel.add(searchLabel);
            searchPanel.add(searchField);
            searchPanel.add(searchButton);
            searchPanel.add(resetButton);
            northPanel.add(topPanel, BorderLayout.NORTH);
            northPanel.add(searchPanel, BorderLayout.SOUTH);

            panel.add(northPanel, BorderLayout.NORTH);
            panel.add(new JScrollPane(table), BorderLayout.CENTER);
            addButton.addActionListener(e -> addTeamForm(panel));
            editButton.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(this, "Select a team first");
                    return;
                }
                int modelRow = table.convertRowIndexToModel(selectedRow);
                int teamId = (int) table.getModel().getValueAt(modelRow, 0);

                editTeamForm(panel, teamId);
            });

            deleteButton.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(this, "Select a row first");
                    return;
                }
                int modelRow = table.convertRowIndexToModel(selectedRow);
                int result = (int) table.getModel().getValueAt(modelRow, 0);
                int confirmDel = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this Team_ID" + result + "?",
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION);
                if (confirmDel != JOptionPane.YES_OPTION)
                    return;
                String sql = "DELETE FROM team WHERE TEAM_ID = ?";

                try (Connection con = DBConnection.getConnection();
                        PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, result);
                    int rowsDeleted = ps.executeUpdate();

                    if (rowsDeleted > 0) {
                        JOptionPane.showMessageDialog(this, "Team delted successfully");
                        showMessage(panel, "Teams");
                    } else {
                        JOptionPane.showMessageDialog(this, "Team not found");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Could not delete team: " + ex.getMessage(), "Database Error",
                            JOptionPane.ERROR_MESSAGE);

                }
            });
            searchButton.addActionListener(e -> {
                String search = searchField.getText().trim();

                javax.swing.table.TableRowSorter<DefaultTableModel> sorter = new javax.swing.table.TableRowSorter<>(
                        model);

                table.setRowSorter(sorter);

                sorter.setRowFilter(
                        javax.swing.RowFilter.regexFilter(
                                "(?i)" + java.util.regex.Pattern.quote(search)));
            });

            resetButton.addActionListener(e -> {
                searchField.setText("");
                table.setRowSorter(null);
            });
            panel.validate();
            panel.repaint();
            return;
        }
        if (section.equals("Players")) {
            String[] columns = { "Player ID", "Gender", "First Name", "Last Name" };
            DefaultTableModel model = new DefaultTableModel(columns, 0);

            String query = "SELECT Player_ID, Gender, First_Name, Last_Name FROM player";

            try (Connection connection = DBConnection.getConnection();
                    Statement statement = connection.createStatement();
                    ResultSet result = statement.executeQuery(query)) {

                while (result.next()) {
                    model.addRow(new Object[] {
                            result.getInt("Player_ID"),
                            result.getString("Gender"),
                            result.getString("First_Name"),
                            result.getString("Last_Name")
                    });
                }
            }

            catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                        this,
                        e.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            JTable table = new JTable(model);
            JButton addButton = new JButton("Add Player");
            JButton editButton = new JButton("Edit Player");
            JButton deleteButton = new JButton("Delete Player");
            JTextField searchField = new JTextField(15);
            JButton searchButton = new JButton("Search");
            JButton resetButton = new JButton("Show All");

            styleTable(table);

            styleActionButton(addButton);
            styleActionButton(editButton);
            styleActionButton(deleteButton);

            styleSearchField(searchField);
            styleSearchButton(searchButton);
            styleSearchButton(resetButton);
            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            topPanel.add(addButton);
            topPanel.add(editButton);
            topPanel.add(deleteButton);
            topPanel.add(searchButton);

            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel searchLabel = new JLabel("Search Player:");
            searchLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
            searchPanel.add(searchLabel);
            searchPanel.add(searchField);
            searchPanel.add(searchButton);
            searchPanel.add(resetButton);

            JPanel northPanel = new JPanel(new BorderLayout());
            northPanel.add(topPanel, BorderLayout.NORTH);
            northPanel.add(searchPanel, BorderLayout.SOUTH);

            panel.add(northPanel, BorderLayout.NORTH);
            panel.add(new JScrollPane(table), BorderLayout.CENTER);

            panel.add(new JScrollPane(table), BorderLayout.CENTER);
            addButton.addActionListener(e -> addPlayerForm(panel));

            editButton.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(this, "Select a player first!");
                    return;
                }

                int modelRow = table.convertRowIndexToModel(selectedRow);
                int playerId = (int) table.getModel().getValueAt(modelRow, 0);

                editPlayerForm(panel, playerId);
            });
            deleteButton.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(this, "Select a player first!");
                    return;
                }

                int modelRow = table.convertRowIndexToModel(selectedRow);
                int playerId = (int) table.getModel().getValueAt(modelRow, 0);

                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete Player ID " + playerId + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    String queryy = "DELETE FROM player WHERE Player_ID = ?";

                    try (Connection con = DBConnection.getConnection();
                            PreparedStatement ps = con.prepareStatement(queryy)) {

                        ps.setInt(1, playerId);
                        ps.executeUpdate();

                        JOptionPane.showMessageDialog(this, "Player deleted successfully!");
                        showMessage(panel, "Players");

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(
                                this,
                                ex.getMessage(),
                                "Database Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            searchButton.addActionListener(e -> {
                String search = searchField.getText().trim();

                javax.swing.table.TableRowSorter<DefaultTableModel> sorter = new javax.swing.table.TableRowSorter<>(
                        model);

                table.setRowSorter(sorter);

                sorter.setRowFilter(
                        javax.swing.RowFilter.regexFilter("(?i)" +
                                java.util.regex.Pattern.quote(search)));
            });

            resetButton.addActionListener(e -> {
                searchField.setText("");
                table.setRowSorter(null);
            });
            panel.revalidate();
            panel.repaint();
            return;
        }
        if (section.equals("Coaches")) {
            String[] columns = { "Coach ID", "First Name", "Last Name", "Phone No", "Team ID" };
            DefaultTableModel model = new DefaultTableModel(columns, 0);

            String query = "SELECT * FROM coach";

            try (Connection connection = DBConnection.getConnection();
                    Statement statement = connection.createStatement();
                    ResultSet result = statement.executeQuery(query)) {

                while (result.next()) {
                    model.addRow(new Object[] {
                            result.getInt("Coach_ID"),
                            result.getString("First_Name"),
                            result.getString("Last_Name"),
                            result.getString("Phone_No"),
                            result.getInt("Team_ID")
                    });
                }
            }

            catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                        this,
                        e.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            JTable table = new JTable(model);
            JButton addButton = new JButton("Add Coach");
            JButton editButton = new JButton("Edit Coach");
            JButton deleteButton = new JButton("Delete Coach");
            JTextField searchField = new JTextField(15);
            JButton searchButton = new JButton("Search");
            JButton resetButton = new JButton("Search all");
            // Style table
            styleTable(table);

            // Style action buttons
            styleActionButton(addButton);
            styleActionButton(editButton);
            styleActionButton(deleteButton);

            // Style search components
            styleSearchField(searchField);
            styleSearchButton(searchButton);
            styleSearchButton(resetButton);
            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            topPanel.add(addButton);
            topPanel.add(editButton);
            topPanel.add(deleteButton);

            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel searchLabel = new JLabel("Search Coach:");
            searchLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
            searchPanel.add(searchLabel);
            searchPanel.add(searchField);
            searchPanel.add(searchButton);
            searchPanel.add(resetButton);

            JPanel northPanel = new JPanel(new BorderLayout());
            northPanel.add(topPanel, BorderLayout.NORTH);
            northPanel.add(searchPanel, BorderLayout.SOUTH);

            panel.add(northPanel, BorderLayout.NORTH);
            panel.add(new JScrollPane(table), BorderLayout.CENTER);

            addButton.addActionListener(e -> addCoachForm(panel));

            editButton.addActionListener(e -> {
                System.out.println("Edit Coach button clicked!");

                int selectedRow = table.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(this, "Select a coach first!");
                    return;
                }

                int modelRow = table.convertRowIndexToModel(selectedRow);
                int coachId = (int) table.getModel().getValueAt(modelRow, 0);

                editCoachForm(panel, coachId);
            });
            deleteButton.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(this, "Select a row first");
                    return;
                }
                int modelRow = table.convertRowIndexToModel(selectedRow);
                int coachId = (int) table.getModel().getValueAt(modelRow, 0);
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete the Coach ID" + coachId + "?",
                        "Confirm deletion",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    String queryy = "DELETE from coach WHERE Coach_ID = ?";

                    try (Connection con = DBConnection.getConnection();
                            PreparedStatement ps = con.prepareStatement(queryy)) {
                        ps.setInt(1, coachId);
                        ps.executeUpdate();

                        JOptionPane.showMessageDialog(this, "Coach deleted successfully");
                        showMessage(panel, "Coaches");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage(), "Database Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            searchButton.addActionListener(e -> {
                String search = searchField.getText().trim();

                javax.swing.table.TableRowSorter<DefaultTableModel> sorter = new javax.swing.table.TableRowSorter<>(
                        model);

                table.setRowSorter(sorter);

                sorter.setRowFilter(
                        javax.swing.RowFilter.regexFilter(
                                "(?i)" + java.util.regex.Pattern.quote(search)));
            });

            resetButton.addActionListener(e -> {
                searchField.setText("");
                table.setRowSorter(null);
            });
            panel.revalidate();
            panel.repaint();
            return;
        }
        if (section.equals("Tournaments")) {
            String[] columns = { "TOURNAMENT_ID", "Tournament_Name", "Start_Date", "End_Date" };
            DefaultTableModel model = new DefaultTableModel(columns, 0);

            String query = "SELECT * FROM tournament";

            try (Connection connection = DBConnection.getConnection();
                    Statement statement = connection.createStatement();
                    ResultSet result = statement.executeQuery(query)) {

                while (result.next()) {
                    model.addRow(new Object[] {
                            result.getInt("TOURNAMENT_ID"),
                            result.getString("Tournament_Name"),
                            result.getString("Start_Date"),
                            result.getString("End_Date")
                    });
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                        this,
                        e.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            JTable table = new JTable(model);
            JButton addButton = new JButton("Add Tournament");
            JButton editButton = new JButton("Edit Tournament");
            JButton deleteButton = new JButton("Delete Tournament");
            JTextField searchField = new JTextField(15);
            JButton searchButton = new JButton("Search");
            JButton resetButton = new JButton("Show all");
            // Style table
            styleTable(table);

            // Style action buttons
            styleActionButton(addButton);
            styleActionButton(editButton);
            styleActionButton(deleteButton);

            // Style search components
            styleSearchField(searchField);
            styleSearchButton(searchButton);
            styleSearchButton(resetButton);
            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            topPanel.add(addButton);
            topPanel.add(editButton);
            topPanel.add(deleteButton);

            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel searchLabel = new JLabel("Search Tournament:");
            searchLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
            searchPanel.add(searchLabel);
            searchPanel.add(searchField);
            searchPanel.add(searchButton);
            searchPanel.add(resetButton);

            JPanel northPanel = new JPanel(new BorderLayout());
            northPanel.add(topPanel, BorderLayout.NORTH);
            northPanel.add(searchPanel, BorderLayout.SOUTH);

            panel.add(northPanel, BorderLayout.NORTH);
            panel.add(new JScrollPane(table), BorderLayout.CENTER);

            addButton.addActionListener(e -> addTournamentForm(panel));
            editButton.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(this, "Select a tournament first!");
                    return;
                }

                int modelRow = table.convertRowIndexToModel(selectedRow);
                int tournamentId = (int) table.getModel().getValueAt(modelRow, 0);
                editTournamentForm(panel, tournamentId);
            });

            deleteButton.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(this, "Select a tournament first!");
                    return;
                }

                int modelRow = table.convertRowIndexToModel(selectedRow);
                int tournamentId = (int) table.getModel().getValueAt(modelRow, 0);

                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this tournament?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    String queryy = "DELETE FROM tournament WHERE TOURNAMENT_ID = ?";

                    try (Connection con = DBConnection.getConnection();
                            PreparedStatement ps = con.prepareStatement(queryy)) {

                        ps.setInt(1, tournamentId);
                        ps.executeUpdate();

                        JOptionPane.showMessageDialog(
                                this, "Tournament deleted successfully!");

                        showMessage(panel, "Tournaments");

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(
                                this,
                                ex.getMessage(),
                                "Database Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            searchButton.addActionListener(e -> {
                String search = searchField.getText().trim();

                javax.swing.table.TableRowSorter<DefaultTableModel> sorter = new javax.swing.table.TableRowSorter<>(
                        model);

                table.setRowSorter(sorter);

                sorter.setRowFilter(
                        javax.swing.RowFilter.regexFilter(
                                "(?i)" + java.util.regex.Pattern.quote(search)));
            });

            resetButton.addActionListener(e -> {
                searchField.setText("");
                table.setRowSorter(null);
            });
            panel.add(new JScrollPane(table), BorderLayout.CENTER);
            panel.revalidate();
            panel.repaint();
            return;

        }
        if (section.equals("Venues")) {
            String[] columns = { "Venue_ID", "Venue_Name", "Venue_Capacity", "Venue_Location", "Email", "City" };
            DefaultTableModel model = new DefaultTableModel(columns, 0);

            String query = "SELECT * FROM venue";

            try (Connection connection = DBConnection.getConnection();
                    Statement statement = connection.createStatement();
                    ResultSet result = statement.executeQuery(query)) {
                while (result.next()) {
                    model.addRow(new Object[] {
                            result.getInt("Venue_ID"),
                            result.getString("Venue_Name"),
                            result.getInt("Venue_Capacity"),
                            result.getString("Venue_Location"),
                            result.getString("Email"),
                            result.getString("City")
                    });
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                        this,
                        e.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);

            }
            JTable table = new JTable(model);

            JButton addButton = new JButton("Add Venue");
            JButton editButton = new JButton("Edit Venue");
            JButton deleteButton = new JButton("Delete Venue");
            JTextField searchField = new JTextField(15);
            JButton searchButton = new JButton("Search");
            JButton resetButton = new JButton("Show all");
            // Style table
            styleTable(table);

            // Style CRUD buttons
            styleActionButton(addButton);
            styleActionButton(editButton);
            styleActionButton(deleteButton);

            // Style search components
            styleSearchField(searchField);
            styleSearchButton(searchButton);
            styleSearchButton(resetButton);
            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            topPanel.add(addButton);
            topPanel.add(editButton);
            topPanel.add(deleteButton);

            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel searchLabel = new JLabel("Search Venue:");
            searchLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
            searchPanel.add(searchLabel);
            searchPanel.add(searchField);
            searchPanel.add(searchButton);
            searchPanel.add(resetButton);

            JPanel northPanel = new JPanel(new BorderLayout());
            northPanel.add(topPanel, BorderLayout.NORTH);
            northPanel.add(searchPanel, BorderLayout.SOUTH);

            panel.add(northPanel, BorderLayout.NORTH);
            panel.add(new JScrollPane(table), BorderLayout.CENTER);

            addButton.addActionListener(e -> addVenueForm(panel));

            editButton.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(this, "Select a venue first!");
                    return;
                }

                int modelRow = table.convertRowIndexToModel(selectedRow);
                int venueId = (int) table.getModel().getValueAt(modelRow, 0);
                editVenueForm(panel, venueId);
            });

            deleteButton.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(this, "Select a venue first!");
                    return;
                }

                int modelRow = table.convertRowIndexToModel(selectedRow);
                int venueId = (int) table.getModel().getValueAt(modelRow, 0);

                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this venue?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    String queryy = "DELETE FROM venue WHERE Venue_ID = ?";

                    try (Connection con = DBConnection.getConnection();
                            PreparedStatement ps = con.prepareStatement(queryy)) {

                        ps.setInt(1, venueId);
                        ps.executeUpdate();

                        JOptionPane.showMessageDialog(
                                this, "Venue deleted successfully!");

                        showMessage(panel, "Venues");

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(
                                this,
                                ex.getMessage(),
                                "Database Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            searchButton.addActionListener(e -> {
                String search = searchField.getText().trim();

                javax.swing.table.TableRowSorter<DefaultTableModel> sorter = new javax.swing.table.TableRowSorter<>(
                        model);

                table.setRowSorter(sorter);

                sorter.setRowFilter(
                        javax.swing.RowFilter.regexFilter(
                                "(?i)" + java.util.regex.Pattern.quote(search)));
            });

            resetButton.addActionListener(e -> {
                searchField.setText("");
                table.setRowSorter(null);
            });
            panel.add(new JScrollPane(table), BorderLayout.CENTER);
            panel.validate();
            panel.repaint();
            return;
        }

        if (section.equals("Matches")) {
            String[] columns = { "Match_ID", "Match_Time", "Match_Date", "Round", "Tournament_ID", "Venue_ID" };
            DefaultTableModel model = new DefaultTableModel(columns, 0);

            String query = "SELECT * FROM `match`";

            try (Connection connection = DBConnection.getConnection();
                    Statement statement = connection.createStatement();
                    ResultSet result = statement.executeQuery(query)) {
                while (result.next()) {
                    model.addRow(new Object[] {
                            result.getInt("Match_ID"),
                            result.getTime("Match_Time"),
                            result.getDate("Match_Date"),
                            result.getString("Round"),
                            result.getInt("Tournament_ID"),
                            result.getInt("Venue_ID")
                    });
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                        this,
                        e.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);

            }
            JTable table = new JTable(model);
            JButton addButton = new JButton("Add Match");
            JButton editButton = new JButton("Edit Match");
            JButton deleteButton = new JButton("Delete Match");
            JTextField searchField = new JTextField(15);
            JButton searchButton = new JButton("Search");
            JButton resetButton = new JButton("Show all");
            styleTable(table);

            styleActionButton(addButton);
            styleActionButton(editButton);
            styleActionButton(deleteButton);

            styleSearchField(searchField);
            styleSearchButton(searchButton);
            styleSearchButton(resetButton);
            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            topPanel.add(addButton);
            topPanel.add(editButton);
            topPanel.add(deleteButton);

            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel searchLabel = new JLabel("Search Match:");
            searchLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
            searchPanel.add(searchField);
            searchPanel.add(searchButton);
            searchPanel.add(resetButton);

            JPanel northPanel = new JPanel(new BorderLayout());
            northPanel.add(topPanel, BorderLayout.NORTH);
            northPanel.add(searchPanel, BorderLayout.SOUTH);

            panel.add(northPanel, BorderLayout.NORTH);
            panel.add(new JScrollPane(table), BorderLayout.CENTER);

            addButton.addActionListener(e -> addMatchForm(panel));

            editButton.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(this, "Select a match first!");
                    return;
                }

                int modelRow = table.convertRowIndexToModel(selectedRow);
                int matchId = (int) table.getModel().getValueAt(modelRow, 0);
                editMatchForm(panel, matchId);
            });

            deleteButton.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(this, "Select a match first!");
                    return;
                }

                int modelRow = table.convertRowIndexToModel(selectedRow);
                int matchId = (int) table.getModel().getValueAt(modelRow, 0);

                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this match?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    String queryy = "DELETE FROM `match` WHERE Match_ID = ?";

                    try (Connection con = DBConnection.getConnection();
                            PreparedStatement ps = con.prepareStatement(queryy)) {

                        ps.setInt(1, matchId);
                        ps.executeUpdate();

                        JOptionPane.showMessageDialog(
                                this, "Match deleted successfully!");

                        showMessage(panel, "Matches");

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(
                                this,
                                ex.getMessage(),
                                "Database Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            searchButton.addActionListener(e -> {
                String search = searchField.getText().trim();

                javax.swing.table.TableRowSorter<DefaultTableModel> sorter = new javax.swing.table.TableRowSorter<>(
                        model);

                table.setRowSorter(sorter);

                sorter.setRowFilter(
                        javax.swing.RowFilter.regexFilter(
                                "(?i)" + java.util.regex.Pattern.quote(search)));
            });

            resetButton.addActionListener(e -> {
                searchField.setText("");
                table.setRowSorter(null);
            });
            panel.add(new JScrollPane(table), BorderLayout.CENTER);
            panel.validate();
            panel.repaint();
            return;
        }

        if (section.equals("Scores")) {
            String[] columns = { "Score_ID", "Team_1_Score", "Team_2_Score", "Match_ID" };
            DefaultTableModel model = new DefaultTableModel(columns, 0);

            String query = "SELECT * FROM score";

            try (Connection connection = DBConnection.getConnection();
                    Statement statement = connection.createStatement();
                    ResultSet result = statement.executeQuery(query)) {
                while (result.next()) {
                    model.addRow(new Object[] {
                            result.getInt("Score_ID"),
                            result.getInt("Team_1_Score"),
                            result.getInt("Team_2_Score"),
                            result.getInt("Match_ID")
                    });
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                        this,
                        e.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);

            }
            JTable table = new JTable(model);
            JButton addButton = new JButton("Add Score");
            JButton editButton = new JButton("Edit Score");
            JButton deleteButton = new JButton("Delete Score");
            JTextField searchField = new JTextField(15);
            JButton searchButton = new JButton("Search");
            JButton resetButton = new JButton("Show all");
            styleTable(table);

            styleActionButton(addButton);
            styleActionButton(editButton);
            styleActionButton(deleteButton);

            styleSearchField(searchField);
            styleSearchButton(searchButton);
            styleSearchButton(resetButton);
            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            topPanel.add(addButton);
            topPanel.add(editButton);
            topPanel.add(deleteButton);

            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel searchLabel = new JLabel("Search Score:");
            searchLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
            searchPanel.add(searchField);
            searchPanel.add(searchButton);
            searchPanel.add(resetButton);
            JPanel northPanel = new JPanel(new BorderLayout());
            northPanel.add(topPanel, BorderLayout.NORTH);
            northPanel.add(searchPanel, BorderLayout.SOUTH);

            panel.add(northPanel, BorderLayout.NORTH);
            panel.add(new JScrollPane(table), BorderLayout.CENTER);

            addButton.addActionListener(e -> addScoreForm(panel));

            editButton.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(this, "Select a score first!");
                    return;
                }

                int modelRow = table.convertRowIndexToModel(selectedRow);
                int scoreId = (int) table.getModel().getValueAt(modelRow, 0);
                editScoreForm(panel, scoreId);
            });

            deleteButton.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(this, "Select a score first!");
                    return;
                }

                int modelRow = table.convertRowIndexToModel(selectedRow);
                int scoreId = (int) table.getModel().getValueAt(modelRow, 0);

                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this score?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    String queryy = "DELETE FROM score WHERE Score_ID = ?";

                    try (Connection con = DBConnection.getConnection();
                            PreparedStatement ps = con.prepareStatement(queryy)) {

                        ps.setInt(1, scoreId);
                        ps.executeUpdate();

                        JOptionPane.showMessageDialog(
                                this, "Score deleted successfully!");

                        showMessage(panel, "Scores");

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(
                                this,
                                ex.getMessage(),
                                "Database Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            searchButton.addActionListener(e -> {
                String search = searchField.getText().trim();

                javax.swing.table.TableRowSorter<DefaultTableModel> sorter = new javax.swing.table.TableRowSorter<>(
                        model);

                table.setRowSorter(sorter);

                sorter.setRowFilter(
                        javax.swing.RowFilter.regexFilter(
                                "(?i)" + java.util.regex.Pattern.quote(search)));
            });

            resetButton.addActionListener(e -> {
                searchField.setText("");
                table.setRowSorter(null);
            });
            panel.add(new JScrollPane(table), BorderLayout.CENTER);
            panel.validate();
            panel.repaint();
            return;
        }
    }

    private void addPlayerForm(JPanel panel) {
        JTextField idField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField firstNameField = new JTextField();
        JTextField lastnameField = new JTextField();

        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        form.add(new JLabel("Player ID: "));
        form.add(idField);
        form.add(new JLabel("Gender: "));
        form.add(genderField);
        form.add(new JLabel("First Name: "));
        form.add(firstNameField);
        form.add(new JLabel("Last Name: "));
        form.add(lastnameField);

        int result = JOptionPane.showConfirmDialog(
                this, form, "Add Player",
                JOptionPane.OK_CANCEL_OPTION);

        if (result != JOptionPane.OK_OPTION)
            return;

        try {
            int playerId = Integer.parseInt(idField.getText().trim());
            String gender = genderField.getText().trim();
            String firstName = firstNameField.getText().trim();
            String lastName = lastnameField.getText().trim();

            if (gender.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill all the fields");
                return;
            }
            String sql = "INSERT INTO player (Player_ID, Gender, First_Name, Last_Name) VALUES (?,?,?,?)";
            try (Connection con = DBConnection.getConnection();
                    java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, playerId);
                ps.setString(2, gender);
                ps.setString(3, firstName);
                ps.setString(4, lastName);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Player added successfully");
                showMessage(panel, "Players");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Player ID must be a number");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editPlayerForm(JPanel panel, int playerId) {
        JTextField genderField = new JTextField();
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();

        String query = "SELECT Gender, First_Name, Last_Name FROM player WHERE Player_ID = ?";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, playerId);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(this, "Player not found!");
                    return;
                }

                genderField.setText(rs.getString("Gender"));
                firstNameField.setText(rs.getString("First_Name"));
                lastNameField.setText(rs.getString("Last_Name"));
            }

            JPanel form = new JPanel(new GridLayout(3, 2, 8, 8));
            form.add(new JLabel("Gender:"));
            form.add(genderField);
            form.add(new JLabel("First Name:"));
            form.add(firstNameField);
            form.add(new JLabel("Last Name:"));
            form.add(lastNameField);

            int result = JOptionPane.showConfirmDialog(
                    this, form, "Edit Player ID: " + playerId,
                    JOptionPane.OK_CANCEL_OPTION);

            if (result != JOptionPane.OK_OPTION)
                return;

            String gender = genderField.getText().trim();
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();

            if (gender.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill in all the fields!");
                return;
            }

            String update = "UPDATE player SET Gender = ?, First_Name = ?, Last_Name = ? WHERE Player_ID = ?";

            try (PreparedStatement updatePs = con.prepareStatement(update)) {
                updatePs.setString(1, gender);
                updatePs.setString(2, firstName);
                updatePs.setString(3, lastName);
                updatePs.setInt(4, playerId);

                int rowsUpdated = updatePs.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Player updated successfully!");
                    showMessage(panel, "Players");
                } else {
                    JOptionPane.showMessageDialog(this, "No player was updated.");
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this, ex.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addTeamForm(JPanel panel) {
        JTextField idField = new JTextField();
        JTextField teamName = new JTextField();
        JTextField department = new JTextField();
        JTextField email = new JTextField();

        JPanel form = new JPanel(new GridLayout(4, 2, 8, 8));
        form.add(new JLabel("Team ID: "));
        form.add(idField);
        form.add(new JLabel("Team Name: "));
        form.add(teamName);
        form.add(new JLabel("Department: "));
        form.add(department);
        form.add(new JLabel("Email: "));
        form.add(email);

        int result = JOptionPane.showConfirmDialog(this, form, "Add Player", JOptionPane.OK_CANCEL_OPTION);

        if (result != JOptionPane.OK_OPTION)
            return;

        try {
            int teamId = Integer.parseInt(idField.getText().trim());
            String name = teamName.getText().trim();
            String teamDept = department.getText().trim();
            String teamEmail = email.getText().trim();

            if (name.isEmpty() || teamDept.isEmpty() || teamEmail.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all the fields");
                return;
            }
            String sql = "INSERT INTO team (TEAM_ID, Team_Name, Department, Email) VALUES (?,?,?,?)";
            try (Connection con = DBConnection.getConnection();
                    PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, teamId);
                ps.setString(2, name);
                ps.setString(3, teamDept);
                ps.setString(4, teamEmail);
                ps.executeUpdate();
            }
            JOptionPane.showMessageDialog(this, "Team added successfully");
            showMessage(panel, "Teams");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Team ID must be a number");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void editTeamForm(JPanel panel, int teamId) {
        JTextField nameField = new JTextField();
        JTextField departmentField = new JTextField();
        JTextField emailField = new JTextField();

        String query = "SELECT Team_Name, Department, Email FROM team WHERE TEAM_ID = ?";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, teamId);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(this, "Team not found!");
                    return;
                }

                nameField.setText(rs.getString("Team_Name"));
                departmentField.setText(rs.getString("Department"));
                emailField.setText(rs.getString("Email"));
            }

            JPanel form = new JPanel(new GridLayout(3, 2, 8, 8));
            form.add(new JLabel("Team Name:"));
            form.add(nameField);
            form.add(new JLabel("Department:"));
            form.add(departmentField);
            form.add(new JLabel("Email:"));
            form.add(emailField);

            int result = JOptionPane.showConfirmDialog(
                    this, form, "Edit Team ID: " + teamId,
                    JOptionPane.OK_CANCEL_OPTION);

            if (result != JOptionPane.OK_OPTION)
                return;

            String name = nameField.getText().trim();
            String department = departmentField.getText().trim();
            String email = emailField.getText().trim();

            if (name.isEmpty() || department.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill all fields!");
                return;
            }

            String update = "UPDATE team SET Team_Name = ?, Department = ?, Email = ? WHERE TEAM_ID = ?";

            try (PreparedStatement updatePs = con.prepareStatement(update)) {
                updatePs.setString(1, name);
                updatePs.setString(2, department);
                updatePs.setString(3, email);
                updatePs.setInt(4, teamId);

                int rowsUpdated = updatePs.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Team updated successfully!");
                    showMessage(panel, "Teams");
                } else {
                    JOptionPane.showMessageDialog(this, "No team was updated.");
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this, ex.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addCoachForm(JPanel panel) {
        JTextField idField = new JTextField();
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField phoneField = new JTextField();
        JComboBox<String> teamBox = new JComboBox<>();

        Map<String, Integer> teamIds = new HashMap<>();
        String query = "SELECT TEAM_ID, Team_Name FROM team";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("TEAM_ID");
                String name = rs.getString("Team_Name");
                String item = id + " - " + name;

                teamBox.addItem(item);
                teamIds.put(item, id);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPanel form = new JPanel(new GridLayout(5, 2, 8, 8));
        form.add(new JLabel("Coach ID:"));
        form.add(idField);
        form.add(new JLabel("First Name:"));
        form.add(firstNameField);
        form.add(new JLabel("Last Name:"));
        form.add(lastNameField);
        form.add(new JLabel("Phone No:"));
        form.add(phoneField);
        form.add(new JLabel("Team ID:"));
        form.add(teamBox);

        int result = JOptionPane.showConfirmDialog(
                this, form, "Add Coach",
                JOptionPane.OK_CANCEL_OPTION);

        if (result != JOptionPane.OK_OPTION)
            return;

        try {
            int coachId = Integer.parseInt(idField.getText().trim());
            String selectedTeam = (String) teamBox.getSelectedItem();

            if (selectedTeam == null) {
                JOptionPane.showMessageDialog(this, "Add a team first!");
                return;
            }

            int teamId = teamIds.get(selectedTeam);

            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String phone = phoneField.getText().trim();

            if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill all fields!");
                return;
            }

            String sql = "INSERT INTO coach (Coach_ID, First_Name, Last_Name, Phone_No, Team_ID) VALUES (?, ?, ?, ?, ?)";

            try (Connection con = DBConnection.getConnection();
                    PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, coachId);
                ps.setString(2, firstName);
                ps.setString(3, lastName);
                ps.setString(4, phone);
                ps.setInt(5, teamId);
                ps.executeUpdate();
            }

            JOptionPane.showMessageDialog(this, "Coach added successfully!");
            showMessage(panel, "Coaches");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this, "Coach ID must be a number!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this, ex.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editCoachForm(JPanel panel, int coachId) {
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField phoneField = new JTextField();
        JComboBox<String> teamBox = new JComboBox<>();

        Map<String, Integer> teamIds = new HashMap<>();
        String tempQuery = "SELECT TEAM_ID, Team_Name FROM team";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(tempQuery);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("TEAM_ID");
                String name = rs.getString("Team_Name");
                String item = id + "-" + name;
                teamBox.addItem(item);
                teamIds.put(item, id);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String query = "SELECT First_Name, Last_Name, Phone_No, Team_ID FROM coach WHERE Coach_ID = ?";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, coachId);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(this, "Coach not found!");
                    return;
                }

                firstNameField.setText(rs.getString("First_Name"));
                lastNameField.setText(rs.getString("Last_Name"));
                phoneField.setText(rs.getString("Phone_No"));

                int currentTeamId = rs.getInt("Team_ID");

                for (int i = 0; i < teamBox.getItemCount(); i++) {
                    String item = teamBox.getItemAt(i);

                    if (teamIds.get(item) == currentTeamId) {
                        teamBox.setSelectedItem(item);
                        break;
                    }
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPanel form = new JPanel(new GridLayout(4, 2, 8, 8));
        form.add(new JLabel("First Name:"));
        form.add(firstNameField);
        form.add(new JLabel("Last Name:"));
        form.add(lastNameField);
        form.add(new JLabel("Phone No:"));
        form.add(phoneField);
        form.add(new JLabel("Team:"));
        form.add(teamBox);

        int result = JOptionPane.showConfirmDialog(
                this, form, "Edit Coach ID: " + coachId,
                JOptionPane.OK_CANCEL_OPTION);

        if (result != JOptionPane.OK_OPTION)
            return;

        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String phone = phoneField.getText().trim();
        String selectedTeam = (String) teamBox.getSelectedItem();

        if (firstName.isEmpty() || lastName.isEmpty()
                || phone.isEmpty() || selectedTeam == null) {
            JOptionPane.showMessageDialog(this, "Fill all fields!");
            return;
        }

        int teamId = teamIds.get(selectedTeam);

        String update = "UPDATE coach SET First_Name = ?, Last_Name = ?, Phone_No = ?, Team_ID = ? WHERE Coach_ID = ?";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(update)) {

            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, phone);
            ps.setInt(4, teamId);
            ps.setInt(5, coachId);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Coach updated successfully!");
                showMessage(panel, "Coaches");
            } else {
                JOptionPane.showMessageDialog(this, "No coach was updated.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addTournamentForm(JPanel panel) {
        JTextField idField = new JTextField();
        JTextField tournaName = new JTextField();
        JTextField startDate = new JTextField();
        JTextField endDate = new JTextField();

        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.add(new JLabel("Tournament ID:"));
        form.add(idField);
        form.add(new JLabel("Tournament Name:"));
        form.add(tournaName);
        form.add(new JLabel("Start Date (YYYY-MM-DD):"));
        form.add(startDate);
        form.add(new JLabel("End Date (YYYY-MM-DD):"));
        form.add(endDate);

        int result = JOptionPane.showConfirmDialog(this, form, "Add Tournament", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String query = "INSERT INTO tournament (TOURNAMENT_ID, Tournament_Name, Start_Date, End_Date) VALUES (?,?,?,?)";
            try (Connection con = DBConnection.getConnection();
                    PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, Integer.parseInt(idField.getText().trim()));
                ps.setString(2, tournaName.getText().trim());
                ps.setDate(3, java.sql.Date.valueOf(startDate.getText().trim()));
                ps.setDate(4, java.sql.Date.valueOf(endDate.getText().trim()));

                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Tournament added successfully");
                showMessage(panel, "Tournaments");
            } catch (SQLException | IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void editTournamentForm(JPanel panel, int tournamentId) {
        JTextField tournaName = new JTextField();
        JTextField startDate = new JTextField();
        JTextField endDate = new JTextField();

        String query = "SELECT Tournament_Name, Start_Date, End_Date " +
                "FROM tournament WHERE TOURNAMENT_ID = ?";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, tournamentId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tournaName.setText(rs.getString("Tournament_Name"));
                    startDate.setText(rs.getDate("Start_Date").toString());
                    endDate.setText(rs.getDate("End_Date").toString());
                } else {
                    JOptionPane.showMessageDialog(this, "Tournament not found!");
                    return;
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this, ex.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));

        form.add(new JLabel("Tournament Name:"));
        form.add(tournaName);

        form.add(new JLabel("Start Date (YYYY-MM-DD):"));
        form.add(startDate);

        form.add(new JLabel("End Date (YYYY-MM-DD):"));
        form.add(endDate);

        int result = JOptionPane.showConfirmDialog(
                this, form, "Edit Tournament", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            String updateQuery = "UPDATE tournament SET " +
                    "Tournament_Name = ?, Start_Date = ?, End_Date = ? " +
                    "WHERE TOURNAMENT_ID = ?";

            try (Connection con = DBConnection.getConnection();
                    PreparedStatement ps = con.prepareStatement(updateQuery)) {

                ps.setString(1, tournaName.getText().trim());
                ps.setDate(2, java.sql.Date.valueOf(startDate.getText().trim()));
                ps.setDate(3, java.sql.Date.valueOf(endDate.getText().trim()));
                ps.setInt(4, tournamentId);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                        this, "Tournament updated successfully!");

                showMessage(panel, "Tournaments");

            } catch (SQLException | IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(
                        this, ex.getMessage(), "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void addVenueForm(JPanel panel) {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField capacityField = new JTextField();
        JTextField locationField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField cityField = new JTextField();

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));

        form.add(new JLabel("Venue ID:"));
        form.add(idField);

        form.add(new JLabel("Venue Name:"));
        form.add(nameField);

        form.add(new JLabel("Venue Capacity:"));
        form.add(capacityField);

        form.add(new JLabel("Venue Location:"));
        form.add(locationField);

        form.add(new JLabel("Email:"));
        form.add(emailField);

        form.add(new JLabel("City:"));
        form.add(cityField);

        int result = JOptionPane.showConfirmDialog(
                this, form, "Add Venue", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String query = "INSERT INTO venue " +
                    "(Venue_ID, Venue_Name, Venue_Capacity, Venue_Location, Email, City) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            try (Connection con = DBConnection.getConnection();
                    PreparedStatement ps = con.prepareStatement(query)) {

                ps.setInt(1, Integer.parseInt(idField.getText().trim()));
                ps.setString(2, nameField.getText().trim());
                ps.setInt(3, Integer.parseInt(capacityField.getText().trim()));
                ps.setString(4, locationField.getText().trim());
                ps.setString(5, emailField.getText().trim());
                ps.setString(6, cityField.getText().trim());

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                        this, "Venue added successfully!");

                showMessage(panel, "Venues");

            } catch (SQLException | IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editVenueForm(JPanel panel, int venueId) {
        JTextField nameField = new JTextField();
        JTextField capacityField = new JTextField();
        JTextField locationField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField cityField = new JTextField();

        String query = "SELECT Venue_Name, Venue_Capacity, Venue_Location, Email, City " +
                "FROM venue WHERE Venue_ID = ?";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, venueId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nameField.setText(rs.getString("Venue_Name"));
                    capacityField.setText(String.valueOf(rs.getInt("Venue_Capacity")));
                    locationField.setText(rs.getString("Venue_Location"));
                    emailField.setText(rs.getString("Email"));
                    cityField.setText(rs.getString("City"));
                } else {
                    JOptionPane.showMessageDialog(this, "Venue not found!");
                    return;
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this, ex.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));

        form.add(new JLabel("Venue Name:"));
        form.add(nameField);

        form.add(new JLabel("Venue Capacity:"));
        form.add(capacityField);

        form.add(new JLabel("Venue Location:"));
        form.add(locationField);

        form.add(new JLabel("Email:"));
        form.add(emailField);

        form.add(new JLabel("City:"));
        form.add(cityField);

        int result = JOptionPane.showConfirmDialog(
                this, form, "Edit Venue", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String updateQuery = "UPDATE venue SET Venue_Name = ?, " +
                    "Venue_Capacity = ?, Venue_Location = ?, Email = ?, City = ? " +
                    "WHERE Venue_ID = ?";

            try (Connection con = DBConnection.getConnection();
                    PreparedStatement ps = con.prepareStatement(updateQuery)) {

                ps.setString(1, nameField.getText().trim());
                ps.setInt(2, Integer.parseInt(capacityField.getText().trim()));
                ps.setString(3, locationField.getText().trim());
                ps.setString(4, emailField.getText().trim());
                ps.setString(5, cityField.getText().trim());
                ps.setInt(6, venueId);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                        this, "Venue updated successfully!");

                showMessage(panel, "Venues");

            } catch (SQLException | IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(
                        this, ex.getMessage(), "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void addMatchForm(JPanel panel) {
        JTextField idField = new JTextField();
        JTextField timeField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField roundField = new JTextField();

        JComboBox<String> tournamentBox = new JComboBox<>();
        JComboBox<String> venueBox = new JComboBox<>();
        JComboBox<String> team1Box = new JComboBox<>();
        JComboBox<String> team2Box = new JComboBox<>();

        // Load tournaments
        try (Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(
                        "SELECT TOURNAMENT_ID, Tournament_Name FROM tournament")) {

            while (rs.next()) {
                tournamentBox.addItem(
                        rs.getInt("TOURNAMENT_ID") + " - "
                                + rs.getString("Tournament_Name"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            return;
        }

        // Load venues
        try (Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(
                        "SELECT Venue_ID, Venue_Name FROM venue")) {

            while (rs.next()) {
                venueBox.addItem(
                        rs.getInt("Venue_ID") + " - "
                                + rs.getString("Venue_Name"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            return;
        }

        // Load teams
        try (Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(
                        "SELECT TEAM_ID, Team_Name FROM team")) {

            while (rs.next()) {
                team1Box.addItem(
                        rs.getInt("TEAM_ID") + " - "
                                + rs.getString("Team_Name"));
                team2Box.addItem(
                        rs.getInt("TEAM_ID") + " - "
                                + rs.getString("Team_Name"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            return;
        }

        JPanel form = new JPanel(new GridLayout(8, 2, 10, 10));

        form.add(new JLabel("Match ID:"));
        form.add(idField);

        form.add(new JLabel("Match Time (HH:MM:SS):"));
        form.add(timeField);

        form.add(new JLabel("Match Date (YYYY-MM-DD):"));
        form.add(dateField);

        form.add(new JLabel("Round:"));
        form.add(roundField);

        form.add(new JLabel("Tournament:"));
        form.add(tournamentBox);

        form.add(new JLabel("Venue:"));
        form.add(venueBox);

        form.add(new JLabel("Team 1:"));
        form.add(team1Box);

        form.add(new JLabel("Team 2:"));
        form.add(team2Box);

        int result = JOptionPane.showConfirmDialog(
                this, form, "Add Match", JOptionPane.OK_CANCEL_OPTION);

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        try {
            if (team1Box.getSelectedItem() == null
                    || team2Box.getSelectedItem() == null
                    || tournamentBox.getSelectedItem() == null
                    || venueBox.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this,
                        "Please add teams, a tournament, and a venue first.");
                return;
            }

            int team1Id = Integer.parseInt(
                    team1Box.getSelectedItem().toString().split(" - ", 2)[0]);
            int team2Id = Integer.parseInt(
                    team2Box.getSelectedItem().toString().split(" - ", 2)[0]);
            int tournamentId = Integer.parseInt(
                    tournamentBox.getSelectedItem().toString().split(" - ", 2)[0]);
            int venueId = Integer.parseInt(
                    venueBox.getSelectedItem().toString().split(" - ", 2)[0]);

            if (team1Id == team2Id) {
                JOptionPane.showMessageDialog(this,
                        "Please select two different teams.");
                return;
            }

            String query = "INSERT INTO `match` "
                    + "(Match_ID, Match_Time, Match_Date, Round, "
                    + "Tournament_ID, Venue_ID, Team_1_ID, Team_2_ID) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection con = DBConnection.getConnection();
                    PreparedStatement ps = con.prepareStatement(query)) {

                ps.setInt(1, Integer.parseInt(idField.getText().trim()));
                ps.setTime(2, java.sql.Time.valueOf(timeField.getText().trim()));
                ps.setDate(3, java.sql.Date.valueOf(dateField.getText().trim()));
                ps.setString(4, roundField.getText().trim());
                ps.setInt(5, tournamentId);
                ps.setInt(6, venueId);
                ps.setInt(7, team1Id);
                ps.setInt(8, team2Id);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Match added successfully!");
                showMessage(panel, "Matches");
            }

        } catch (SQLException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editMatchForm(JPanel panel, int matchId) {
        JTextField timeField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField roundField = new JTextField();

        JComboBox<String> tournamentBox = new JComboBox<>();
        JComboBox<String> venueBox = new JComboBox<>();
        JComboBox<String> team1Box = new JComboBox<>();
        JComboBox<String> team2Box = new JComboBox<>();

        // Load dropdown data
        try (Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(
                        "SELECT TOURNAMENT_ID, Tournament_Name FROM tournament")) {
            while (rs.next()) {
                tournamentBox.addItem(
                        rs.getInt("TOURNAMENT_ID") + " - "
                                + rs.getString("Tournament_Name"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            return;
        }

        try (Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(
                        "SELECT Venue_ID, Venue_Name FROM venue")) {
            while (rs.next()) {
                venueBox.addItem(
                        rs.getInt("Venue_ID") + " - "
                                + rs.getString("Venue_Name"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            return;
        }

        try (Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(
                        "SELECT TEAM_ID, Team_Name FROM team")) {
            while (rs.next()) {
                String item = rs.getInt("TEAM_ID") + " - "
                        + rs.getString("Team_Name");
                team1Box.addItem(item);
                team2Box.addItem(item);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            return;
        }

        // Load existing match values
        String query = "SELECT Match_Time, Match_Date, Round, "
                + "Tournament_ID, Venue_ID, Team_1_ID, Team_2_ID "
                + "FROM `match` WHERE Match_ID = ?";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, matchId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    timeField.setText(
                            rs.getTime("Match_Time") == null ? ""
                                    : rs.getTime("Match_Time").toString());

                    dateField.setText(
                            rs.getDate("Match_Date") == null ? ""
                                    : rs.getDate("Match_Date").toString());

                    roundField.setText(rs.getString("Round"));

                    selectComboItem(tournamentBox,
                            rs.getInt("Tournament_ID"));
                    selectComboItem(venueBox,
                            rs.getInt("Venue_ID"));

                    int team1Id = rs.getInt("Team_1_ID");
                    if (!rs.wasNull()) {
                        selectComboItem(team1Box, team1Id);
                    }

                    int team2Id = rs.getInt("Team_2_ID");
                    if (!rs.wasNull()) {
                        selectComboItem(team2Box, team2Id);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Match not found!");
                    return;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            return;
        }

        JPanel form = new JPanel(new GridLayout(7, 2, 10, 10));

        form.add(new JLabel("Match Time (HH:MM:SS):"));
        form.add(timeField);

        form.add(new JLabel("Match Date (YYYY-MM-DD):"));
        form.add(dateField);

        form.add(new JLabel("Round:"));
        form.add(roundField);

        form.add(new JLabel("Tournament:"));
        form.add(tournamentBox);

        form.add(new JLabel("Venue:"));
        form.add(venueBox);

        form.add(new JLabel("Team 1:"));
        form.add(team1Box);

        form.add(new JLabel("Team 2:"));
        form.add(team2Box);

        int result = JOptionPane.showConfirmDialog(
                this, form, "Edit Match", JOptionPane.OK_CANCEL_OPTION);

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        try {
            if (tournamentBox.getSelectedItem() == null
                    || venueBox.getSelectedItem() == null
                    || team1Box.getSelectedItem() == null
                    || team2Box.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this,
                        "Please select a tournament, venue, and both teams.");
                return;
            }

            int tournamentId = getComboId(tournamentBox);
            int venueId = getComboId(venueBox);
            int team1Id = getComboId(team1Box);
            int team2Id = getComboId(team2Box);

            if (team1Id == team2Id) {
                JOptionPane.showMessageDialog(this,
                        "Please select two different teams.");
                return;
            }

            String updateQuery = "UPDATE `match` SET Match_Time = ?, "
                    + "Match_Date = ?, Round = ?, Tournament_ID = ?, "
                    + "Venue_ID = ?, Team_1_ID = ?, Team_2_ID = ? "
                    + "WHERE Match_ID = ?";

            try (Connection con = DBConnection.getConnection();
                    PreparedStatement ps = con.prepareStatement(updateQuery)) {

                ps.setTime(1, java.sql.Time.valueOf(timeField.getText().trim()));
                ps.setDate(2, java.sql.Date.valueOf(dateField.getText().trim()));
                ps.setString(3, roundField.getText().trim());
                ps.setInt(4, tournamentId);
                ps.setInt(5, venueId);
                ps.setInt(6, team1Id);
                ps.setInt(7, team2Id);
                ps.setInt(8, matchId);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                        this, "Match updated successfully!");

                showMessage(panel, "Matches");
            }
        } catch (SQLException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                    this, ex.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private int getComboId(JComboBox<String> combo) {
        return Integer.parseInt(
                combo.getSelectedItem().toString().split(" - ", 2)[0]);
    }

    private void selectComboItem(JComboBox<String> combo, int id) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            String item = combo.getItemAt(i);
            if (Integer.parseInt(item.split(" - ", 2)[0]) == id) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }

    private void addScoreForm(JPanel panel) {
        JTextField idField = new JTextField();
        JTextField team1ScoreField = new JTextField();
        JTextField team2ScoreField = new JTextField();
        JTextField matchIdField = new JTextField();

        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));

        form.add(new JLabel("Score ID:"));
        form.add(idField);

        form.add(new JLabel("Team 1 Score:"));
        form.add(team1ScoreField);

        form.add(new JLabel("Team 2 Score:"));
        form.add(team2ScoreField);

        form.add(new JLabel("Match ID:"));
        form.add(matchIdField);

        int result = JOptionPane.showConfirmDialog(
                this, form, "Add Score", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String query = "INSERT INTO score " +
                    "(Score_ID, Team_1_Score, Team_2_Score, Match_ID) " +
                    "VALUES (?, ?, ?, ?)";

            try (Connection con = DBConnection.getConnection();
                    PreparedStatement ps = con.prepareStatement(query)) {

                ps.setInt(1, Integer.parseInt(idField.getText().trim()));
                ps.setInt(2, Integer.parseInt(team1ScoreField.getText().trim()));
                ps.setInt(3, Integer.parseInt(team2ScoreField.getText().trim()));
                ps.setInt(4, Integer.parseInt(matchIdField.getText().trim()));

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                        this, "Score added successfully!");

                showMessage(panel, "Scores");

            } catch (SQLException | IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editScoreForm(JPanel panel, int scoreId) {
        JTextField team1ScoreField = new JTextField();
        JTextField team2ScoreField = new JTextField();
        JTextField matchIdField = new JTextField();

        String query = "SELECT Team_1_Score, Team_2_Score, Match_ID " +
                "FROM score WHERE Score_ID = ?";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, scoreId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    team1ScoreField.setText(
                            String.valueOf(rs.getInt("Team_1_Score")));
                    team2ScoreField.setText(
                            String.valueOf(rs.getInt("Team_2_Score")));
                    matchIdField.setText(
                            String.valueOf(rs.getInt("Match_ID")));
                } else {
                    JOptionPane.showMessageDialog(this, "Score not found!");
                    return;
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this, ex.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));

        form.add(new JLabel("Team 1 Score:"));
        form.add(team1ScoreField);

        form.add(new JLabel("Team 2 Score:"));
        form.add(team2ScoreField);

        form.add(new JLabel("Match ID:"));
        form.add(matchIdField);

        int result = JOptionPane.showConfirmDialog(
                this, form, "Edit Score", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String updateQuery = "UPDATE score SET Team_1_Score = ?, " +
                    "Team_2_Score = ?, Match_ID = ? WHERE Score_ID = ?";

            try (Connection con = DBConnection.getConnection();
                    PreparedStatement ps = con.prepareStatement(updateQuery)) {

                ps.setInt(1, Integer.parseInt(
                        team1ScoreField.getText().trim()));
                ps.setInt(2, Integer.parseInt(
                        team2ScoreField.getText().trim()));
                ps.setInt(3, Integer.parseInt(
                        matchIdField.getText().trim()));
                ps.setInt(4, scoreId);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                        this, "Score updated successfully!");

                showMessage(panel, "Scores");

            } catch (SQLException | IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(
                        this, ex.getMessage(), "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showOverview(JPanel panel) {
        panel.removeAll();
        panel.setLayout(new BorderLayout(15, 15));

        JLabel title = new JLabel("Tournament Dashboard");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(20, 35, 65));
        title.setBorder(BorderFactory.createEmptyBorder(25, 25, 10, 15));

        JPanel statsPanel = new JPanel(new GridLayout(0, 4, 15, 15));
        statsPanel.setBackground(new Color(245, 247, 252));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 25, 25));

        String[][] stats = {
                { "Total Players", "SELECT COUNT(*) FROM player", "👤" },
                { "Total Teams", "SELECT COUNT(*) FROM team", "🏆" },
                { "Total Coaches", "SELECT COUNT(*) FROM coach", "🎯" },
                { "Total Tournaments", "SELECT COUNT(*) FROM tournament", "🏅" },
                { "Total Venues", "SELECT COUNT(*) FROM venue", "🏟" },
                { "Total Matches", "SELECT COUNT(*) FROM `match`", "⚽" },
                { "Score Records", "SELECT COUNT(*) FROM score", "📊" },
                { "Upcoming Matches",
                        "SELECT COUNT(*) FROM `match` WHERE Match_Date >= CURDATE()", "📅" }
        };

        try (Connection con = DBConnection.getConnection()) {
            for (String[] stat : stats) {
                int count;

                try (Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(stat[1])) {

                    rs.next();
                    count = rs.getInt(1);
                }

                JPanel card = new JPanel();
                card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
                card.setBackground(Color.WHITE);
                card.setPreferredSize(new Dimension(220, 165));
                card.setBackground(Color.WHITE);
                Color[] accents = {
                        new Color(37, 99, 235),
                        new Color(22, 163, 74), // Green
                        new Color(147, 51, 234), // Purple
                        new Color(234, 88, 12), // Orange
                        new Color(8, 145, 178), // Cyan
                        new Color(220, 38, 38), // Red
                        new Color(202, 138, 4), // Gold
                        new Color(79, 70, 229)
                };

                Color accent = accents[statsPanel.getComponentCount()];
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 5, 0, 0, accent),
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(new Color(220, 226, 235)),
                                BorderFactory.createEmptyBorder(12, 10, 12, 10))));
                JLabel iconLabel = new JLabel(stat[2]);
                iconLabel.setFont(new Font("SansSerif", Font.PLAIN, 26));
                iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                JLabel nameLabel = new JLabel(stat[0]);
                nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
                nameLabel.setForeground(new Color(85, 98, 115));
                nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel countLabel = new JLabel(String.valueOf(count));
                countLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
                countLabel.setForeground(accent);
                countLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                card.add(Box.createVerticalGlue());
                card.add(iconLabel);
                card.add(Box.createVerticalStrut(12));
                card.add(nameLabel);
                card.add(Box.createVerticalStrut(12));
                card.add(countLabel);
                card.add(Box.createVerticalGlue());
                statsPanel.add(card);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this, ex.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        panel.add(title, BorderLayout.NORTH);
        panel.add(statsPanel, BorderLayout.CENTER);

        panel.revalidate();
        panel.repaint();
    }

    private void showMatchSchedule(JPanel panel) {
        panel.removeAll();
        panel.setLayout(new BorderLayout(10, 20));

        JLabel titleLabel = new JLabel("Match Schedule", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setForeground(new Color(30, 41, 69));

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setBorder(
                BorderFactory.createEmptyBorder(8, 10, 8, 10));
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        panel.add(titlePanel, BorderLayout.NORTH);
        String[] columns = {
                "Match ID", "Date", "Time", "Round",
                "Tournament", "Venue", "City"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        String query = "SELECT m.Match_ID, m.Match_Date, m.Match_Time, " +
                "m.Round, t.Tournament_Name, v.Venue_Name, v.City " +
                "FROM `match` m " +
                "LEFT JOIN tournament t ON m.Tournament_ID = t.TOURNAMENT_ID " +
                "LEFT JOIN venue v ON m.Venue_ID = v.Venue_ID " +
                "ORDER BY m.Match_Date, m.Match_Time";
        try (Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                model.addRow(new Object[] {
                        rs.getInt("Match_ID"),
                        rs.getDate("Match_Date"),
                        rs.getTime("Match_Time"),
                        rs.getString("Round"),
                        rs.getString("Tournament_Name"),
                        rs.getString("Venue_Name"),
                        rs.getString("City")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        JTable table = new JTable(model);
        styleTable(table);
        table.setAutoCreateRowSorter(true);

        panel.add(titlePanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }

    private void showMatchResults(JPanel panel) {
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        String[] columns = {
                "Match ID", "Tournament", "Team 1", "Score",
                "Team 2", "Score", "Winner", "Venue"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String query = "SELECT m.Match_ID, t.Tournament_Name, "
                + "tm1.Team_Name AS Team1, s.Team_1_Score, "
                + "tm2.Team_Name AS Team2, s.Team_2_Score, "
                + "v.Venue_Name "
                + "FROM `match` m "
                + "LEFT JOIN tournament t ON m.Tournament_ID = t.TOURNAMENT_ID "
                + "LEFT JOIN venue v ON m.Venue_ID = v.Venue_ID "
                + "LEFT JOIN team tm1 ON m.Team_1_ID = tm1.TEAM_ID "
                + "LEFT JOIN team tm2 ON m.Team_2_ID = tm2.TEAM_ID "
                + "LEFT JOIN score s ON m.Match_ID = s.Match_ID "
                + "ORDER BY m.Match_Date, m.Match_Time";

        try (Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                Object score1 = rs.getObject("Team_1_Score");
                Object score2 = rs.getObject("Team_2_Score");

                String winner = "Not played";

                if (score1 != null && score2 != null) {
                    int s1 = ((Number) score1).intValue();
                    int s2 = ((Number) score2).intValue();

                    if (s1 > s2) {
                        winner = rs.getString("Team1") == null
                                ? "Team 1 wins"
                                : rs.getString("Team1");
                    } else if (s2 > s1) {
                        winner = rs.getString("Team2") == null
                                ? "Team 2 wins"
                                : rs.getString("Team2");
                    } else {
                        winner = "Draw";
                    }
                }

                model.addRow(new Object[] {
                        rs.getInt("Match_ID"),
                        rs.getString("Tournament_Name"),
                        rs.getString("Team1"),
                        score1,
                        rs.getString("Team2"),
                        score2,
                        winner,
                        rs.getString("Venue_Name")
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this, ex.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        panel.removeAll();
        panel.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Match Results", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setForeground(new Color(30, 41, 69));

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setBorder(
                BorderFactory.createEmptyBorder(8, 10, 8, 10));
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        panel.add(titlePanel, BorderLayout.NORTH);
        JTable table = new JTable(model);
        styleTable(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(25);

        int[] widths = { 70, 230, 160, 70, 160, 70, 170, 220 };

        for (int i = 0; i < widths.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
        }

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }

    private void styleSidebarButton(JButton button) {
        button.setBackground(new Color(30, 41, 70));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setFont(new Font("SansSerif", Font.BOLD, 13));
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private void setActiveButton(JButton activeButton, JButton... buttons) {
        for (JButton button : buttons) {
            button.setBackground(new Color(30, 41, 70));
        }

        activeButton.setBackground(new Color(37, 99, 235));
    }

    private void styleTable(JTable table) {
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.setRowHeight(30);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(true);
        table.setGridColor(new Color(230, 234, 240));

        table.setSelectionBackground(new Color(219, 234, 254));
        table.setSelectionForeground(new Color(30, 41, 59));

        table.setBackground(Color.WHITE);
        table.setForeground(new Color(51, 65, 85));

        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 13));
        header.setBackground(new Color(30, 41, 70));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 36));
    }

    private void styleActionButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setBackground(new Color(37, 99, 235));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(9, 16, 9, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
    }

    private void styleSearchField(JTextField field) {
        field.setFont(new Font("SansSerif", Font.PLAIN, 13));
        field.setPreferredSize(new Dimension(220, 34));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225)),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        field.setBackground(Color.WHITE);
        field.setForeground(new Color(30, 41, 59));
    }

    private void styleSearchButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setBackground(new Color(37, 99, 235));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setVisible(true);
        button.setEnabled(true);
    }

}
