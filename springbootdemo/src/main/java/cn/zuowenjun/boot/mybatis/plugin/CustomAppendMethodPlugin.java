package cn.zuowenjun.boot.mybatis.plugin;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

/*
 * 自定义通用可添加生成自定义方法插件类
 * Author:zuowenjun
 * Date:2019-1-29
 */
public abstract class CustomAppendMethodPlugin<TE extends AbstractXmlElementGenerator,TM extends AbstractJavaMapperMethodGenerator>  
extends PluginAdapter {

	protected final Class<TE> teClass;
	protected final Class<TM> tmClass;
	
	
	@SuppressWarnings("unchecked")
	public CustomAppendMethodPlugin(Class<? extends AbstractXmlElementGenerator> teClass,
			Class<? extends AbstractJavaMapperMethodGenerator> tmClass) {
		this.teClass=(Class<TE>) teClass;
		this.tmClass=(Class<TM>) tmClass;
	}
	
    @Override
    public boolean sqlMapDocumentGenerated(Document document,
            IntrospectedTable introspectedTable) {
    		
			try {
				AbstractXmlElementGenerator elementGenerator = teClass.newInstance();
				elementGenerator.setContext(context);
				elementGenerator.setIntrospectedTable(introspectedTable);
				elementGenerator.addElements(document.getRootElement());
				
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		return super.sqlMapDocumentGenerated(document, introspectedTable);
    }
    
    @Override
    public boolean clientGenerated(Interface interfaze,
            TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
    	
		try {
			AbstractJavaMapperMethodGenerator methodGenerator = tmClass.newInstance();
			methodGenerator.setContext(context);
			methodGenerator.setIntrospectedTable(introspectedTable);
			methodGenerator.addInterfaceElements(interfaze);
			
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

	@Override
	public boolean validate(List<String> warnings) {
		// TODO Auto-generated method stub
		return true;
	}

}
