package com.jsj141.osport.util;

import com.iw86.base.FileUtil;
import com.iw86.base.StringUtil;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 工具类，用来生成数据库对应的domain、dao和xml文件，使用在测试用例中
 */
public class AutoGenerator {
    private static String[] intType = new String[]{"int", "tinyint", "smallint"};
    private DataSource dataSource;

    public AutoGenerator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void create(String dbTable, String dbBean, String packagePrefix, String mapDir) {
        String mainPath = System.getProperty("user.dir").replace('\\', '/') + "/src/main/";
        String srcPath = mainPath + "/java/";
        String mapPath = (mapDir == null ? srcPath : mainPath + mapDir) + "/";
        String beanName = packagePrefix + ".domain." + setPascalCase(dbBean);
        this.createBean(dbTable, dbBean, srcPath, packagePrefix + ".domain");
        this.createDao(dbBean, srcPath, packagePrefix + ".dao");
        this.createSqlMap(dbTable, dbBean, mapPath, beanName, StringUtil.str(new Object[]{packagePrefix, ".dao.", setPascalCase(dbBean), "Dao"}));
    }

    public void create(String dbTable, String packagePrefix) {
        this.create(dbTable, dbTable, packagePrefix, (String) null);
    }

    public void createBean(String dbTable, String dbBean, String javaPath, String packageName) {
        try {
            JdbcTemplate jdbc = new JdbcTemplate(this.dataSource);
            List<Map<String, Object>> list = jdbc.queryForList("desc " + dbTable);
            if (list != null) {
                String beanPath = javaPath + packageName.replaceAll("\\.", "/");
                String bean = beanPath + "/" + setPascalCase(dbBean) + ".java";
                System.out.println(bean + "开始生成。");
                FileUtil.mkDirs(beanPath);
                StringBuilder buf = new StringBuilder();
                buf.append("package ").append(packageName).append(";\n\n");
                buf.append("import org.apache.commons.lang3.builder.ToStringBuilder;\n");
                buf.append("import org.apache.commons.lang3.builder.ToStringStyle;\n\n");
                buf.append("import java.io.Serializable;\n\n");
                buf.append("/**\n").append(" * @author AutoGenerator\n").append(" * \n").append(" */\n");
                buf.append("public class ").append(setPascalCase(dbBean)).append(" implements Serializable {\n\n");
                StringBuilder fun = new StringBuilder();
                Iterator var12 = list.iterator();

                while (var12.hasNext()) {
                    Map<String, Object> row = (Map) var12.next();
                    String field = row.get("Field").toString();
                    field = setCamelCase(field);

                    String type = this.getType((String) row.get("Type"));
                    buf.append("    private ").append(type).append(" ").append(field).append(";\n\n");
                    fun.append("    public ").append(type).append(" get");
                    fun.append(setFirstCharUpcase(field)).append("() {\n");
                    fun.append("        return ").append(field).append(";\n    }\n\n");
                    fun.append("    public void set").append(setFirstCharUpcase(field)).append("(");
                    fun.append(type).append(" ").append(field).append("){\n");
                    fun.append("        this.").append(field).append(" = ").append(field).append(";\n    }\n\n");
                }

                fun.append("    @Override\n");
                fun.append("    public String toString() {\n");
                fun.append("        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);\n");
                fun.append("    }\n\n");
                buf.append(fun).append("}");
                FileUtil.writIn(bean, buf.toString(), "UTF-8");
                System.out.println(bean + "生成完毕。");
            }
        } catch (Exception var15) {
            var15.printStackTrace();
        }

    }

