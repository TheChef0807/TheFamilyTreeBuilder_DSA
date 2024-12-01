package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class FamilyTreeBuilder extends Application {

    static class Node {
        String name;
        Node left, right;

        Node(String name) {
            this.name = name;
            this.left = this.right = null;
        }
    }

    static class FamilyTree {
        Node root;
        FamilyTreeBuilder parent;

        FamilyTree(FamilyTreeBuilder parent) {
            this.parent = parent;
        }

        public void addMember(String parentName, String childName, boolean isLeft) {
            if (root == null) {
                root = new Node(parentName);
                if (isLeft) root.left = new Node(childName);
                else root.right = new Node(childName);
            } else {
                Node parentNode = findNode(root, parentName);
                if (parentNode != null) {
                    if (isLeft && parentNode.left == null) parentNode.left = new Node(childName);
                    else if (!isLeft && parentNode.right == null) parentNode.right = new Node(childName);
                    else parent.showAlert("Error", "Child already exists on this side!");
                } else {
                    parent.showAlert("Error", "Parent not found!");
                }
            }
        }

        private Node findNode(Node node, String name) {
            if (node == null) return null;
            if (node.name.equals(name)) return node;
            Node leftResult = findNode(node.left, name);
            if (leftResult != null) return leftResult;
            return findNode(node.right, name);
        }

        public List<String> traverse(Node node, String order) {
            List<String> result = new ArrayList<>();
            traverseHelper(node, order, result);
            return result;
        }

        private void traverseHelper(Node node, String order, List<String> result) {
            if (node == null) return;
            if (order.equals("pre")) result.add(node.name);
            traverseHelper(node.left, order, result);
            if (order.equals("in")) result.add(node.name);
            traverseHelper(node.right, order, result);
        }
    }

    private FamilyTree tree = new FamilyTree(this);  // Pass the parent reference
    private TextArea outputArea;
    private VBox treeView;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Family Tree Builder");

        // Header
        Text header = new Text("Interactive Family Tree Builder");
        header.setFont(Font.font("Arial", 24));
        header.setStyle("-fx-fill: white;");

        // Input fields
        TextField parentNameField = new TextField();
        parentNameField.setPromptText("Parent Name");

        TextField childNameField = new TextField();
        childNameField.setPromptText("Child Name");

        ComboBox<String> isLeftField = new ComboBox<>();
        isLeftField.getItems().addAll("Left", "Right");
        isLeftField.setValue("Left");

        Button addButton = new Button("Add Member");
        addButton.setOnAction(e -> {
            String parentName = parentNameField.getText().trim();
            String childName = childNameField.getText().trim();
            boolean isLeft = isLeftField.getValue().equals("Left");
            if (!parentName.isEmpty() && !childName.isEmpty()) {
                tree.addMember(parentName, childName, isLeft);
                showAlert("Success", "Member added successfully!");
                parentNameField.clear();
                childNameField.clear();
                updateTreeView();
            } else {
                showAlert("Error", "Please fill in all fields!");
            }
        });

        // Buttons for traversal
        Button preOrderButton = new Button("Display Pre-Order");
        preOrderButton.setOnAction(e -> {
            List<String> result = tree.traverse(tree.root, "pre");
            outputArea.setText("Pre-Order Traversal:\n" + String.join(" -> ", result));
        });

        Button inOrderButton = new Button("Display In-Order");
        inOrderButton.setOnAction(e -> {
            List<String> result = tree.traverse(tree.root, "in");
            outputArea.setText("In-Order Traversal:\n" + String.join(" -> ", result));
        });

        // Output area
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPromptText("Output will be displayed here...");
        outputArea.setStyle("-fx-control-inner-background: #dfe6d8;");

        // Tree view
        treeView = new VBox();
        treeView.setPadding(new Insets(10));
        treeView.setStyle("-fx-background-color: #4a5c41; -fx-border-color: white; -fx-border-radius: 5;");

        // Layout setup
        GridPane formGrid = new GridPane();
        formGrid.setPadding(new Insets(10));
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.add(new Label("Parent Name:"), 0, 0);
        formGrid.add(parentNameField, 1, 0);
        formGrid.add(new Label("Child Name:"), 0, 1);
        formGrid.add(childNameField, 1, 1);
        formGrid.add(new Label("Position:"), 0, 2);
        formGrid.add(isLeftField, 1, 2);
        formGrid.add(addButton, 1, 3);

        VBox buttonBox = new VBox(10, preOrderButton, inOrderButton);
        buttonBox.setPadding(new Insets(10));

        VBox layout = new VBox(10, header, formGrid, buttonBox, new Label("Tree View:"), treeView, new Label("Output:"), outputArea);
        layout.setPadding(new Insets(10));
        layout.setStyle("-fx-background-color: olivegreen; -fx-font-family: Arial;");

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateTreeView() {
        treeView.getChildren().clear();
        if (tree.root != null) {
            buildTreeView(tree.root, treeView, 0);
        }
    }

    private void buildTreeView(Node node, VBox container, int depth) {
        if (node == null) return;
        HBox nodeBox = new HBox();
        nodeBox.setPadding(new Insets(5, 0, 5, depth * 20));
        nodeBox.getChildren().add(new Label(node.name));
        container.getChildren().add(nodeBox);

        buildTreeView(node.left, container, depth + 1);
        buildTreeView(node.right, container, depth + 1);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// Andrew Jacob M. lomanta Role:Leader 09163959320 cobielomanta@gmail.com
// John Kairos M. Amoguis Role:Script Writer and  Editor 09292454052 jkamoguis04@gmail.com
