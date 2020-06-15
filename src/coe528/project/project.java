/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.io.File;
import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import java.util.ArrayList;
import static javafx.application.Application.launch;
import javafx.scene.layout.FlowPane;

/**
 * Dieu Quang Ly
 *
 */
public class project extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Manager man = Manager.getInstance();
        final File folder = new File("C:/Users/Quang/Documents/NetBeansProjects/project/"); //"home/student1/dqly/COE528/project/project/"
        man.inputCustomers(folder);
        VBox primary = new VBox(10);
        HBox pane = new HBox(10);
        Scene scene = new Scene(primary);

        primary.setAlignment(Pos.CENTER);
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(10);

        Button bankmanager = new Button("Manager");
        bankmanager.setPrefSize(200, 50);
        pane.setMargin(bankmanager, new Insets(30, 30, 30, 30));
        bankmanager.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                VBox x = new VBox(10);
                x.setAlignment(Pos.CENTER);

                Label userlab = new Label("Username:");
                TextField user = new TextField();
                TextField pass = new TextField();
                Label passlab = new Label("Password:");

                x.setMargin(user, new Insets(20, 20, 20, 20));
                x.setMargin(pass, new Insets(20, 20, 20, 20));
                x.setMargin(userlab, new Insets(20, 20, 20, 20));
                x.setMargin(passlab, new Insets(20, 20, 20, 20));

                Button login = new Button("Login");
                login.setPrefSize(200, 50);
                login.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (man.login(user.getText(), pass.getText())) {
                            VBox t = new VBox(10);
                            Scene scene5 = new Scene(t);
                            t.setAlignment(Pos.CENTER);
                            t.setSpacing(10);

                            Button addC = new Button("Add");
                            addC.setPrefSize(200, 100);
                            addC.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    HBox z = new HBox(10);
                                    VBox field = new VBox(10);
                                    field.setAlignment(Pos.CENTER);
                                    z.setAlignment(Pos.CENTER);
                                    z.setSpacing(10);

                                    TextField user = new TextField();
                                    Label userlab = new Label("Username:");
                                    TextField pass = new TextField();
                                    Label passlab = new Label("Password:");

                                    Button addC = new Button("Add");
                                    Button ba = new Button("Back");
                                    Label res = new Label();
                                    addC.setPrefSize(200, 100);
                                    addC.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {
                                            String u = user.getText();
                                            String p = pass.getText();

                                            man.add(u, p);
                                            res.setText("Account opened for: " + u);
                                        }
                                    });

                                    ba.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {
                                            stage.setScene(scene5);
                                        }
                                    });
                                    z.setMargin(user, new Insets(30, 30, 30, 30));
                                    z.setMargin(pass, new Insets(30, 30, 30, 30));
                                    z.setMargin(addC, new Insets(30, 30, 30, 30));

                                    field.getChildren().addAll(userlab, user, passlab, pass, addC, ba, res);
                                    z.getChildren().addAll(field);
                                    Scene scene6 = new Scene(z);

                                    stage.setTitle("Account");
                                    stage.setScene(scene6);
                                    stage.show();
                                }
                            });

                            t.setMargin(addC, new Insets(30, 30, 30, 30));
                            Button removeC = new Button("Remove");
                            removeC.setPrefSize(200, 100);
                            removeC.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                                public void handle(ActionEvent event) {
                                    HBox removeCustomerPane = new HBox(10);
                                    VBox fieldRemoveCustomer = new VBox(10);
                                    fieldRemoveCustomer.setAlignment(Pos.CENTER);
                                    removeCustomerPane.setAlignment(Pos.CENTER);
                                    removeCustomerPane.setSpacing(10);

                                    TextField user = new TextField();
                                    Label userlab = new Label("Username:");

                                    Button removeC = new Button("Remove");
                                    Label res = new Label();
                                    removeC.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent event) {
                                            String u = user.getText();
                                            man.delete(u);
                                            res.setText(u + " no longer exists");
                                        }
                                    });

                                    Button back = new Button("Back");
                                    back.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent event) {
                                            stage.setScene(scene5);
                                        }
                                    });

                                    removeCustomerPane.setMargin(user, new Insets(30, 30, 30, 30));
                                    removeCustomerPane.setMargin(pass, new Insets(30, 30, 30, 30));

                                    fieldRemoveCustomer.getChildren().addAll(userlab, user, removeC, back, res);
                                    removeCustomerPane.getChildren().addAll(fieldRemoveCustomer);
                                    Scene scene7 = new Scene(removeCustomerPane);

                                    stage.setTitle("Account");
                                    stage.setScene(scene7);
                                    stage.show();
                                }
                            });

                            t.setMargin(removeC, new Insets(30, 30, 30, 30));
                            Button logout = new Button("Logout");
                            logout.setPrefSize(200, 50);
                            logout.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    User.logout();
                                    stage.setScene(scene);
                                }
                            });

                            t.setMargin(logout, new Insets(30, 30, 30, 30));
                            t.getChildren().addAll(addC, removeC, logout);

                            stage.setTitle("Account");
                            stage.setScene(scene5);
                            stage.show();
                        } else {
                            System.out.println("Invalid username/password.");
                        }
                    }
                });

                Button cancel = new Button("Cancel");
                cancel.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        stage.setScene(scene);
                    }
                });

                cancel.setPrefSize(200, 50);
                x.getChildren().addAll(userlab, user, passlab, pass, login, cancel);
                Scene scene4 = new Scene(x);

                stage.setTitle("Account");
                stage.setScene(scene4);
                stage.show();
            }
        });

        Button cust = new Button("Customer");
        cust.setPrefSize(200, 50);
        pane.setMargin(cust, new Insets(30, 30, 30, 30));
        cust.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                VBox pane = new VBox(10);
                pane.setAlignment(Pos.CENTER);
                pane.setSpacing(10);

                Label userlab = new Label("Username");
                TextField user = new TextField();
                TextField pass = new TextField();
                Label passlab = new Label("Password:");

                pane.setMargin(user, new Insets(30, 30, 30, 30));
                pane.setMargin(pass, new Insets(30, 30, 30, 30));
                Button login = new Button("Login");
                login.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            if (man.getCustomer(user.getText()).login(user.getText(), pass.getText()) == true) {
                                VBox pane = new VBox(10);
                                Scene scene3 = new Scene(pane);
                                pane.setAlignment(Pos.CENTER);
                                pane.setSpacing(10);
                                Label acc = new Label();
                                Customer a = man.getCustomer(user.getText());

                                Button withdraw = new Button("Withdrawal");
                                withdraw.setPrefSize(200, 100);
                                withdraw.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        HBox t = new HBox(10);
                                        VBox z = new VBox(10);
                                        VBox field = new VBox(10);

                                        t.setAlignment(Pos.CENTER);
                                        field.setAlignment(Pos.CENTER);
                                        z.setAlignment(Pos.CENTER);

                                        Label withdrawLabel = new Label("The amount of withdrawal:");
                                        Label withdrawAmountLabel = new Label("Amount :");
                                        TextField am = new TextField();

                                        Button withdraw = new Button("Withdraw");
                                        Button ba = new Button("Back");
                                        ba.setPrefSize(200, 100);
                                        withdraw.setPrefSize(200, 100);

                                        Label res = new Label();
                                        withdraw.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                                int amount = Integer.parseInt(am.getText());
                                                if (a.getBalance() >= amount) {
                                                    a.withdraw(amount);
                                                    res.setText(a.getUsername() + " Balance: " + a.getBalance());
                                                    field.getChildren().addAll(res);
                                                } else {
                                                    res.setText("Not enough money");
                                                    field.getChildren().addAll(res);
                                                }
                                            }
                                        });

                                        ba.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                                stage.setScene(scene3);
                                            }
                                        });

                                        z.setMargin(field, new Insets(30, 30, 30, 30));
                                        z.setMargin(withdrawLabel, new Insets(30, 30, 30, 30));
                                        z.setMargin(t, new Insets(30, 30, 30, 30));
                                        z.setMargin(withdrawLabel, new Insets(30, 30, 30, 30));
                                        z.setMargin(ba, new Insets(30, 30, 30, 30));

                                        t.getChildren().addAll(withdrawAmountLabel, am);
                                        z.getChildren().addAll(withdrawLabel, t, withdraw, ba, field);
                                        Scene scene8 = new Scene(z);

                                        stage.setTitle("Account");
                                        stage.setScene(scene8);
                                        stage.show();
                                    }
                                });

                                pane.setMargin(withdraw, new Insets(30, 30, 30, 30));
                                Button deposit = new Button("Deposit");
                                deposit.setPrefSize(200, 100);
                                deposit.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        HBox t = new HBox(10);
                                        VBox z = new VBox(10);
                                        VBox field = new VBox(10);

                                        t.setAlignment(Pos.CENTER);
                                        field.setAlignment(Pos.CENTER);
                                        z.setAlignment(Pos.CENTER);

                                        Label withdrawLabel = new Label("Enter amount of deposit: ");
                                        Label withdrawAmountLabel = new Label("Amount :");
                                        TextField am = new TextField();

                                        Button withdraw = new Button("Deposit");
                                        Button back = new Button("Back");
                                        back.setPrefSize(200, 100);

                                        withdraw.setPrefSize(200, 100);
                                        Label res = new Label();
                                        withdraw.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                                int amount = Integer.parseInt(am.getText());
                                                a.deposit(amount);
                                                res.setText(a.getUsername() + " Balance:  " + a.getBalance());
                                                field.getChildren().addAll(res);
                                            }
                                        });

                                        back.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                                stage.setScene(scene3);
                                            }
                                        });

                                        z.setMargin(field, new Insets(30, 30, 30, 30));
                                        z.setMargin(withdrawLabel, new Insets(30, 30, 30, 30));
                                        z.setMargin(t, new Insets(30, 30, 30, 30));
                                        z.setMargin(withdraw, new Insets(30, 30, 30, 30));
                                        z.setMargin(back, new Insets(30, 30, 30, 30));

                                        t.getChildren().addAll(withdrawAmountLabel, am);
                                        z.getChildren().addAll(withdrawLabel, t, withdraw, back, field);
                                        Scene scene9 = new Scene(z);

                                        stage.setTitle("Account");
                                        stage.setScene(scene9);
                                        stage.show();
                                    }
                                });

                                pane.setMargin(deposit, new Insets(30, 30, 30, 30));
                                Button OS = new Button("Online Shopping");

                                OS.setPrefSize(200, 20);
                                OS.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        FlowPane onlineShoppingPane = new FlowPane();
                                        onlineShoppingPane.setPadding(new Insets(20, 21, 22, 23));
                                        onlineShoppingPane.setAlignment(Pos.CENTER);
                                        onlineShoppingPane.setHgap(5);
                                        onlineShoppingPane.setVgap(5);
                                        TextField total = new TextField();
                                        Label tot = new Label("Total Amount");
                                        Label lab = new Label("Additonal Fee: Silver members: $20 fee, Gold members: $10 fee, Platinum Members: No fee");

                                        VBox field = new VBox(10);
                                        field.setAlignment(Pos.CENTER);
                                        VBox mainPane = new VBox(10);
                                        mainPane.setAlignment(Pos.CENTER);

                                        Label ob1 = new Label("iPhone 11");
                                        Label ob2 = new Label("Airpods Pro");
                                        Label ob3 = new Label("Apple Watch");
                                        Label ob4 = new Label("Apple Earphones");
                                        Label ob5 = new Label("iPad");

                                        int[] sum = new int[1];
                                        total.setText("0");
                                        sum[0] = 0;

                                        Button b1 = new Button("2000");
                                        b1.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                                sum[0] += 2000;
                                                total.setText(Integer.toString(sum[0]));
                                            }
                                        });

                                        Button b2 = new Button("250");
                                        b2.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                                sum[0] += 250;
                                                total.setText(Integer.toString(sum[0]));
                                            }
                                        });

                                        Button b3 = new Button("550");
                                        b3.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                                sum[0] += 550;
                                                total.setText(Integer.toString(sum[0]));
                                            }
                                        });

                                        Button b4 = new Button("40");
                                        b4.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                                sum[0] += 40;
                                                total.setText(Integer.toString(sum[0]));
                                            }
                                        });

                                        Button b5 = new Button("1100");
                                        b5.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                                sum[0] += 1100;
                                                total.setText(Integer.toString(sum[0]));
                                            }
                                        });

                                        Button pay = new Button("Pay");
                                        pay.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                                Label res = new Label();
                                                if (sum[0] >= 50) {
                                                    a.purchase(sum[0]);
                                                    res.setText("Balance after shopping: " + a.getBalance());
                                                    field.getChildren().addAll(res);
                                                    sum[0] = 0;
                                                    total.setText(Integer.toString(sum[0]));
                                                } else {
                                                    res.setText("The selected items cost less than $50");
                                                    field.getChildren().addAll(res);
                                                }
                                            }
                                        }
                                        );

                                        Button back = new Button("Back");

                                        back.addEventHandler(ActionEvent.ACTION,
                                                new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event
                                            ) {
                                                stage.setScene(scene3);
                                            }
                                        }
                                        );

                                        field.getChildren()
                                                .addAll(lab, ob1, b1, ob2, b2, ob3, b3, ob4, b4, ob5, b5, tot, total);
                                        onlineShoppingPane.getChildren()
                                                .addAll(field);
                                        mainPane.getChildren()
                                                .addAll(onlineShoppingPane, pay, back);
                                        Scene scene11 = new Scene(mainPane);

                                        stage.setScene(scene11);

                                        stage.show();
                                    }
                                }
                                );

                                Button balance = new Button("Balance: ");

                                balance.setPrefSize(
                                        200, 100);
                                Label balres = new Label();

                                balance.addEventHandler(ActionEvent.ACTION,
                                        new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event
                                    ) {
                                        VBox balancePane = new VBox(10);
                                        balancePane.setAlignment(Pos.CENTER);

                                        balres.setText(a.getUsername() + " Balance: " + a.getBalance() + ", Level: " + a.getLevel().getClass().getSimpleName());

                                        Button ba = new Button("Back");
                                        ba.setPrefSize(200, 50);
                                        ba.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                                stage.setScene(scene3);
                                            }
                                        });

                                        balancePane.setMargin(balres, new Insets(30, 30, 30, 30));
                                        balancePane.setMargin(ba, new Insets(30, 30, 30, 30));
                                        balancePane.getChildren().addAll(balres, ba);
                                        Scene scene10 = new Scene(balancePane);

                                        stage.setTitle("Account");
                                        stage.setScene(scene10);
                                        stage.show();
                                    }
                                }
                                );

                                Button logout = new Button("Logout");

                                logout.setPrefSize(
                                        200, 50);
                                logout.addEventHandler(ActionEvent.ACTION,
                                        new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event
                                    ) {
                                        User.logout();
                                        stage.setScene(scene);
                                    }
                                }
                                );
                                acc.setText(user.getText());
                                pane.setMargin(OS,
                                        new Insets(30, 30, 30, 30));
                                pane.getChildren()
                                        .addAll(acc, deposit, withdraw, OS, balance, logout);

                                stage.setTitle(
                                        "Account");
                                stage.setScene(scene3);

                                stage.show();
                            } else {
                                System.out.println("Try again");
                            }
                        } catch (NullPointerException e) {
                            System.out.println("Try again");
                        }
                    }
                }
                );

                Button cancel = new Button("Cancel");

                cancel.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent event) {
                        stage.setScene(scene);
                    }
                });

                pane.getChildren().addAll(userlab, user, passlab, pass, login, cancel);
                Scene scene2 = new Scene(pane);

                stage.setTitle("Account");
                stage.setScene(scene2);
                stage.show();
            }
        });

        Button quit = new Button("Quit");
        quit.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        pane.getChildren().addAll(bankmanager, cust);
        primary.getChildren().addAll(pane, quit);
        stage.setTitle("Account");
        stage.setScene(scene);
        stage.setMinHeight(250);
        stage.setMinWidth(500);
        stage.setMaxHeight(1500);
        stage.setMaxWidth(1000);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
