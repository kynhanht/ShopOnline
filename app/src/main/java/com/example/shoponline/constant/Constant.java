package com.example.shoponline.constant;

public class Constant {
    //Create table
    public static final String CREATE_TABLE_TYPE_PRODUCT="" +
            "CREATE TABLE TypeProduct( " +
            "typeProductId INTEGER primary key AUTOINCREMENT not null," +
            "typeProductName nvarchar(100) not null," +
            "typeProductImage int not null )";
    public static final String CREATE_TABLE_PRODUCT="" +
            "CREATE TABLE Product( " +
            "productId INTEGER primary key AUTOINCREMENT not null," +
            "productName nvarchar(100) not null," +
            "productPrice bigint not null," +
            "productImage int not null," +
            "productDescription text not null," +
            "typeProductId INTEGER not null," +
            "quantity int not null," +
            "foreign key(typeProductId) REFERENCES TypeProduct(typeProductId) )";

    public static final String CREATE_TABLE_USER="" +
            "CREATE TABLE User( " +
            "username varchar(100) primary  key not null," +
            "password varchar(100) not null," +
            "email varchar(100) not null," +
            "phone varchar(100) not null," +
            "name nvarchar(100) not null )";

    public static final String CREATE_TABLE_ORDER="" +
            "CREATE TABLE Orders( " +
            "orderId INTEGER primary key AUTOINCREMENT not null," +
            "orderName varchar(100) not null," +
            "username varchar(100) not null," +
            "payment bigint not null," +
            "orderDate date not null," +
            "foreign key(username) REFERENCES User(username) )";
    public static final String CREATE_TABLE_ORDER_LINE="" +
            "CREATE TABLE OrderLine( " +
            "orderId INTEGER not null," +
            "productId INTEGER not null," +
            "quantity int not null," +
            "unitPrice bigint not null," +
            "primary key(orderId,productId)," +
            "foreign key(orderId) REFERENCES Orders(orderId)," +
            "foreign key(productId) REFERENCES Product(productId) )";

    //drop table
    public static final String DROP_TABLE="" +
            "DROP TABLE OrderLine;\n" +
            "DROP TABLE Order;\n" +
            "DROP TABLE User;\n" +
            "DROP TABLE Product;\n" +
            "DROP TABLE TypeProduct;\n";
    //Select

    public static final String SELECT_PRODUCT_BY_TYPE_PRODUCT="" +
            "SELECT* FROM PRODUCT " +
            "WHERE typeProductId = ? and quantity>0 ";
    public static  final String SELECT_NEW_PRODUCT="" +
            "SELECT* FROM PRODUCT " +
            "WHERE quantity>0 " +
            "ORDER BY productId DESC " +
            "LIMIT 6";
    public static final String SELECT_ALL_USER="" +
            "SELECT* FROM User";

    public static final String SELECT_CART_ITEM="" +
            "SELECT t2.productId,t2.productName,t1.unitPrice,t2.productImage,t1.quantity " +
            "FROM OrderLine AS t1 " +
            "INNER JOIN Product AS t2 ON t1.productId = t2.productId " +
            "WHERE t1.orderId=?";

    public static final String SELECT_ORDER_BY_USER_NAME="" +
            "SELECT* FROM Orders " +
            "WHERE username=?";
    // Insert
    public static  final String INSERT_TYPE_PRODUCT="" +
            "INSERT INTO TypeProduct(typeProductName,typeProductImage) VALUES(?,?)";

    public static final String INSERT_PRODUCT="" +
            "INSERT INTO Product(productName,productPrice,productImage,productDescription,typeProductId,quantity) VALUES(?,?,?,?,?,?)";

    public static final String INSERT_USER="" +
            "INSERT INTO User(username,password,email,phone,name) VALUES(?,?,?,?,?)";

    public static final String INSERT_ORDER="" +
            "INSERT INTO Orders(orderName,username,payment,orderDate) VALUES(?,?,?,?)";
    public static final String SELECT_COUNT_ORDER="SELECT COUNT(*) AS count FROM Orders";

    public static final String INSERT_ORDER_LINE="" +
            "INSERT INTO OrderLine(orderId,productId,quantity,unitPrice) VALUES(?,?,?,?)";
    //update
    public static final  String UPDATE_QUANTITY_PRODUCT="" +
            "UPDATE PRODUCT SET quantity=? " +
            "WHERE productId=?";

}
