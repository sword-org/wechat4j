/**
 * 
 */
package org.sword.wechat4j.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.sword.wechat4j.request.WechatRequest;
import org.sword.wechat4j.response.WechatResponse;

import sun.util.logging.resources.logging;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import com.sun.org.apache.xml.internal.serializer.ToXMLSAXHandler;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

/**
 * @author ChengNing
 * @date   2014年12月7日
 */
public class JaxbParser {
	
	private static Logger logger = Logger.getLogger(JaxbParser.class);
	
	private Class clazz;
	private String[] cdataNode;
	
	public JaxbParser(Class clazz){
		this.clazz = clazz;
	}
	
//	public String[] getCdataNode() {
//		return cdataNode;
//	}

	public void setCdataNode(String[] cdataNode) {
		this.cdataNode = cdataNode;
	}


	public String toXML(Object obj){
		String result = null;
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.setProperty(Marshaller.JAXB_FRAGMENT, true);// 去掉报文头
//			
//		    OutputStream os = new ByteOutputStream();
//			XMLSerializer serializer = getXMLSerializer();
//			m.marshal(obj, serializer.asContentHandler());

//	        serializer.setOutputByteStream(os);
//			m.marshal(obj, os);
//			result = os.toString();		
			
			
			
			
//			
//			
//			context = JAXBContext.newInstance(WechatResponse.class);
//			Marshaller m = context.createMarshaller();
//			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//			m.setProperty(Marshaller.JAXB_FRAGMENT, true);// 去掉报文头

		    OutputStream os = new ByteOutputStream();
			StringWriter writer = new StringWriter();
			XMLSerializer serializer = getXMLSerializer(os);
			m.marshal(obj, serializer.asContentHandler());

//	        serializer.setOutputCharStream(writer);
//			result = writer.toString();
			result = os.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("response text:" + result);
		return result;
	}
	

	
	public Object toObj(InputStream is){
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(clazz);
			Unmarshaller um = context.createUnmarshaller();
//			um.setProperty(Marshaller.JAXB_ENCODING,"UTF-8");
			Object obj = um.unmarshal(is);
			return obj;
		} catch (Exception e) {
			logger.error("post data parse error");
			e.printStackTrace();
		}
		return null;
	}
	
	public Object toObj(String xmlStr){
		InputStream is = new ByteArrayInputStream(xmlStr.getBytes());
		return toObj(is);
	}
	
	private XMLSerializer getXMLSerializer(OutputStream os) {
        OutputFormat of = new OutputFormat();
        formatCDataTag();
        of.setCDataElements(cdataNode);   
        of.setPreserveSpace(true);
        of.setIndenting(true);
        of.setOmitXMLDeclaration(true);
//        of.setVersion("");
//        StringWriter writer = new StringWriter();
        // create the serializer
        XMLSerializer serializer = new XMLSerializer(of);
        serializer.setOutputByteStream(os);
        
        return serializer;
    }
	
	private void formatCDataTag(){
		for(int i=0;i<cdataNode.length;i++){
			cdataNode[i] = "^" + cdataNode[i];
		}
	}
}
