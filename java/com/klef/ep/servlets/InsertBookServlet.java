package com.klef.ep.servlets;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.klef.ep.models.Book;
import com.klef.ep.services.AdminService;


@WebServlet("/insertbook")
@MultipartConfig
public class InsertBookServlet extends HttpServlet 
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try
		{
			String btitle = request.getParameter("btitle");
			String bauthor = request.getParameter("bauthor");
			String bcategory = request.getParameter("bcategory");
			String bdesc = request.getParameter("bdesc");
			int byear = Integer.parseInt(request.getParameter("byear"));
			//Part file = request.getPart("bimage");
			String imageFileName = "";
			
			//System.out.println(file);
			String savePath = getServletContext().getRealPath("/images");
	        File fileSaveDir = new File(savePath);
	        if (!fileSaveDir.exists()) {
	            fileSaveDir.mkdir();
	        }

	        for (Part part : request.getParts()) {
	            String fileName = extractFileName(part);
	            if (fileName != null && !fileName.equals("")) {
	            	String uniqueFileName = generateUniqueFileName(fileName, savePath);
                    part.write(savePath + File.separator + uniqueFileName);
	                imageFileName = fileName;
	            }
	        }
			
			InitialContext context = new InitialContext();
			AdminService adminService = (AdminService) context.lookup("java:global/LMSPROJ/AdminServiceImpl!com.klef.ep.services.AdminService");
			
			/*InputStream inputStream = file.getInputStream();
	        Blob blob = new javax.sql.rowset.serial.SerialBlob(getBytesFromInputStream(inputStream));*/
			
			Book book = new Book();
			book.setTitle(btitle);
			book.setAuthor(bauthor);
			book.setCategory(bcategory);
			book.setDescription(bdesc);
			book.setYear(byear);
			book.setImagedata(imageFileName);
			
			System.out.print(book);
			
			adminService.AddBook(book);
			response.sendRedirect("addbooksuccess.jsp");
			
		}
		catch (Exception e) 
		{
			out.print(e);
		}
	}
	
	/*private byte[] getBytesFromInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }*/
	
	private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return null;
    }
	
	 private String generateUniqueFileName(String fileName, String savePath) {
	        File file = new File(savePath + File.separator + fileName);
	        String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
	        String extension = fileName.substring(fileName.lastIndexOf('.'));
	        int count = 0;
	        while (file.exists()) {
	            count++;
	            fileName = baseName + "_" + count + extension;
	            file = new File(savePath + File.separator + fileName);
	        }
	        return fileName;
	    }

}