package com.example;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonMainClass {

    public static <T extends Person & Creature> void display(T t) {
        System.out.println(t.toString());
    }


    public static void main(String[] args) {
        Gson gson = new Gson();

        //1.tojson
        String s1 = gson.toJson(20);

        Person person = new Person("owen", 28);
        String json = gson.toJson(person);
        System.out.println(json);


        //2.fromjson
        Person person1 = gson.fromJson("{\"name\":\"owen\", \"age\":28}", Person.class);
        System.out.println(person1);


        //3.@SerializedName
        Person person2 = gson.fromJson("{\"name\":\"owen\", \"age\":28, \"email_address\":\"xsxxx@163.com\"}", Person.class);
        System.out.println(person2);


        //4.TypeToken (解析Array)
        String jsonArray = "[\"Android\",\"Java\",\"Swift\"]";
        String[] strings = gson.fromJson(jsonArray, String[].class);
        System.out.println(strings);
        ArrayList<String> list = gson.fromJson(jsonArray, new TypeToken<List<String>>() {}.getType());
        for (String s : list) {
            System.out.println(s);
        }


        //5.GsonBuilder
        Gson gson1 = new GsonBuilder()
                             //序列化null
                             .serializeNulls()
                             .create();
        Person person3 = new Person("owen", 28);
        System.out.println(gson1.toJson(person3));
/*        Gson gson = new GsonBuilder()
                            //序列化null
                            .serializeNulls()
                            // 设置日期时间格式，另有2个重载方法
                            // 在序列化和反序化时均生效
                            .setDateFormat("yyyy-MM-dd")
                            // 禁此序列化内部类
                            .disableInnerClassSerialization()
                            //生成不可执行的Json（多了 )]}' 这4个字符）
                            .generateNonExecutableJson()
                            //禁止转义html标签
                            .disableHtmlEscaping()
                            //格式化输出
                            .setPrettyPrinting()
                            .create();*/


        //6.手动序列化和反序列化


        //7.@Expose
        String json1 = "{\n" +
                               "  \"id\": 1,\n" +
                               "  \"name\": \"电脑\",\n" +
                               "  \"children\": [\n" +
                               "    {\n" +
                               "      \"id\": 100,\n" +
                               "      \"name\": \"笔记本\"\n" +
                               "    },\n" +
                               "    {\n" +
                               "      \"id\": 101,\n" +
                               "      \"name\": \"台式机\"\n" +
                               "    }\n" +
                               "  ]\n" +
                               "}";
        Gson gson2 = new GsonBuilder()
                            .excludeFieldsWithoutExposeAnnotation()
                            .create();
        Category category = gson2.fromJson(json1, Category.class);
        System.out.println(category);


        //8.版本管理 @Since和@Until
        SinceUntilModifierModel model = new SinceUntilModifierModel();
        model.setId("owen");  //@Until(1.1)
        model.setNewId("666"); //@Since(1.1)
        Gson gson3 = new GsonBuilder()
                            .setVersion(1.1)
                            .create();
        System.out.println(gson3.toJson(model));


        //9.修饰符
        Gson gson4 = new GsonBuilder()
                             .excludeFieldsWithModifiers(Modifier.STATIC, Modifier.FINAL)
                             .create();
        System.out.println(gson4.toJson(model));


        //10.ExclusionStrategy
        Gson gson5 = new GsonBuilder()
                            .addSerializationExclusionStrategy(new ExclusionStrategy() {
                                @Override
                                public boolean shouldSkipField(FieldAttributes f) {
                                    String name = f.getName();
                                    if ("staticField".equals(name) || "finalField".equals(name)) {
                                        return true;
                                    }

                                    Expose expose = f.getAnnotation(Expose.class);
                                    if (expose != null && !expose.serialize()) {
                                        return true;
                                    }

                                    return false;
                                }

                                @Override
                                public boolean shouldSkipClass(Class<?> clazz) {
                                    return clazz == String.class;
                                }
                            })
                            .create();
        System.out.println(gson5.toJson(model)); //{"publicField":100}


        //11.泛型T
        String jsonString = "{\"code\":\"0\",\"message\":\"success\",\"data\":[{\"name\":\"owen\", \"age\":28, " +
                                    "\"email_address\":\"xsxxx@163.com\"},{\"name\":\"jessica\", \"age\":25, " +
                                    "\"email_address\":\"jessica@gmail.com\"}]}";
        //gson.fromJson(jsonString, ResultT<List<Person>>.class);  //泛型的类型擦除，编译会出错
        Type type = new TypeToken<ResultT<List>>(){}.getType();
        ResultT<List<Person>> resultT = gson.fromJson(jsonString, type);
        System.out.println(resultT.toString());

        String jsonString2 = "{\"code\":\"0\",\"message\":\"success\",\"data\":{\"name\":\"owen\", \"age\":28, " +
                                    "\"email_address\":\"xsxxx@163.com\"}}";
        Type type1 = new TypeToken<ResultT<Person>>(){}.getType();
        System.out.println(gson.fromJson(jsonString2, type1).toString());


        //12.TypeAdapter:用来自定义Serialize和Deserialize规则
        //可以使用@JsonAdapter(PersonTypeAdapter.class)代替
        Person person4 = new Person("xiaolifeidao", 28);
        person4.setEmail("gaga@gamil.com");
        TypeAdapter typeAdapter = new PersonTypeAdapter();
        Gson gson6 = new GsonBuilder()
                             .registerTypeAdapter(Person.class, typeAdapter)
                             .create();
        System.out.println(gson6.toJson(person4));

        //可以使用@JsonAdapter(PersonTypeAdapter.class)
        Gson gson7 = new Gson();
        System.out.println(gson7.toJson(person4));





    }
}
