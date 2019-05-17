package cn.edu.cqvie.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 创建一个Spring定义FactoryBean
 *
 * @author ZHENG SHAOHONG
 */
public class ColorFactoryBean implements FactoryBean<Color> {

	//private Color
    /**
     * 返回一个Color对象，这个对象会添加到容器中
     *
     * @return
     * @throws Exception
     */
    @Override
    public Color getObject() throws Exception {
//        return new Color();
    	return new Color(null);
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    /**
     * 控制是否是单例
     *
     * @return
     */
    @Override
    public boolean isSingleton() {
        return false;
    }
}
