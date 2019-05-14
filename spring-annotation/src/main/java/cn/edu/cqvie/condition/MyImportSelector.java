package cn.edu.cqvie.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {

    /**
     *
     * @param importingClassMetadata 当前类被标注的所有注解信息
     * @return 导入到组件中的全类名数组
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        // 不要返回null
        return new String[] {"cn.edu.cqvie.bean.Blue", "cn.edu.cqvie.bean.Yellow"};
    }
}
