package ru.mephi.vikingdemo.gui;

import ru.mephi.vikingdemo.service.VikingService;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

public class VikingDesktopFrame extends JFrame {

    private final VikingService vikingService;
    private final VikingTableModel tableModel = new VikingTableModel();

    public VikingDesktopFrame(VikingService vikingService) {
        this.vikingService = vikingService;

        setTitle("Viking Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1000, 420));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel header = new JLabel("Viking Demo", SwingConstants.CENTER);
        header.setFont(header.getFont().deriveFont(Font.BOLD, 18f));
        add(header, BorderLayout.NORTH);

        // Создаём таблицу
        JTable vikingTable = new JTable(tableModel);
        vikingTable.setRowHeight(28);
        JScrollPane scrollPane = new JScrollPane(vikingTable);
        add(scrollPane, BorderLayout.CENTER);

        // Кнопки
        JButton createButton = new JButton("Create random viking");
        createButton.addActionListener(event -> onCreateViking());

        JButton massButton = new JButton("Generate 10 Vikings");
        massButton.addActionListener(event -> onMassGenerate());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(createButton);
        bottomPanel.add(massButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Отложенная связка с сервисом и загрузка данных
        javax.swing.Timer timer = new javax.swing.Timer(500, e -> {
            System.out.println("Timer: connecting service to table");
            vikingService.setTableModel(tableModel);
            System.out.println("Timer: loading data");
            tableModel.setVikings(vikingService.findAll());
            // Принудительно просим таблицу перерисоваться
            vikingTable.revalidate();
            vikingTable.repaint();
            System.out.println("Timer: done");
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void onCreateViking() {
        vikingService.createRandomViking();
    }
    
    private void onMassGenerate() {
        vikingService.generateManyRandom(10);
    }
}