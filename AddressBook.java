/*
Blake Glover
CSC 20 section 1
12/7/2016
Prof. Wang
*/
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AddressBook implements ActionListener{
   static CardLayout contentPaneLayout;
   
   //Labels
   static JLabel mainTitle = new JLabel("<html><font size=6><b>Use the Buttons below to manage Contacts</b></html>", SwingConstants.CENTER);
   static JLabel createTitle = new JLabel("<html><font size=6><b>Create a New Address Book</b></html>", SwingConstants.CENTER);
   static JLabel loadTitle = new JLabel("<html><font size=6><b>Load Contacts from a File</b></html>", SwingConstants.CENTER);
   static JLabel searchTitle = new JLabel("<html><font size=6><b>Search Contacts by First Name or Last Name</b></html>", SwingConstants.CENTER);
   static JLabel sortTitle = new JLabel("<html><font size=6><b>Sort Contacts by:</b></html>", SwingConstants.CENTER);
   static JLabel listTitle = new JLabel("<html><font size=6><b>Contact List</b></html>", SwingConstants.CENTER);
   static JLabel saveTitle = new JLabel("<html><font size=6><b>Save Contacts to a File?</b></html>", SwingConstants.CENTER);
   static JLabel userN = new JLabel("User Name:");
      
   //TextFields
   static JTextField user = new JTextField("",20);
   static JTextField TopT1 = new JTextField("",15);
   static JTextField TopT2 = new JTextField("",15);
   static JTextField AddT1 = new JTextField("");
   static JTextField AddT2 = new JTextField("");
   static JTextField AddT3 = new JTextField("");
   static JTextField AddT4 = new JTextField("");
   static JTextField AddT5 = new JTextField("");
   static JTextField SearchT = new JTextField("", 30);
   static JTextField Loaduser = new JTextField("",20);
   
   // getting all panels created for top level cards
   static JPanel Top = new JPanel();
   static JPanel Create = new JPanel();
   static JPanel List = new JPanel();
   static JPanel Load = new JPanel();
   static JPanel Add = new JPanel();
   static JPanel Search = new JPanel();
   static JPanel Save = new JPanel();
   static JPanel Sort = new JPanel();
   
   static JPanel contentPane;
   
   //getting all the buttons made for actionlistener
   static JButton CreateTop = new JButton("Top Menu");
   static JButton LoadTop = new JButton("Top Menu");
   static JButton AddTop = new JButton("Top Menu");
   static JButton SearchTop = new JButton("Top Menu");
   static JButton SortTop = new JButton("Top Menu");
   static JButton ListTop = new JButton("Top Menu");
   static JButton CreateB = new JButton("Create a New Address book");
   static JButton CreateB2 = new JButton("Create");
   static JButton LoadB = new JButton("Load Contacts");
   static JButton LoadB2 = new JButton("Load");
   static JButton AddB = new JButton("Add New Contacts");
   static JButton AddB2 = new JButton("Add");
   static JButton SearchB = new JButton("Search Contacts");
   static JButton SearchB2 = new JButton("Search");
   static JButton SortB = new JButton("Sort Contacts");
   static JButton SortB2 = new JButton("Sort");
   static JButton ListB = new JButton("View/Delete Contacts");
   static JButton SaveB = new JButton("Backup Contacts");
   static JButton SaveB2 = new JButton("Yes");
   static JButton Exit = new JButton("Exit");
   static JButton Delete = new JButton("Delete");
   static JButton No = new JButton("No");
   static JRadioButton RButton1 = new JRadioButton("By First name");
   static JRadioButton RButton2 = new JRadioButton("By Last Name");
   static ButtonGroup SortG = new ButtonGroup();
   
   //stuff for scrollpane list
   static Object[] names = {"First Name", "Last Name", "Email Address", "Address", "Phone No."};
   static JTable abtable = new JTable(new DefaultTableModel(names,0));
   DefaultTableModel model = (DefaultTableModel) abtable.getModel();
   static JScrollPane ListS = new JScrollPane();
   static JScrollPane SearchS = new JScrollPane();
   static JTable tmp = new JTable(new DefaultTableModel(names,0));
   static DefaultTableModel Tmodel = (DefaultTableModel) tmp.getModel();

   //creating actionlistener response
   public void actionPerformed(ActionEvent e){
      Object source = e.getSource();
      if (source == CreateTop || source == LoadTop || source == AddTop || source == No ||
      source == SortTop) {contentPaneLayout.show(contentPane, "MainMenu");}
      if (source == SearchTop) {
         contentPaneLayout.show(contentPane, "MainMenu");
         SearchT.setText("");
      }
      if(source == ListTop) {
         TopT2.setText("" + abtable.getRowCount());
         contentPaneLayout.show(contentPane, "MainMenu");
      }
      if (source == CreateB) contentPaneLayout.show(contentPane, "Create");
      if (source == LoadB) contentPaneLayout.show(contentPane, "Load");
      if (source == AddB) contentPaneLayout.show(contentPane, "Add");
      if (source == SortB) contentPaneLayout.show(contentPane, "Sort");
      if (source == SearchB) {
         JScrollPane tmpS = new JScrollPane(tmp);
         SearchS.setViewport(tmpS.getViewport());
         contentPaneLayout.show(contentPane, "Search");
      }
      if (source == ListB) {
         JScrollPane tmp = new JScrollPane(abtable);
         ListS.setViewport(tmp.getViewport());
         contentPaneLayout.show(contentPane, "List");
      }
      if (source == Exit) System.exit(0);
      if (source == SaveB) contentPaneLayout.show(contentPane, "Save");
      if (source == CreateB2) {
         TopT1.setText(user.getText());
         user.setText("");
         TopT2.setText("0");
         abtable = new JTable(new DefaultTableModel(names,0));
         model = (DefaultTableModel) abtable.getModel();
         contentPaneLayout.show(contentPane, "MainMenu");
      }
      if(source == AddB2) {
         Object[] data = {AddT1.getText(),AddT2.getText(),AddT3.getText(),AddT4.getText(),AddT5.getText()};
         model.addRow(data);
         contentPaneLayout.show(contentPane, "MainMenu");
         TopT2.setText("" + abtable.getRowCount());
         AddT1.setText("");
         AddT2.setText("");
         AddT3.setText("");
         AddT4.setText("");
         AddT5.setText("");
      }
      if(source == Delete) {
         model.removeRow(abtable.getSelectedRow());
      }
      if(source == SaveB2) {
         StringBuffer sbTableData = new StringBuffer();
         for(int row = 0; row < abtable.getRowCount(); row ++){
            for(int column = 0; column < 5; column ++){
               sbTableData.append(abtable.getValueAt(row, column)).append(" ");
            }
         sbTableData.append("\n");
         }
         try{
         WriteFile dataT = new WriteFile(TopT1.getText());
         dataT.writeToFile(sbTableData.toString());
         } 
         catch (IOException ioe){}
         contentPaneLayout.show(contentPane, "MainMenu");
      }
      if(source == LoadB2) {
         String filename = Loaduser.getText();
         String Textdata = "";
         String sCurrentL = "";
         try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((sCurrentL = br.readLine())  != null) { 
               Textdata = Textdata + sCurrentL;
            }
         }
         catch (IOException ios) {
            ios.printStackTrace();
         }
         String[] arr = Textdata.split(" ");
         Object[][] to = new Object[arr.length/5][5];
         for(int i = 0; i < arr.length/5; i++){
            int y = 0;
            for(int j = (0 + i*5); j < (i+1)*5; j++) {
               to [i][y] = arr[j];
               y++;
            }
         }
         DefaultTableModel deftablemodel = new DefaultTableModel(to, names);
         abtable = new JTable(deftablemodel);
         model = (DefaultTableModel) abtable.getModel();
         TopT1.setText(Loaduser.getText());
         TopT2.setText("" + abtable.getRowCount());
         Loaduser.setText("");
         contentPaneLayout.show(contentPane, "MainMenu");
      }    
      if(source == SearchT) {
         tmp = new JTable(new DefaultTableModel(names,0));
         Tmodel = (DefaultTableModel) tmp.getModel();
         Object s = (Object)SearchT.getText();
         for(int row = 0; row < abtable.getRowCount(); row++) {
            if(abtable.getValueAt(row,0).equals(s)){
               Object[] ram = {abtable.getValueAt(row,0), abtable.getValueAt(row,1), abtable.getValueAt(row,2), abtable.getValueAt(row,3), abtable.getValueAt(row,4)};
               Tmodel.addRow(ram);
            }
            else if(abtable.getValueAt(row,1).equals(s)){
               Object[] ram = {abtable.getValueAt(row,0), abtable.getValueAt(row,1), abtable.getValueAt(row,2), abtable.getValueAt(row,3), abtable.getValueAt(row,4)};
               Tmodel.addRow(ram);
            }
         }
         JScrollPane tmpS = new JScrollPane(tmp);
         SearchS.setViewport(tmpS.getViewport());
      }
      if(source == SortB2) {
         int column;
         if(RButton1.isSelected()) {
            column = 0;
         } else {
            column = 1;
         }
         
         for(int i = 0; i < abtable.getRowCount(); i++) {
            String S = (String)abtable.getValueAt(i,column);
            for(int j = 0; j < abtable.getRowCount(); j++) {
               String S2 = (String)abtable.getValueAt(j,column);
               int compare = (S.compareToIgnoreCase(S2));
               if(compare < 0) {
                  Object[] tmpO = new Object[5];
                  for(int y = 0; y < 5; y++) {
                     tmpO[y] = abtable.getValueAt(i,y);
                     abtable.setValueAt(abtable.getValueAt(j,y),i,y);
                     abtable.setValueAt(tmpO[y],j,y);
                  }
               }
            }
         }
         contentPaneLayout.show(contentPane, "MainMenu");
      }
   }
   
   public static void main(String[] args) {
      //making frame and setting defaults
      JFrame frame = new JFrame("Address Book");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(600,230);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
      frame.setResizable(false);
      
      //Adding ActionListener to the buttons and some text fields
      ActionListener AL = new AddressBook();
      CreateB.addActionListener(AL);
      CreateB2.addActionListener(AL);
      CreateTop.addActionListener(AL);
      AddB.addActionListener(AL);
      AddB2.addActionListener(AL);
      AddTop.addActionListener(AL);
      LoadB.addActionListener(AL);
      LoadB2.addActionListener(AL);
      LoadTop.addActionListener(AL);
      SearchB.addActionListener(AL);
      SearchB2.addActionListener(AL);
      SearchTop.addActionListener(AL);
      SortB.addActionListener(AL);
      SortB2.addActionListener(AL);
      SortTop.addActionListener(AL);
      ListB.addActionListener(AL);
      ListTop.addActionListener(AL);
      Delete.addActionListener(AL);
      Exit.addActionListener(AL);
      SaveB.addActionListener(AL);
      SaveB2.addActionListener(AL);
      No.addActionListener(AL);
      SearchT.addActionListener(AL);
      
      JPanel List = new JPanel();
      
      //using card layout to get each instance of labels to switch between using action listener
      contentPane = (JPanel)frame.getContentPane();
      contentPane.setLayout(contentPaneLayout = new CardLayout());
      contentPane.add("MainMenu", Top);
      contentPane.add("List", List);
      contentPane.add("Create", Create);
      contentPane.add("Load", Load);
      contentPane.add("Add", Add);
      contentPane.add("Search", Search);
      contentPane.add("Save", Save);
      contentPane.add("Sort", Sort);
      contentPaneLayout.show(contentPane, "MainMenu");
      
      //top panel construction
      Top.setLayout(new BorderLayout());
      Top.add(mainTitle, BorderLayout.NORTH);
      
      JPanel Top1 = new JPanel();
      JLabel TopL = new JLabel("User Name:");
      TopT1.setEditable(false);
      TopT2.setEditable(false);
      JLabel contacts = new JLabel("Number of Contacts");
      Top1.add(TopL);
      Top1.add(TopT1);
      Top1.add(contacts);
      Top1.add(TopT2);
      Top.add(Top1);
      
      JPanel Top2 = new JPanel();
      Top.add(Top2, BorderLayout.SOUTH);
      Top2.setLayout(new GridLayout(2,4));
      Top2.add(CreateB);
      Top2.add(LoadB);
      Top2.add(AddB);
      Top2.add(SearchB);
      Top2.add(SortB);
      Top2.add(ListB);
      Top2.add(SaveB);
      Top2.add(Exit);
      
      //Create user, panel construction
      Create.setLayout(new GridLayout(3,1));
      Create.add(createTitle);
      
      JPanel Create2 = new JPanel();
      Create2.add(userN);
      Create2.add(user);
      Create.add(Create2);
      
      JPanel Create3 = new JPanel();
      Create3.add(CreateB2);
      Create3.add(CreateTop);
      Create.add(Create3);
      
      //Load user, panel construction
      Load.setLayout(new GridLayout(3,1));
      
      JLabel LoaduserN = new JLabel("User Name:");
      JPanel Load2 = new JPanel();
      Load2.add(LoaduserN);
      Load2.add(Loaduser);
      
      JPanel Load3 = new JPanel();
      Load3.add(LoadB2);
      Load3.add(LoadTop);
      
      Load.add(loadTitle);
      Load.add(Load2);
      Load.add(Load3);
      
      //Search, panel construction
      Search.setLayout(new BorderLayout());
      Search.add(searchTitle, BorderLayout.NORTH);
      
      JPanel Search2 = new JPanel();
      JLabel SearchL = new JLabel("Search String:");
      Search2.add(SearchL);
      Search2.add(SearchT);
      Search2.add(SearchTop);
      Search.add(Search2, BorderLayout.SOUTH);
      Search.add(SearchS);
      
      //List, panel construction
      List.setLayout(new BorderLayout());
      
      JPanel List2 = new JPanel();
      List2.add(Delete);
      List2.add(ListTop);
      
      List.add(listTitle, BorderLayout.NORTH);
      List.add(ListS);
      List.add(List2, BorderLayout.SOUTH);
      
      //Add, panel construction
      Add.setLayout(new GridLayout(6,1));
      JPanel Add1 = new JPanel(new GridLayout(1,2));
      JPanel Add2 = new JPanel(new GridLayout(1,2));
      JPanel Add3 = new JPanel(new GridLayout(1,2));
      JPanel Add4 = new JPanel(new GridLayout(1,2));
      JPanel Add5 = new JPanel(new GridLayout(1,2));
      JPanel Add6 = new JPanel();
      JLabel FirstN = new JLabel("First Name", SwingConstants.RIGHT);
      JLabel LastN = new JLabel("Last Name", SwingConstants.RIGHT);
      JLabel Email = new JLabel("Email Address", SwingConstants.RIGHT);
      JLabel Address = new JLabel("Address", SwingConstants.RIGHT);
      JLabel Phone = new JLabel("Phone No.", SwingConstants.RIGHT);
      
      Add1.add(FirstN);
      Add1.add(AddT1);
      Add2.add(LastN);
      Add2.add(AddT2);
      Add3.add(Email);
      Add3.add(AddT3);
      Add4.add(Address);
      Add4.add(AddT4);
      Add5.add(Phone);
      Add5.add(AddT5);
      Add6.add(AddB2);
      Add6.add(AddTop);
      
      Add.add(Add1);
      Add.add(Add2);
      Add.add(Add3);
      Add.add(Add4);
      Add.add(Add5);
      Add.add(Add6);
      
      //Save, panel construction
      Save.setLayout(new BorderLayout());
      JPanel Save2 = new JPanel();
      Save2.add(SaveB2);
      Save2.add(No);
      Save.add(saveTitle, BorderLayout.NORTH);
      Save.add(Save2, BorderLayout.SOUTH);
      
      //Sort, panel construction
      Sort.setLayout(new BorderLayout());
      RButton1.setSelected(true);
      JPanel Sort2 = new JPanel();
      JPanel Sort3 = new JPanel();
      Sort2.add(SortB2);
      Sort2.add(SortTop);
      SortG.add(RButton1);
      SortG.add(RButton2);
      Sort3.add(RButton1);
      Sort3.add(RButton2);
      Sort.add(sortTitle, BorderLayout.NORTH);
      Sort.add(Sort3, BorderLayout.CENTER);
      Sort.add(Sort2, BorderLayout.SOUTH);
   }
}
class WriteFile {
   private String path;
   private boolean append_to_file;
   
   public WriteFile(String file_path) {
      path = file_path;
   }
   public void writeToFile(String textLine) throws IOException {
      FileWriter write = new FileWriter(path, append_to_file);
      PrintWriter print_line = new PrintWriter(write);
      
      print_line.printf("%s" + " ", textLine);
      
      print_line.close();
   }
}  

