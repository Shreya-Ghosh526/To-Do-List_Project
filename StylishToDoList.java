import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class StylishToDoList extends JFrame {
    private DefaultListModel<String> taskModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JButton addButton, deleteButton;

    public StylishToDoList() {
        setTitle("🌸 To-Do List");
        setSize(450, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(250, 250, 250));
        setLayout(new BorderLayout(15, 15));

        // Fonts
        Font mainFont = new Font("Segoe UI", Font.PLAIN, 16);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 24);

        // Header
        JLabel titleLabel = new JLabel("My To-Do List 📝", JLabel.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setBorder(new EmptyBorder(15, 10, 5, 10));
        titleLabel.setForeground(new Color(60, 60, 60));
        add(titleLabel, BorderLayout.NORTH);

        // Task List
        taskModel = new DefaultListModel<>();
        taskList = new JList<>(taskModel);
        taskList.setFont(mainFont);
        taskList.setFixedCellHeight(35);
        taskList.setSelectionBackground(new Color(100, 149, 237));
        taskList.setBorder(new EmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel
        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.setBorder(new EmptyBorder(10, 15, 15, 15));
        inputPanel.setBackground(new Color(250, 250, 250));

        taskField = new JTextField();
        taskField.setFont(mainFont);
        taskField.setBorder(BorderFactory.createTitledBorder("New Task"));
        inputPanel.add(taskField, BorderLayout.CENTER);

        // Buttons
        addButton = createStyledButton("Add");
        deleteButton = createStyledButton("Delete");

        addButton.addActionListener(e -> addTask());
        deleteButton.addActionListener(e -> deleteTask());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.setBackground(new Color(250, 250, 250));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        inputPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(inputPanel, BorderLayout.SOUTH);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(new RoundedBorder(10));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(65, 105, 225));
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(100, 149, 237));
            }
        });
        return button;
    }

    private void addTask() {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            taskModel.addElement("🗂 " + task);
            taskField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Task cannot be empty!", "Oops!", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteTask() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            taskModel.remove(index);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to delete.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Rounded border for buttons
    private static class RoundedBorder extends AbstractBorder {
        private final int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(new Color(180, 180, 180));
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 2, radius + 2, radius + 2, radius + 2);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StylishToDoList().setVisible(true);
        });
    }
}
