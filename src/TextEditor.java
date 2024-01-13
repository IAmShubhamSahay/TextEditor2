import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
// Implement method for action listener class
public class TextEditor implements ActionListener {

    JFrame frame;
    // Declaring properties of TextEditor
    JMenuBar menuBar;

    // initiallise jmenu class
    JMenu file,edit;

    //File Menu items
    JMenuItem newFile,openFile,saveFile;

    // Edit menu items
    JMenuItem cut,copy,paste,selectAll,close;

    JTextArea textArea;

    TextEditor(){
        // Intitialize a frame
        frame = new JFrame();


        // Initialize menubar
        menuBar = new JMenuBar();

        // initialize test area
        textArea = new JTextArea();

        // Initialize menus

        file = new JMenu("File");
        edit = new JMenu("Edit");

        //Initialize file menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");
        // Add action listeners to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // Add menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // Initialize edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        // Add action listeners to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // Adding to the Edit Menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // Add menus to menubar
        menuBar.add(file);
        menuBar.add(edit);


        // set Menubar to frame
        frame.setJMenuBar(menuBar);
//        // Add text area to frame
//        frame.add(textArea);


        // Create Content Pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        // Add text area to panel
        panel.add(textArea,BorderLayout.CENTER);
        // Create Scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // Add scroll pane to panel
        panel.add(scrollPane);
        //Add panel to frame
        frame.add(panel);

        // Set Dimensions of frame
        frame.setBounds(0,0,400,400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            // perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            //perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            // perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            // perform selectAll Operation
            textArea.selectAll();

        }
        if(actionEvent.getSource()==close){
            // perform close Operation
            System.exit(0);

        }
        if(actionEvent.getSource()==newFile){
            // perform newFile operation
            TextEditor newTextEditor = new TextEditor();
        }
        if(actionEvent.getSource()==openFile){
            // perform openFile operation

            // first step is Open a file Chooser
            JFileChooser fileChooser = new JFileChooser("C:"); //initial it will choose from c drive
            int chooseOption = fileChooser.showOpenDialog(null);
            //if we have clicked on Open button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                // Getting selected file
                File file = fileChooser.getSelectedFile();
                // Get the path of selected file
                String filePath = file.getPath();

                // for exception handling
                try{
                    //Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //Initialize Buffered Reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output="";
                    //Read contents of file line by line
                    while((intermediate=bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    //Set the output string to text area
                    textArea.setText(output);
                } catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }


        }
        if(actionEvent.getSource()==saveFile){
            // perform saveFile operation
            // Initialize file picker
            JFileChooser fileChooser = new JFileChooser("C:");
            // Get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //Check if we clicked on save button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                // create a new file with chosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    // Initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    // Initialize Buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    // Write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
       TextEditor textEditor = new TextEditor();

    }
}