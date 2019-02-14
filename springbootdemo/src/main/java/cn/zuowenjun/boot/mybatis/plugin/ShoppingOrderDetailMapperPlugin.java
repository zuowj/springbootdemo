package cn.zuowenjun.boot.mybatis.plugin;

import java.util.Set;
import java.util.TreeSet;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

/*
 * ref see https://www.cnblogs.com/se7end/p/9293755.html
 * Author:zuowenjun
 * Date:2019-1-29
 */
public class ShoppingOrderDetailMapperPlugin 
extends CustomAppendMethodPlugin<ShoppingOrderDetailXmlElementGenerator, AbstractJavaMapperMethodGenerator> {

	public ShoppingOrderDetailMapperPlugin() {
		super(ShoppingOrderDetailXmlElementGenerator.class,ShoppingOrderDetailJavaMapperMethodGenerator.class);
	}

}


class ShoppingOrderDetailXmlElementGenerator extends AbstractXmlElementGenerator{

	@Override
	public void addElements(XmlElement parentElement) {
		
		if(!introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime().equalsIgnoreCase("TA_TestShoppingOrderDetail")) {
			return;
		}

		TextElement selectText = new TextElement("select * from " + introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()
		+ " where shoppingOrderId=#{shoppingOrderId}");
		
		 XmlElement selectByOrderId = new XmlElement("select");
		 selectByOrderId.addAttribute(new Attribute("id", "selectByOrderId"));
		 selectByOrderId.addAttribute(new Attribute("resultMap", "BaseResultMap"));
		 selectByOrderId.addAttribute(new Attribute("parameterType", "int"));
		 selectByOrderId.addElement(selectText);
	     parentElement.addElement(selectByOrderId);
	     
			TextElement deleteText = new TextElement("delete from " + introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()
			+ " where shoppingOrderId=#{shoppingOrderId}");
			
			 XmlElement deleteByOrderId = new XmlElement("delete");
			 deleteByOrderId.addAttribute(new Attribute("id", "deleteByOrderId"));
			 deleteByOrderId.addAttribute(new Attribute("parameterType", "int"));
			 deleteByOrderId.addElement(deleteText);
		     parentElement.addElement(deleteByOrderId);
	}
	
}


class ShoppingOrderDetailJavaMapperMethodGenerator extends AbstractJavaMapperMethodGenerator{

	@Override
	public void addInterfaceElements(Interface interfaze) {
		
		if(!introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime().equalsIgnoreCase("TA_TestShoppingOrderDetail")) {
			return;
		}
		
		addInterfaceSelectByOrderId(interfaze);
		addInterfaceDeleteByOrderId(interfaze);
	}
	
	   private void addInterfaceSelectByOrderId(Interface interfaze) {
	        // 先创建import对象
	        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
	        // 添加Lsit的包
	        importedTypes.add(FullyQualifiedJavaType.getNewListInstance());
	        // 创建方法对象
	        Method method = new Method();
	        // 设置该方法为public
	        method.setVisibility(JavaVisibility.PUBLIC);
	        // 设置返回类型是List
	        FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();
	        FullyQualifiedJavaType listArgType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
	        returnType.addTypeArgument(listArgType);
	        
	        // 方法对象设置返回类型对象
	        method.setReturnType(returnType);
	        // 设置方法名称为我们在IntrospectedTable类中初始化的 “selectByOrderId”
	        method.setName("selectByOrderId");

	        // 设置参数类型是int类型
	        FullyQualifiedJavaType parameterType;
	        parameterType = FullyQualifiedJavaType.getIntInstance();
	        // import参数类型对象(基本类型其实可以不必引入包名)
	        //importedTypes.add(parameterType);
	        // 为方法添加参数，变量名称record
	        method.addParameter(new Parameter(parameterType, "shoppingOrderId")); //$NON-NLS-1$
	        //
	        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
	        if (context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable)) {
	            interfaze.addImportedTypes(importedTypes);
	            interfaze.addMethod(method);
	        }
	    }
	   
	   private void addInterfaceDeleteByOrderId(Interface interfaze) {
	        // 创建方法对象
	        Method method = new Method();
	        // 设置该方法为public
	        method.setVisibility(JavaVisibility.PUBLIC);
	        // 设置方法名称为我们在IntrospectedTable类中初始化的 “deleteByOrderId”
	        method.setName("deleteByOrderId");
	        // 设置参数类型是int类型
	        FullyQualifiedJavaType parameterType;
	        parameterType = FullyQualifiedJavaType.getIntInstance();
	        method.addParameter(new Parameter(parameterType, "shoppingOrderId")); //$NON-NLS-1$
	        
	        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
	        if (context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable)) {
	            interfaze.addMethod(method);
	        }
	        
	   }
	    
	
}