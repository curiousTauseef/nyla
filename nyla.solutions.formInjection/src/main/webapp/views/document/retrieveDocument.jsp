<%@ page import="
    java.util.*,
    com.bms.informatics.gcsm.document.model.*,
    java.io.PrintWriter,
    java.io.*"%>
<%
  
  ServletOutputStream out2 = response.getOutputStream();
   
 response.setHeader("Content-disposition","inline;filename=test2.pdf" );
 response.setHeader("Content-Type", "application/pdf");
  //response.setHeader("Pragma","public" );  
  //response.setHeader("Cache-Control","max-age=0" );    
// response.setContentType("application/pdf"); 
  //out2.flush();
  DocumentDTO dto = (DocumentDTO)session.getAttribute("document");
  ByteArrayInputStream bis = new ByteArrayInputStream(dto.getData());
  response.setContentLength(dto.getData().length);
  BufferedOutputStream bos = new BufferedOutputStream(out2);
  byte[] buff = new byte[1];
  int bytesRead = 0;
  while(-1 != (bytesRead = bis.read())){
	  bos.write(bytesRead);
  }
  out2.flush();
  out2.close();
  bis.close();
  bos.close();

%>