    public void createDao(String dbBean, String javaPath, String packageName) {
        String daoPath = javaPath;

        try {
            daoPath = daoPath + packageName.replaceAll("\\.", "/");
            String bean = daoPath + "/" + setPascalCase(dbBean) + "Dao.java";
            System.out.println(bean + "开始生成。");
            FileUtil.mkDirs(daoPath);
            StringBuilder ibuf = new StringBuilder();
            ibuf.append("package ").append(packageName).append(";\n\n");
            ibuf.append("import org.springframework.stereotype.Repository;\n");
            ibuf.append("/**\n").append(" * @author AutoGenerator\n").append(" * \n").append(" */\n").append("@Repository\n");
            ibuf.append("public interface ").append(setPascalCase(dbBean)).append("Dao extends BaseDao {\n\n");
            ibuf.append("}");
            FileUtil.writIn(bean, ibuf.toString(), "UTF-8");
            System.out.println(bean + "生成完毕。");
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    public void createSqlMap(String dbTable, String dbBean, String mapPath, String beanName, String daoName) {
        try {
            JdbcTemplate jdbc = new JdbcTemplate(this.dataSource);
            List<Map<String, Object>> list = jdbc.queryForList("desc " + dbTable);
            if (list != null) {
                String xml = mapPath + setPascalCase(dbBean) + "Dao.xml";
                System.out.println(xml + "开始生成。");
                String keyName = ((Map) list.get(0)).get("Field").toString();
                String camelKey = setCamelCase(keyName);
                StringBuilder buf = new StringBuilder();
                buf.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
                buf.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n\n");
                buf.append("<mapper namespace=\"").append(daoName).append("\">\n\n");
                buf.append("    <sql id=\"dynamicWhere\">\n");
                buf.append("        <where>\n");
                buf.append("            <if test=\"_parameter.containsKey('").append(camelKey).append("s') and ").append(camelKey).append("s != null and ").append(camelKey).append("s != ''\">\n");
                buf.append("                and ").append(keyName).append(" in (${").append(camelKey).append("s})\n");
                buf.append("            </if>\n");
                buf.append("        </where>\n");
                buf.append("    </sql>\n\n");
                buf.append("    <select id=\"select\" resultType=\"").append(beanName).append("\">\n");
                buf.append("        select * from ").append(dbTable).append(" where ").append(keyName).append("=#{").append(camelKey).append("}\n");
                buf.append("    </select>\n\n");
                buf.append("    <select id=\"count\" resultType=\"int\">\n");
                buf.append("        select count(*) from ").append(dbTable).append("\n");
                buf.append("        <include refid=\"dynamicWhere\" />\n");
                buf.append("    </select>\n\n");
                buf.append("    <select id=\"list\" resultType=\"").append(beanName).append("\">\n");
                buf.append("        select * from ").append(dbTable).append("\n");
                buf.append("        <include refid=\"dynamicWhere\" />\n");
                buf.append("        <if test=\"_parameter.containsKey('order') and order!=null\">\n");
                buf.append("            order by ${order}\n");
                buf.append("        </if>\n");
                buf.append("        limit #{start},#{size}\n");
                buf.append("    </select>\n\n");
                buf.append("    <insert id=\"insert\" parameterType=\"").append(beanName).append("\">\n");
                buf.append("        insert into ").append(dbTable).append(" (");
                StringBuilder insert = new StringBuilder();
                StringBuilder update = new StringBuilder();
                int i = 0;

                for (Iterator var15 = list.iterator(); var15.hasNext(); ++i) {
                    Map<String, Object> row = (Map) var15.next();
                    if (i != 0) {
                        String field = row.get("Field").toString();
                        String camelField = setCamelCase(field);
                        String type = this.getType((String) row.get("Type"));
                        if (i != 1) {
                            buf.append(",");
                            insert.append(",");
                        }

                        buf.append(field);
                        insert.append("#{").append(camelField).append("}");
                        if ("String".equals(type)) {
                            update.append("            <if test=\"").append(camelField).append(" != null\">\n");
                        } else {
                            update.append("            <if test=\"").append(camelField).append(" != 0\">\n");
                        }

                        update.append("                ").append(field).append("=").append("#{").append(camelField).append("},\n");
                        update.append("            </if>\n");
                    }
                }

                buf.append(")\n        values (").append(insert.toString()).append(")\n");
                buf.append("    </insert>\n\n");
                buf.append("    <update id=\"update\" parameterType=\"").append(beanName).append("\">\n");
                buf.append("        update ").append(dbTable).append(" \n");
                buf.append("        <set>\n");
                buf.append(update);
                buf.append("        </set>\n");
                buf.append("        where ").append(keyName).append("=#{").append(camelKey).append("}\n");
                buf.append("    </update>\n\n");
                buf.append("    <update id=\"delete\">\n");
                buf.append("        delete from ").append(dbTable).append(" where ").append(keyName).append("=#{").append(camelKey).append("}\n");
                buf.append("    </update>\n\n");
                buf.append("</mapper>");
                FileUtil.writIn(xml, buf.toString());
                System.out.println(xml + "生成完毕。");
            }
        } catch (Exception var18) {
            var18.printStackTrace();
        }

    }

    private String getType(String str) {
        if (StringUtil.isEmpty(str)) {
            return "";
        } else {
            str = str.toLowerCase();
            int i = 0;

            for (int n = intType.length; i < n; ++i) {
                if (str.startsWith(intType[i])) {
                    return "int";
                }
            }

            return str.startsWith("bigint") ? "long" : (!str.startsWith("float") && !str.startsWith("decimal") ? (str.startsWith("double") ? "double" : "String") : "float");
        }
    }

    private static String setFirstCharUpcase(String s) {
        if (s != null && s.length() >= 1) {
            char[] c = s.toCharArray();
            if (c.length > 0 && c[0] >= 97 && c[0] <= 122) {
                c[0] = (char) ((short) c[0] - 32);
            }

            return String.valueOf(c);
        } else {
            return s;
        }
    }

    private static String setPascalCase(String str) {
        String[] arr = str.split("_");
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(setFirstCharUpcase(s));
        }
        return sb.toString();
    }

    private static String setCamelCase(String str) {
        String[] arr = str.split("_");
        StringBuilder sb = new StringBuilder();
        sb.append(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            sb.append(setFirstCharUpcase(arr[i]));
        }
        return sb.toString();
    }


}
