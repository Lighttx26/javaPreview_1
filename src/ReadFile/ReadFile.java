package ReadFile;

import connectDB.DatabaseManager;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReadFile {
    public ReadFile() {
        UpdateDatabase();
    }

    public void UpdateDatabase() {
        try {
            DatabaseManager dm = new DatabaseManager();
            Connection cn = dm.getConnection();

            String query = "Insert into hocvien values (?, ?, ?, ?, ?)";
            PreparedStatement stm = cn.prepareStatement(query);

            String filePath = "D:\\QUANG\\Hoc tap\\Java\\JavaPreview_2\\src\\asset\\input.txt";
            FileReader fr = new FileReader(new File(filePath));
            BufferedReader br = new BufferedReader(fr);
            String line;
            int count = 0;
            while((line = br.readLine()) != null) {
                count += 1;
                try {
                    // Retrieve id
                    String id = line.substring(0,10);
                    // Retrieve name
                    String name = line.substring(10, 60);
                    // Retrieve and convert to sql.Date
                    java.sql.Date date;
                    try {
                        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
                        Date tdate = dateformat.parse(line.substring(60, 70));
                        date =  new java.sql.Date(tdate.getTime());
                    }

                    catch (Exception date_exception) {
                        throw new Exception("Loi dinh dang ngay thang");
                    }

                    // Retrieve gender
                    String gender = line.substring(70,73);
                    // Retrieve grade
                    Double grade = Double.parseDouble(line.substring(73,77));

                    stm.setString(1, id);
                    stm.setString(2, name);
                    stm.setDate(3, date);
                    stm.setString(4, gender);
                    stm.setDouble(5, grade);

                    stm.executeUpdate();
                }

                catch (Exception e) {
                    e.printStackTrace();
                    FileWriter fw = new FileWriter(new File("D:\\QUANG\\Hoc tap\\Java\\JavaPreview_2\\src\\asset\\error.txt"));
                    BufferedWriter bw = new BufferedWriter(fw);

                    bw.write("Line: " + Integer.toString(count) + ", Error: " + e.getMessage());
                    bw.newLine();

                    bw.close();
                }
            }

            br.close();
            fr.close();
            cn.close();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
