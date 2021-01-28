package Helpers;

import Servlets.Register;
import Servlets.uploadFile;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FileUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author shreyas
 */
public class User extends HttpServlet {

    private File file;
    public String directory = null;
    private String mEmail;
    private String mName;
    private String filePath = "/home/shreyas/StorageSpace/";

    public boolean registerUser(String name, String email, String password) throws SQLException {

        Statement stmt = Register.getConnection().createStatement();
        String sql = "INSERT INTO users(name, email, password)"
                + "VALUES ('" + name + "','" + email + "','" + password + "')";
        stmt.executeUpdate(sql);
        return true;

    }

    public boolean makeDir(String email) {
        try {
            File file = new File(filePath + email);
            if (file.mkdir()) {
                directory = filePath + email;
                System.out.println(directory);
                return true;
            }
            return false;
        } catch (Exception e) {

            System.out.println(e);
            return false;
        }
    }

    public String getSize(String file) {

        File f = new File(directory + "/" + file);

        String size = FileUtils.byteCountToDisplaySize(f.length());
        System.out.println(size);
        return size;

    }

    public long getSpace() {

        long size = FileUtils.sizeOfDirectory(new File(directory));
        long mb = size / (1024 * 1024);
        long gb = mb / 1024;
        return gb;
    }

    public String handleFile(HttpServletRequest request) {
        if (getSpace() < 10) {
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // maximum size that will be stored in memory
            factory.setSizeThreshold(uploadFile.maxMemSize);

            // Location to save data that is larger than maxMemSize.
            factory.setRepository(new File("c:\\temp"));

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // maximum file size to be uploaded.
            upload.setSizeMax(uploadFile.maxFileSize);

            try {
                // Parse the request to get file items.
                List fileItems = upload.parseRequest(request);

                // Process the uploaded file items
                Iterator i = fileItems.iterator();

                while (i.hasNext()) {
                    FileItem fi = (FileItem) i.next();
                    if (!fi.isFormField()) {
                        // Get the uploaded file parameters
                        String fieldName = fi.getFieldName();
                        String fileName = fi.getName();
                        String contentType = fi.getContentType();
                        boolean isInMemory = fi.isInMemory();
                        long sizeInBytes = fi.getSize();

                        // Write the file
                        if (fileName.lastIndexOf("\\") >= 0) {
                            file = new File(directory + "/" + fileName.substring(fileName.lastIndexOf("\\")));
                        } else {
                            file = new File(directory + "/" + fileName.substring(fileName.lastIndexOf("\\") + 1));
                        }
                        fi.write(file);
                        System.out.println("Uploaded Filename: " + fileName + "<br>");

                        long fileSize = FileUtils.sizeOf(file);

                    }
                }

                return "success";
            } catch (FileExistsException e) {
                return "That File Already Exists!";
            } catch (Exception ex) {
                System.out.println(ex);
                return "An Unexpected Error Occured! - " + ex;
            }
        }
        else{
            return "Your 10 GB Quota has been exceeded!";
        }

    }

    public String[] getContent(String dir) {

        System.out.println(directory + dir);
        File file = new File(directory + dir);
        String[] fileList = file.list();

        return fileList;

    }

    public boolean validateEmail(String email) throws SQLException {

        PreparedStatement preparedStatement = Register.getConnection().prepareStatement("select * from users where email = ?");
        preparedStatement.setString(1, email);

        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return false;
        }
        preparedStatement.close();
        return true;
    }

    public boolean validate(String email, String password) throws SQLException {

        PreparedStatement preparedStatement = Servlets.Login.getConnection().prepareStatement("select * from users where email = ? and password = ? ");
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);

        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            mName = rs.getString("name");
            mEmail = rs.getString("email");
            String rsPassword = rs.getString("password");
            directory = filePath + email;
            return true;
        }
        preparedStatement.close();
        return false;

    }

    public String getFileLocation(String name) {
        return directory + "/" + name;
    }

    public boolean delete(String name) {
        File file = new File(directory + "/" + name);

        if (file.delete()) {
            System.out.println("File deleted successfully");
            return true;
        } else {
            System.out.println("Failed to delete the file");
            return false;
        }
    }

    public boolean rename(String file, String nname) throws IOException {
        String String1 = directory + "/" + file;
        String String2 = directory + "/" + nname;

        String1 = String1.replaceAll("\\s", "");
        String2 = String2.replaceAll("\\s", "");

        File oldName
                = new File(String1);
        File newName
                = new File(String2);

        if (oldName.renameTo(newName)) {
            System.out.println("Renamed successfully");
            return true;
        } else {
            System.out.println("Error");
            return false;
        }
    }
}
