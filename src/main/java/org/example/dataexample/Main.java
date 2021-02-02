package org.example.dataexample;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


class Main {

    public static void main (String[] args){

               //  JFrame.setDefaultLookAndFeelDecorated(true);// default view of window
                TestFrame frame = new TestFrame(); //run variable
                frame.pack(); // optimize size of window of all frames
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
    }

    class TestFrame extends JFrame {

        private Container c;
        private JLabel titleUser;
        private JLabel titleAge;
        private JLabel titleEmail;
        private JButton reset;
        private JLabel res;
        private JTextArea username;
        private JTextArea age;
        private JTextArea email;

        public TestFrame() {

            createGUI();
        }

        public void createGUI() {

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setTitle("Registration Form");

            setBounds(300, 90, 900, 600);


            c = getContentPane();
            c.setLayout(null);


            JButton sub = new JButton("Submit");
            sub.setSize(200, 40);
            sub.setLocation(250, 400);
            sub.setFont(new Font("Arial", Font.PLAIN, 18));
            c.add(sub);

            reset = new JButton("Reset");
             reset.setFont(new Font("Arial", Font.PLAIN, 15));
            reset.setSize(100, 20);
            reset.setLocation(50, 400);
            c.add(reset);


            username = new JTextArea();

            username.setFont(new Font("Arial", Font.PLAIN, 15));

            username.setSize(200, 20);

            username.setLocation(140, 50);

            username.setLineWrap(true);

            c.add(username);

            titleUser = new JLabel("name user");

            titleUser.setFont(new Font("Arial", Font.PLAIN, 14));

            titleUser.setSize(150, 20);

            titleUser.setLocation(20, 50);

            c.add(titleUser);


            age = new JTextArea();

            age.setFont(new Font("Arial", Font.PLAIN, 15));

            age.setSize(200, 20);

            age.setLocation(140, 100);

            age.setLineWrap(true);

            c.add(age);

            titleAge = new JLabel("age");

            titleAge.setFont(new Font("Arial", Font.PLAIN, 14));

            titleAge.setSize(150, 20);

            titleAge.setLocation(20, 100);

            c.add(titleAge);


            email = new JTextArea();

            email.setFont(new Font("Arial", Font.PLAIN, 15));

            email.setSize(200, 20);

            email.setLocation(140, 150);

            email.setLineWrap(true);

            c.add(email);

            titleEmail = new JLabel("email");

            titleEmail.setFont(new Font("Arial", Font.PLAIN, 14));

            titleEmail.setSize(150, 20);

            titleEmail.setLocation(20, 150);

            c.add(titleEmail);


            res = new JLabel("");

            res.setFont(new Font("Arial", Font.PLAIN, 20));

            res.setSize(500, 25);

            res.setLocation(100, 500);

            c.add(res);


            ActionListener actionListener = new TestActionListener();

            sub.addActionListener(actionListener);
            reset.addActionListener(actionListener);


            setPreferredSize(new Dimension(500, 500));
            setVisible(true);
        }


        public class TestActionListener implements ActionListener {


            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == reset) {

                    String def = "";


                    username.setText(def);
                    age.setText(def);
                    email.setText(def);

                } else {

                    String data1 = username.getText();
                    String data2 = age.getText();
                    String data3 = email.getText();


                    String URL = "jdbc:postgresql://localhost:5432/testdb";
                    String USERNAME = "postgres";
                    String PASSWORD = "root";

                    try {
                        Driver driver = new org.postgresql.Driver();
                        DriverManager.registerDriver(driver);
                    } catch (SQLException i) {
                        System.err.println("fuck");
                    }

                    try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                         Statement statement = connection.createStatement()) {

                        if (!connection.isClosed()) {
                            System.out.println("Ушло в базу");
                        }
                        statement.execute("INSERT INTO users(name, age, email) " +
                                "VALUES ('" + data1 + "', '" + data2 + "', '" + data3 + "')");


                        System.out.println("OK");


                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
        }
    }





