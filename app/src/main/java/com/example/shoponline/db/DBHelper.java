package com.example.shoponline.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.shoponline.R;
import com.example.shoponline.activity.MainActivity;
import com.example.shoponline.constant.Constant;

import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constant.CREATE_TABLE_TYPE_PRODUCT);
        db.execSQL(Constant.CREATE_TABLE_PRODUCT);
        db.execSQL(Constant.CREATE_TABLE_USER);
        db.execSQL(Constant.CREATE_TABLE_ORDER);
        db.execSQL(Constant.CREATE_TABLE_ORDER_LINE);
      //insert type Product
        db.execSQL(Constant.INSERT_TYPE_PRODUCT, new Object[]{"LapTop", R.drawable.laptop});
        db.execSQL(Constant.INSERT_TYPE_PRODUCT, new Object[]{"SmartPhone", R.drawable.smartphone});
        db.execSQL(Constant.INSERT_TYPE_PRODUCT, new Object[]{"Watch", R.drawable.watch});
        //insert product- Laptop
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"Acer Aspire A514 52 33AB",11990000,R.drawable.acer_aspire_a514,"Laptop Acer Aspire A514 i3 (NX.HMHSV.001) được thiết kế mỏng nhẹ phù hợp với giới trẻ, đặc biệt là các bạn học sinh sinh viên cần di chuyển nhiều. Máy sử dụng con chip Intel thế hệ thứ 10 hiện đại, ổ cứng SSD khởi động cực nhanh, màn hình Full HD góc nhìn siêu rộng đem đến những trải nghiệm tuyệt vời.",1,100});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"Acer Aspire A315",11290000,R.drawable.acer_aspire_a315,"Acer Aspire A315 54 36QY (NX.HM2SV.001) là chiếc laptop có thiết kế nhỏ gọn, hợp thời trang, màn hình chân thực, sắc nét cùng cấu hình ổn định đáp ứng nhu cầu học tập, làm việc văn phòng.",1,35});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"Acer Aspire A515",14230000,R.drawable.acer_aspire_a515,"Acer Aspire A515 54 54EU vượt trội trong dòng laptop văn phòng với Chip Core i5 thế hệ 10 cùng thời lượng pin lên đến 10 tiếng sẵn sàng đồng hành cùng bạn suốt cả ngày dài.",1,25});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"Asus VivoBook A512F",17990000,R.drawable.asus_vivobook_a512f,"Laptop Asus Vivobook A512F là máy tính xách tay nhỏ gọn hướng tới nhân viên văn phòng và giới trẻ, sinh viên. Máy chỉ nặng 1.6 kg dễ dàng mang theo hàng ngày, cấu hình cao cùng với SSD siêu nhanh giúp bạn sẵn sàng xử lý công việc trong nháy mắt.",1,50});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"Asus VivoBook A512FA",15290000,R.drawable.asus_vivobook_a512fa,"Được mệnh danh là chiếc UltraBook 15 inch nhỏ nhất thế giới, laptop ASUS Vivobook A512FA i5 (EJ552T) không chỉ sở hữu cấu hình mạnh cùng thiết kế sang trọng, mỏng nhẹ mà còn được trang bị màn hình NanoEdge viền mỏng 4 cạnh, tối ưu không gian hiển thị.",1,30});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"Asus VivoBook X509F",19890000,R.drawable.asus_vivobook_x509f,"Laptop Asus Vivobook X509FJ (EJ133T) là chiếc máy tính xách tay mang đến hiệu năng mạnh mẽ cùng hình ảnh chân thực đáp ứng mọi nhu cầu cho dù là học tập văn phòng hay giải trí.",1,50});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"Dell Inspiron 3480",12590000,R.drawable.dell_inspiron_3480,"Laptop Dell Inspiron 3480 thuộc dòng laptop tầm trung của hãng Dell với cấu hình khá mạnh, thiết kế đẹp và sang trọng. Máy hướng tới đối tượng nhân viên văn phòng và các bạn học sinh, sinh viên.",1,70});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"HP 15s du0056TU",11390000,R.drawable.hp_15s,"Laptop HP 15s du0056tu là laptop giá phổ thông cấu hình đủ dùng cho dân văn phòng, bộ nhớ khủng 1 TB lưu trữ thả ga. Hơn nữa, mọi hình ảnh hiển thị sống động hơn với độ phân giải màn hình Full HD.",1,20});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"Lenovo Ideapad S145",11490000,R.drawable.lenovo_ideapad_s145,"Lenovo Ideapad S145 15IWL i3 (81MV00SXVN) là mẫu laptop văn phòng nhỏ gọn, thiết kế đẹp với viền màn hình siêu mỏng. Máy đảm nhận tốt hầu hết các tác vụ văn phòng, thiết kế đồ họa nhờ cấu hình khá và card đồ họa rời MX110.",1,20});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"Apple MacBook Air 2017",21490000,R.drawable.macbook_air_2017,"MacBook Air 2017 i5 128GB là mẫu laptop văn phòng, có thiết kế siêu mỏng và nhẹ, vỏ nhôm nguyên khối sang trọng. Máy có hiệu năng ổn định, thời lượng pin cực lâu 12 giờ, phù hợp cho hầu hết các nhu cầu làm việc và giải trí.",1,10});
        //insert product- SmartPhone
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"iPhone 11 64GB",21990000,R.drawable.iphone_11_64g,"Sau bao nhiêu chờ đợi cũng như đồn đoán thì cuối cùng Apple đã chính thức giới thiệu bộ 3 siêu phẩm iPhone 11 mạnh mẽ nhất của mình vào tháng 9/2019. Có mức giá rẻ nhất nhưng vẫn được nâng cấp mạnh mẽ như chiếc iPhone Xr năm ngoái, đó chính là phiên bản iPhone 11 64GB",2,70});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"iPhone 11 Pro Max 512GB",43990000,R.drawable.iphone_pro_max,"Để tìm kiếm một chiếc smartphone có hiệu năng mạnh mẽ và có thể sử dụng mượt mà trong 2-3 năm tới thì không có chiếc máy nào xứng đang hơn chiếc iPhone 11 Pro Max 512GB mới ra mắt trong năm 2019 của Apple.",2,40});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"iPhone Xs Max 256GB",30490000,R.drawable.iphone_xs_max,"Sau 1 năm mong chờ, chiếc smartphone cao cấp nhất của Apple đã chính thức ra mắt mang tên iPhone Xs Max 256 GB. Máy các trang bị các tính năng cao cấp nhất từ chip A12 Bionic, dàn loa đa chiều cho tới camera kép tích hợp trí tuệ nhân tạo.",2,50});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"OPPO A5s",3690000,R.drawable.oppo_a5s,"OPPO A5s là một chiếc máy giá rẻ và vẫn giữ được cho mình những ưu điểm mà người dùng yêu thích của một chiếc smartphone tới từ OPPO.",2,10});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"Samsung Galaxy A30s",5490000,R.drawable.samsung_galaxy_a30s,"Samsung Galaxy A30s, chiếc smartphone mới ra mắt sở hữu nhiều ưu điểm nổi bật trong phân khúc, nổi bật nhất phải kể đến là dung lượng pin lên tới 4000 mAh,bộ 3 camera cùng vi xử lý đủ mạnh, ổn định.",2,25});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"Samsung Galaxy Note 9",22990000,R.drawable.samsung_galaxy_note9,"Mang lại sự cải tiến đặc biệt trong cây bút S Pen, siêu phẩm Samsung Galaxy Note 9 còn sở hữu dung lượng pin khủng lên tới 4.000 mAh cùng hiệu năng mạnh mẽ vượt bậc, xứng đáng là một trong những chiếc điện thoại cao cấp nhất của Samsung.",2,15});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"Samsung Galaxy S10+",22990000,R.drawable.samsung_galaxy_note9,"Samsung Galaxy S10+ 128GB là một trong những chiếc smartphone được trông chờ nhiều nhất trong năm 2019 và không phụ sự kỳ vọng của mọi người thì chiếc Galaxy S thứ 10 của Samsung thực sự gây ấn tượng mạnh cho người dùng.",2,55});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"Xiaomi Redmi 8 (4GB/64GB)",3590000,R.drawable.xiaomi_redmi8,"Với nhiều ưu điểm vượt trội so với các đối thủ, Xiaomi Redmi 8 4GB/64GB hứa hẹn là một con bài chiến lược của Xiaomi trong phân khúc smartphone giá rẻ, hiện đang rất sôi động hiện nay.",2,60});
        //insert product- Watch
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"Apple Watch S3 GPS 38mm",5490000,R.drawable.apple_watch_3,"Đặc điểm nổi bật của Apple Watch S3 GPS, 38mm viền nhôm, dây cao su",3,10});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"Apple Watch S3 GPS 42mm",6490000,R.drawable.apple_watch_s3_gps,"Đặc điểm nổi bật của Apple Watch S3 GPS 42mm viền nhôm xám dây cao su",3,20});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"Apple Watch S4 GPS 44mm",11990000,R.drawable.apple_watch_s4_gps,"Apple Watch S4 GPS 44mm viền nhôm dây cao su. Màn hình 44m giúp hiển thị thông tin dễ nhìn, rõ ràng hơn cho người dùng.",3,40});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"Apple Watch S4 GPS 44mm viền nhôm",11990000,R.drawable.apple_watch_s4_gps_44mm,"Apple Watch S4 GPS 44mm viền nhôm dây cao su. Màn hình 44m giúp hiển thị thông tin dễ nhìn, rõ ràng hơn cho người dùng.",3,15});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"ICE 015773",2400000,R.drawable.ice_015773,"CE chứa đựng tất cả những yếu tố tốt nhất về kiểu dáng thiết kế, chất lượng sản phẩm. Khi nhìn vào mặt đồng hồ ICE, bạn sẽ cảm nhận ngay một sự độc đáo trong từng sản phẩm như thủy tinh Swarovski và đặc biệt là thiết kế của dây đeo.",3,35});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"Kenneth Cole KC50780005",3072000,R.drawable.kenneth_cole_kc50780005,"Đồng hồ Kenneth Cole đã trở thành thương hiệu hấp dẫn đối với các tín đồ thời trang trên toàn thế giới. Danh tiếng của thương hiệu được tạo dựng bởi các thiết kế độc đáo, mang nét đặc trưng riêng của Kenneth Cole.",3,40});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"Kenneth Cole KC50785002",2112000,R.drawable.kenneth_cole_kc50785002,"Đồng hồ Kenneth Cole đã trở thành thương hiệu hấp dẫn đối với các tín đồ thời trang trên toàn thế giới. Danh tiếng của thương hiệu được tạo dựng bởi các thiết kế độc đáo, mang nét đặc trưng riêng của Kenneth Cole.",3,70});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"MVMT D-MC02-GUBL",3040000,R.drawable.mvmt_d_mc02_gubl,"MVMT là dòng đồng hồ nguyên bản tạo nên sự khác biệt bằng cách mang đến cho bạn những thiết kế tối giản, chất lượng với giá cả hợp lý. Trên hết, MVMT được tạo ra với ước mơ làm sống lại sứ mệnh cuối cùng của chúng tôi: truyền cảm hứng cho bạn để sống cuộc sống theo cách riêng của bạn.",3,50});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"MVMT D-MC02-GUBL",3040000,R.drawable.mvmt_d_mc02_gubl,"MVMT là dòng đồng hồ nguyên bản tạo nên sự khác biệt bằng cách mang đến cho bạn những thiết kế tối giản, chất lượng với giá cả hợp lý. Trên hết, MVMT được tạo ra với ước mơ làm sống lại sứ mệnh cuối cùng của chúng tôi: truyền cảm hứng cho bạn để sống cuộc sống theo cách riêng của bạn.",3,55});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"Orient FEM7J007D9",2616000,R.drawable.orient_fem7f007d9,"Đồng hồ Orient đem đến những sản phẩm ấn tượng chinh phục người nhìn một cách nhanh chóng. Đồng hồ Orient với những chất liệu cao cấp bóng bẩy nâng tầm đẳng cấp cho người sở hữu, phù hợp với doanh nhân thành đạt, dân văn phòng hay các giám đốc công ty",3,70});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"BlackBerry KEY2",15990000,R.drawable.blackberry_key2,"BlackBerry Key2 là một bản nâng cấp toàn diện cho chiếc KeyOne với rất nhiều thay đổi và điều đáng mừng là yếu tố đặc trưng nhất của dòng điện thoại BlackBerry là bàn phím vật lý cổ điển vẫn được giữ lại",2,30});
        db.execSQL(Constant.INSERT_PRODUCT,new Object[]{"Huawei P30 Pro",20690000,R.drawable.huawei_p30_pro,"Huawei P30 Pro là một bước đột phá của Huawei cũng như camera trên smartphone khi đem lại khả năng zoom như một \"kính viễn vọng\"",2,20});
        //insert use
        db.execSQL(Constant.INSERT_USER,new Object[]{"nhandayht","kynhan123","nhandayht@gmail.com","0824917021","Nguyen Thanh Ky Nhan"});
        db.execSQL(Constant.INSERT_USER,new Object[]{"thuanhht","thuanh123","thuanhht@gmail.com","0826617021","Nguyen Dang Thu Anh"});
        db.execSQL(Constant.INSERT_USER,new Object[]{"ducnv","duc123","ducnv@gmail.com","0352617021","Nguyen Van Duc"});



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion){
            db.execSQL(Constant.DROP_TABLE);
            onCreate(db);
        }
    }
}
